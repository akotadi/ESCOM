/*BUDGET3.CPP - Budget program with inheritance and late vinding (aka polymorphism). Notice
				how much smaller the program is compared with Budget2 now that the redundancy 
				has been removed. A single function can now handle both checking and savings 
				accounts (and any other accounts that you might invent in the future).

				In addition, this vesion stored accounts in a linked list rather than a fixed 
				array in order to avoid the limitation of a fixed maximum number of objects.*/

#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <string.h>
#include <windows.h>

using namespace std;

class LinkedListObject
{
public:
	LinkedListObject(){
		// add the current object to the end of the linked list
		addToEnd();
	}
	
	// link list manipulation
	static LinkedListObject* first(){
		return pFirst;
	}

	LinkedListObject* next(){
		return pNext;
	}

	void addToEnd();

protected:
	// keep accounts in a linked list so there's no limit to the number of objects
	static LinkedListObject* pFirst;
	LinkedListObject* pNext;
	
};
// allocated the static pointer to the first object in the linked list
LinkedListObject* LinkedListObject::pFirst = 0;

// add the current object to the linked list of
void LinkedListObject::addToEnd(){
	// add this to end of list and count it
	if (pFirst == 0)
	{
		pFirst = this; // empty list: make it first
	}
	else{
		// search for the last element in the list
		LinkedListObject* pA;
		for (pA = pFirst; pA->pNext; pA = pA->pNext)
		{
		}
		pA->pNext = this; // tack us onto end
	}
	pNext = 0; // we're always last
}



/* Account - this abstract class incorporates properties common to both account types: 
			 Checking and Savings. However, it's missing the concept withdrawal(), wich 
			 is different between the two.*/
class Account : public LinkedListObject	
{
public:
	Account(unsigned accNo, double initialBalance = 0.0){
		// initialize the data members of the object 
		accountNumber = accNo;
		balance = initialBalance;

		// count it 
		count++;
	}

	// access functions
	int accountNo(){
		return accountNumber;
	}

	double acntBalance(){
		return balance;
	}

	static int noAccounts(){
		return count;
	}

	// linked list functions wich provide the proper promotions, save a lot of hassle later
	static Account* first(){
		return (Account*)LinkedListObject::first();
	}

	Account* next(){
		return (Account*)LinkedListObject::next();
	}

	// transaction functions 
	void deposit(double amount){
		balance += amount;
	}

	virtual void withdrawal(double amount) = 0;

	// display function for displaying self on 'cout'
	void display(){
		char* pType = type();
		cout << pType << " account " << accountNumber
			 << " = " << balance << "\n";
		//free(pType);
	}

	virtual char* type() = 0;

protected: 
	static int count;
	unsigned accountNumber;
	double balance;
	
};
// allocate space for statics
int Account::count = 0;



// Checking - this class contains properties unique to checking accounts.
class Checking : public Account
{
public:
	Checking(unsigned accNo, double initialBalance = 0.0) : Account(accNo, initialBalance){
	}

	// overload pure virtual functions
	virtual void withdrawal(double amount);

	char* type(){
		//char* pType = (char*)malloc(sizeof("Checking"));
		//strcpy(pType,"Checking");
		char* pType = new char[strlen("Checking")+1];
		strcpy(pType,"Checking");
		return pType;
	}
	
};

/* withdrawal - overload the Account::withdrawal() member function to charge a 20 cents
				per check if the balance is below $500*/
void Checking::withdrawal(double amount){
	if (balance < amount || (balance < 500 && (balance-.19) < amount))	
	{
		cout << "Insufficient funds: balance " << balance
			 << ", check " << amount << "\n";
	}
	else{
		balance -= amount;

		// if balance falls too low, charge service fee
		if (balance < 500.00)
		{
			balance -= 0.20;
		}
	}
}



// Savings - same story as Checking except that it also has a unique data member
class Savings : public Account
{
public:
	Savings(unsigned accNo, double initialBalance = 0.0) : Account(accNo, initialBalance){
		noWithdrawals = 0;
	}

	// transaction functions
	virtual void withdrawal(double amount);

	char* type(){
		//char* pType = (char*)malloc(sizeof("Savings"));
		char* pType = new char[strlen("Savings")+1];
		strcpy(pType,"Savings");
		return pType;
	}

protected: 
	int noWithdrawals;
	
};

/* withdrawal - overload the Account::withdrawal() member function to charge a $5.00 fee after 
				the first withdrawal of the month*/
void Savings::withdrawal(double amount){
	if (balance < amount || (noWithdrawals + 1 > 1 && balance < amount+5.00))
	{
		cout << "Insufficent funds: balance " << balance
			 << ", withdrawal " << amount << "\n";
	}
	else{
		if (++noWithdrawals > 1)
		{
			balance -= 5.00;
		}
		balance -= amount;
	}
}



// prototype declarations
unsigned getAccntNo();
void process(Account* pAccount);
void getAccounts();
void displayResults();

// main - accumulate the initial input and output totals
int main(int argc, char const *argv[])
{
	// read accounts from user 
	getAccounts();

	// display the linked list of accounts
	displayResults();

	return 0;
}



// getAccounts - load up the specified array of Accounts
void getAccounts(){
	Account* pA;

	// loop until someone enters 'X' or 'x'
	char accountType; // S or C
	while(1){
		cout << "Enter S for Savings, C for Checking, X for Exit: ";
		cin >> accountType;

		switch(accountType){
			case 'c':
			case 'C':
				pA = new Checking(getAccntNo());
				break;

			case 's':
			case 'S':
				pA = new Savings(getAccntNo());
				break;

			case 'x':
			case 'X':
				return;

			default:
				cout << "I didn't get that.\n";

		}

		// now process the object we just created
		process(pA);
	}
	return;
}

// displayResults - displau the accounts found in the Account link list
void displayResults(){
	// now present totals
	double total = 0.0;
	cout << "Account totals:\n";
	for ( Account* pA = Account::first() ; pA ; pA = pA->next() )
	{
		pA->display();
		total += pA->acntBalance();
	}
	cout << "Total worth = " << total << "\n";
	return;
}

// getAccntNo - return the account number entered
unsigned getAccntNo(){
	unsigned accntNo;
	cout << "Enter account number: ";
	cin >> accntNo;
	return accntNo;
}

// process(Account) - input the data for an account 
void process(Account* pAccount){
	cout << "Enter positive number for deposit, negative for withdrawal, 0 to terminate.\n";

	double transaction;
	do{
		cout << ":";
		cin >> transaction;

		// deposit
		if (transaction > 0)
		{
			pAccount->deposit(transaction);
		}

		// withdrawal
		if (transaction < 0)
		{
			pAccount->withdrawal(-transaction);
		}
	}while(transaction != 0);

	return;
}