#include <bits/stdc++.h>

using namespace std;

typedef long long int lli;

int main(int argc, char const *argv[])
{
	int n;
	scanf ("%d",&n);
	lli partialSum, aux;
	scanf ("%lld",&partialSum);
	lli maxSum = partialSum;
	for (int i = 1; i < n; ++i)
	{
		scanf ("%lld",&aux);
		if (aux > partialSum + aux)
		{
			partialSum = aux;
		}else{
			partialSum += aux;
		}
		if (partialSum > maxSum)
		{
			maxSum = partialSum;
		}
	}
	printf("%lld\n", maxSum);
	return 0;
}
