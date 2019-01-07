#include <bits/stdc++.h>
#include <sys/resource.h>
#include <sys/time.h>
#include "ServerSocket.hpp"
using namespace std;

struct Respuesta{

	int fi, ci;
	int ff, cf;

	Respuesta(int fi, int ci, int ff, int cf){
		this -> fi = fi;
		this -> ci = ci;
		this -> ff = ff;
		this -> cf = cf;
	}

	Respuesta() : fi(0), ci(0), ff(0), cf(0) {}

	bool igual(const Respuesta &r) const {
		bool flag = false;
		flag |= (r.fi == fi && r.ci == ci && r.ff == ff && r.cf == cf);
		flag |= (r.fi == ff && r.ci == cf && r.ff == fi && r.cf == ci);
		flag |= (r.fi == fi && r.ci == ci && r.ff == ff && r.cf == cf);
		flag |= (r.fi == ff && r.ci == cf && r.ff == fi && r.cf == ci);
		return flag;
	}


};

void Arma(vector<string> &cads, vector<string> &mapa, vector<Respuesta> &inicios){
	typedef pair<int,int> Par;

	int movs[8][2] = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

	int n = cads.size();
	const int B = 15;
	const int M = 8;
	const int L = 26;
	vector<Par> pos;
	mapa = vector<string>(B, string(B, '\0'));

	for(int i = 0; i < B ; i ++)
		for(int j = 0; j < B; j ++)
			pos.push_back(Par(i, j));

	for(int i = 0; i < n; i ++){
		
		while(true){

			bool malo = false;
			Par p = pos[rand()%(B*B)];
			int dir = rand()%M;
			int f = p.first;
			int c = p.second;

			for(int j = 0; j < cads[i].size(); j ++){
				if(f < 0 || f >= B || c < 0 || c >= B || (mapa[f][c] != '\0' && mapa[f][c] != cads[i][j]) ){
					malo = true;
					break;
				}
				f += movs[dir][0], c += movs[dir][1];
			}

			if(!malo){

				f = p.first, c = p.second;
				for(int j = 0; j < cads[i].size(); j ++){
					mapa[f][c] = cads[i][j];
					f += movs[dir][0], c += movs[dir][1];
				}

				inicios.push_back(Respuesta(p.first, p.second, f - movs[dir][0], c - movs[dir][1]));

				break;

			}

		}

	}

	for(int i = 0; i < B; i++){
		for(int j = 0; j < B; j ++){
			if(mapa[i][j] == '\0'){
				mapa[i][j] = rand()%L + 'a';
			}
		}
	}

}

void Anagrama(vector<string> &cadenas, vector<string> &mapa, vector<Respuesta> &inicios){


	const int palabras = 15;
	ifstream fin("anagrama");

	cadenas = vector<string>(palabras);

	int i = 0;
	while(i<palabras){
		int aux = rand() % 7; 
		// cout<<"Saltanda "<<aux<<" palabras"<<endl;
		fin >> cadenas[i++];
		string random;
		for (int i = 0; i < aux; ++i)
		{
			fin >> random;
		}
	}

	// for(int i = 0; i < palabras; i ++)
	// 	fin >> cadenas[i];

	// for (int i = 0; i < cadenas.size(); ++i)
	// {
	// 	cout<<cadenas[i]<<endl;
	// }
	
 	Arma(cadenas, mapa, inicios);

 	for (int i = 0; i < inicios.size(); ++i)
 	{
 		cout<<inicios[i].fi<<":"<<inicios[i].ci<<" - "<<inicios[i].ff<<":"<<inicios[i].cf<<endl;
 	}

 	for(int i = 0; i < cadenas.size(); i ++)
 		random_shuffle(cadenas[i].begin(), cadenas[i].end());


	fin.close();

}

void Concepto(vector<string> &mostrar, vector<string> &mapa, vector<Respuesta> &inicios){
	const int palabras = 15;
	const int conceptos = 3;

	ifstream fin("concepto");

	vector<string> cadenas(palabras);
	mostrar = vector<string>(palabras);
	int i = 0;
	while(i<palabras){
		int aux = rand() % 3; 
		aux++;
		// cout<<"Saltanda "<<aux<<" palabras"<<endl;
		fin >> cadenas[i];
		string random;
		for (int j = 0; j < 3; ++j)
		{
			if (j+1 == aux)
			{
				fin >> mostrar[i++];
			}else{
				fin >> random;
			}
		}
	}
	// for(int i = 0; i < palabras; i ++)
	// 	fin >> cadenas[i];

	for (int i = 0; i < cadenas.size(); ++i)
	{
		cout<<cadenas[i]<<" - "<<mostrar[i]<<endl;
	}

	// mostrar = vector<string>(conceptos);
	// for(int i = 0; i < conceptos; i ++)
	// 	fin >> mostrar[i];

	Arma(cadenas, mapa, inicios);

	fin.close();	

}

void Juega(ServerSocket &server, vector<Respuesta> &inicios){
	int n = inicios.size();

	int fi, ci;
	int ff, cf;
	bool bueno;
	Respuesta r;

	while(n){

		// cin >> fi >> ci >> ff >> cf ;

		// cout<<"Leyendo... "<<endl<<endl;

		string sLine = server.ReceiveString();

		// cout<<"Recibi... "<<sLine<<endl;

		stringstream sToken(sLine);
		string sNumber; 
		getline(sToken, sNumber, ';');
		fi = stoi(sNumber);
		getline(sToken, sNumber, ';');
		ci = stoi(sNumber);

		string sLine2 = server.ReceiveString();

		// cout<<"Recibi... "<<sLine2<<endl;

		stringstream sToken2(sLine2);
		getline(sToken2, sNumber, ';');
		ff = stoi(sNumber);
		getline(sToken2, sNumber, ';');
		cf = stoi(sNumber);

		
		// cout<<"Recibi... "<<fi<<":"<<ci<<" - "<<ff<<":"<<cf<<endl;	

		r = Respuesta(fi, ci, ff, cf);
		bueno = false;
		int aux = 0;

		for(Respuesta &R : inicios){
			if(r.igual(R)){
				bueno = true;
				R = Respuesta(-1, -1, -1, -1);				
				n--;
				break;
			}
			aux++;
		}	
		

		// if(bueno) cout << "1\n";
		// else cout << "0\n";

		if(bueno) server.Send(to_string(aux));
		else server.Send("-1");		

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

	vector<string> mapa, mostrar;
	vector<Respuesta> inicios;

	ServerSocket server(8000);

	cout << "Conexion establecida\n";

	server.Complete();

	// cin >> tipo;

	// cout << "Recibi: " << tipo << endl;

	// tipo = 4;
	tipo = stoi(server.ReceiveString());

	if(tipo == 1 || tipo == 2 || tipo == 3 ) Anagrama(mostrar, mapa, inicios);
	else Concepto(mostrar, mapa, inicios);

	/*

	Mostrar datos de la sopa de letras
	Solo es para debug, no para conexion.

	for(string &s : mostrar)
		cout << s << '\n';

	for(string &s : mapa)
		cout << s << '\n';
	for(Respuesta &r : inicios)
		cout << r.fi << ' ' << r.ci << ' ' << r.ff << ' ' << r.cf << '\n';*/
	
	string compacto;
	for(int i = 0; i < mostrar.size(); i ++){
		if(i) compacto += ";";
		compacto += mostrar[i];
	}

	// cout << compacto << '\n';

	server.Send(compacto);

	compacto = "";
	for(int i = 0; i < mapa.size(); i ++){
		compacto += (mapa[i]+";");
	}
	// cout << compacto << '\n';

	server.Send(compacto);

	uswtime(dTimeStart);
	Juega(server, inicios);
	uswtime(dTimeEnd);

	cout<<"Juego finalizado en "<<to_string(dTimeEnd-dTimeStart)<<"s."<<endl;
	server.Send(to_string(dTimeEnd-dTimeStart));

	return 0;
	
}	