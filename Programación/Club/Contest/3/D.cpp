#include <bits/stdc++.h>

using namespace std;

typedef unsigned long long int Long;

const Long mod = 1000000007;

Long Exponenciar(Long a, Long n) {
    Long resultado = 1;
    for (; n; n >>= 1) {
        if (n & 1) 
        	resultado = (resultado * a) % mod;
        a = (a * a) % mod;
    }
    return resultado;
}

int main(int argc, char const *argv[])
{
	Long n, m;
	int k;
	cin>>n>>m>>k;
	Long ans;
	if (k == -1 && ((m+n) & 1))
	{
		ans = 0;
	}else{
		ans = (Exponenciar(Exponenciar(2, n-1), m-1));
	}
	cout<<ans<<endl;
	return 0;
}