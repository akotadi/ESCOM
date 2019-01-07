



func Producto2Mayores(A,n){
	if (A[1] > A[2])
	{
		mayor1 = A[1];				
		mayor2 = A[2];				
	}
	else{
		mayor2 = A[1];				
		mayor1 = A[2];				
	}
	i = 3;												
	while(i<=n){	
		if (A[i]>mayor1)						
		{
			mayor2 = mayor1;		
			mayor1 = A[i];			
		}
		else if (A[i]>mayor2)					
		{
			mayor2 = A[i];			
		}
		i++;
	}
	return = mayor1 * mayor2;
}
fin

-------------------------------


func OrdenamientoIntercambio(a,n){
	for (int i = 0; i < n-1; ++i)	
	{
		for (int j = i; j < n; ++j)		
		{
			if (a[j]<a[i])				
			{
				temp = a[i];
				a[i] = a[j];
				a[j] = temp;
			}
		}
	}
}
fin


-------------------------------


fun MaximoComunDivisor(m,n){
	a = max(n,m);
	b = min(n,m);
	residuo = 1;
	while(residuo > 0){
		residuo = a%b;	
		a = b;
		b = residuo;
	}
	MaximoComunDivisor = a;
	return MaximoComunDivisor;
}
fin


-------------------------------


Procedimiento BurbujaOptimizada(A,n){
	cambios = true;
	i = 0;
	while(i<n-1 && cambios){	
		cambios = false;
		for (int j = 0; j < (n-1)-i; ++j)
		{
			if (A[i]<A[j])					
			{
				aux = A[j];		
				A[j] = A[i];	
				A[i] = aux;		
				cambios = true;
			}
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

Procedimiento Seleccion(A,n)
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
Fin Procedimiento