#include <bits/stdc++.h>

using namespace std;

int n,r,t,maxHours = 0;
bool possible = true;
vector<int> hours;
vector< vector<int> > v;

int main()
{
	cin>>n;
	hours.resize(n);
	v.resize(n, vector<int>(100,0));
	for (int i = 0; i < n; ++i)
	{
		cin>>hours[i]>>r>>t;
		if (hours[i] > maxHours)
		{
			maxHours = hours[i];
		}
		if (t>r)
		{
			for (int j = r+1; j < t; ++j)
			{
				v[i][j] = 1;
			}
		}else{
			for (int j = 0; j < t; ++j)
			{
				v[i][j] = 1;
			}
			for (int j = r+1; j < hours[i]; ++j)
			{
				v[i][j] = 1;
			}
		}
	}
	for (int i = 0; i < maxHours*1825; ++i)
	{
		for (int j = 0; j < n; ++j)
		{
			possible = true;
			if (v[j][i%hours[j]] == 1)
			{
				possible = false;
				break;
			}
		}
		if (possible)
		{
			cout<<i<<endl;
			break;
		}
	}
	if (!possible)
	{
		cout<<"impossible"<<endl;
	}
	return 0;
}