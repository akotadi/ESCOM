#include <iostream>

using namespace std;

int main(int argc, char const *argv[])
{
	int x[3]={-1},y[3]={-1},n=0;
	cin>>x[0];
	for (int i = 1; i < 3; ++i)
	{
		cin>>x[i];
		if (x[i]<x[i-1] && i==2 && x[i]<x[i-2])
		{
			n=x[i-2];
			x[i-2]=x[i];
			x[i]=x[i-1];
			x[i-1]=n;
		}
		else if (x[i]<x[i-1])
		{
			n=x[i-1];
			x[i-1]=x[i];
			x[i]=n;
		}
	}
	cin>>y[0];
	for (int i = 0; i < 3; ++i)
	{
		cout<<x[i]<<endl;
	}
	for (int i = 1; i < 3; ++i)
	{
		cin>>y[i];
		if (y[i]<y[i-1] && i==2 && y[i]<y[i-2])
		{
			n=y[i-2];
			y[i-2]=y[i];
			y[i]=y[i-1];
			y[i-1]=n;
		}
		else if (y[i]<y[i-1])
		{
			n=y[i-1];
			y[i-1]=y[i];
			y[i]=n;
		}
	}
	for (int i = 0; i < 3; ++i)
	{
		cout<<y[i]<<endl;
	}
	n=0;
	for (int i = 0; i < 3; ++i)
	{
		if (x[i]>y[i])
		{
			n=1;
			break;
		}
	}
	if (n==1)
	{
		cout<<"0"<<endl;
	}
	else
		cout<<"1"<<endl;

	return 0;
}