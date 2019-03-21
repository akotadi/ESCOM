#include <bits/stdc++.h>

using namespace std;

typedef long long lld;

int main(int argc, char const *argv[])
{
	ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

	int n, k;
	cin >> n >> k;
	lld result = 0;
	int carry = 0;
	bool flag = false;
	for (int i = 0; i < n; ++i)
	{
		int a;
		cin >> a;
		if (a == 0 && carry == 0)
		{
			continue;
		}
		a += carry;
		if (a / k == 0)
		{
			if (carry > 0 || i + 1 == n)
			{
				flag = true;
			}
		}
		if (flag)
		{
			result += 1;
			carry = 0;
			flag = false;
		}else{
			result += (a / k);
			carry = (a % k);
		}
	}
	// printf ("%I64d\n", result);
	cout << result << endl;
	return 0;
}