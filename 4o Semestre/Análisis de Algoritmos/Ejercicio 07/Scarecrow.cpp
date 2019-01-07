#include <bits/stdc++.h>

using namespace std;

int main(int argc, char const *argv[])
{
	int t, n;
	cin>>t;
	for (int i = 0; i < t; ++i)
	{
		cin>>n;
		char c;
		int result = 0;
		while(n-- > 0){
			cin>>c;
			if (c == '#')
			{
				continue;
			}else if (c == '.'){
				result++;
				if (n>2)
				{
					n-=2;
					cin>>c;
					cin>>c;
				}else
				{
					while(n-- > 0){
						cin>>c;
					}
				}
			}
		}
		cout<<result<<endl;
	}
	return 0;
}