#include <bits/stdc++.h>

using namespace std;

int main(int argc, char const *argv[])
{
	int n, m;
	while(scanf("%d %d", &n, &m) != EOF){
		int result = 0;
		while(n <= m){
			string aux = to_string(n);
			unordered_map<char, int> map;
			bool flag = false;
			for (int i = 0; i < aux.length(); ++i)
			{
				if (map[aux[i]] != 0)
				{
					flag = true;
					break;
				}
				map[aux[i]]++;
			}
			if (!flag)
			{
				result++;
			}
			n++;
		}
		cout << result << endl;
	}
	return 0;
}