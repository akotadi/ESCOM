#include <bits/stdc++.h>

using namespace std;

typedef unsigned long long int lli;

lli int_mo2(lli a, lli m){
	return ((a%m+m)%m);
}

bool subString(string s, string p){
	
	lli h = 0;
	lli m = 512927377;
	lli x = 31;

	for (int i = 0; i < p.size(); ++i)
	{
		h = int_mo2((h*x+p[i]),m);
	}
	lli subHash = 0;
	for (int i = 0; i < p.size(); ++i)
	{
		subHash = int_mo2((subHash*x+s[i]),m);
	}
	if (subHash == h)
	{
		return true;
	}
	for (int i = p.size(); i < s.size(); ++i)
	{
		subHash = (subHash*x+s[i]-s[i-p.size()]*pow(x,p.size()-1));
		if (subHash == h)
		{
			return true;
		}
	}
	return false;
}

int main(int argc, char const *argv[])
{
	string s;
	for (int i = 0; i < p.size(); ++i)
	{
		/* code */
	}
	return 0;
}