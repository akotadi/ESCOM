#include <bits/stdc++.h>

using namespace std;

const int INF = -(1 << 30);

vector< vector<int> > vOptimize;
vector< pair<int,int> > vItems;

int Knapsack(int pos, int avaliable){
	if(pos == -1){
		return 0;
	}
	if (vOptimize[pos][avaliable] != -1)
	{
		return vOptimize[pos][avaliable];
	}
	int aux;
	(vItems[pos].first <= avaliable)? (aux = Knapsack(pos - 1, avaliable - vItems[pos].first) + vItems[pos].second) : (aux = INF);
	return vOptimize[pos][avaliable] = max(Knapsack(pos - 1, avaliable), aux);
}

int main(){
	int s, n;
	cin >> s >> n;
	vOptimize.resize(n, vector<int>(s+1, -1));
	vItems.resize(n);
	int weight, value;
	for(int i = 0; i < n; i++){
		cin >> weight >> value;
		vItems[i] = make_pair(weight,value);
	}
	cout <<Knapsack(n - 1, s)<<endl;
	return 0;
}