#include <bits/stdc++.h>
using namespace std;

int main ()
{
	int n,m;
	cin >> n >> m;
	vector < pair <int,int> > s(n);
	vector <int> p(m);
	vector <int> r(m);
	vector <bool> vis(m);
	vector < pair <int, int > > res(n);
	bool flag = false;
	for(int i = 0; i < n; i++)
	{
		cin >> s[i].first;
		s[i].second = i;
	}
	for(int i = 0; i < m; i++)
	{
		cin >> p[i];
	}
	for(int i = 0; i < m; i++)
	{
		cin >> r[i];
	}
	sort(s.rbegin(),s.rend());
	for(int i = 0; i < n; i++)
	{
		int nMin = 1000000000, index = -1;
		for(int j = 0; j < m; j++)
		{
			if(!vis[j] && r[j] < nMin && p[j] >= s[i].first)
			{
				nMin = r[j];
				index = j;
			}
		}
		if(index == -1)
		{
			flag = true;
			break;
		}
		vis[nMin] = true;
		res[i].first = s[i].second;
		res[i].second = index;
		//cout << res[i].second;
	}
	sort(res.begin(),res.end());
	if(flag)
		cout << "impossible" << endl;
	else
	{
		for(int i = 0; i < n; i++)
		{
			cout << res[i].second + 1 << " ";
		}
		cout << endl;
	}
	return 0;
}
