#include <bits/stdc++.h>

using namespace std;

long int n,m;
string s;

int main(int argc, char const *argv[])
{
	cin>>n;
	getline(cin,s);
	while(n>0){
		while(getline(cin,s)){
			m = (1<<s.size());
			if (s.empty())
			{
				n--;
				cout<<"vacio"<<endl;
				break;
			} else{
				for (int i = 0; i < s.size(); ++i)
				{
					if (s[i] == '0')
					{
						m &= (0<<i);
					}
				}
				cout<<m<<endl;
			}
		}
	}
	return 0;
}