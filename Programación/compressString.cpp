#include <bits/stdc++.h>

using namespace std;

int main(int argc, char const *argv[])
{
	const int maxn = 300000;
    
    char strOriginal[maxn], strCopy[maxn], strCompress[maxn];
    int k;
    
    cin >> strOriginal >> k;
    
    for (int i = 0; i < strlen(strOriginal); ++i)
	{
		strOriginal[i] = ::toupper(strOriginal[i]);
	}
	
	strcpy(strCopy, strOriginal);
	
	char *token = strtok(strCopy, "-"); 
	
	while(token != NULL){
		strcat(strCompress, token);
		token = strtok(NULL, "-");
	}
	
	int nDivisions = strlen(strCompress) % k;

	cout << strCompress << endl << nDivisions << endl;

	for (int i = 0; i < nDivisions; ++i)
	{
		strCopy[i] = strCompress[i];
	}
	
	for (int i = nDivisions, j = nDivisions, counter = 0; i < strlen(strCompress); j++)
	{
		if (j == nDivisions && nDivisions != 0)
		{
			strCopy[j] = '-';
		}else{
			if(counter++ == k){
				counter = 0;
				strCopy[j] = '-';
			}else{
				strCopy[j] = strCompress[i++];
			}
		}
	}

	cout << strCopy << endl;
	
	// string aux(strCopy);

	// cout << aux << endl;

	return 0;
}