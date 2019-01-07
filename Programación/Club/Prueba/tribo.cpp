#include <iostream>
#include <math.h>

using namespace std;

int tribo(int n,signed long long int* x){
	if (n==1)
	{
		x[0]=1;
		return 1;
	}
	else if (n==2)
	{
		x[0]=1;
		x[1]=1;
		return 1;
	}
	else if (n==3)
	{
		x[0]=1;
		x[1]=1;
		x[2]=2;
		return 2;
	}
	else if (x[n-1]==0)
	{
		x[n-1]=tribo(n-1,x)+tribo(n-2,x)+tribo(n-3,x);
		return tribo(n-1,x)+tribo(n-2,x)+tribo(n-3,x);
	}
	else{
		return x[n-1];
	}
}

int main(int argc, char const *argv[])
{
	int n;
	cin>>n;
	signed long long int x[n]={0};
	tribo(n,x);
	for (int i = 0; i < n; ++i)
	{
		cout<<x[i]<<" ";
	}
	cout<<endl;
	return 0;
}