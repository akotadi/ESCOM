#include<unistd.h>
#include<stdio.h>
#include<stdlib.h>

int main(void)
{
	int id_proc, i=10, j=20, k=30;
	
	if((id_proc = fork()) == 0)
	{
		j+= k;
		printf("Valor: %i\n", j);
	
		if((id_proc = fork())== 0)
		{
			i*=j;
			printf("Valor: %i\n", i);
			if((id_proc = fork()) == 0)
			{
				k-= i;
				printf("Valor: %i\n",k);
			}
			
			i*= k;
			printf("Valor: %i\n", i);
			if((id_proc = fork()) == 0)
			{
				j+= i;
				printf("Valor: %i\n",j);
			}
		}
		
		k-= j;
		printf("Valor: %i\n", k);	
	}
	printf("Valor: %i,%i,%i\n",i,j,k );
	j*= k;
	if((id_proc = fork())== 0 )
	{
		i-= j;
		printf("Valor: %i\n",i);
		exit(0);
	}
	if((id_proc = fork())== 0)
	{
		k+= i;
		printf("Valor: %i\n", k);
	}
	if((id_proc = fork())== 0)
	{
		j*= k;
		printf("Valor: %i\n",k);
	}

	printf("Valor: %i,%i,%i\n", i,j,k);


}
