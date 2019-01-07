#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <string.h>
#include <windows.h>

using namespace std;

//#include "gotoxy.h"

/*char *Function1()
{ return “Some text”;}*/

void gotoxy(int x, int y){
	HANDLE hcon = GetStdHandle(STD_OUTPUT_HANDLE);
	COORD dwPos;
	dwPos.X = x;
	dwPos.Y = y;
	SetConsoleCursorPosition(hcon,dwPos);
}

const char *Function2()
{ return "Some text";}



int main(int argc, char const *argv[])
{
	
	char szNombre[25], szEdad[5];
	char * lol;
	const char * lel;
	cout << "Hola mundo\n" << "Ingresa tu nombre: " ;
	cin.getline(szNombre,20);
	//int& a = szEdad;
	cout << "Ingresa tu edad: ";
	cin >> szEdad;
	//cout << "Tu edad es: " << szEdad;
	strcat(szNombre," - ");
	strcat(szNombre,szEdad);
	cout << "Tus datos son: " << szNombre << "\n";

	/*for (int i = 0; i < strlen(szNombre); ++i)
		printf("%c",szNombre[i]);
	printf("\n");
	char* pszNombre = szNombre;
	for (int i = 0; i < strlen(szNombre); ++i, pszNombre++)
		printf("%c",*pszNombre);
	printf("\n");
	for (int i = 0; i < strlen(szNombre); ++i)
		printf("%c",*(szNombre+i));
	printf("\n");*/
	
	lel=Function2();
	for (int i = 0; i < strlen(lel); ++i)
	{
		cout << *(lel+i);
	}

	gotoxy(40,20);
	cout << "Lala";

	return 0;
}