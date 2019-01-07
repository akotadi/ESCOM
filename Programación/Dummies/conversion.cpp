/*Program to convert temperature from Celsius degree
units into Fahrenheit degree units:
Fahrenheit = Celsius * (212-32)/100 + 32*/

//using namespace std;

#include <stdio.h>
#include <iostream>

using namespace std;

int main(int argc, char const *argv[])
{
	//enter the temperature in Celsius
	int celsius;
	cout << "Enter the temperature in Celsius:";
	cin >> celsius;

	//calculate conversion factor for Celsius to Fahrenheit
	int factor;
	factor = 212-32;

	//use conversion factor to convert Celsius into Fahrenheit values
	int fahrenheit;
	fahrenheit = factor * celsius/100 + 32;

	//output the results
	cout << "Fahrenheit value is:" << fahrenheit;

	return 0;
}