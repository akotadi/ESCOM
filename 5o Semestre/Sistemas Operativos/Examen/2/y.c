// EXAMEN Y

#include<unistd.h>
#include<stdio.h>
#include<stdlib.h>

int main(void)
{
	int id_proc;
	id_proc = fork();

	if(id_proc == 0) // Es el primer proceso
	{
		printf("Soy el proceso hijo\n");
		for(i = 0; i < 3; i++) // Creo tres hijos horizontales
		{
			if(i == 0) 
			{
				printf("Soy un proceso hijo\n");
			}
			if(i == 1) // Soy el segundo hijo creado horizontalmente
			{
				for(j = 0; j < 3; j++) // Creo 3 hijos horizontales
				{
					id_proc = fork();
					if(id_proc == 0)
					{
						printf("Soy el proceso hijo\n");
					}
				} // Fin for de 3 hijos horizontales
				exit();
			}
			if(i == 2)
			{
				printf("Soy el proceso hijo\n");
			}
		}	// Fin for de los 3 hijos horizontales
		exit();
	}
	else
	{
		printf("Soy el proceso Padre\n");
	}
}