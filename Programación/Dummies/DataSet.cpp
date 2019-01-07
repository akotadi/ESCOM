/* DataSet - store associated data in an array of objects*/

#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <string.h>
#include <windows.h>

using namespace std;

// NameDataSet - stores name and credit card information
class NameDataSet
{
public:
	//NameDataSet();
	//~NameDataSet();
	char firstName[128];
	char lastName[128];
	int creditCard;
};

//getData - read a name and credit card number: return 0 if no more to read
int getData(NameDataSet& nds){
	cout << "\nEnter first name:";
	cin >> nds.firstName;
	if(stricmp(nds.firstName, "exit") == 0){
		return 0;
	}

	cout << "Enter last name:";
	cin >> nds.lastName;

	cout << "Enter credit card number:";
	cin >> nds.creditCard;

	return 1;
}

// displayData - output the index data set 
void displayData(NameDataSet& nds){
	cout << nds.firstName << " " 
		 << nds.lastName << "/" 
		 << nds.creditCard << "\n";
}



int main(int argc, char const *argv[])
{
	// allocate 25 name data sets 
	NameDataSet nds[25];
	
	// load first names, last names and social security numbers
	cout << "Read name/credit card information\n"
		 << "Enter 'exit' for first name to exit\n";

	int index = 0;
	while(getData(nds[index])){
		index++;
	}

	cout << "\nEntries:\n";
	for (int i = 0; i < index; ++i)
	{
		displayData(nds[i]);
	}

	return 0;
}