#include <bits/stdc++.h>

using namespace std;

typedef long long int Long;

Long arr[500000], suma[500000];
int aux[500000];

void actualizeNode(int nodo) {
	if(suma[nodo]) {
		suma[nodo<<1] += suma[nodo];
		suma[nodo<<1|1] += suma[nodo];
		suma[nodo] = 0;
	}
	return ;
}

void initializeSuma(int nodo, int ini, int fin){
	suma[nodo] = 0;
	if (ini == fin)
	{
		return;
	}else{
		int mitad = (ini + fin)>>1;
		initializeSuma(nodo<<1, ini, mitad);
		initializeSuma(nodo<<1|1, mitad + 1, fin);
	}
}

void update(int nodo, int ini, int fin, int maxL, int maxR) {
	if(ini == maxL && fin == maxR) {
		suma[nodo] += 1;
		return;
	}
	actualizeNode(nodo);
	int mitad = (ini + fin)>>1;
	if(fin <= mitad) {
		update(nodo<<1, ini, fin, maxL, mitad);
	} else if(ini > mitad) {
		update(nodo<<1|1, ini, fin, mitad + 1, maxR);
	} else {
		update(nodo<<1, ini, mitad, maxL, mitad);
		update(nodo<<1|1, mitad + 1, fin, mitad + 1, maxR);
	}
}

int query(int nodo, int pos, int ini, int fin) {
	if(ini == fin) {
		return suma[nodo];
	}
	if (suma[nodo])
	{
		suma[nodo*2] += suma[nodo];
		suma[nodo*2 + 1] += suma[nodo];
		suma[nodo] = 0;
	}
	int mitad = (ini + fin) / 2;
	if(pos <= mitad) {
		return query(nodo*2, pos, ini, mitad);
	} else {
		return query(nodo*2 + 1, pos, mitad + 1, fin);
	}
}

int main(int argc, char const *argv[])
{
	int n, q;
	cin>>n>>q;
	for (int i = 1; i <= n; ++i)
	{
		cin>>arr[i];
	}
	initializeSuma(1, 1, n);
	for(int i = 1; i <= q; ++i) {
		int l, r;
		cin>>l>>r;
		update(1, l, r, 1, n);
	}
	// sort(arr+1, arr+n+1);
	// for(int i = 1; i <= n; ++i) {
	// 	aux[i] = query(1, i, 1, n);
	// }
	// sort(aux+1, aux+n+1);
	// Long ans = 0;
	// for(int i = 1; i <= n; ++i) {
	// 	ans += 1LL * arr[i] * aux[i];
	// }
	// cout<<ans<<endl;
	return 0;
}