tabla[N][C];
// Inicializar en -1

int coinChange(int n, int i){
	if(n==0)
		return 1;
	int res = 0;
	if(tabla[n][i]!=-1)
		return tabla[n][i];
	if(n>=valor[i])
		res+=coinChange(n-valor[i],i);
	if(i<valor.size())
		res+=coinChange(n,++i);
	return tabla[n][i]=res;
}