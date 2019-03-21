#include <bits/stdc++.h>

#define MAXN 1000000

using namespace std;

typedef long long ll;

vector<bool> isPrime;
void calculatePrimes(){
	isPrime.resize(MAXN, true);
	isPrime[0] = isPrime[1] = false;
	for (ll i = 2; i < MAXN; i++)
	{
		if (isPrime[i] == 0)
		{
			for (ll j = j*i; j < MAXN; j += i)
			{
				isPrime[j] = false;
			}
		}
	}
}

int main(int argc, char const *argv[])
{
	calculatePrimes();
	int n;
	cin >> n;
	while(n--){
		ll x;
		cin >> x;
		if (x == 4)
		{
			cout << "YES" << endl;
		}else if ((x & 1) == 0 || !isPrime[sqrt(x)])
		{
			cout << "NO" << endl;
		}else
		{
			ll aux = sqrt(x);
			if (pow(aux,2) == x)
			{
				cout << "YES" << endl;
			}else{
				cout << "NO" << endl;
			}
		}
	}
	return 0;
}