#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <string.h>
#include <windows.h>

using namespace std;

class Base
{
public:
	virtual void fn(int x){
		cout << "In Base class, int x = " << x << "\n";
	}
};

class SubClass : public Base
{
public:
	virtual void fn(float x){
		cout << "In SubClass, float x = " << x << "\n";
	}
};

void test(Base& b){
	int i = 1;
	b.fn(i);
	float f = 2.03;
	b.fn(f);
}

int main(int argc, char const *argv[])
{
	Base bc;
	SubClass sc;
	cout << "Calling test(bc)\n";
	test(bc);
	cout << "Calling test(sc)\n";
	test(sc);
	
	return 0;
}