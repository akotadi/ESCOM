#include <bits/stdc++.h>

using namespace std;

int main(int argc, char const *argv[])
{
	ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

	string a;
	cin >> a;
	int number[4] = {0};
	for (int i = 0; i < a.size(); ++i)
	{
		if (a[i] != '+')
		{
			number[a[i] - '0' - 1]++;
		}
	}
	int aux = 0;
	for (int i = 0; i < a.size(); ++i)
	{
		if (a[i] != '+')
		{
			while (number[aux] == 0)
			{
				aux++;
			}
			number[aux]--;
			cout << aux+1;			
		}else{
			cout << '+';
		}
	}
	cout << endl;
	return 0;
}