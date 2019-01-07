#include <fstream>
#include <iostream>

using namespace std;

int main(int argc, char const *argv[])
{
	ofstream mvn("MYNAME");

	if (mvn.bad())
	{
		cout << "Error opening file MYNAME\n";
		return 1;
	}
	else{
		mvn << "Randy Davis is suave and handsome\n" << "and definitely not balding prematurel\n";
	}

	ofstream bfile("BINFILE", ios::binary | ios::ate);

	bfile << "Randy Davis is suave and handsome\n" << "and definitely not balding prematurel\n";

	ifstream bankStatement("STATEMENT", ios::nocreate);
	if (bankStatement.bad())
	{
		cout << "Couldn't find bank statement.\n"
		return 1;
	}

	while(!bankStatement.eof()){
		bankStatement > accountNumber > amount;
	}

	return 0;
}