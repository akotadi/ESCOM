/*MainModule - demonstrate how programs can be broken into multiple modules to make
				them easier to write and test; this module containt the main() function*/

#include <stdio.h>
#include <iostream>

using namespace std;

#include "SeparateModule.h"

//int sumSequence(ostream& out, istream& in);

int main(int argc, char const *argv[])
{
	cout << "This program sums multiple series of the numbers. Terminate each sequence by entering a negative\n"
		<< "number. Terminating the series by entering two negative numbers in a row.\n";

	// accumulate sequences of numbers...
	int nAccumulatedValue;
	do
	{
		// sum a sequence  of numbers entered  from the keyboard
		cout << "\nEnter next sequence\n";
		nAccumulatedValue = sumSequence(cout,cin);

		// now output the accumulated result
		cout << "The total is " << nAccumulatedValue << "\n";

		// ... until the sum returned is 0
	} while (nAccumulatedValue != 0);
	cout << "Program terminating \n";
	return 0;
}