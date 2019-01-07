#include <iostream>

using namespace std;

int main(int argc, char const *argv[])
{
	long long int n;
	cin>>n;
	while(n>60){
		long int m=n%60;
		long int k=m%10;
		m/=10;
		for (int i = 0; i < m; ++i)
		{
			cout<<"L";
		}
		for (int i = 0; i < k; ++i)
		{
			cout<<"I";
		}
		cout<<".";
		n/=60;
	}
	int k=n%10;
	n/=10;
	for (int i = 0; i < n; ++i)
	{
		cout<<"L";
	}
	for (int i = 0; i < k; ++i)
	{
		cout<<"I";
	}	
	cout<<endl;
	return 0;
}