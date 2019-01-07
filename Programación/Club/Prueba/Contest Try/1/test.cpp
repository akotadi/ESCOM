#include <bits/stdc++.h>

using namespace std;

typedef long int lli;

int main(int argc, char const *argv[])
{
	lli n, total = 0, key = 0;
    cin>>n;
    vector<lli> v(n);
    for (lli i = 0; i < n; ++i)
    {
    	cin>>v[i];
    	total += v[i];
    }
    key = total/2;
    if (total%2 == 1)
     {
     	key++;
     }
    for (lli i = 0; i < n; ++i)
    {
    	key -= v[i];
    	if (key <= 0)
    	{
    		cout<< i+1 <<endl;
    		break;
    	}
    }
    return 0;
}