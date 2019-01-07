#include <bits/stdc++.h>
#include <sys/resource.h>
#include <sys/time.h>
#include "ServerSocket.hpp"

#define MAX_ERRORS 7

using namespace std;

void Juega(ServerSocket &server, string &palabra){
	int n = palabra.length(), errors = MAX_ERRORS;
	string sSend = "";
	for (int i = 0; i < palabra.length(); ++i)
	{
		sSend += "_ ";
	}

	while(n){

		bool bueno = false;
		int aux = 0;

		// cout<<"Leyendo... "<<endl<<endl;

		string sLetter = server.ReceiveString();

		// cout<<"Recibi... "<<sLetter<<endl;

		// string sSend = "";
		for (int i = 0; i < palabra.length(); ++i)
		{
			if (sLetter[0] == palabra[i])
			{
				n--; aux++;
				sSend[i*2] = sLetter[0];
				bueno = true;
			}
		}

		if (n == 0)
		{
			server.Send(to_string(0));
		}else if (bueno)
		{
			server.Send(to_string(1));
			server.Send(to_string(aux));
		}else{
			server.Send(to_string(2));
			errors--;
		}

		// cout << sSend << '\n';

		server.Send(sSend);	

		if (errors == 0)
		{
			break;
		}

	}

}

void uswtime(double &userTime){
	double mega = 1.0e-6;
	struct rusage buffer;
	struct timeval tp;
	struct timezone tzp;
	getrusage(RUSAGE_SELF, &buffer);
	gettimeofday(&tp, &tzp);
	userTime = (double) tp.tv_sec + 1.0e-6 * tp.tv_usec; 
}

int main(){

	int tipo;
	double dTimeStart = 0.0, dTimeEnd = 0.0;

	srand(time(NULL));

	ServerSocket server(8000);

	cout << "Conexion establecida\n";

	server.Complete();

	ifstream fin("palabras");
	int aux = rand() % 100; 
	string random, sWord;
	for (int i = 0; i < aux; ++i)
	{
		fin >> random;
	}
	fin >> sWord;

	cout << sWord << '\n';
	// cout << sWord.length() << '\n';

	server.Send(to_string(sWord.length()));

	string sSend = "";
	for (int i = 0; i < sWord.length(); ++i)
	{
		sSend += "_ ";
	}

	// cout << sSend << '\n';

	server.Send(sSend);

	uswtime(dTimeStart);
	Juega(server, sWord);
	uswtime(dTimeEnd);

	cout<<"Juego finalizado en "<<to_string(dTimeEnd-dTimeStart)<<"s."<<endl;
	server.Send(to_string(dTimeEnd-dTimeStart));

	return 0;
	
}	