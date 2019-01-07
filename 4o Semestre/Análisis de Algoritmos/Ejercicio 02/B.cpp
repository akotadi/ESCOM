// Número de impresiones de "Algoritmos", determinar función y verificar en código
// Verificar para 10, 100, 1000, 5000 y 100000

#include <stdio.h>

int main(int argc, char const *argv[])
{
	int n, count = 0;
	scanf("%i",&n);
	for (int i = 10; i < n*5; i*=2)
	{
		count++;
		printf("\"Algoritmos\"\n");
	}
	printf("%i\n",count);
	return 0;
}

Dado que el ciclo irá aumentando al doble, se considera que este es exponencial. Por tanto, la función principal irá respecto de un logaritmo de base 2, y se considera únicamente la n porque la i está iniciándose en el doble de 5, que es 10. Se le hará función piso a la fórmula debido a que el ciclo nunca llegará a igualarse con el valor de n.
f(n) = floor(log2(n))

------------------------

#include <stdio.h>

int main(int argc, char const *argv[])
{
	int n, count = 0;
	scanf("%i",&n);
	for (int j = n; j > 1; j/=2)
	{
		if (j<(n/2))
		{
			for (int i = 0; i < n; i+=2)
			{
				count++;
				printf("\"Algoritmos\"\n");
			}
		}
	}
	printf("%i\n",count);
	return 0;
}

La primer parte corresponde al ciclo interno, debido a que inicia en 0 y va aumentando de 2 en 2, se considera una función techo respecto a la mitad de n. Por otra parte, el ciclo externo se considera que irá reduciendo a la mitad la n, sin embargo, al tener la restricción de menor a su primera mitad y ser mayor a 1, descontaremos dos valores del resultado inicial que nos arreje la función piso sobre el logaritmo.
f(n) = (ceiling(n/2))(floor(log2(n))-2)

------------------------

#include <stdio.h>

int main(int argc, char const *argv[])
{
	int n, count = 0;
	scanf("%i",&n);
	int i = n;
	while(i>=0){
		for (int j = n; i < j; i-=2,j/=2)
		{
			count++;
			printf("\"Algoritmos\"\n");
		}
	}
	printf("%i\n",count);
	return 0;
}

Es sencillo ver que este ciclo será infinito siempre que la n sea mayor a 0, sin embargo, nunca va a imprimir nada debido a que la condición del ciclo interno nunca se podrá inicializar al ser tanto la i como la j del mismo valor que n.
f(n) = 0