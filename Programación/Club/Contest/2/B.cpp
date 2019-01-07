#include <bits/stdc++.h>

using namespace std;

typedef long long int lli;

int main(int argc, char const *argv[])
{
	int x0, x1, x2, y0, y1, y2;
	cin>>x0>>y0>>x1>>y1>>x2>>y2;
	double area1 = abs(x0 * (y1 - y2) + x1 * (y2 - y0) + x2 * (y0 - y1)) / 2;
	int w0, w1, w2, z0, z1, z2;
	cin>>w0>>z0>>w1>>z1>>w2>>z2;
	double area2 = abs(w0 * (z1 - z2) + w1 * (z2 - z0) + w2 * (z0 - z1)) / 2;
	double maxA = max(area1, area2);
	maxA*=2;
	cout<<area1<<endl<<area2<<endl;
	double minA = min(area1, area2);
	minA*=2;
	if ((int)maxA % (int)minA == 0)
	{
		cout<<"YES"<<endl;
	}else{
		cout<<"NO"<<endl;
	}
	return 0;
}