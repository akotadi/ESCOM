#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <string.h>
#include <windows.h>
#include <iomanip>

using namespace std;

int main(int argc, char const *argv[])
{
	cout << setw(8) << 10 << hex << 12 << oct << 12 << setfill('4') << setw(4) << dec << 12 << setw(8) << 13 << 14 << "\n"; // iomanip library
		 //<< setw(8)
	cout.width(8); // iostream library
	cout << 20 ;
	//cout.flags('16');
	cout.setf(ios::hex);
	cout << 20 ;
	//cout.flags('8');
	cout << 20 << "\n";

	return 0;
}