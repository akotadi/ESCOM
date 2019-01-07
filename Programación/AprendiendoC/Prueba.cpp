#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <string.h>
#include <windows.h>

using namespace std;

int main(int argc, char const *argv[])
{
	printf("%d, %d, %d, %d, %d, %d \n",sizeof(int), sizeof(short int), sizeof(long int), sizeof(char), sizeof(float), sizeof(double) );
	return 0;
}