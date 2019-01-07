#include <bits/stdc++.h>

using namespace std;

typedef long long int lli;

vector<lli> primes;
vector<bool> isPrime;
void primesSieve(lli n){
	isPrime.resize(n + 1, true);
	isPrime[0] = isPrime[1] = false;
	primes.push_back(2);
	for(lli i = 4; i <= n; i += 2) isPrime[i] = false;
	lli limit = sqrt(n);
	for(lli i = 3; i <= n; i += 2){
		if(isPrime[i]){
			primes.push_back(i);
			if(i <= limit)
			for(lli j = i * i; j <= n; j += 2 * i)
			isPrime[j] = false;
		}
	}
}

vector<pair<lli, int>> factorize(lli n){
	vector<pair<lli, int>> f;
	for(lli p : primes){
		if(p * p > n) break;
		int pot = 0;
		while(n % p == 0){
			pot++;
			n /= p;
		}
		if(pot) f.emplace_back(p, pot);
	}
	if(n > 1) f.emplace_back(n, 1);
	return f;
}

lli sigma(lli n, lli pot){
	lli ans = 1;
	auto f = factorize(n);
	for(auto & factor : f){
		lli p = factor.first;
		int a = factor.second;
		if(pot){
			lli p_pot = pow(p, pot);
			ans *= (pow(p_pot, a + 1) - 1) / (p_pot - 1);
		}else{
			ans *= a + 1;
		}
	}
	return ans;
}

int main(int argc, char const *argv[])
{
	lli x;
	cin>>x;
	lli result = 0;
	unordered_map<int,bool> mymap;
	primesSieve(1000000000001);
	while(x != 0){
		cin>>x;
		for (int i = 1; i <= x; ++i)
		{
			lli aux = sigma(i,1);
			auto it = mymap.find(aux);
			if (it == mymap.end())
			{
				if (aux % 2 == 0)
				{
					mymap[aux] = true;
				}else{
					mymap[aux] = false;
				}
			}
			if (mymap[aux])
			{
				result++;
			}
		}
	}
	return 0;
}