#include <bits/stdc++.h>

using namespace std;

typedef long long lld;

int main(int argc, char const *argv[])
{
	int t;
	cin >> t;
	while(t--){
		lld a, b, k;
		cin >> a >> b >> k;
		lld result = ((a - b) * (k >> 1));
		if (k & 1)
		{
			result += a;
		}
		cout << result << endl;
	}
	return 0;
}