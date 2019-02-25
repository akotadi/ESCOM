#include <bits/stdc++.h>

using namespace std;

int main(int argc, char const *argv[])
{
	ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

	string a, b;
	cin >> a >> b;
	for (int i = 0; i < a.size(); ++i)
	{
		a[i] = ::tolower(a[i]);
		b[i] = ::tolower(b[i]);
		if (a[i] == b[i])
		{
			if (i == a.size()-1)
			{
				cout << "0\n";
			}
			continue;
		}else if (a[i] < b[i])
		{
			cout << "-1\n";
			break;
		}else{
			cout << "1\n";
			break;
		}
	}
	return 0;
}