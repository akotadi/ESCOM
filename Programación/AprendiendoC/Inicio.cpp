#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <string.h>
#include <windows.h>
#include <time.h>

using namespace std;

int main(int argc, char const *argv[])
{
	time_t ahora;
	cout << ahora << "\n" << asctime(localtime(&ahora));
	time(&ahora);
	cout << ahora << "\n" << asctime(localtime(&ahora));

	return 0;
}