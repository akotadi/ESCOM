#include <iostream>
#include <string.h>
#include <math.h>

using namespace std;

typedef long long int lli;

int main(int argc, char const *argv[])
{
	string s1,s2;
	cin>>s1>>s2;
	lli n=0;
	for (int i = 0; i < s1.size(); ++i)
	{
		n+=((lli)s1[i]-48)*pow(10,s1.size()-1-i);
	}
	lli m=0;
	for (int i = 0; i < s1.size(); ++i)
	{
		if (s1[i]=='6')
		{
			s1[i]='5';
			m+=((lli)s1[i]-48)*pow(10,s1.size()-1-i);
		}
		else if (s1[i]=='5')
		{
			s1[i]='6';
			m+=((lli)s1[i]-48)*pow(10,s1.size()-1-i);
		}
		else
			m+=((lli)s1[i]-48)*pow(10,s1.size()-1-i);
	}
	lli a=0;
	for (int i = 0; i < s2.size(); ++i)
	{
		a+=((lli)s2[i]-48)*pow(10,s2.size()-1-i);
	}
	lli b=0;
	for (int i = 0; i < s2.size(); ++i)
	{
		if (s2[i]=='6')
		{
			s2[i]='5';
			b+=((lli)s2[i]-48)*pow(10,s2.size()-1-i);
		}
		else if (s2[i]=='5')
		{
			s2[i]='6';
			b+=((lli)s2[i]-48)*pow(10,s2.size()-1-i);
		}
		else
			b+=((lli)s2[i]-48)*pow(10,s2.size()-1-i);
	}
	cout<<n+a<<" "<<m+b<<endl;
	return 0;
}	