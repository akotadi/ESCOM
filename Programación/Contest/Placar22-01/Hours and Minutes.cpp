#include <bits/stdc++.h>

using namespace std;

int main(int argc, char const *argv[])
{
	int n;
	while(scanf("%d", &n) != EOF){
		if (n % 6 == 0 && n <= 180)
		{
			cout << "Y" << endl;
		}else{
			cout << "N" << endl;
		}
	}
	return 0;
}