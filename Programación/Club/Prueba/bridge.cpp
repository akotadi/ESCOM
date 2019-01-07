#include <bits/stdc++.h>

using namespace std;

int main(int argc, char const *argv[])
{
	int n;
	cin>>n;
	while(n--){
		int p;
		cin>>p;
		if (p>0)
		{
			vector<int> bridge, ans, across;
			int m;
			for (int i = 0; i < p; ++i)
			{
				cin>>m;
				bridge.insert(bridge.begin(),m);
			}
			int i = 0;
			if (bridge.size() == 1)
			{
				across.insert(across.begin(),bridge.back());
				cout<<bridge.back()<<"\n";
				i+=bridge.back();
				bridge.pop_back();
				cout<<i<<"\n";
			}
			else{
				sort(bridge.begin(),bridge.end());
				reverse(bridge.begin(),bridge.end());
				while(bridge.size()>1){
					across.insert(across.begin(),bridge.back());
					ans.insert(ans.begin(),bridge.back());
					bridge.pop_back();
					across.insert(across.begin(),bridge.back());
					ans.insert(ans.begin(),bridge.back());
					i+=bridge.back();
					bridge.pop_back();
					if (bridge.size()>0)
					{
						sort(across.begin(),across.end());
						reverse(across.begin(),across.end());
						bridge.insert(bridge.begin(),across.back());
						ans.insert(ans.begin(),across.back());
						i+=across.back();
						across.pop_back();
					}
				}
				cout<<i<<"\n";
				int aux;
				while(ans.size()>3){
					cout<<ans.back()<<" ";
					ans.pop_back();
					cout<<ans.back()<<"\n";
					ans.pop_back();
					cout<<ans.back()<<"\n";
					ans.pop_back();
				}
				cout<<ans.back()<<" ";
				ans.pop_back();
				cout<<ans.back()<<"\n";
				ans.pop_back();
			}
			if (n>0)
				{
					cout<<"\n";
				}	
		}
	}
	return 0;
}