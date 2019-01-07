#include <stdio.h>
#include <windows.h>
#include <time.h>
#include "TADColaDin.c"

#define TIEMPO_BASE 100

int main()
{
	char nombre[45];
	int cant,tiempo_cliente,estado=1,checar=0,tiempo_cont=0,clientes_cont=0,r;
	int* tiempo_cajeras;
	cola* cola_cajeras;
	elemento e;

	srand(time(NULL));//Se inicia la semilla de random que se usara despues

	//Se pide informacion basica
	printf("Nombre del Supermercado: \n");
	scanf("%s",&nombre);

	printf("Numero de cajeras: \n");
	scanf("%i",cant);
	
	tiempo_cajeras=(int*)malloc(cant*sizeof(int));//Se crea un arreglo para almacenar el tiempo que le toma a cada cajera atender
	cola_cajeras=(cola*)malloc(cant*sizeof(cola));//Se crea un arreglo con las colas necesarias para las cajeras

printf("Numero de cajeras: \n");
	for (int i = 0; i < cant; ++i){
		Initialize(&cola_cajeras[i]);
		printf("Tiempo para cajera No.%i :\n",i+1);//Se piden los tiempos correspondientes
		scanf("%i",&tiempo_cajeras[i]);
	}

	printf("Tiempo para clientes:\n");//Se introduce el tiempo de llegada de los clientes
	scanf("%i",&tiempo_cliente);

	
	while(estado){
		Sleep(TIEMPO_BASE);//Se espera el tiempo base
		tiempo_cont++;//Se incrementa el contador para saber cuanto tiempo ha pasado

		for (int i = 0; i < cant; ++i){
			if (tiempo_cajeras[i] % (TIEMPO_BASE * tiempo_cont) == 0){//Se va recorriendo cada una de las cajas para ver si ya estan en tiempo de desencolar
				if (!Empty(&cola_cajeras[i])){//Si no esta vacia, entonces desencola
					e=Dequeue(&cola_cajeras[i]);
					printf("Se atendio al cliente %i por la cajera %i \n",e.id,i+1);
				}
			}
		}

		if(tiempo_cliente % (TIEMPO_BASE * tiempo_cont) == 0){//Si es tiempo de que llegue un cliente, se le asigna una caja
			clientes_cont++;//Se incrementa el contador de clientes
			e.id=clientes_cont;
			r=(rand() % cant) + 1;
			Queue(&cola_cajeras[r],e);//Se introduce el cliente en alguna cola asignada al azar
			printf("Se metio al cliente %i en la cajera %i \n",e.id,r);
		}

		for (int i = 0; i < cant; ++i){//Loop usado para checar cada una e las cajas
			if(Empty(&cola_cajeras[i])){//Si todas las cajas estan vacias, etnonces se puede cerrar el super
				checar++;
			}
		}
		if(checar == cant && clientes_cont > 10){//Ya no hay clientes en ninguna caja y ya pasaron mas de 100 clientes
			printf("El supermercado ha cerrado \n");
			estado=0;//El supermercado cierra
		}
	}
	
	for (int i = 0; i < cant; ++i)
	{
		Destroy(&cola_cajeras[i]);
	}
	
	return 0;
}
