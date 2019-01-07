#include <bits/stdc++.h>

using namespace std;

vector<int> primes;
vector<bool> isPrime;

void primesSieve(int n){
	isPrime.resize(n + 1, true);
	isPrime[0] = isPrime[1] = false;
	primes.push_back(2);
	for(int i = 4; i <= n; i += 2) isPrime[i] = false;
	int limit = sqrt(n);
	for(int i = 3; i <= n; i += 2){
		if(isPrime[i]){
			primes.push_back(i);
			if(i <= limit){
				for(int j = i * i; j <= n; j += 2 * i){
					isPrime[j] = false;
				}
			}
		}
	}
}

int main(int argc, char const *argv[])
{
	primesSieve(1000);
	int n;
	cin>>n;
	string s;
	for (int i = 0; i < n; ++i)
	{
		cin>>s;
		int x = 0;
		for (int j = 0; j < s.length(); ++j)
		{
			x += s.at(j) - '0';
		}
		if (x == 1 || isPrime[x])
		{
			cout<<"UltraPrimo"<<endl;
		}else{
			cout<<"No UltraPrimo"<<endl;
		}
	}
	return 0;
}