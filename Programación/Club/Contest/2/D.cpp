#include <bits/stdc++.h>

using namespace std;

typedef long long int lli;

int maxN = 1299709+100;

bool IsPrime(int n) {
	bool flag = true;
	if(n % 2 == 0) {
		flag = false;
	}if (n % 3 == 0)
	{
		flag = false;
	}else{
		for( int k = 1; 36*k*k-12*k < n;++k){
			if ( (n % (6*k+1) == 0) || (n % (6*k-1) == 0) ){
				flag = false;
				break;
			}
		}
	}
	return flag;
}

int main(int argc, char const *argv[])
{
	map<int,int> primes;
	primes[2] = 2;
	primes[3] = 2;
	int lastIndex = 3;
	for (int i = 4; i < maxN; ++i)
	{
		if (IsPrime(i))
		{
			primes[i] = lastIndex;
			lastIndex = i;
		}
	}

	int x;
	cin>>x;
	while(x != 0){
		auto it = primes.find(x);
		if (it != primes.end())
		{
			cout<<0<<endl;
		}else{
			auto aux = primes.upper_bound(x);
			cout<<(aux->first) - (aux->second)<<endl;
		}
		cin>>x;
	}
	return 0;
}

/*
10
11
27
2
492170
0

*/