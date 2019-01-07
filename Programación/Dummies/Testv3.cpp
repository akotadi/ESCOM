#include <iostream>

using namespace std;

class Obj
{
public:
	Obj(char c){
		label = c;
		cout << "Constructing object " << label << endl;
	}	
	~Obj(){
		cout << "Destructing object " << label << endl;
	}

protected:
	char label;
	
};



void f2(){
	Obj d('d');
	throw 10;
}

void f1(){
	try{
		Obj c('c');
		f2();
	}
	catch(char* pMsg){
		cout << "String catch" << endl;
	}
}

int main(int argc, char const *argv[])
{
	Obj a('a');
	try{
		Obj b('b');
		f1();
	}

	catch(float f){
		cout << "Float catch" << endl;
	}
	catch(int j){
		cout << "Int catch" << endl;
	}
	catch(...){
		cout << "Generic catch" << endl;
	}
	
	return 0;
}