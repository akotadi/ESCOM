// 04 de Octubre 2018
// Para generar muchos procesos HIJO
#include<unistd.h>
#include<stdio.h>
#include<stdlib.h>
#include <sys/types.h>
int i = 10;

void hijosVertical(int n) 
{
	if (n == 0)
		exit(0);
	printf("proceso padre: %d\n", getppid());
	printf("proceso actual %d\n", getpid());
	int id_proc = fork();
	if (id_proc == 0) 
	{
		printf("proceso hijo %d\n", getpid());
		// nodosEnColados(--n);
		n--;
		exit(0);
	}
	wait(0);
}

void hijosHorizontal(int n) {
	printf("proceso actual %d\n", getpid());
	int id_proc=0;
	for (int i = 0; i < n; i++) 
	{
		if ((id_proc = fork()) == 0) 
		{
			printf("proceso padre: %d\n", getppid());
			printf("proceso hijo: %d\n", getpid());
			exit(0);
		}
		wait(0);
	}
}
int main(void)
{
	int i, j, k, m, l;
	int id_proc;
	for(i = 0; i<5; i++) // CREAR 5 PROCESOS HORIZONTALES
	{
		id_proc = fork();
		if(id_proc == 0)
		{
			printf("Soy el proceso hijo %i\n", i);
			if(i == 0)	// PARA EL PRIMER HIJO
			{
				for(j = 0; j<3; j++) // CREAR 3 PROCESOS HORIZONTALES
				{
					id_proc = fork();
					if(id_proc == 0)
					{
						printf("Soy el proceso hijo\n");
						if(j == 0) // PARA EL PRIMER HIJO
						{
							for(k = 0; k<2; k++) // CREA 2 PROCESOS VERTICALES
							{
								id_proc = fork();
								if(id_proc == 0)
								{
									printf("Soy el proceso hijo %i\n", i);
									if(k == 1) // EN EL ULTIMO PROCESO
									{
										for(l = 0; l<3; l++) // CREA  PROCESOS HORIZONTALES
										{
											id_proc = fork();
											if(id_proc == 0)
											{
												if(l == 1)
												{

													id_proc = fork();
													if(id_proc == 0)
													{
														hijosHorizontal(3);
													}
												}
											}
										}
										exit(0);
									}
								} 
								else
								{
									break; // Sale del ciclo 
								}	
							}
							exit(0);
						} // Fin if - primer hijo
					}
					exit(0);
				}
				exit(0);
			} // Fin if - Hijo 0
			
			if(i == 1) // PARA EL SEGUNDO HIJO
			{
				id_proc = fork(); // Crear un hijo
				if(id_proc == 0) 
				{
					for(j = 0; j<2; j++) // Crear dos hijos horizontales
					{
						id_proc = fork();
						if(id_proc == 0)
						{
							if(j == 0) // Para el primer hijo
							{
								id_proc = fork(); 
								if(id_proc == 0) // Crear un hijo
								{
									hijosHorizontal(2);
								}
							}
							if(j == 1) // Para el segundo hijo
							{
								id_proc = fork(); 
								if(id_proc == 0) // Crear un hijo
								{
									hijosHorizontal(2);
								}
							}
						}
					} 
					exit(0);
				}
			}
			if(j == 2) // PARA EL TERCER HIJO
			{
				exit(0); // No tiene hijos
			}
			if(j == 3) // PARA EL CUARTO HIJO
			{
				id_proc = fork();
				if(id_proc == 0) // Crear un hijo
				{
					for(k = 0; k<2; k++) // Crear dos hijos
					{
						id_proc = fork();
						if(id_proc == 0)
						{
							if(k == 0) // Para el hijo 1
							{
								exit(0);
							}
							if (k == 1) // Para el hijo 2
							{
								id_proc = fork();
								if(id_proc == 0)
								{
									hijosHorizontal(2);
								}
							}
						}
					}
					exit(0);
				}
			}
			if(j == 4)
			{
				for(k = 0; k<3; k++) // Crear 3 hijos
				{
					id_proc = fork();
					if(id_proc == 0)
					{
						if(k == 0) // Para el hijo 1
						{
							exit(0);
						}
						if(k == 1) // Para el hijo 2
						{
							id_proc = fork();
							if(id_proc == 0) // Crear un hijo
							{
								hijosHorizontal(3);
							}
						}
						if(k == 2) // Para el hijo 3
						{
							for(l = 0; l<2; l++) // Crea 2 hijos verticales
							{
								id_proc = fork();
								if(id_proc == 0)
								{
									if(l == 1)
									{
										for(m = 0; m<3; m++)
										{
											id_proc = fork();
											if(id_proc == 0)
											{
												if(m == 0 || m == 2)
												{
													hijosHorizontal(2);
												}
												if(m == 1)
												{
													exit(0);
												}
											}
										}
									}
								}
								else
								{
									break;
								}
							}
							
						}
					}
				}
				exit(0);
			}
		}  
	}	// Fin 5 procesos horizontales
	exit(0);
}

