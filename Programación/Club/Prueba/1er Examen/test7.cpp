#include <bits/stdc++.h>

using namespace std;

int main(int argc, char const *argv[])
{
	int m, n, x, y;
	vector<int> length, width;
	cin >> m >> n;
	long int total = 0;
	int xParts = 1, yParts = 1;
	length.resize(1001,0);
	for (int i = 0; i < m-1; ++i)
	{
		cin>>x;
		length[x]++;
	}
	width.resize(1001,0);
	for (int i = 0; i < n-1; ++i)
	{
		cin>>y;
		width[y]++;
	}
	int index = 1001;
	while(--index >= 0){
		total += (length[index]*yParts*index);
		xParts += length[index];
		total += (width[index]*xParts*index);
		yParts += width[index];
	}
	cout << total << "\n";
	return 0;
}