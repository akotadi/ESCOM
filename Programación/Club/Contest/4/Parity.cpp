#include <bits/stdc++.h>

using namespace std;

int main()
{
	int b, k;
	cin >> k >> b;
	bool flag = true;
	vector<int> a(b,0);
	if (k & 1)
	{
		flag = false;
	}
	int counter = 0;
	for(int i = 0; i < b; i++){
		cin >> a[i];
		if (a[i] & 1)
		{
			counter++;
		}
	}
	if (flag)
	{
		if (a[b-1] & 1)
		{
			cout << "odd" << endl;
		}else{
			cout << "even" << endl;
		}
	}else{
		if (counter & 1)
		{
			cout << "odd" << endl;
		}else{
			cout << "even" << endl;
		}
	}
	return 0;
}