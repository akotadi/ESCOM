#include <bits/stdc++.h>

using namespace std;

typedef unsigned long ul;

bool isPrime(ul number){
	if (number < 2) return false;
	if (number == 2) return true;
	if ((number & 1) == 0) return false;
	for (int i = 3; (i*i) <= number; i+=2)
		if (number % i == 0) return false;
	return true;
}

int main(int argc, char const *argv[])
{
	ul n;
	cin >> n;
	if (isPrime(n)) cout << 1 << endl;
	else if((n & 1) == 0) cout << 2 << endl;
	else if(isPrime(n-2)) cout << 2 << endl;
	else cout << 3 << endl;
	return 0;
}