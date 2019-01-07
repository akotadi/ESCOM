#include <bits/stdc++.h>

#define endl '\n'
#define forn(i, n) for(int i = 0; i < n; ++i)

using namespace std;

int const MAXN = 507;

int n, m, k;

vector<string> data;

bool vis[MAXN][MAXN];

int mvI[] = { 1, -1, 0,  0};
int mvJ[] = { 0,  0, 1, -1};

void dfs(int x, int y) {
	if (vis[x][y] || data[x][y] != '.') return;
	vis[x][y] = true;

	forn(i, 4)
		dfs(x + mvI[i], y + mvJ[i]);
	
	if (k)
		data[x][y] = 'X', --k;
}

int main() {
	cin >> n >> m >> k;
	data.assign(n + 7, " ");
	forn(i, MAXN)
		fill(vis[i], vis[i] + MAXN, false);

	int x, y;
	for (int i = 1; i <= n; ++i) {
		string s;
		cin >> s;
		data[i] += s + " ";

		forn(j, data[i].size())
			if (data[i][j] == '.') x = i, y = j;
	}
	data[n + 1] += string(500, ' ');

	dfs(x, y);

	for (int i = 1; i <= n; ++i) {
		for (int j = 1; j <= m; ++j)
			cout << data[i][j];
		cout << endl;
	}

	return 0;
}