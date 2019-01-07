// Función de complejidad temporal y espacial
// Operaciones asignación, aritméticas, condicionales y saltos implícitos

for(i = 1;i<n;i++){				->	1 (asignación) + n (comparaciones) + 2(n-1) (asignaciones)
	for(j=0;j<n-1;j++){			->	(n-1) ( 1 (asignación) + n (comparaciones) + 2(n-1) (asignaciones) )
		temp = A[j];			->	(n-1)² ( 1 (asignación) )
		A[j] = A[j+1];			->	(n-1)² ( 1 (asignación) + 1 (adición) )
		A[j+1] = temp;			->	(n-1)² ( 1 (asignación) + 1 (adición) )
	}							->	n-1 ( n-1 (saltos implícitos) + 1 (salto en falso) )
}								->	n-1 (saltos implícitos) + 1 (salto en falso)

ftemp(n) = 9n²-12n+5

Dado que se utiliza una variable auxiliar para guardar los elementos a intercambiar, además de las dos variables para los ciclos y el arreglo que presuntamente tiene tamaño n, la fórmula espacial es:
fesp(n) = n+3

------------------------

polinomio = 0;									->	1 (asignación)
for (int i = 0; i <= n; ++i)					->	1 (asignación) + n+2 (comparaciones) + 2(n+1) (asignaciones)
{
	polinomio = polinomio * z + A[n-i];			-> n+1 ( 1 (producto) + 1 (adición) + 1 (sustracción) + 1 (asignación) )
}												-> n+1 (saltos implícitos) + 1 (salto en falso)

ftemp(n) = 8n+12

Tomaremos en cuenta un arreglo presuntamente de tamaño n, además de una variable auxiliar para ir llevan el resultado, una variable multiplicativa "z" y la variable utilizada para el ciclo; por tanto la fórmula espacial es:
fesp(n) = n+3

------------------------

for i = 1 to n do 									->	1 (asignación) + n+1 (comparaciones) + 2n (asignaciones)
{
	for j = 1 to n do{								->	n ( 1 (asignación) + n+1 (comparaciones) + 2n (asignaciones) )
		c[i,j] = 0;									->	n² ( 1 (asignación) )
		for k = 1 to n do{							->	n² ( 1 (asignación) + n+1 (comparaciones) + 2n (asignaciones) )
			c[i,j] = c[i,j] + A[i,k] * B[k,j];		->	n³ ( 1 (producto) + 1 (adición) + 1 (asignación) )
		}											->	n² ( n (saltos implícitos) + 1 (saltos en falso) )
	}												->	n ( n (saltos implícitos) + 1 (salto en falso) )
} 													-> n (saltos implícitos) + 1 (salto en falso)

ftemp(n) = 7n³+8n²+7n+3

Se utilizarán tres variables para los ciclos, además, se tienen tres arreglos bidimensionales, presuntamente de tamaño n*n, por tanto, la fórmula espacial es:
fesp(n) = 3n²+3

------------------------

anterior = 1;							->	1 (asignación)
actual = 1;								->	1 (asignación)
while(n>2){								->	n-1 (comparaciones)
	aux = anterior + actual;			->	(n-2) ( 1 (asignación) + 1 (adición) )
	anterior = actual;					->	(n-2) ( 1 (asignación) )
	actual = aux;						->	(n-2) ( 1 (asignación) )
	n = n-1;							->	(n-2) ( 1 (asignación) + 1 (sustracción) )
}										->	n-2 (saltos implícitos) + 1 (salto en falso)

ftemp(n) = 8n-12
fesp(n) = 3

------------------------

for (int i = n-1, j = 0; i >= 0; --i, j++)		->	1 (asignación) + 1(asignación) + n+1 (comparaciones) + 2n (asignaciones) + 2n (asignaciones)
{
	s2[j] = s[i];								->	n ( 1 (asignaciones) )
}												->	n (saltos implícitos) + 1 (salto en falso)
for (int i = 0; i < n; ++i)						->	1 (asignación) + n+1 (comparaciones) + 2n (asignaciones)
{
	s[i] = s2[i];								->	n ( 1 (asignación) )
}												->	n (saltos implícitos) + 1 (salto en falso)

ftemp(n) = 12n+7

Se utiliza una misma variable para ambos ciclos, pero en el primero se tiene otra variable adicional, además, tenemos dos arreglos que asumiremos tienen de tamaño n, así, la fórmula espacial es la siguiente:
fesp(n) = 2n+2