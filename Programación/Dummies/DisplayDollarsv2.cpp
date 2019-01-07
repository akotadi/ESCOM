#include <strstream>
#include <iostream>
#include <iomanip>

using namespace std;

class Currency
{
public:
	Currency(double v = 0.0){
		unit = v;
		cent = int((v-unit) * 100.0 + 0.5);
	}

	virtual void display(ostream& out) = 0;

protected:
	unsigned int unit;
	unsigned int cent;
	
};

class USDollar : public Currency
{
public:
	USDollar(double v = 0.0) : Currency(v){
	}

	// display format: $123.45
	virtual void display(ostream& out){
		out << '$' << unit << '.' << setfill('0') << setw(2) << cent << setfill(' ');
	}
	
};

class DMark : public Currency
{
public:
	DMark(double v = 0.0) : Currency(v){
	}

	// display format: 123.00DM
	virtual void display(ostream& out){
		out << unit << '.' 
		// set fill to 0's for cents
		<< setfill('0') << setw(2) << cent
		// now put it back to spaces
		<< setfill(' ') << " DM";

	}
	
};

ostream& operator<<(ostream& o, Currency& c){
	c.display(o);
	return o;
}



void fn(Currency& c){
	// the following output is polymorphic because operator(ostream&, Currency)
	// is through a virtual member function
	cout << "Deposit was " << c << "\n";
}



int main(int argc, char const *argv[])
{
	// create a dollar and output it using the proper format for a dollar
	USDollar usd(1.50);
	fn(usd);

	// now create a DMark and output it using its own format
	DMark d(3.50);
	fn(d);

	return 0;
}