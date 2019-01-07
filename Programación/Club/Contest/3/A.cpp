#include <bits/stdc++.h>

using namespace std;

typedef long long int Long;

const int MAXN = 1000005;
 
Long solve(string &s) 
{
	Long ans = 0LL;
	modsum[0] = 0;
	for (int i = 0; i < s.size(); ++i) 
	{
		int val = s[i] - '0';
		modsum[i + 1] = (modsum[i] + val) % 3;
	}
	seen[0][0] = 1LL;
	seen[1][0] = seen[2][0] = 0LL;
	
	for (int i = 0; i < s.size(); ++i) 
	{
		for (int k = 0; k < 3; ++k) 
		{
			int val = modsum[i + 1];
			seen[k][i + 1] = seen[k][i] + (val == k);
			ans += (val == k) ? seen[k][i] : 0;
		}
	}
	return ans;
}
 
int main() 
{
	string s;
	while (getline(cin,s)) 
	{
		string num = "";
		Long ans = 0LL;
		for (int i = 0; i < s.length(); i++) {
			if (isdigit(s[i])){
				num += s[i];
			}else {
				int modsum[s.length()];
				Long seen[3][s.length()];
				if (num != "") 
					ans += solve(num);
				num = "";
			}
		}
		if (num != "") ans += solve(num); 
		cout << ans << 'n';
	}
	return 0;   
}