/*FuncionDemo - demonstrate the use of functions by breaking the inner loop
	of the NestedDemo program off into its own fuction*/

#include <stdio.h>
#include <iostream>

using namespace std;

/*sumSequence - add a sequence of numbers entered from the keyboard until the
	user enters a negative number.
	return - the summation of nmbers entered.*/
int sumSequence(void){
	// loop forever
	int accumulator = 0;
	for (;;)
	{
		// fetch another number
		int value = 0;
		cout << "Enter next number: ";
		cin >> value;

		// if it's negative...
		if (value < 0)
		{
			// ...then exit from the loop
			break;
		}

		// ...otherwise add the number to the accumulator 

		accumulator = accumulator + value;
	}

	// return the accumlated value
	return accumulator;
}

int main(int argc, char const *argv[])
{
	cout << "This program sums multiple series of the numbers. Terminate each sequence by entering a negative\n"
		<< "number. Terminating the series by entering two negative numbers in a row.\n";

	// accumulate sequences of numbers...
	int accumulatedValue;
	do
	{
		// sum a sequence  of numbers entered  from the keyboard
		cout << "\nEnter next sequence\n";
		accumulatedValue = sumSequence();

		// now output the accumulated result
		cout << "The total is " << accumulatedValue << "\n";

		// ... until the sum returned is 0
	} while (accumulatedValue != 0);
	cout << "Program terminating \n";
	return 0;
}