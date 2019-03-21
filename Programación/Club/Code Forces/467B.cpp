#include <bits/stdc++.h>

using namespace std;

int main(int argc, char const *argv[])
{

	ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

	int n, m, k;
	cin >> n >> m >> k;
	vector<int> armies(m, 0);
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
		while(process){
			if (process & 1)
			{
				counter++;
			}
			if (counter > k)
			{
				flag = false;
				break;
			}
			process>>=1;
		}
		if (flag)
		{
			result++;
		}
	}
	cout << result << endl;
	return 0;
}