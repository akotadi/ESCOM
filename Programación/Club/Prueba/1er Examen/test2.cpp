#include <bits/stdc++.h>
#define INF 0xFFFFFFFF

using namespace std;

typedef long int li;

vector<li> gold;
vector< vector<li> > values;
li minGold = INF;
li b;
int n;

li minExGold(int totalGold, int index){
	if (++index >= n)
	{
		if (totalGold < b)
		{
			return INF;
		}
		else{
			return (totalGold - b);
		}
	}
	if (totalGold >= b)
	{
		return (totalGold - b);
	}
	return (li)min(minExGold(totalGold+gold[index],index),minExGold(totalGold,index));
}

int main(int argc, char const *argv[])
{
	cin >> n;
	cin >> b;
	gold.resize(n, -1);
	for (int i = 0; i < n; ++i)
	{
		cin >> gold[i];
	}
	cout << (li)min(minExGold(0,0),minExGold(gold[0],0)) << "\n";
	return 0;
}