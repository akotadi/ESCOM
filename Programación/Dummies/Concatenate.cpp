#include <stdio.h>
#include <iostream>
#include <string.h>

using namespace std;

void concatString(char szTarget[], char szSource[]){
	int targetIndex = 0;
	while(szTarget[targetIndex]){
		targetIndex++;
	}

	int sourceIndex = 0;
	while(szSource[sourceIndex]){
		szTarget[targetIndex] = szSource[sourceIndex];
		targetIndex++;
		sourceIndex++;
	}

	szTarget[targetIndex] = '\0';
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