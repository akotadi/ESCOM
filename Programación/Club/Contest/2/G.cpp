#include <bits/stdc++.h>

using namespace std;

typedef long long int lli;

struct pair_hash {
	template <class T1, class T2>
	size_t operator () (const pair<T1,T2> &p) const {
		auto h1 = hash<T1>{}(p.first);
		auto h2 = hash<T2>{}(p.second);
		return h1 ^ h2;  
	}
};

lli GCD(lli a, lli b){
	lli result;
	for(lli i = 1; i <= a && i <= b; i++){
		if(a%i==0 && b%i == 0){
			result=i;
		}
	}
	return result;
}

int main(int argc, char const *argv[])
{
	lli n, x0, y0;
	cin>>n>>x0>>y0;
	unordered_map<pair<lli,lli>,int,pair_hash> aux1, aux2;
	for (lli i = 0; i < n; ++i)
	{
		lli x, y;
		cin>>x>>y;
		x -= x0;
		y -= y0;
		if (x == 0)
		{
			aux1[make_pair(0,1)]++;
		}else if (y == 0)
		{
			aux2[make_pair(1,0)]++;
		}else{
			lli aux = GCD(abs(x),abs(y));
			auto auxPoint = make_pair(abs(x)/aux,abs(y)/aux);
			if (x*y < 0)
			{
				aux1[auxPoint]++;
			}else{
				aux2[auxPoint]++;
			}
		}
	}
	cout<<aux1.size()+aux2.size()<<endl;
	return 0;
}