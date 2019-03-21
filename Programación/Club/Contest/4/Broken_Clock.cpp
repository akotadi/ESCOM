#include <bits/stdc++.h>

using namespace std;

int main(int argc, char const *argv[])
{
	int n;
	cin >> n;
	string s;
	cin >> s;
	int h = stoi(s.substr(0,2)), m = stoi(s.substr(3,2));
	if(n == 12){
		if(h == 0)
			s[1] = '1';
		else if(h > n){
			if(s[1] == '0')
				s[0] = '1';
			else
				s[0] = '0';
		}
	}
	else
		s[0] = (h > n-1)?'0':s[0];
	s[3] = (m > 59)?'0':s[3];
	cout << s << endl;
	return 0;
}