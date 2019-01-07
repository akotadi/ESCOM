#include <iostream>
#include <fstream>
#include <string.h>
#include <stdio.h>
#include <conio.h>
#include <list>

using namespace std;

int main(int argc, char const *argv[])
{
	string sFile;
	cout<<"Ingresa el nombre del archivo a leer: ";
	cin>>sFile;

	char cSymbol;
	cout<<"Ingresa el símbolo para tokenizar: \n";
	cSymbol = getch();
	string sToken;
	sToken[0] = cSymbol;

	ifstream myFile;
	myFile.open(sFile.c_str());

	string sLine;
	list<string> lToken;
	if (myFile.is_open())
	{
		while ( getline (myFile,sLine) ){
			cout << sLine << '\n';
			char * cLine = new char [sLine.length()+1];
			strcpy (cLine, sLine.c_str());

			char * cToken = strtok (cLine,sToken.c_str());
			while (cToken!=0)
			{
				lToken.push_back(cToken);
				cout << cToken << '\n';
				cToken = strtok(NULL,sToken.c_str());
			}

			delete[] cLine;
		}
		myFile.close();
	}
	else
		cout<<"Unable to open file"<<endl;

	cout<<endl;
	while(!lToken.empty()){
		cout << lToken.front() << endl;
		lToken.pop_front();
	}
	
	return 0;
}
