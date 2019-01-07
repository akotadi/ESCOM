//#include <arpa/inet.h>
//#include <netinet/in.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <unistd.h> //close()
#include <stdlib.h> //malloc() free()
#include <string.h>  //memset()
#include <netdb.h>  //getaddrinfo()
#include <stdio.h> //gets()
#include <netinet/in.h>
#include <arpa/inet.h>

#define BUFLEN 512
#define PORT "9931"
#define SRV_IP "127.0.0.1"
#define SRV_IP6 "2001::1234:1"
//#define GPO6 "ff3e:40:2001:db8:cafe:1:11ff:11ee"
#define GPO6 "ff3e:2001::1234:1"
//#define GPO6 "ff3e::11ff:11ee"
#define GPO4 "230.1.1.1"
#define GPO6_1 "FF02:0:0:0:0:0:0:1"
#define GPO4_1 "224.0.0.1"


 void err(char *s)
  {
   perror(s);
   exit(1);
  }

  int main(int argc, char* argv[])
  {
    struct addrinfo hints;
    struct addrinfo *result, *rp, *maddr;
    char host[NI_MAXHOST], service[NI_MAXSERV],buf[BUFLEN];
    struct sockaddr_storage emisor;
    struct dato *o2;
    socklen_t ctam;
    int s,cd, i,v=1, op=0,n,ttl6=10;
    unsigned char ttl = 10; 

   /* Resolve the multicast group address */
    memset(&hints, 0, sizeof(struct addrinfo));
    hints.ai_family = PF_UNSPEC;
    hints.ai_flags  = AI_NUMERICHOST;
    if ( getaddrinfo(GPO6, NULL, &hints, &maddr) != 0 )
    {
        perror("getaddrinfo() multicast failed");
    }

    printf("Usando %s\n", maddr->ai_family == PF_INET6 ? "IPv6" : "IPv4");

    /* Get a local address with the same family (IPv4 or IPv6) as our multicast group */
    hints.ai_family   = maddr->ai_family;
    hints.ai_socktype = SOCK_DGRAM; 
    hints.ai_flags    = AI_PASSIVE; /* Return an address we can bind to */
    hints.ai_protocol = 0;
    if ( getaddrinfo(GPO6, PORT, &hints, &result) != 0 )
    {
        perror("getaddrinfo() failed");
    }

   /* getaddrinfo() returns a list of address structures.
       Try each address until we successfully bind(2).
       If socket(2) (or bind(2)) fails, we (close the socket
       and) try the next address. */

   for (rp = result; rp != NULL; rp = rp->ai_next) {
        cd = socket(rp->ai_family, rp->ai_socktype,rp->ai_protocol);
        if (cd == -1){
            continue;
	} else{
	int op = 0;
        int r = setsockopt(cd, IPPROTO_IPV6, IPV6_V6ONLY, &op, sizeof(op));
	if (setsockopt(cd, SOL_SOCKET, SO_REUSEADDR, &v,sizeof(int)) == -1) {
            perror("setsockopt");
            exit(1);
        }//if
	if (bind(cd, rp->ai_addr, rp->ai_addrlen) == 0){
	   //printf("hizo el bind\n");
	  break;
	}else{
	perror("error en funcion bind()\n");
	exit(1);
	}//else
          
        }//else
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
        multicastRequest.imr_interface.s_addr = htonl(INADDR_ANY);//inet_addr("148.204.57.24");

	if (setsockopt(cd, IPPROTO_IP, IP_MULTICAST_TTL, &ttl,sizeof(ttl)) == -1) {
            perror("setsockopt TTL");
            exit(1);
        }//if
		//printf("antes de unirse al grupo ipv4\n");
        /* Join the multicast address */
        if ( setsockopt(cd, IPPROTO_IP, IP_ADD_MEMBERSHIP, (char*) &multicastRequest, sizeof(multicastRequest)) != 0 )
        {
            perror("setsockopt() IP_ADD_MEMBERSHIP  failed");
        } 	//printf("Despues de unirse al grupo ipv4\n");
    }else if ( maddr->ai_family  == PF_INET6 && maddr->ai_addrlen == sizeof(struct sockaddr_in6) ) // IPv6 
    {
        struct ipv6_mreq multicastRequest;  // Multicast address join structure

        // Specify the multicast group
        memcpy(&multicastRequest.ipv6mr_multiaddr,&((struct sockaddr_in6*)(maddr->ai_addr))->sin6_addr,sizeof(multicastRequest.ipv6mr_multiaddr));

        // Accept multicast from any interface
        multicastRequest.ipv6mr_interface = 0;

	if (setsockopt(cd, IPPROTO_IPV6, IPV6_MULTICAST_HOPS, &ttl6,sizeof(ttl6)) == -1) {
            perror("setsockopt TTL");
            exit(1);
        }//if
	printf("antes de unirse al grupo ipv6\n");
        // Join the multicast address
        if ( setsockopt(cd, IPPROTO_IPV6, IPV6_ADD_MEMBERSHIP, (char*) &multicastRequest, sizeof(multicastRequest)) != 0 )
        {
            perror("setsockopt()  IPV6_ADD_MEMBERSHIP failed");
        }//is setsockopt
    }else{
        perror("Neither IPv4 or IPv6");
    }//else (if ipv6)
   

    printf("Cliente unido al grupo\n");
    ctam = sizeof(struct sockaddr_storage);
    for(;;){
	  memset(buf,0,sizeof(buf)); 
	  //printf("antes de recvfrom\n");       
          if ((n=recvfrom(cd, buf, sizeof(buf), 0,(struct sockaddr *)&emisor, &ctam))==-1)
               err("recvfrom()");
          i = getnameinfo((struct sockaddr *) &emisor,ctam, host, NI_MAXHOST,service, NI_MAXSERV, NI_NUMERICHOST|NI_NUMERICSERV);
       if (i == 0){
          printf(" %d bytes recibidos desde %s puerto%s con el mensaje: %s\n",n, host, service,buf);
	}//if
    }//for(;;)
     //free(o1);
    freeaddrinfo(result);
    freeaddrinfo(maddr);
    close(s);
    return 0;
  }//main