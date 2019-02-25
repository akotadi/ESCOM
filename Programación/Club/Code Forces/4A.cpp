#include <bits/stdc++.h>

using namespace std;

int main(int argc, char const *argv[])
{
	int n;
	cin >> n;
	if ((n & (1<<0)) || n == 2)
	{
		cout << "NO\n";
	}else{
		cout << "YES\n";
	}
	return 0;
}