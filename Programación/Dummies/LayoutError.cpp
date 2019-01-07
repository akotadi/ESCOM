#include <stdio.h>
#include <iostream>
#include <string.h>

using namespace std;

int main(int argc, char const *argv[])
{
	int upper = 0, n = 0, lower = 0;

	cout << "upper = " << upper << "\n";
	cout << "n = " << n << "\n";
	cout << "lower = " << lower << "\n";

	cout << "\nPerforming assignment of double\n";
	double* pD = (double*)&n;
	*pD = 13.0;

	cout << "upper = " << upper << "\n";
	cout << "n = " << n << "\n";
	cout << "lower = " << lower << "\n";
	
	return 0;
}