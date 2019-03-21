#include <bits/stdc++.h>

using namespace std;

int main(int argc, char const *argv[])
{
	int n;
	cin >> n;
	while(n--){
		unordered_set<char> check;
		string s;
		cin >> s;
		for(auto c : s){
			check.insert(c);
		}
		if (check.size() == 1)
		{
			cout << -1 << endl;
		}
		else{
			sort(s.begin(), s.end());
			cout << s << endl;
		}
	}
	return 0;
}