#include <bits/stdc++.h>

using namespace std;

int main(int argc, char const *argv[])
{
	int n, k;
	cin >> n >> k;
	int result = -(1 << 30);
	while(n--){
		int f, t, aux = 0;
		cin >> f >> t;
		aux = (t > k)?(f-(t-k)):f;
		result = max(result, aux);
	}
	cout << result << endl;
	return 0;
}