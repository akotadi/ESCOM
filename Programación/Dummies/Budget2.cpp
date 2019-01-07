//BUDGET2.CPP - Budget program with active classes

#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <string.h>
#include <windows.h>

using namespace std;

// the maximum number of accounts one can have
const int maxAccounts = 10;
 
// Checking - this describes checking accounts
class Checking
{
public:
	Checking(int initializeAN = 0){
		accountNumber = initializeAN;
		balance = 0.0;
	}

	// access functions
	int accountNo(){
		return accountNumber;
	}

	double acntBalance(){
		return balance;
	}

	// transaction functions
	void deposit(double amount){
		balance += amount;
	}

	void withdrawal(double amount);

	// display function for displaying self on 'cout'
	void display(){
		cout << "Account " << accountNumber << " - " << balance << "\n";
	}

protected:
	unsigned accountNumber;
	double balance;
	
};

//withdrawal - this member function is too big to be defined inline
void Checking::withdrawal(double amount){
	if (balance < amount)
	{
		cout << "Insufficient funds: balance " << balance << ", check " << amount << "\n";
	}
	else{
		balance -= amount;
		// if balance falls too low...
		if (balance < 500.00)
		{
			// ... charge a service fee
			balance -= 0.20;
		}
	}	
}



// Savings -  you can probably figure this one out
class Savings
{
public:
	Savings(int initialAN = 0){
		accountNumber = initialAN;
		balance = 0.0;
		noWithdrawals = 0;
	}
	
	// access functions 
	int accountNo(){
		return accountNumber;
	}

	double acntBalance(){
		return balance;
	}

	// transaction functions 
	void deposit(double amount){
		balance += amount;
	}

	void withdrawal(double amount);

	// display function - display self to cout
	void display(){
		cout << "Account " << accountNumber << " = " << balance
			 << " (no. withdrawals = " << noWithdrawals << ")\n";
	}

protected:
	unsigned accountNumber;
	double balance;
	int noWithdrawals;

};

void Savings::withdrawal(double amount){
	if (balance < amount)
	{
		cout << "Insufficient funds: balance " << balance << ", withdrawal " << amount << "\n";
	}
	else{
		// after more than one withdrawal in a month...
		if (++noWithdrawals > 1)
		{
			// ...charge a $5 fee
			balance -= 5.00;
		}

		//now make the withdrawal
		balance -= amount;
	}	
}

// prototype declarations 
void process(Checking* pChecking);
void process(Savings* pSavings);

// checking and savings account objects
Checking* chkAcnts[maxAccounts];
Savings* svgAcnts[maxAccounts];

// main - accumulate the initial input and output totals
int main(int argc, char const *argv[])
{
	// loop until someone enters an 'X' or 'x'
	int noChkAccounts = 0;
	int noSvgAccounts = 0;
	char accountType; // S or C
	while(1){
		cout << "Enter S for Savings, C for Checking, X for exit: ";
		cin >> accountType;

		// exit the loop when the user enters an X
		if (accountType == 'x' || accountType == 'X')
		{
			break;
		}

		// otherwise, handle according to the account type 
		switch(accountType){
			// checking account
			case 'c':
			case 'C':
				if (noChkAccounts < maxAccounts)
				{
					int acnt;
					cout << "Enter account number:";
					cin >> acnt;
					chkAcnts[noChkAccounts] = new Checking(acnt);
					process(chkAcnts[noChkAccounts]);
					noChkAccounts++;
				}
				else{
					cout << "No more room for checking accounts\n";
				}
				break;

			// savings account
			case 's':
			case 'S':
				if (noSvgAccounts < maxAccounts)
				{
					int acnt;
					cout << "Enter account number:";
					cin >> acnt;
					svgAcnts[noSvgAccounts] = new Savings(acnt);
					process(svgAcnts[noSvgAccounts]);
					noSvgAccounts++;
				}
				else{
					cout << "No more room for savings accounts\n";
				}
				break;

			default:
				cout << "I didn't get that.\n";
		}
	}

	// now present totals
	double chkTotal = 0; // total of all checking accounts
	cout << "Checking accounts:\n";
	for (int i = 0; i < noChkAccounts; ++i)
	{
		chkAcnts[i]->display(); // Note 10
		chkTotal += chkAcnts[i]->acntBalance();
	}

	double svgTotal = 0; // total of all savings accounts
	cout << "Savings accounts:\n";
	for (int i = 0; i < noSvgAccounts; ++i)
	{
		svgAcnts[i]->display();
		svgTotal += svgAcnts[i]->acntBalance();
	}

	double total = chkTotal + svgTotal;
	cout << "Total for checking accounts = " << chkTotal << "\n";
	cout << "Total for savings accounts = " << svgTotal << "\n";
	cout << "Total worth = " << total << "\n";

	return 0;
}



// process(Checking) - input the data for a checking account
void process(Checking* pChecking){
	cout << "Enter positive number for deposit.\n"
		 << "Negative for check. 0 to terminate. \n";
	double transaction;
	do{
		cout << ":";
		cin >> transaction;

		// deposit 
		if (transaction > 0)
		{
			pChecking->deposit(transaction);
		}

		// withdrawal
		if (transaction < 0)
		{
			pChecking->withdrawal(-transaction);
		}
	}while(transaction != 0);
	return;
}

// process(Savings) - input the data for a savings account
void process(Savings* pSavings){
	cout << "Enter positive number for deposit.\n"
		 << "Negative for withdrawal. 0 to terminate.\n";
	double transaction;
	do{
		cout << ":";
		cin >> transaction;

		// deposit
		if (transaction > 0)
		{
			pSavings->deposit(transaction);
		}

		// withdrawal
		if (transaction < 0)
		{
			pSavings->withdrawal(-transaction);
		}
	}while(transaction != 0);
	return;
}