#include <bits/stdc++.h>

using namespace std;

int main(int argc, char const *argv[])
{
	int n;
	cin >> n;
	vector<string> a(2*n - 2);
	vector<int> p;
	for (int i = 0; i < 2*n-2; ++i)
	{
		cin >> a[i];
		if (a[i].size() == n-1)
		{
			p.push_back(i);
		}
	}
	string s1 = a[p[0]]+a[p[1]].back(), s2 = a[p[1]]+a[p[0]].back(), result1 = "", result2 = "";
	// cout << s1 << endl << s2 << endl;
	int counter = 0;
	unordered_map<string,char> use;
	for(auto str : a){
		int index = 0;
		bool flag = true;
		if (use.find(str) != use.end())
		{
			// result1 += ((use[str] == 'S')?'P':'S');
			if (use[str] == 'S')
			{
				result2 += 'P';
			}else{
				result2 += 'S';
			}
		}else{
			for(auto c : str){
				if (c != s1[index++])
				{
					break;
				}
				if (index == str.size())
				{
					counter++;
					result1 += 'P';
					use[str] = 'P';
					flag = false;
				}
			}
			if (flag)
			{
				index = s1.size();
				for(auto c : str){
					if (c != s1[--index])
					{
						break;
					}
					if (index == s1.size() - str.size())
					{
						counter++;
						result1 += 'S';
						use[str] = 'S';
					}
				}
			}
		}
	}
	// cout << counter << endl << result1 << endl << a.size() << endl;
	use.clear();
	if (counter < a.size())
	{
		for(auto str : a){
			int index = 0;
			bool flag = true;
			if (use.find(str) != use.end())
			{
				// result1 += (use[str] == 'S')?'P':'S';
				if (use[str] == 'S')
				{
					result2 += 'P';
				}else{
					result2 += 'S';
				}
			}else{
				for(auto c : str){
					if (c != s2[index++])
					{
						break;
					}
					if (index == str.size())
					{
						result2 += 'P';
						flag = false;
					}
				}
				if (flag)
				{
					index = s2.size();
					for(auto c : str){
						if (c != s2[--index])
						{
							break;
						}
						if (index == s1.size() - str.size())
						{
							result2 += 'S';
						}
					}
				}
			}
		}
		cout << result2 << endl;
	}else{
		cout << result1 << endl;
	}
	return 0;
}