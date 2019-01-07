#include <bits/stdc++.h>

using namespace std;

typedef long long int lli;

int main(int argc, char const *argv[])
{
	int n;
	cin>>n;
	for (int i = 0; i < n; ++i)
	{
		int x, y;
		cin>>x>>y;
		if (y % x != 0)
		{
			cout<<-1<<endl;
		}else{
			cout<<x<<" "<<y<<endl;
		}
	}
	return 0;
}