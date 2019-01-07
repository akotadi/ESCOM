#include <bits/stdc++.h>

using namespace std;

int main(int argc, char const *argv[])
{
	int myints[] = {-1,0,1,2,3};
	vector<int> vNumbers (myints, myints+5); 

	int d = 2;

	vector<int> result(A.size());

	for (int i = 0; i < vNumbers.size(); ++i)
	{
		int index = vNumbers[i], counter = 0;
		while(index != -1){
			index = vNumbers[index];
			counter++;
		}
		if (counter >= d)
		{
			result[i] = counter - d;
		}else{
			result[i] = -1;
		}
	}

	return 0;
}