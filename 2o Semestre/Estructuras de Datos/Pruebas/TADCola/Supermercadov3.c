#include <stdio.h>
#include <windows.h>
#include <time.h>
#include "TADColaDin.c"

#define TIEMPO_BASE 1000



void Simulacion(int cant, int t_cliente, int* t_cajeras, int* ejecucion, cola* cola_cajeras){
	int checar,estado=1,t_cont=0,c_cont=0,r;
	elemento e;

	srand(time(NULL));//Se inicia la semilla de random que se usara despues

	while(estado == 1){
		checar=0;
		//Sleep(TIEMPO_BASE);//Se espera el tiempo base
		//Sleep(200);
		t_cont++;//Se incrementa el contador para saber cuanto tiempo ha pasado
		//printf("%i\n",tiempo_cont);

		for (int i = 0; i < cant; ++i){
			if(!Empty(&cola_cajeras[i]))
				ejecucion[i]++;
			if (t_cajeras[i] == ejecucion[i]){//Se va recorriendo cada una de las cajas para ver si ya estan en tiempo de desencolar
				ejecucion[i]=0;
				e=Dequeue(&cola_cajeras[i]);
				printf("Se atendio al cliente %i por la cajera %i \n",e.id,i+1);
			}
		}

		if( t_cont % t_cliente  == 0){//Si es tiempo de que llegue un cliente, se le asigna una caja
			c_cont++;//Se incrementa el contador de clientes
			e.id=c_cont;
			r=(rand() % cant);
			//printf("random: %i \n",r);
			if(r==0){//Se introduce el cliente en alguna cola asignada al azar
				Queue(&cola_cajeras[cant-1],e);
				printf("Se metio al cliente %i en la cajera %i \n",e.id,cant);
			}
			else{
				Queue(&cola_cajeras[r-1],e);
				printf("Se metio al cliente %i en la cajera %i \n",e.id,r);
			}
		}

		for (int j = 0; j < cant; ++j){//Loop usado para checar cada una e las cajas
			if(Empty(&cola_cajeras[j])){//Si todas las cajas estan vacias, etnonces se puede cerrar el super
				checar++;
			}
		}

		if(checar == cant && c_cont >= 100){//Ya no hay clientes en ninguna caja y ya pasaron mas de 100 clientes
			printf("El supermercado ha cerrado \n");
			estado=0;//El supermercado cierra
		}
	}
}



int main()
{
	char nombre[45];
	int cant,t_cliente;
	int* t_cajeras,* ejecucion;
	cola* cola_cajeras;
			
			//Se pide informacion basica
	printf("Nombre del Supermercado: \n");
	scanf("%s",&nombre);

	printf("Numero de cajeras: \n");
	scanf("%i",&cant);
	
	t_cajeras=(int*)malloc(cant*sizeof(int));//Se crea un arreglo para almacenar el tiempo que le toma a cada cajera atender
	ejecucion=(int*)malloc(cant*sizeof(int));//Se crea un arreglo para almacenar el tiempo que lleva la cajera atendiendo
	cola_cajeras=(cola*)malloc(cant*sizeof(cola));//Se crea un arreglo con las colas necesarias para las cajeras

	for (int i = 0; i < cant; ++i){
		Initialize(&cola_cajeras[i]);
		printf("Tiempo para cajera No.%i :\n",i+1);//Se piden los tiempos correspondientes
		scanf("%i",&t_cajeras[i]);
		ejecucion[i]=0;
	}

	printf("Tiempo para clientes:\n");//Se introduce el tiempo de llegada de los clientes
	scanf("%i",&t_cliente);

	Simulacion(cant,t_cliente,t_cajeras,ejecucion,cola_cajeras);
	
	for (int i = 0; i < cant; ++i){
		Destroy(&cola_cajeras[i]);
	}
	
	return 0;
}