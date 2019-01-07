#include <stdio.h>

int count;

void Exercise6(int n){
	count = 0;
	for (int i = 10; i < n*5; i*=2)
	{
		count++;
	}
	printf("Con n = %i, impresiones: %i\n",n,count);
}

void Exercise7(int n){
	count = 0;
	for (int j = n; j > 1; j/=2)
	{
		if (j<(n/2))
		{
			for (int i = 0; i < n; i+=2)
			{
				count++;
			}
		}
	}
	printf("Con n = %i, impresiones: %i\n",n,count);
}

void Exercise8(int n){
	int i = n;
	while(i>=0){
		for (int j = n; i < j; i-=2,j/=2)
		{
			count++;
		}
	}
	printf("Con n = %i, impresiones: %i\n",n,count);
}

int main(int argc, char const *argv[])
{
	int n[] = {10,100,1000,5000,100000}, count = 0;
	printf("Imprimiendo Ejercicio 6\n");
	for (int j = 0; j < 5; ++j)
	{
		Exercise6(n[j]);
	}
	printf("Imprimiendo Ejercicio 7\n");
	for (int j = 0; j < 5; ++j)
	{
		Exercise7(n[j]);
	}
	printf("Imprimiendo Ejercicio 8\n");
	for (int j = 0; j < 5; ++j)
	{
		Exercise8(n[j]);
	}
	return 0;
}