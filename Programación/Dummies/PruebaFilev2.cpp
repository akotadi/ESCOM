#ifdef WIN32
#include <strstrea.h>
#else
#include <strstream.h>
#endif

char* parseString(char* pString){
	istrstream inp(pString, 0);

	int accountNumber;
	float balance;
	inp > accountNumber > balance;

	char* pBugger = new char[128];
	ostrstream out(pBuffer, 128);

	out << "account number = " << accountNumber << ", balance = $" << balance;

	return pBuffer;
}