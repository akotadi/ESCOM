#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <string.h>

using namespace std;

int main(int argc, char const *argv[])
{
	cout << "The arguments to " << argv[0] << "\n";

	for (int i = 0; i < argc; ++i)
	{
		cout << i << ":" << argv[i] << "\n";
	}

	cout << "That's it\n";

	return 0;
}