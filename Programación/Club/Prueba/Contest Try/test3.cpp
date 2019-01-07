#include <bits/stdc++.h>

using namespace std;

/*
funcion Producto2Mayores(A,n){
	if (A[1] > A[2])
	{
		mayor1 = A[1];
		mayor2 = A[2];
	}
	else{
		mayor1 = A[2];
		mayor2 = A[1];
	}
	i = 3;
	while(i++ <= n){
		if (A[i] > mayor1)
		{
			mayor2 = mayor1;
			mayor1 = A[i];
		}
		else if (A[i] > mayor2)
		{
			mayor2 = A[i];
		}
	}
	producto = mayor1 * mayor2;
	return producto;
}
*/



int main(int argc, char const *argv[])
{

	int z = 0, ni = 0, i, j, n;
	ni++; // asignación z
	cin>>n;
	for (i = 1, ni++; i <= n; i+=2, ni+=2)
	{
		ni++; // condicional
		for (j = i, ni++; j <= n; ++j, ni+=2)
		{
			ni++; // condicional
			z = z+i;
			ni+=2; // asignación + operación z
			ni++; // salto del for
		}
		ni+=2; // no se cumple
		ni++; // salto del for
	}
	ni+=2; // no se cumple

	cout<<ni<<endl;

	// psein

	/*
	int z = 0, n = 1000, ni = 0, x, y;
	ni++;
	int A[n+1][n+1];
	for (x = 1,ni++; x <= n; ++x,++ni)
	{
		ni++;
		for (y = 1,ni++; y <= n; ++y,++ni)
		{
			ni++;
			z = z + A[x][y];
			ni+=2;
		}
		ni++;
	}
	ni++;
	cout<<ni<<endl<<z<<endl;
	*/
	return 0;
}