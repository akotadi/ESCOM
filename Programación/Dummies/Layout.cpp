#include <stdio.h>
#include <iostream>
#include <string.h>

using namespace std;

int main(int argc, char const *argv[])
{
	int m1, m2, n;
	long l;
	float f;
	double d;

	cout.setf(ios::hex);

	cout << "-- = 0x" << (long)&m1 << "\n";
	cout << "&n = 0x" << (long)&n << "\n";
	cout << "&l = 0x" << (long)&l << "\n";
	cout << "&f = 0x" << (long)&f << "\n";
	cout << "&d = 0x" << (long)&d << "\n";
	cout << "-- = 0x" << (long)&m2 << "\n";

	return 0;
}