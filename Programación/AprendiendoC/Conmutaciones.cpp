#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <string.h>
#include <windows.h>

using namespace std;

int factorial(int n){
	if (n == 1)
	{
		return 1;
	}
	else{
		return n * factorial(n-1);
	}
}

void conmutar(char* pWord, int n){
	char* pCopy = new char[strlen(pWord)];
	for (int j = 0; j < strlen(pWord)-n-1; ++j)
  	{
		if (j<i)
		{
			pCopy[j]=pWord[i];
		}
		else{
			pCopy[j]=pWord[i+1];
		}
	}
	pCopy[j]='\0';
	conmutar(pCopy,1)
	
	return;
}

int main(int argc, char const *argv[])
{
	char cWord[30];
	cout << "Introduce la palabra a conmutar: ";
	cin >> cWord;
	char* pWord = new char[strlen(cWord)+1];
	strcpy(pWord,cWord);
	int nConmutaciones;
	nConmutaciones = factorial(strlen(pWord));
	cout << "El numero total de conmutaciones posibles es: " << nConmutaciones << "\n";
	conmutar(pWord,strlen(pWord));
	return 0;
}