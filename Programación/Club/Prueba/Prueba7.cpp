#include <iostream>

using namespace std;

int Busqueda(int* m,int n,int a,int b){
	if (b >= a)
   {
        int mid = a + (b - a)/2;

        if (m[mid] == n)  return mid+1;
 
        if (m[mid] > n) return Busqueda(m, n,a, mid-1);
 
        return Busqueda(m, n,mid+1, b);
   }
 
   return -1;
}

int* Ordenar(int* x, int n, int a, int b){
	if (n<x[a])
	{
		for (int i = b; i > a; --i)
		{
			x[i]=x[i-1];
		}
		x[a]=n;
		return x;
	}
	else if (a==b)
	{
		x[a]=n;
		return x;
	}
	else
		return Ordenar(x,n,a+1,b);
}

int main(int argc, char const *argv[])
{
	int n;
	cin>>n;
	if (n!=0)
	{
		int* x;
		x = new int[n];
		for (int i = 0; i < n; ++i)
		{
			x[i]=0;
		}
		cin>>x[0];
		int a;
		if (n>1)
		{
			for (int i = 1; i < n; ++i)
			{
				cin>>a;
				x=Ordenar(x,a,0,i);
			}
		}
		int m,b;
		cin>>m;
		for (int i = 0; i < m; ++i)
		{
			cin>>a;
			if(Busqueda(x,a,0,n)==-1)
				cout<<"NO"<<endl;
			else
				cout<<"SI"<<endl;
		}
	}
	else{
		int m;
		cin>>m;
		int a;
		for (int i = 0; i < m; ++i)
		{
			cin>>a;
			cout<<"NO"<<endl;
		}
	}
	return 0;
}	