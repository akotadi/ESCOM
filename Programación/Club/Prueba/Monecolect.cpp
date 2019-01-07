#include <bits/stdc++.h>

using namespace std;

typedef long long int lli;

double pi = 3.14159265;

int main(int argc, char const *argv[])
{
	int n;
	double r;
	cin>>n>>r;
	cout<<n<<endl<<r<<endl;
	double ang = 180 / n;
	double tn = tan(180 / n);
	cout<<tn<<endl;
	double ap = r / (2 * tn);
	cout<<ap-r<<endl;
	return 0;
}