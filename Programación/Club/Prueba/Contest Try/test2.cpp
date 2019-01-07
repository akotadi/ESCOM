#include <bits/stdc++.h>

using namespace std;

int busca(int pos, int peso){
	if (pos == N)
	{
		return 0;
	}
	if (visit[pos][peso]!=-1)
	{
		return visit[pos][peso];
	}
	if (w[pos]>peso)
	{
		tomar = busca(pos+1,peso-w[pos])+v[pos];
	}
	no_tomar=busca(pos+1,peso);
	return visit[pos][peso]=max(tomar,no_tomar);
}

int main(int argc, char const *argv[])
{
	
	return 0;
}