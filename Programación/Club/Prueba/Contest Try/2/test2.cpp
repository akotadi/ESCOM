#include <bits/stdc++.h>

using namespace std;

vector< vector<double> > v;

double numberHeads(int k, pair<int,int> current){
	if (k<=0)
	{
		return v[current.first][k] = (double)current.second;
	}
	if (v[current.first][k] != 0)
	{
		return v[current.first][k];
	}
	double ans = numberHeads(k-1,current);
	if (current.first != 0)
	{
		ans += numberHeads(k-1,make_pair(current.first-1,current.second+1));
	}else{
		ans += numberHeads(k-1,make_pair(current.first+1,current.second-1));
	}
	return v[current.first][k] = (ans/2);
}

int main()
{
	int n,k;
	double nHeads = 0;
	cin>>n>>k;
	v.resize(n+1, vector<double>(k+1,0));
	pair<int,int> current;
	current = make_pair(n,0);
	cout<<setprecision(10)<<fixed<<numberHeads(k,current);<<endl;
	return 0;
}