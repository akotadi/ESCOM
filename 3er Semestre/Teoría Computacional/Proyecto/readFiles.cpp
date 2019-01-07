#include <iostream>
#include <fstream>
#include <string.h>
#include <stdio.h>

using namespace std;

int main(int argc, char const *argv[])
{
	string sFile;
	cout<<"Ingresa el nombre del archivo a leer: ";
	cin>>sFile;

	ifstream myFile;
	myFile.open(sFile.c_str());

	string sLine;
	if (myFile.is_open())
	{
		while ( getline (myFile,sLine) ){
			cout << sLine << '\n';
			char * cLine = new char [sLine.length()+1];
			strcpy (cLine, sLine.c_str());

			char * cToken = strtok (cLine," ");
			while (cToken!=0)
			{
				cout << cToken << '\n';
				cToken = strtok(NULL," ");
			}
		}
		myFile.close();
	}
	else
		cout<<"Unable to open file"<<endl;
	
	
	
	return 0;
}
