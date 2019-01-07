#include <bits/stdc++.h>

using namespace std;

vector< vector<int> > vOptimize;

int LCS(string& s1, string& s2, int m, int n) {
	int nRow, nColumn;

	for(nRow = 0; nRow <= m; nRow++) {

		for(nColumn = 0; nColumn <= n; nColumn++) {

			if(nRow == 0 || nColumn == 0) {

				vOptimize[nRow][nColumn] = 0;

			} else if(s1[nRow - 1] == s2[nColumn - 1]) {

				vOptimize[nRow][nColumn] = vOptimize[nRow - 1][nColumn - 1] + 1;

			} else {

				vOptimize[nRow][nColumn] = max(vOptimize[nRow - 1][nColumn], vOptimize[nRow][nColumn - 1]);
			}
		}
	}

	return vOptimize[m][n];

}

int main()
{
	string s1, s2;
	cin>>s1>>s2;
	vOptimize.resize(s1.size()+1, vector<int>(s2.size()+1) );
	cout<<LCS(s1,s2,s1.size(),s2.size())<<endl;
	return 0;
}