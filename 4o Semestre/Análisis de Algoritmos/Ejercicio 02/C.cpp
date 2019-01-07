// Se tomarán en cuenta como operaciones la comparación en arreglo A, asignaciones mayor1 y mayor2

func Producto2Mayores(A,n){
	if (A[1] > A[2])									->	1 (comparación)
	{
		mayor1 = A[1];									->	1 (asignación)
		mayor2 = A[2];									->	1 (asignación)
	}
	else{
		mayor2 = A[1];									->	1 (asignación)
		mayor1 = A[2];									->	1 (asignación)
	}
	// Podemos observar que la primera comparación siempre va a realizar, y dado que el número de operaciones 
	// en ambos caminos es el mismo, tomaremos como valor 3 operaciones de este bloque de instrucciones
	i = 3;												
	while(i<=n){										->	n-2 (comparaciones)
		if (A[i]>mayor1)								->	1 (comparación)
		{
			mayor2 = mayor1;							->	1 (asignación)
			mayor1 = A[i];								->	1 (asignación)
			// En este camino encontramos 3 operaciones en total
		}
		else if (A[i]>mayor2)							->	1 (comparación)
		{
			mayor2 = A[i];								-> 	1 (asignación)
			// Por este, también encontramos 3 operaciones
		}
		// Si no se entra a ningún if, únicamente se realizan 2 operaciones
		i++;
	}
	return = mayor1 * mayor2;
}
fin

-------------------------------

// Se tomarán en cuenta como operaciones la comparación arreglo a, asignaciones tanto de temp como de arreglo a

func OrdenamientoIntercambio(a,n){
	for (int i = 0; i < n-1; ++i)				->	n-1 (número de ejecuciones del ciclo)
	{
		for (int j = i; j < n; ++j)				->	n-1-i (número de ejecuciones del ciclo)
		{
			if (a[j]<a[i])						->	1 (comparación)
			{
				temp = a[i];					->	1 (asignación)
				a[i] = a[j];					->	1 (asignación)
				a[j] = temp;					->	1 (asignación)
				// Por este camino se tiene un total de 4 operaciones
			}
			// Si no entra al if, únicamente realiza una operación de comparación
		}
	}
}
fin


-------------------------------

// Se tomarán en cuenta como operaciones la operación de módulo a y b

fun MaximoComunDivisor(m,n){
	a = max(n,m);
	b = min(n,m);
	residuo = 1;
	while(residuo > 0){
		residuo = a%b;			->	1 (asignación de módulo)
		a = b;
		b = residuo;
	}
	MaximoComunDivisor = a;
	return MaximoComunDivisor;
}
fin


-------------------------------

// Se tomarán en cuenta como operaciones las comparaciones y asignaciones entre auxiliares y el arreglo

Procedimiento BurbujaOptimizada(A,n){
	cambios = true;
	i = 0;
	while(i<n-1 && cambios){						->	n-1 (comparaciones)
		cambios = false;
		for (int j = 0; j < (n-1)-i; ++j)			->	n-1-i (comparaciones)
		{
			if (A[i]<A[j])							->	1 (comparación)
			{
				aux = A[j];							->	1 (asignación)
				A[j] = A[i];						->	1 (asignación)
				A[i] = aux;							->	1 (asignación)
				cambios = true;
				// Si entra al if, tendremos un total de 4 operaciones
			}
			// Si no se entra al if, tendremos únicamente 1 operación
		}
		i++;
	}
}
fin

-------------------------------

Procedimiento BurbujaSimple(A,n){
	for (int i = 0; i < n-1; ++i)
	{
		for (int j = 0; j < (n-1)-i; ++j)
		{
			if (A[j]>A[j+1])
			{
				aux = A[j];
				A[j] = A[j+1];
				A[j+1] = aux;
			}
		}
	}
}
fin

-------------------------------

Procedimiento Ordena(a,b,c){
	if (a>b)
	{
		if (a>c)
		{
			if (a>c)
			{
				salida(a,b,c);
			}
			else
				salida(a,c,b);
		}
		else
			salida(c,a,b);
	}
	else{
		if (b>c)
		{
			if (a>c)
			{
				salida(b,a,c);
			}
			else
				salida(b,c,a);
		}
		else
			salida(c,b,a);
	}
}
fin

-------------------------------

func Seleccion(A,n)
	Para k=0 hasta n-2 hacer
		p = k
		Para i=k+1 hasta n-1 hacer
			Si A[i]<A[p] entonces
				p = i
			Fin Si
		Fin Para
		temp = A[p]
		A[p] = A[k]
		A[k] = temp
	Fin Para
Fin func

Procedimiento Seleccion(A,n){
	for (int k = 0; k < n-1; ++k)			->	n (comparaciones)
	{
		p = k;
		for (int i = k+1; i < n; ++i)		->	n-k (comparaciones)
		{
			if (A[i] < A[p])
			{
				p = i;
			}
		}
		temp = A[p];
		A[p] = A[k];
		A[k] = temp;
	}
}
fin