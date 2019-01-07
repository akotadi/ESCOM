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
 
   return 0;
}

int main(int argc, char const *argv[])
{
	int n;
	cin>>n;
	int m[n];
	for (int i = 0; i < n; ++i)
	{
		cin>>m[i];
	}
	int x,y;
	cin>>x;
	for (int i = 0; i < x; ++i)
	{
		cin>>y;
		if (m[0]==y)
		{
			cout<<"1 ";
		}
		else if (m[n-1]==y)
		{
			cout<<n<<" ";
		}
		else{
			cout<<Busqueda(m,y,0,n-1)<<" ";
		}
	}
	cout<<endl;
	return 0;
}