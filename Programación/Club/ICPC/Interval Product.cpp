#include <bits/stdc++.h>

using namespace std;

typedef long long int lli;

int n, k, arr[100002], ST[300002];

int query(int nodo, int ini, int fin, int l, int r){
	if (fin < l || r < ini)
	{
		return 1;
	}else if (l <= ini && fin <= r)
	{
		return ST[nodo];
	}else{
		int mitad = (ini + fin) / 2;
		int izq = query(nodo*2, ini, mitad, l, r);
		int der = query(nodo*2 + 1, mitad + 1, fin, l, r);
		return (izq * der);
	}
}

void update(int nodo, int ini, int fin, int x, int val){
	if (ini == fin && ini == x)
	{
		ST[nodo] = val;
	}else if (ini <= x && x <= fin)
	{
		int mitad = (ini + fin) / 2;
		update(nodo*2, ini, mitad, x, val);
		update(nodo*2 + 1, mitad + 1, fin, x, val);
		ST[nodo] = ST[nodo*2] * ST[nodo*2 + 1];
	}
}

int helper(int x){
	if (x == 0)
	{
		return 0;
	}
	if (x < 0)
	{
		return -1;
	}
	return 1;
}

void initializeST(int nodo, int ini, int fin){
	if (ini == fin)
	{
		ST[nodo] = helper(arr[ini]);
	}else{
		int mitad = (ini + fin) / 2;
		initializeST(nodo*2, ini, mitad);
		initializeST(nodo*2+1, mitad+1, fin);
		ST[nodo] = ST[nodo*2] * ST[nodo*2 + 1];
	}
}

int main(int argc, char const *argv[])
{
	while(scanf("%d %d", &n, &k) != EOF){
		for (int i = 1; i <= n; ++i)
		{
			scanf("%d", &arr[i]);
		}
		initializeST(1, 1, n);
		for (int i = 1; i <= k; ++i)
		{
			char opcion;
			scanf(" %c", &opcion);
			if (opcion == 'C')
			{
				int x, val;
				scanf("%d %d", &x, &val);
				update(1, 1, n, x, helper(val));
			}else{
				int l, r;
				scanf("%d %d", &l, &r);
				int res = query(1, 1, n, l, r);
				if (res == 0)
				{
					printf("0");
				}else if (res < 0)
				{
					printf("-");
				}else{
					printf("+");
				}
			}
		}
		printf("\n");
	}
	return 0;
}