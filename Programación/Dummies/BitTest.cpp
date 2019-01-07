/*BitTest - initialize two variables and output the results
and applying the ~ . & . | and ^ operations */

#include <stdio.h>
#include <iostream>

using namespace std;

int main(int argc, char const *argv[])
{
	//set output format to hexadecimal
	cout.setf(ios::hex, ios::hex);

	//initialize two arguments
	int nArg1;
	nArg1 = 0x1234;

	int nArg2;
	nArg2 = 0x00ff;

	//now perform each operation in turn
	// first the unary NOT operator
	cout << "Arg1 = 0x" << nArg1 << "\n";
	cout << "Arg2 = 0x" << nArg2 << "\n";
	cout << "~nArg1 = 0x" << ~nArg1 << "\n";
	cout << "~nArg2 = 0x" << ~nArg2 << "\n";

	//now the binary operators
	cout << "nArg1 & nArg2 = 0x"
		<< (nArg1 & nArg2)
		<< "\n";
	cout << "nArg1 | nArg2 = 0x"
		<< (nArg1 | nArg2)
		<< "\n";
	cout << "nArg1 ^ nArg2 = 0x"
		<< (nArg1 ^ nArg2)
		<< "\n";
		
	return 0;
}