#include <iostream>
#include <stdio.h>

using namespace std;

long power(long x, long y, long m){
	long ans=1;
	while(y>0){
		if (y&1)
		{
			ans=(ans*x)%m;
		}
		x=(x*x)%m;
		y>>=1;
	}
	return ans;
}

/*
pow(A base, int exp){
	if(exp==0)
		return Id;
	if(exp==1)
		return base;
	if(exp%2==0)
		A res = pow(base,exp/2);
		return res*res;
	if(exp%2==1)
		A res = pow(base,(exp-1)/2);
		return base*res;
}
*/

int main(int argc, char const *argv[])
{
	long x, y, m;
	while(cin>>x>>y>>m){
		x%=m;
		printf("%li\n",power(x,y,m));
		cout<<power(x,y,m)<<endl;
	}
	return 0;
}