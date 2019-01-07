#include <iostream>
#include <bitset>
#include <math.h>

using namespace std;

/*string binary(int n)
{
    string binary = bitset<8>(n).to_string(); //to binary
    cout<<binary<<"\n";

    bitset<8> foo (binary);
    cout << foo << "\n";
    cout<< foo.flip() << "\n";
    unsigned long decimal2 = foo.to_ulong() + 1;
    string binary2 = bitset<8>(decimal2).to_string();
   	bitset<8> foo2 (binary2);
   	cout << foo2 << "\n";

    unsigned long decimal = bitset<8>(binary).to_ulong();
    cout<<decimal<<"\n";

    foo2.flip();
    decimal2 = foo2.to_ulong() + 1;
    cout << decimal2;

    return binary;
}*/

int main(int argc, char const *argv[])
{
	int n,x,y;

	cout << "Ingrese el numero de bits a complementar: ";
	cin >> n;
	

	cout << "Ingresa un numero del 1 al " << (pow(2,n)/2)-1 << ": ";
	cin >> x;

	//const int n = y;

	bitset<8> foo (bitset<8>(x).to_string());
    cout << "Representacion binaria: " << foo << "\n";
    foo.flip();

    //y=pow(2,n)-x;

    int i=0;
   	while(foo[i]){
    	foo[i]=0;
    	i++;
    }
    foo[i]=1;

    cout << "Representacion del complemento de -" << x << ": " << foo << "\n";

	return 0;
}