#include <bits/stdc++.h>

using namespace std;

int n;

int main (){
	cin >> n;
	int nOdd = 0, nRight = 0, nLeft = 0;
	for(int i = 0; i < n; i++){
		int a1, a2;
		cin >> a1 >> a2;
		if ((a1 & 1) != (a2 & 1))
		{
			if (a1 & 1)
			{
				nLeft++;
			}else if (a2 & 1)
			{
				nRight++;
			}
		}else if ((a1 & 1) == 1 && (a2 & 1) == 1)
		{
			nOdd++;
			nOdd = (nOdd & 1);
		}
	}
	int aux = 0;
	bool flag = false;
	if (nLeft != nRight)
	{
		if ((nLeft & 1) == 1 && (nRight & 1) == 1)
		{
			flag = true;
		}
		aux = max(nLeft, nRight) - min(nLeft, nRight);
		if (aux & 1)
		{
			cout << -1 << endl;
		}else{
			if (nOdd)
			{
				if (flag)
				{
					cout << 0 << endl;
				}else{
					cout << 1 << endl;
				}
			}else{
				if (flag)
				{
					cout << 1 << endl;
				}else{
					cout << 0 << endl;
				}
			}
		}
	}else{
		if (nOdd && (nLeft & 1))
		{
			cout << 0 << endl;
		}else if(nOdd){
			if (nLeft > 0)
			{
				cout << 1 << endl;
			}else{
				cout << -1 << endl;
			}
		}else if(nLeft & 1){
			cout << 1 << endl;
		}else{
			cout << 0 << endl;
		}
	}
	return 0;
}
