#include <stdio.h>
#include <windows.h>
#include <time.h>
#include "TADColaDin.c"

#define TIEMPO_BASE 1000


int Caja(cola* c, int* p, int n){
	int m=0,a,b;
	for (int i = 1; i < n; ++i)
	{
		a=Size(&c[m]);
		b=Size(&c[i]);
		printf("Size: %i-%i\nTime: %i-%i\n",a,b,p[m],p[i]);
		if(a>b)
			m=i;
		else if(a==b && a==0 && p[i]<=p[m])
			m=i;
	}
	/*for (int i = 1; i < n; ++i)
	{
		a=Size(&c[m]);
		b=Size(&c[i]);
		printf("Size: %i-%i\nTime: %i-%i\n",a,b,p[m],p[i]);
		if(a==0 && a==b && p[i]<
	}*/
	return m;
}


int main()
{
	char nombre[45];
	int cant,tiempo_cliente,estado=1,checar=0,tiempo_cont=0,clientes_cont=1,r,n;
	int* tiempo_cajeras,* ejecucion;
	cola* cola_cajeras;
	elemento e;
			
			//Se pide informacion basica
	printf("Nombre del Supermercado: \n");
	scanf("%s",&nombre);

	printf("Numero de cajeras: \n");
	scanf("%i",&cant);
	
	tiempo_cajeras=(int*)malloc(cant*sizeof(int));//Se crea un arreglo para almacenar el tiempo que le toma a cada cajera atender
	ejecucion=(int*)malloc(cant*sizeof(int));//Se crea un arreglo para almacenar el tiempo que lleva la cajera atendiendo
	cola_cajeras=(cola*)malloc(cant*sizeof(cola));//Se crea un arreglo con las colas necesarias para las cajeras

	for (int i = 0; i < cant; ++i){
		Initialize(&cola_cajeras[i]);
		printf("Tiempo para cajera No.%i :\n",i+1);//Se piden los tiempos correspondientes
		scanf("%i",&tiempo_cajeras[i]);
		ejecucion[i]=0;
	}

	printf("Tiempo para clientes:\n");//Se introduce el tiempo de llegada de los clientes
	scanf("%i",&tiempo_cliente);
	

	e.id=1;
	Queue(&cola_cajeras[0],e);
	printf("Se metio al cliente %i en la cajera %i \n",e.id,1);

	while(1){
		
		//Sleep(TIEMPO_BASE);//Se espera el tiempo base
		tiempo_cont++;//Se incrementa el contador para saber cuanto tiempo ha pasado
		//printf("%i\n",tiempo_cont);
		for (int i = 0; i < cant; ++i){
			if(!Empty(&cola_cajeras[i]))
				ejecucion[i]++;
			if (tiempo_cajeras[i] == ejecucion[i]){//Se va recorriendo cada una de las cajas para ver si ya estan en tiempo de desencolar
				ejecucion[i]=0;
				e=Dequeue(&cola_cajeras[i]);
				printf("Se atendio al cliente %i por la cajera %i \n",e.id,i+1);
			}
		}

		checar=0;
		for (int j = 0; j < cant; ++j){//Loop usado para checar cada una e las cajas
			if(Empty(&cola_cajeras[j])){//Si todas las cajas estan vacias, etnonces se puede cerrar el super
				checar++;
			}
		}
		if(checar == cant && clientes_cont >= 100){//Ya no hay clientes en ninguna caja y ya pasaron mas de 100 clientes
			printf("El supermercado ha cerrado \n");
			break;//El supermercado cierra
		}
		printf("%i\n", checar);

		if( tiempo_cont % tiempo_cliente  == 0){//Si es tiempo de que llegue un cliente, se le asigna una caja
			clientes_cont++;//Se incrementa el contador de clientes
			e.id=clientes_cont;
			n=Caja(cola_cajeras,tiempo_cajeras,cant);
			Queue(&cola_cajeras[n],e);
			printf("Se metio al cliente %i en la cajera %i \n",e.id,n+1);
		}

		printf("\n");
	}
	
	for (int i = 0; i < cant; ++i){
		Destroy(&cola_cajeras[i]);
	}
	
	return 0;
}
