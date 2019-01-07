// LinkedListData - store name data in a linked list of objects

#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <string.h>
#include <windows.h>

using namespace std;

// NameDataSet - stores name and social security information
class NameDataSet
{
public:
	char szFirstName[128];
	char szLastName[128];
	int nSocialSecurity;
	
	// the link to the next entry in the list
	NameDataSet* pNext;	
};

// the pointer to the first entry in the list
NameDataSet* pHead = 0;

// addTail - add a new member to the linked list 
void addTail(NameDataSet* pNDS){
	// make sure that our list pointer is NULL since we are now the last element in the list 
	pNDS->pNext = 0;

	//if the list is empty, then just point the head pointer to the current entry and quit
	if (pHead == 0)
	{
		pHead = pNDS;
		return;
	}

	// otherwise find the last element in the list
	NameDataSet* pCurrent = pHead;
	while(pCurrent->pNext){
		pCurrent = pCurrent->pNext;
	}

	// now add the current entry onto the end of that 
	pCurrent->pNext = pNDS;
	return;
}

// getData - read a name and social security number; return NULL if no more to read
NameDataSet* getData(){
	// get a new entry to fill
	NameDataSet* pNDS = new NameDataSet;

	// read the first name
	cout << "\nEnter first name:";
	cin >> pNDS->szFirstName;

	// if the name entered id 'exit'...
	if (stricmp(pNDS->szFirstName, "exit") == 0)
	{
		// ... delete the still empty object...
		delete pNDS;

		// ... return a null to terminate input
		return 0;
	}

	// read the remaining members
	cout << "Enter last name:";
	cin >> pNDS->szLastName;
	cout << "Enter social security number:";
	cin >> pNDS->nSocialSecurity;

	// zero the pointer to the next entry
	pNDS->pNext = 0;

	// return the address of the object created
	return pNDS;
}

// displayData - output the index'th data set
void displayData(NameDataSet* pNDS){
	cout << pNDS->szFirstName << " " << pNDS->szLastName << "/" << pNDS->nSocialSecurity << "\n";
}

int main(int argc, char const *argv[])
{
	cout << "Read name/social security information\n"
		 << "Enter 'exit' for first name to exit\n";

	// create (another) NameDataSet object
	NameDataSet* pNDS;
	while(pNDS = getData()){
		// add it onto the end of the list od NameDataSet objects 
		addTail(pNDS);
	}

	// to display the obhects, iterate through the list (stop when the next address is NULL)
	cout << "Entries:\n";
	pNDS = pHead;
	while(pNDS){
		// display current entry 
		displayData(pNDS);

		// get the next entry 
		pNDS = pNDS->pNext;
	}

	return 0;
}