#include <bits/stdc++.h>

using namespace std;

vector<int> numeros;
vector<int> valores;

int LIS(int i){
	if( i > numeros.size() )
		return 1;
	if ( valores[i] != -1)
	{
		return valores[i];
	}
	int res = 0, aux = 0;
	for (int j = i+1; j < numeros.size(); ++j)
	{
		if ( numeros[j] > numeros[i] )
		{
			res = 1 + max( res, LIS(j) ); // Suma después de terminar
		}
	}
	cout<<"Guardando "<<res<<" en "<<i<<endl;
	valores[i] = res;
	return res;
}

int main(int argc, char const *argv[])
{
	int n;
	cin>>n;
	numeros.resize(n);
	valores.resize(n,-1);
	for (int i = 0; i < n; ++i)
	{
		cin>>numeros[i];
	}
	cout<<LIS(0)<<endl;
	return 0;
}