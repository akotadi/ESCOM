//BUDGET1.CPP - A "functional" Budget program

#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <string.h>
#include <windows.h>

using namespace std;

// the maximum number of accounts you can have
const int maxAccounts = 10;

// data describes accounts
unsigned accountNumber[maxAccounts];
double balance[maxAccounts];

/* init - initialize an account by reading in the account number and
		  zeroing out the balance*/
void init(unsigned& accountNumber, double& balance){
//void init(unsigned& accountNumber, double& balance){
	cout << "Enter account number:";
	cin >> accountNumber;
	balance = 0.0;
	return;
}

/* process - update the account balance by entering the transactions
			 from the user*/
void process(unsigned& accountNumber, double& balance){
//void process(unsigned& accountNumber, double& balance){
	cout << "Enter positive number for deposit. Negative for withdrawal.\n";

	double transaction;
	do{
		cout << ":";
		cin >> transaction;

		// is it a deposit?
		if (transaction > 0)
		{
			balance += transaction;
		}

		// how about withdawal?
		if (transaction < 0)
		{
			// withdrawal
			transaction = -transaction;
			if (balance < transaction)
			{
				cout << "Insufficient funds: balance " << balance
					 << ", check " << transaction << "\n";
			}
			else
			{
				balance -= transaction;
			}
		}
	} while (transaction != 0);
	return;
}



// main - accumulate the initial input and output totals
int main(int argc, char const *argv[])
{
	// loop until someone enters
	int noAccounts = 0; // the number of accounts

	// don't create more accounts than we have room for
	while(noAccounts < maxAccounts){
		char transactionType;
		cout << "Enter C to continue or X to terminate:";
		cin >> transactionType;

		// quit if the user enters an X; otherwise...
		if (transactionType == 'X' || transactionType == 'x')		
		{
			break;
		}

		// if the user enters a C...
		if (transactionType == 'C' || transactionType == 'c')
		{
			// ...then initialize a new account...
			init(accountNumber[noAccounts], balance[noAccounts]);

			// ...and input transaction information
			process(accountNumber[noAccounts], balance[noAccounts]);

			// move the next index over to the next account
			noAccounts++;
		}
	}

	// now present totals 

	// first for each account
	double total = 0;
	cout << "Account information:\n";
	for (int i = 0; i < noAccounts; ++i)
	{
		cout << "Balance for account " << accountNumber[i]
			 << " = " << balance[i] << "\n";

		// accumulate the total for all accounts 
		total += balance[i];
	}

	// now display the accumulated value
	cout << "Balance for all accounts = " << total << "\n";

	return 0;
}
