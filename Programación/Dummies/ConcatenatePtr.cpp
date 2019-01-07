#include <stdio.h>
#include <iostream>
#include <string.h>

using namespace std;

void concatString(char* pszTarget, char* pszSource){
	while(*pszTarget){
		pszTarget++;
	}

	while(*pszTarget++ = *pszSource++){
	}
	return;
}

int main(int argc, char const *argv[])
{
	char szString1[256];
	cout << "Enter string #1:";
	cin.getline(szString1,128);

	char szString2[128];
	cout << "Enter string #2:";
	cin.getline(szString2,128);

	concatString(szString1," - ");

	concatString(szString1,szString2);

	cout << "\n" << szString1 << "\n";
	
	return 0;
}