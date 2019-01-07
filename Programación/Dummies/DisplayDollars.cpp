//#ifdef WIN32
//#include <strstrea>
//#else
#include <strstream>
//#endif

#include <iostream>
#include <iomanip>

using namespace std;

class USDollar
{
public:
	USDollar(double v = 0.0){
		dollars = v;
		cents = int((v - dollars) * 100.0 + 0.5);
	}

	operator double(){
		return dollars + ((double)cents/100);
	}

	void display(ostream& out){
		out << '$' << dollars << '.'
		// set fill to 0's for cents
		<< setfill('0') << setw(2) << cents
		// now put it back to spaaces
		<< setfill(' ');
	}

protected:
	unsigned int dollars;
	unsigned int cents;
	
};

// operator<< - overload the inserter for our class
ostream& operator<< (ostream& o, USDollar& d){
	d.display(o);
	return o;
}



int main(int argc, char const *argv[])
{
	USDollar usd(1.50);
	cout << "Initially usd = " << usd << "\n";
	usd = 2.0 * usd;
	out << "then usd = " << usd << "\n";
	// USDollar usd2((double)usd + (double)usd);
	// cout << "then usd = " << usd2 << "\n";

	return 0;
}