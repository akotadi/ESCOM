#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <string.h>
#include <windows.h>

using namespace std;

double conversion(double dCelsius){
	return ((dCelsius*9)/5)+32;
}



int main(int argc, char const *argv[])
{
	int nLowerLimit=-1;
	while(nLowerLimit < 0){
		cout << "Enter lower limit (>= 0): ";
		cin >> nLowerLimit;
	}

	int nHigherLimit=-1;
	while(nHigherLimit < nLowerLimit || nHigherLimit > 5000){
		cout << "Enter higher limit (lower limit <= higher limit <= 5000): ";
		cin >> nHigherLimit;
	}

	int nStep = -1, nLimit = nHigherLimit - nLowerLimit;
	while(nStep <= 1 || nStep > nLimit){
		cout << "Enter step (1 <= step <= higher limit - lower limit): ";
		cin >> nStep;
	}

	double dCelsius = nLowerLimit;

	cout << "\nCelcius\t\t\tFahrenheit\n" << "-------------------------------------\n\n"; 

	do{
		cout << dCelsius << "\t\t\t" << conversion(dCelsius) << "\n";
		dCelsius += nStep;
	}while(dCelsius < nHigherLimit);

	return 0;
}