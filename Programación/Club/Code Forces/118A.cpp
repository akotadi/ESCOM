#include <bits/stdc++.h>

using namespace std;

int main(int argc, char const *argv[])
{

	ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

	char vowels[] = {'A', 'O', 'Y', 'E', 'U', 'I', 'a', 'o', 'y', 'e', 'u', 'i'};
	set<char> vowelsList(vowels, vowels+12);

	string s;
	cin >> s;
	string result = "";
	for(int i = 0; i < s.size(); ++i){
		if(vowelsList.find(s[i]) == vowelsList.end()){
			result += ".";
			result += ::tolower(s[i]);
		}
	}
	cout << result << endl;
	return 0;
}