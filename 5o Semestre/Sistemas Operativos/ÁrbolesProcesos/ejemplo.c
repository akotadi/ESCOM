#include<unistd.h>
#include<stdio.h>
#include<stdlib.h>

int main(void)
{
	int id_proc, x=100, y=10, z=5;
	
	if((id_proc = fork()) == 0) // Línea 1 - Divide en dos hojas
/*
	  o Linea 1
	 / \
	✖   ✖
*/
		// Entra el hijo derecho
	{
		x+= y;
		printf("Valor x: %i\n", x); // Imprime una vez el único hijo derecho
	
		if((id_proc = fork())== 0) // Línea 2 - Divide en dos hojas el hijo más a la derecha hasta el momento
/*
	  o
	 / \
	o   o Linea 2
	   / \
	  ✖   ✖
*/
			// Entra el hijo más a la derecha
		{
			z+=x;
			printf("Valor z: %i\n", z); // Imprime una vez el hijo más a la derecha

			if((id_proc = fork()) == 0) // Línea 3 - Divide en dos hojas el hijo más a la derecha hasta el momento
/*
	  o
	 / \
	o   o
	   / \
	  o   o Linea 3
	  	 / \
	  	✖   ✖
*/
				// Entra el hijo más a la derecha
			{
				y*= z;
				printf("Valor y: %i\n",y); // Imprime una vez el hijo más a la derecha
			}
			
			if((id_proc = fork()) == 0) // Línea 4 - Divide en cuatro hojas las dos hojas generadas por la línea 3
/*
	  o
	 / \
	o   o
	   / \
	  o   o
	  	 / \
	  	o   o LInea 4
	   / \ / \
	  ✖  ✖ ✖  ✖
*/
				// Entran las hojas derechas únicamente
			{
				z-= y;
				printf("Valor z: %i\n",z); // Imprime una vez las hojas derechas de la línea 4
			}
			
			if((id_proc = fork()) == 0) // Línea 5 - Divide en ocho hojas las cuatro hojas generadas por la línea 4
/*
	  o
	 / \
	o   o
	   / \
	  o   o
	  	 / \
	  	o   o
	   / \ / \
	  o  o o  o Linea 5
	 /\ /\ /\ /\
	✖ ✖✖ ✖✖ ✖✖ ✖
*/
				// Entran las hojas derechas únicamente
			{
				y*= x;
				printf("Valor y: %i\n",y); // Imprime una vez las hojas derechas de la línea 5
			}
		}

		x*= z;
		printf("Valor x: %i\n", x);	// Imprime una vez todas las hojas del hijo derecho generado en la línea 1

		if((id_proc = fork())== 0 ) // Línea 6 - Divide en dos hojas todas las hojas a partir del hijo derecho de la Línea 1
/*
	   o   						Línea 1
	 /    \
	o      o 					Línea 2
         /    \
        o       o     			Línea 3
       / \   /        \
	  ✖   ✖ o          o  		Línea 4
 	       / \        /  \
 	  	  o   o      o    o 	Línea 5
	     / \ / \    / \  / \
	    o  o o  o  o  o  o  o
	   /\ /\ /\ /\ /\ /\ /\ /\
	  ✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖
*/
			// Entran las hojas derechas de la línea 6
		{
			z-= x;
			printf("Valor z: %i\n",z); // Imprime una vez las hojas derechas de la línea 6
		}
	}

	if((id_proc = fork())== 0) // Línea 7 - Divide en dos hojas todas las hojas generadas por el código anterior
/*
	   	  o
	 /    		\
	o       	  o
   / \   /    				\
  ✖  ✖  o       				o
       / \      	/        			 \
	  o  o 		  o 					  o
	 /\ /\	 /		  \				 /		   \
	✖ ✖✖ ✖	o          o            o           o
 	       / \        /  \		  /   \       /   \
 	  	  o   o      o    o 	 o     o     o     o
	     / \ / \    / \  / \    / \   / \   / \   / \
	    o  o o  o  o  o  o  o   o o   o o   o o   o o
	   /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\
	  ✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖
*/
		// Entran las hojas derechas recién generadas
	{
		y+= x;
		printf("Valor y: %i\n", y); // Imprime una vez las hojas derechas de la línea 7
	}

	if((id_proc = fork())== 0) // Línea 8 - Divide en dos hojas todas las hojas de la línea 7
/*
	   	  		    o
	/    						   \
   o       	  						o
  /\        /    											   \
  o o      o       												o
 /\/\     / \      						  /        			 						   \
✖ ✖✖ ✖	 o   o 		  					 o 					 							o
	    /\   /\	 			  /		  				 \				  		 /		   			   \
	   o o   o o			 o          			  o           	 		o           			o
 	  /\ /\ /\ /\      	 /		  \				 /		   \ 		   /		 \			   /		 \
	  ✖ ✖✖ ✖✖ ✖✖ ✖		o          o            o           o 		  o          o            o           o
			 	       / \        /  \		  /   \       /   \      / \        /  \	    /   \       /   \
			 	  	  o   o      o    o 	 o     o     o     o    o   o      o    o 	   o     o     o     o
				     / \ / \    / \  / \    / \   / \   / \   / \  / \ / \    / \  / \    / \   / \   / \   / \
				    o  o o  o  o  o  o  o   o o   o o   o o   o o  o o   o o   o o   o o   o o   o o   o o   o o
				   /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\/\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\ /\
				  ✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖✖ ✖
*/
		// Entran las hojas derechas recién generadas
	{
		x*= y;
		printf("Valor x: %i\n",x); // Imprime una vez las hojas derechas de la línea 8
	}

	z-=x;
	printf("Valor z: %i\n",z); // Imprime todas las hojas generadas hasta el momento

	if((id_proc = fork())== 0) // Línea 9 - Divide en dos hojas todas las hojas de la línea 8
		// Entran las hojas derechas recién generadas
	{
		y*= z;
		printf("Valor y: %i\n",y); // Imprime una vez las hojas derechas de la línea 9
		exit(0); // Elimina todas las hojas derechas generadas por la línea 9
	}

	if((id_proc = fork())== 0) // Línea 10 - Divide en dos hojas todas las hojas de la línea 9
		// Entran las hojas derechas recién generadas
	{
		if((id_proc = fork())== 0) // Línea 11 - Divide en dos hojas todas las hojas de la línea 10
			// Entran las hojas derechas recién generadas
		{
			y-= y;
			printf("Valor y: %i\n",y); // Imprime una vez las hojas derechas de la línea 11
			exit(0); // Elimina las hojas derechas generadas por la línea 11
		}
		exit(0); // Elimina las hojas generadas por la línea 10
	}

	// printf("Valor: %i,%i,%i\n", x,y,z); // Imprime una vez todas las hojas del árbol
	
}
