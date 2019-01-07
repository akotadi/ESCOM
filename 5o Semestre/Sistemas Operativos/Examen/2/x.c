// EXAMEN X

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
		
		id_proc = fork();
		if(id_proc == 0) // Proceso que sigue
		{
			for(i = 0; i < 3; i++) // Creo 3 hijos horizontales
			{
				id_proc = fork();
				if(id_proc == 0)
				{
					printf("Soy el proceso hijo\n");
					// Creo un hijo vertical
					if(i == 0)
					{
						id_proc = fork();
						if(id_proc == 0)
						{
							printf("Soy el proceso hijo\n");
						}
					}
					// OMITO i == 1 porque no tiene hijos
					// Creo un hijo vertical
					if(i == 2)
					{
						id_proc = fork();
						if(id_proc == 0)
						{
							printf("Soy el proceso hijo\n");
						}
					}

				}
			} // Fin for de 3 hijos horizontales
			exit();
		}
	}
	else
	{
		printf("Soy el proceso Padre\n");
	}
}