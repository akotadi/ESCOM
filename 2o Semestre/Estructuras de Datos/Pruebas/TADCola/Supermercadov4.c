/*
Implementación Práctica 02: Simulación 1 - Supermercado
Por: #TeamYalja
Versión: 1.0

Descripción: Simular la atención de clientes en un supermercado, el cuál
deberá de atender al menos 100 clientes por día para no tener
perdidas, por lo que una vez que ya se atendieron a más de
100 personas y no hay gente formada en las cajas puede
cerrar la tienda. Mientras no se cierre la tienda, las personas
podrán seguir llegando con productos a las cajas.
	
Observaciones: 
*/


//LIBRERÍAS
#include <stdio.h>
#include <windows.h>
#include <time.h>
#include <windows.h>
#include "TADColaDin.c"

//DEFINICIONES DE CONSTANTES
#define TIEMPO_BASE 1000



//DEFINICIÓN DE FUNCIONES

/*
Descripción: Función encargada de mostrar en pantalla las cajas simuladas
Recibe: int cant (cantidad de cajas), char nombre[] (nombre del supermercado), int t_cont (contador de tiempo),
	int* ejecucion (arreglo dinámico que contiene el tiempo que lleva atendiendo cada cajera),
	cola* cola_cajeras (arreglo dinámico que simula las colas de cada caja)
Devuelve: 
Observaciones: 
*/
void Mostrar(int cant,char nombre[],int t_cont,int* ejecucion,cola* cola_cajeras){
	elemento aux;

	printf("Supermercado: %s\n",nombre); // Nombre del supermercado
	printf("Tiempo actual: %i\n\n",t_cont); // Tiempo corriendo actual

	for (int i = 0; i < cant; ++i) // Iteración usada para mostrar cada una de las cajas
	{
		printf("Caja: %i\n",i+1); // Número de caja
		if(Empty(&cola_cajeras[i])){ // Si la caja está vacia
			printf("Atendiendo al cliente: Vacia\n");
			printf("Tiempo atendiendo: -\n");
			printf("Siguiente cliente en la cola: -\n");
			printf("Ultimo cliente en la cola: -\n\n");
		}
		else if(Size(&cola_cajeras[i])==1){ // Si la caja sólo contiene una persona
			aux=Front(&cola_cajeras[i]);
			printf("Atendiendo al cliente: %i\n",aux.n);
			printf("Tiempo atendiendo: %i\n", ejecucion[i]);
			printf("Siguiente cliente en la cola: -\n");
			printf("Ultimo cliente en la cola: -\n\n");
		}
		else{
			aux=Front(&cola_cajeras[i]); // Si la caja contiene dos o más personas
			printf("Atendiendo al cliente: %i\n",aux.n);
			printf("Tiempo atendiendo: %i\n", ejecucion[i]);
			aux=Element(&cola_cajeras[i],2);
			printf("Siguiente cliente en la cola: %i\n",aux.n);
			aux=Final(&cola_cajeras[i]);
			printf("Ultimo cliente en la cola: %i\n\n",aux.n);
		}
	}
	return;
}

/*
Descripción: Función encargada de simular el supermercado
Recibe: char nombre[] (nombre del supermercado), int cant (cantidad de cajas),int t_cliente (tiempo de llegada de
	cada cliente), int* t_cajeras (tiempo que tarda cada cajera en atender)
	int* ejecucion (arreglo dinámico que contiene el tiempo que lleva atendiendo cada cajera),
	cola* cola_cajeras (arreglo dinámico que simula las colas de cada caja)
Devuelve: 
Observaciones: 
	int checar: Entero que nos dirá la cantidad de cajas vacías
	int estado: Entero que detendrá la iteración
	int t_cont: Entero que simula el tiempo actual en la simulación
	int c_cont: Entero que lleva la cuenta de los clientes que entran en el supermercado
	int c_aten: Entero que nos indica la cantidad de clientes atendidos hasta el momento
	int r: Entero que contendrá el número al azar para la caja
	int* atend: Arreglo dinámico que nos ayudará a saber que clientes fueron atendidos
	elemento e: Elemento auxiliar para el manejo de colas e impresiones
*/
void Simulacion(char nombre[],int cant, int t_cliente, int* t_cajeras, int* ejecucion, cola* cola_cajeras){
	int checar,estado=1,t_cont=0,c_cont=0,c_aten=0,r;
	int* atend;
	elemento e;

	atend=(int*)malloc((cant+1)*sizeof(int));

	srand(time(NULL)); // Se inicia la semilla de random que se usara despues

	while(estado == 1){
		system("cls"); // Se limpia la pantalla por estética
		checar=0;
		t_cont++; // Se incrementa el contador para saber cuanto tiempo ha pasado

		for (int i = 0; i < cant; ++i){ // Iteración para revisar el estado de las cajas
			atend[i]=0;
			if(!Empty(&cola_cajeras[i]))
				ejecucion[i]++;
			if (t_cajeras[i] == (ejecucion[i] * TIEMPO_BASE)){ // Se va recorriendo cada una de las cajas para ver si ya estan en tiempo de desencolar
				ejecucion[i]=0;
				e=Dequeue(&cola_cajeras[i]);
				atend[i]=e.n;
				c_aten++;
			}
		}

		if( (t_cont * TIEMPO_BASE) % t_cliente  == 0){ // Si es tiempo de que llegue un cliente, se le asigna una caja
			c_cont++; // Se incrementa el contador de clientes
			e.n=c_cont;
			r=(rand() % cant); // Número generado al azar que se le aplicará una reducción en función del módulo de las cajas
			if(r==0){ // Si la división modular genera un 0, indica que es el máximo número posible, por lo tanto tiene condiciones especiales
				Queue(&cola_cajeras[cant-1],e);
				Mostrar(cant,nombre,t_cont,ejecucion,cola_cajeras);
				printf("\tSe metio al cliente %i en la cajera %i \n",e.n,cant);
			}
			else{ // Los demás casos se pueden manejar igual
				Queue(&cola_cajeras[r-1],e);
				Mostrar(cant,nombre,t_cont,ejecucion,cola_cajeras);
				printf("\tSe metio al cliente %i en la cajera %i \n",e.n,r);
			}
		}
		else
			Mostrar(cant,nombre,t_cont,ejecucion,cola_cajeras);

		for (int i = 0; i < cant; ++i) // Iteración para mostrar los clientes atendidos, usada por estética
		{
			if(atend[i]!=0)
				printf("\tSe atendio al cliente %i por la cajera %i \n",atend[i],i+1);
		}
		printf("\nClientes al momento: %i\n\n",c_cont);

		for (int j = 0; j < cant; ++j){ // Iteración usada para checar cada una de las cajas
			if(Empty(&cola_cajeras[j])){ 
				checar++;
			}
		}

		if(checar == cant && c_cont >= 100){ // Si todas las cajas están vacías, y ya se llevan más de 100 clientes
			printf("El supermercado ha cerrado \n");
			estado=0; // El supermercado cierra
		}
		
		//system("pause");
		Sleep(500);
	}
	free(atend); // Se libera la memoria dinámica usada en esta función
	return;
}



/*
Variables usadas en el programa:
	char nombre[45]: Cadena de texto que contendrá el nombre del supermercado
	int cant: Entero que indicará la cantidad de cajeras disponibles
	int t_cliente: Entero que indicará cada cuanto tiempo llegará un cliente
	int* t_cajeras: Apuntador a entero que contendrá el tiempo que tarda cada cajera en atender
	int* ejecucion: Apuntador a entero que contendrá un arreglo que guardará el tiempo que lleva atendiendo cada cajera
	cola* cola_cajeras: Apuntador de cola que simulará la cola de cada caja en el supermercado.
*/
int main()
{
	char nombre[45];
	int cant,t_cliente;
	int* t_cajeras,* ejecucion;
	cola* cola_cajeras;
			
	//Se pide informacion básica sobre el supermercado
	printf("Nombre del Supermercado: \n");
	scanf("%s",&nombre);

	printf("Numero de cajeras: \n");
	scanf("%i",&cant);
	
	t_cajeras=(int*)malloc(cant*sizeof(int)); // Se crea un arreglo para almacenar el tiempo que le toma a cada cajera atender
	ejecucion=(int*)malloc(cant*sizeof(int)); // Se crea un arreglo para almacenar el tiempo que lleva la cajera atendiendo
	cola_cajeras=(cola*)malloc(cant*sizeof(cola)); // Se crea un arreglo con las colas necesarias para las cajeras

	for (int i = 0; i < cant; ++i){
		Initialize(&cola_cajeras[i]); // Se inician las colas que serán utilizadas
		printf("Tiempo para cajera No.%i :\n",i+1); // Se piden los tiempos correspondientes de cada cajera
		scanf("%i",&t_cajeras[i]);
		ejecucion[i]=0;
	}

	printf("Tiempo para clientes:\n"); // Se introduce el tiempo de llegada de los clientes
	scanf("%i",&t_cliente);

	Simulacion(nombre,cant,t_cliente,t_cajeras,ejecucion,cola_cajeras);
	
	for (int i = 0; i < cant; ++i){ // Se destruyen las colas usadas durante la simulación
		Destroy(&cola_cajeras[i]);
	}
	free(t_cajeras); // Se libera la memoria dinámica solicitada durante la ejecución
	free(ejecucion);
	free(cola_cajeras);
	
	return 0;
}