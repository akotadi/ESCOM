#include <bits/stdc++.h>

using namespace std;

int LIS(vector<int>& vNumbers) 
{
	if (vNumbers.size() < 2)
		return vNumbers.size();
	int result = 0;
	vector<int> vOptimize(vNumbers.size(),1);
	for (int i = 1; i < vNumbers.size(); ++i)
	{
		int aux = 1;
		for (int j = 0; j < i; ++j)
		{
			if (vNumbers[j] < vNumbers[i])
			aux = max(aux, vOptimize[j] + 1);
		}
		vOptimize[i] = aux;
		result = max(result, aux);
	}
	return result;
}

int main(int argc, char const *argv[])
{
	int n;
	cin>>n;
	vector<int> vNumbers(n);
	for (int i = 0; i < n; ++i)
	{
		cin>>vNumbers[i];
	}
	cout<<LIS(vNumbers)<<endl;
	return 0;
}