#include <bits/stdc++.h>

using namespace std;

int main(int argc, char const *argv[])
{
	ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

	int n;
	cin >> n;
	if (n < 4)
	{
		cout << 1 << endl;
	}else{
		cout << n - 2 << endl;
	}
	return 0;
}