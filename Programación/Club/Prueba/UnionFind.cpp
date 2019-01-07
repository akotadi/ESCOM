vector<int> padre;

Busca(int u){
	if(padre[u] == u)
		return u;
	return padre[u] = Busca(padre[u]); // Esto mejora resulta log* N
}

Unir(int u, int v){
	u = Busca(u);
	v = Busca(v);
	if(u == v)
		return;
	if (h[u] < h[v])
		padre[u] = v;
	else if(h[v] < h[u])
		padre[v] = u;
	else{
		padre[u] = v;
		h[v]++;
	}
}