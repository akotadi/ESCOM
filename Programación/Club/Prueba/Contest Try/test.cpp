#include <bits/stdc++.h>

using namespace std;

int main(int argc, char const *argv[])
{
	int n;
	cin>>n;
	while(n--){
		vector<pair<int,int>> v, v_ans;
		int m;
		cin>>m;
		int x,y;
		while(cin>>x>>y){
			if (x == 0 && y == 0)
			{
				break;
			}
			if (!(x>m || y<0))
			{
				v.push_back(make_pair(x,y));
			}
		}
		bool cambios=true, terminado=false;
		if (v.size() != 0)
		{
			sort(v.begin(),v.end());
			int pos = 0, i = 0, newSegment = 0;
			int maxRange = -1;
			while(pos<=m && cambios){
				i = 0;
				cambios = false;
				while(pos>=v[i].first && i<v.size()){
					if (v[i].second>maxRange)
					{
						maxRange = v[i].second;
						newSegment = i;
						cambios = true;
					}
					i++;
				}
				pos = maxRange;
				if (cambios)
				{
					v_ans.push_back(v[newSegment]);
				}
				if (pos >= m)
				{
					terminado = true;
					break;
				}
			}
		}
		if (v_ans.size() == 0 || terminado == false)
		{
			cout<<"0\n";
		}
		else{
			cout<<v_ans.size()<<endl;
			sort(v_ans.begin(),v_ans.end());
			for (auto& it : v_ans)
			{
				cout<<it.first<<" "<<it.second<<"\n";
			}
		}
		if (n>0)
		{
			cout<<endl;
		}
	}
	return 0;
}