//#include <arpa/inet.h>
//#include <netinet/in.h>
#include <stdio.h> //printf
#include <sys/types.h>
#include <sys/socket.h>
#include <unistd.h> //close()
#include <stdlib.h> //exit()
#include <string.h>
#include <netdb.h>//getaddrinfo()
#define BUFLEN 256
#define PORT "9930"
//#define GPO6 "ff3e:40:2001:db8:cafe:1:11ff:11ee"
#define GPO6 "ff3e:2001::1234:1"
//#define GPO6 "ff3e::11ff:11ee"
#define GPO4 "230.1.1.1"
#define GPO6_1 "FF02:0:0:0:0:0:0:1"
#define GPO4_1 "224.0.0.1"

  void diep(char *s)
  {
   perror(s);
   exit (0);
  }

  int main(int argc, char* argv[])
  {
    struct addrinfo hints,dst;
    struct addrinfo *result, *rp, *maddr;
    char host[NI_MAXHOST], service[NI_MAXSERV],buf[BUFLEN];
    struct sockaddr_storage emisor;
    struct dato *o2;
    int sd, i,v=1,ttl6=10;
    socklen_t ctam;
    unsigned char ttl=10;
    ssize_t n;


/* Resolve the multicast group address */
    memset(&hints, 0, sizeof(struct addrinfo));
    hints.ai_family = PF_UNSPEC;
    hints.ai_flags  = AI_NUMERICHOST;
    if ( getaddrinfo(GPO6, NULL, &hints, &maddr) != 0 )
    {
        perror("getaddrinfo() multicast failed");
    }//if

    printf("Usando %s\n", maddr->ai_family == PF_INET6 ? "IPv6" : "IPv4");

    /* Get a local address with the same family (IPv4 or IPv6) as our multicast group */
    hints.ai_family   = maddr->ai_family;
    hints.ai_socktype = SOCK_DGRAM;
    hints.ai_flags    = AI_PASSIVE; /* Return an address we can bind to */
    if ( getaddrinfo(GPO6, PORT, &hints, &result) != 0 )
    {
        perror("getaddrinfo2() failed");
    }//if

   /* getaddrinfo() returns a list of address structures.
       Try each address until we successfully bind(2).
       If socket(2) (or bind(2)) fails, we (close the socket
       and) try the next address. */

   for (rp = result; rp != NULL; rp = rp->ai_next) {
        sd = socket(rp->ai_family, rp->ai_socktype,rp->ai_protocol);
        if (sd == -1){
            continue;
	}else{
	unsigned char loop = 0;
	int op = 0;
	//setsockopt(sd, IPPROTO_IP,IP_MULTICAST_LOOP, &loop, sizeof(loop)); 
        int r = setsockopt(sd, IPPROTO_IPV6, IPV6_V6ONLY, &op, sizeof(op));
	if (setsockopt(sd, SOL_SOCKET, SO_REUSEADDR, &v,sizeof(int)) == -1) {
            perror("setsockopt");
            exit(1);
        }//if
	
	
       if (bind(sd, rp->ai_addr, rp->ai_addrlen) == 0)
	  break;
                  /* Success */

       close(sd);
       }//if
    }//for

   if (rp == NULL) {               /* No address succeeded */
        fprintf(stderr, "Could not bind\n");
        exit(EXIT_FAILURE);
    }//if

/* Join the multicast group. We do this seperately depending on whether we
     * are using IPv4 or IPv6. WSAJoinLeaf is supposed to be IP version agnostic
     * but it looks more complex than just duplicating the required code. */
    if ( maddr->ai_family  == PF_INET && maddr->ai_addrlen == sizeof(struct sockaddr_in) ) /* IPv4 */
    {
        struct ip_mreq multicastRequest;  /* Multicast address join structure */

        /* Specify the multicast group */
        memcpy(&multicastRequest.imr_multiaddr,&((struct sockaddr_in*)(maddr->ai_addr))->sin_addr,sizeof(multicastRequest.imr_multiaddr));

        /* Accept multicast from any interface */
        multicastRequest.imr_interface.s_addr = htonl(INADDR_ANY);

     if (setsockopt(sd, IPPROTO_IP, IP_MULTICAST_TTL, &ttl,sizeof(ttl)) == -1) {
            perror("setsockopt TTL ip4");
            exit(1);
        }//if

        /* Join the multicast address */
        if ( setsockopt(sd, IPPROTO_IP, IP_ADD_MEMBERSHIP, (char*) &multicastRequest, sizeof(multicastRequest)) != 0 )
        {
            perror("setsockopt() IP_ADD_MEMBERSHIP  failed");
        }
    }else if ( maddr->ai_family  == PF_INET6 && maddr->ai_addrlen == sizeof(struct sockaddr_in6) ) /* IPv6 */
    {
        struct ipv6_mreq multicastRequest;  /* Multicast address join structure */

        /* Specify the multicast group */
        memcpy(&multicastRequest.ipv6mr_multiaddr,&((struct sockaddr_in6*)(maddr->ai_addr))->sin6_addr,sizeof(multicastRequest.ipv6mr_multiaddr));

        /* Accept multicast from any interface */
        multicastRequest.ipv6mr_interface = 0;

	if (setsockopt(sd, IPPROTO_IPV6, IPV6_MULTICAST_HOPS, &ttl6,sizeof(ttl6)) == -1) {
            perror("setsockopt TTL ip6 ");
            exit(1);
        }//if

        /* Join the multicast address */
        if ( setsockopt(sd, IPPROTO_IPV6, IPV6_ADD_MEMBERSHIP, (char*) &multicastRequest, sizeof(multicastRequest)) != 0 )
        {
            perror("setsockopt()  IPV6_ADD_MEMBERSHIP failed");
        }
    }else{
        perror("Neither IPv4 or IPv6");
    }

//    freeaddrinfo(result);
//    freeaddrinfo(maddr);
       printf("Servidor unido al grupo\n\n");
   char *x="mensaje";
   memset(&dst,0,sizeof(dst));
   dst.ai_family   = maddr->ai_family;
   dst.ai_socktype = SOCK_DGRAM;
   if ( getaddrinfo(GPO6, "9931", &dst, &result) != 0 )
    {
        perror("getaddrinfo3() failed");
    }//if
    for(;;){
       printf("Enviando datos..\n");
       if (sendto(sd, x, strlen(x)+1, 0, (struct sockaddr *)result->ai_addr, result->ai_addrlen)==-1)
          diep("sendto()"); 
       sleep(5); 
     }//for(;;)
     freeaddrinfo(result);
     freeaddrinfo(maddr);
     close(sd);
     return 0;
  }