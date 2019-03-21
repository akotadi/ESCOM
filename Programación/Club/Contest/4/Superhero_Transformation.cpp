#include <bits/stdc++.h>

using namespace std;

set<char> sVowels = {'a', 'e', 'i', 'o', 'u'};

int main(int argc, char const *argv[])
{
	string s, t;
	cin >> s >> t;
	if (s.size() != t.size())
	{
		cout << "NO" << endl;
	}else{
		for (int i = 0; i < s.size(); ++i)
		{
			if (sVowels.find(s[i]) != sVowels.end())
			{
				if (sVowels.find(t[i]) != sVowels.end())
				{
					continue;
				}else{
					cout << "NO" <<endl; 
					return 0;
				}
			}else{
				if (sVowels.find(t[i]) == sVowels.end())
				{
					continue;
				}else{
					cout << "NO" <<endl; 
					return 0;
				}
			}
		}
		cout << "YES" << endl;
	}
	return 0;
}