#include <bits/stdc++.h>

using namespace std;

int main(int argc, char const *argv[])
{
	int n;
	cin >> n;
	unordered_map<string,int> result;
	while(n--){
		string aux;
		cin >> aux;
		result[aux]++;
	}
	string winner;
	int score = -1, aux;
	for(auto it : result){
		if (it.second > score)
		{
			score = it.second;
			winner = it.first;
		}
	}
	cout << winner << endl;
	return 0;
}