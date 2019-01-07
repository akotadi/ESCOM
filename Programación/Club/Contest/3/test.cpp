#include <bits/stdc++.h>

using namespace std;

typedef long long int Long;

int main(int argc, char const *argv[])
{
	string s;
	while (getline(cin,s)) {
		Long ans = 0;
		for (int i = 0; i <= s.length(); i++) {
			if (isdigit(s[i--])) {
				int c[3] = {1, 0, 0};
				Long sum = 0;
				while (isdigit(s[++i])) {
					sum += s[i] - '0';
					sum %= 3;
					ans += c[sum], c[sum]++;
				}
			}
		}
		cout<<ans<<endl;
	}
	return 0;
}