#include <iostream>
#include <fstream>
#include <cstring>
#include <unistd.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <stdio.h>
#include <sys/types.h>
#include <string.h>
#include <stdlib.h>//exit
#include <netdb.h>

using namespace std;

class ServerSocket{
typedef long long int Long;

private:

	string last;
	struct sockaddr_in alow; 
	struct sockaddr_storage their_addr;
	const int MAXT = 1500;
	int fd, port, op_con, client, socklen = sizeof(alow), ctam = sizeof(their_addr);
	int flags = SO_REUSEADDR | SO_REUSEPORT;// | SO_KEEPALIVE;		

	void Connect(){

		fd = socket(AF_INET, SOCK_STREAM, 0);
		if(fd <= 0){
			cout << "Error de socket";
        	exit(EXIT_FAILURE); 
		}

		if(setsockopt(fd, SOL_SOCKET, flags, &op_con , sizeof(int) ) ){
			cout << "Error al configurar socket\n";
			exit(EXIT_FAILURE); 
		}       

		memset((char *)&alow, 0, sizeof(alow));
		alow.sin_family = AF_INET; 
	    alow.sin_addr.s_addr = INADDR_ANY; 
	    alow.sin_port = htons( port ); 

	    if(bind(fd, (struct sockaddr *)&alow, socklen) < 0){
	    	cout << "Error en el bind\n";
	    	exit(EXIT_FAILURE);
	    }

	    if(listen(fd, 5) < 0){
	    	cout << "Error en el listen\n";
	    	exit(EXIT_FAILURE);
	    }	    		

	}		

	int Receive(char * buffer){

		int size;				  
		size = read( client , buffer, sizeof(buffer)); 
		return size;		

	}

	 void Send(char * buffer, int len){	 	
    	write(client,buffer,len);
    }

public:

	ServerSocket(int port, int op_con = 1){

		this -> port = port;		
		this -> op_con = op_con;

		Connect();

		
	}

	void Send(string s){
    	char * buffer = new char[s.size()+sizeof("\n")];
    	bzero(buffer,sizeof(buffer)); 

    	strcpy(buffer, s.c_str());
    	strcat(buffer, "\n");
    	Send(buffer, strlen(buffer));
    }

	string ReceiveString(){

        int size;
        char *buffer = new char[MAXT];
        bzero(buffer,sizeof(buffer)); 

        size = Receive(buffer);

        string s;
        for(int i = 0; i < size; i ++)
            s += buffer[i];
        
        return s;

    }

    void SendFile(string archivo){
    	
        Send(archivo);               

        ifstream fin(archivo, ifstream::binary | ifstream::ate);
        Long size = fin.tellg();
        
        Send(to_string(size));
    
        Long cur = 0;
        char * buffer = new char[MAXT];

        fin.seekg(0, ios :: beg);
        while(cur < size){
            Long sig = min(size - cur , (Long) MAXT );
            fin.read(buffer, sig);            
            Send(buffer, sig);
            cur += sig;
        }

        fin.close();

    }

    void ReceiveFile(){

        string name = ReceiveString();
        Long size = stoll(ReceiveString());      

        ofstream fout(name, ofstream::binary);

        Long cur = 0;
        char * buffer = new char[MAXT];

        while(cur < size){
            Long sig = Receive(buffer);            
            fout.write(buffer, sig);
            cur += sig;
        }

        fout.close();

    }

	string getLast(){
		return last;
	}

	void Close(){
		close(fd);
	}	

	void Complete(){
		client = accept(fd, (struct sockaddr *)&alow, (socklen_t*)&socklen);
		// client = accept(fd, (struct sockaddr *)&their_addr, (socklen_t*)&their_addr);
	    if(client < 0){
	    	cout << "Error al aceptar\n";
	    	exit(EXIT_FAILURE);
	    }	 	    
	}

	void CloseCur(){
		close(client);
	}
	
};

