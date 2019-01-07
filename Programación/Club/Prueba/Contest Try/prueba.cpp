#include <iostream>
#include <math.h>
#include <iomanip>

using namespace std;

int main(int argc, char const *argv[])
{
	int a,b,c;
	cin>>a>>b>>c;
	int x=pow(b,2)-(4*a*c);
	if (x==0)
	{
		cout<<setprecision(2)<<(float)-b/2<<endl;
	}
	else if (x>0)
	{
		float y=((float)b+pow(x,0.5))/2;
		cout<<y<<endl;
		y=((float)b-pow(x,0.5))/2;
		cout<<setprecision(2)<<y<<endl;
	}
	else
		cout<<"NA";
	return 0;
}