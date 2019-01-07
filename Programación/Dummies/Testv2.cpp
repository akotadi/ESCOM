#include <iostream>
#include <string.h>

using namespace std;

int factorial(int n){
	if(n < 0){
		char* pType = new char[strlen("Argument for factorial negative"+1)];
		strcpy(pType,"Argument for factorial negative");
		throw pType;
	}

	int accum = 1;
	while(n > 0){
		accum *= n;
		n--;
	}
	return accum;
}

int main(int argc, char const *argv[])
{
	try{
		cout << "Factorial of -1 is " << factorial(-1) << endl;

		cout << "Factorial of 10 is " << factorial(10) << endl;
	}

	catch(char* pError){
		cout << "Error ocurred: " << pError << endl;
	}

	return 0;
}