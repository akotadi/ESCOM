#include <bits/stdc++.h>

using namespace std;

int main(int argc, char const *argv[])
{
	int m, s;
	cin >> m >> s;
	if (s == 0)
	{
		cout << ((m == 1)?"0 0":"-1 -1") << endl;
	}else if (m * 9 < s)
	{
		cout << "-1 -1" << endl;
	}else{
		string result = "";
		while(s > 0){
			int aux = min(s, 9);
			result += aux + '0';
			s -= aux;
			m--;
		}
		while(m-- > 0) result += '0';
		string aux = result;
		reverse(result.begin(), result.end());
		if (result[0] == '0')
		{
			for (int i = 0; i < result.size(); ++i)
			{
				if (result[i] != '0')
				{
					result[0]++, result[i]--;
					break;
				}
			}
		}
		cout << result << " " << aux << endl;
	}
	return 0;
}