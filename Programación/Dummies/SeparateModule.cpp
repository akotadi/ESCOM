/*SeparateModule - demonstrate how programs can be broken into multiple modules to make them easier to
				   write and test; this module contains the function thhat main() calls*/

#include <stdio.h>
#include <iostream>

using namespace std;

/*sumSequence - add a sequence of numbers entered from the keyboard until the
	user enters a negative number.
	return - the summation of nmbers entered.*/
int sumSequence(ostream& out, istream& in){
	// loop forever
	int nAccumulator = 0;
	for (;;)
	{
		// fetch another number
		int nValue = 0;
		cout << "Enter next number: ";
		cin >> nValue;

		// if it's negative...
		if (nValue < 0)
		{
			// ...then exit from the loop
			break;
		}

		// ...otherwise add the number to the accumulator 

		nAccumulator = nAccumulator + nValue;
	}

	// return the accumlated value
	return nAccumulator;
}