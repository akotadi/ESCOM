#include <bits/stdc++.h>

using namespace std;

int main(int argc, char const *argv[])
{

	ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

	int n, m, k;
	cin >> n >> m >> k;
	vector<int> armies(n, 0);
	for (int i = 0; i < m; ++i)
	{
		cin >> armies[i];
	}
	int value;
	cin >> value;
	int result = 0;
	for (int i = 0; i < m; ++i)
	{
		int counter = 0;
		int process = (armies[i] xor value);
		bool flag = true;
		for (int j = 0; j < n; ++j)
		{
			if (process & (1 << j))
			{
				counter++;
			}
			if (counter > k)
			{
				flag = false;
				break;
			}
		}
		if (flag)
		{
			result++;
		}
	}
	cout << result << endl;
	return 0;
}