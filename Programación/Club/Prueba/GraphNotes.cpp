// the little baby giant step

// __int128 -> maneja hasta 128 bits (OmegaUp - RPC - UVA)

#include <bits/stdc++.h>
using namespace std;

// vis[N];
// fill(vis, vis+N, -1)

bool DFS(int s, int p){
	vis[n] = 1;
	bool ciclo = false;
	for (auto v : grafo[s])
	{
		if (vis[v] != -1 && v != p)
		{
			return true;
		}
		if (vis[v] == -1)
		{
			ciclo = DFS(v,s);
		}
	}
	return ciclo;
}

bool hayCiclo(){
	fill(vis, vis+N, -1);
	bool ciclo = false;
	for (int i = 0; i < Nodos; ++i)
	{
		if (vis[i] == -1)
		{
			ciclo |= hayCiclo_(i,-1);
		}
	}
	return ciclo;
}

bool hayCiclo_(int s, int p){
	vis[s] = 0;
	bool ciclo = false;
	for (auto v : grafo[s]){
		if (vis[v] == 0 && p != v)
		{
			return true;
		}
		if (vis[v] == -1)
		{
			ciclo |= hayCiclo_(v,s);
		}
	}
	return ciclo;
}

bool bipartite(){
	vis[N];
	fill(vis, vis+N, -1):
	for (int i = 0; i < Nodos; ++i)
	{
		if (vis[i] == -1)
		{
			queue q;
			vis[i] = 0;
			q.push(i):
			while(!q.empty()){
				u = q.front();
				q.pop();
				for (auto v : grafos)
				{
					if (vis[v] == -1)
					{
						q.push(v);
						vis[v] = 1-vis[v];
					}
					if (vis[v] != -1 && vis[v] == vis[u])
					{
						return false;
					}
				}
			}
		}
	}
}

int main(int argc, char const *argv[])
{

	return 0;
}