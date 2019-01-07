/*
Implementación Práctica 02: Simulación 3 - Banco
Por: #TeamYalja
Versión: 1.1

Descripción: Simular la atención de personas en un banco, cuidando sean
respetadas las políticas de atención del mismo y evitando que
las personas no dejen de ser atendidas.
	
Observaciones: 
*/


//LIBRERÍAS
#include <stdio.h>
#include <time.h>
#include <windows.h>
#include "TADColaDin.c"

//DEFINICIONES DE CONSTANTES
#define TIEMPO_BASE 1000


//DEFINICIÓN DE FUNCIONES

/*
Descripción: Función encargada de mostrar en pantalla tanto las cajas como las filas del banco
Recibe: int n (número de cajas en el banco), int* ejecucion (arreglo dinámico que simula el tiempo que lleva 
	atendiendo cada cajera), cola* cajeras (arreglo dinámico que simula las colas en cada una de las cajas),
	cola preferentes (cola que simula la fila de los clientes preferentes), cola clientes (cola que simula la fila
	de los clientes), cola usuarios (cola que simula la fila de los usuarios)
Devuelve: 
Observaciones: 
	elemento aux: Elemento auxiliar que nos permitirá conocer la información de los clientes en cada caso
*/
void Mostrar(int n,int* ejecucion,cola* cajeras,cola preferentes,cola clientes,cola usuarios){
	elemento aux;

	+
	for (int i = 0; i < n; ++i) // Iteración que muestra el estado de cada caja
	{
		printf("Caja: %i\n",i+1);
		if(Empty(&cajeras[i])){ // En el caso de estar vacías
			printf("\tAtendiendo al cliente: Vacia\n");
			printf("\tTipo de cliente: -\n");
			printf("\tTiempo atendiendo: -\n");
		}
		else{ // En el caso de encontrarse atendiendo a algún cliente
			aux=Front(&cajeras[i]); 
			printf("\tAtendiendo al cliente: %i\n",aux.n);
			printf("\tTipo de cliente: %c\n",aux.status);
			printf("\tTiempo atendiendo: %i\n", ejecucion[i]);
		}
	}

	printf("\nFila Preferentes:\n"); // Estado de la fila de preferentes
	if(Empty(&preferentes)){ // En caso de estar vacía
		printf("La fila esta vacia.\n\n");
	}
	else{ // En caso de contener por lo menos un preferente
		aux=Front(&preferentes); 
		printf("Sigue el cliente %i en la fila \n",aux.n);
		printf("Hay %i en la fila\n",Size(&preferentes));
	}


	printf("\nFila Clientes:\n"); // Estado de la fila de cliente
	if(Empty(&clientes)){ // En caso de estar vacía
		printf("La fila esta vacia.\n\n");
	}
	else{ // En caso de contener por lo menos un cliente
		aux=Front(&clientes); 
		printf("Sigue el cliente %i en la fila \n",aux.n);
		printf("Hay %i en la fila\n",Size(&clientes));
	}

	printf("\nFila Usuarios:\n"); // Estado de la fila de usuarios
	if(Empty(&usuarios)){ // En caso de estar vacía
		printf("La fila esta vacia.\n\n");
	}
	else{ // En caso de contener por lo menos un cliente
		aux=Front(&usuarios);
		printf("Sigue el cliente %i en la fila \n",aux.n);
		printf("Hay %i en la fila\n\n",Size(&usuarios));
	}
	
	return;
}

/*
Descripción: Función encargada de señalar la cantidad de cajas vacías
Recibe: cola* cajeras (arreglo dinámico que simula cada una de las cajas del banco), int n (entero que indica la cantidad de cajas)
Devuelve: int (número de cajas vacías)
Observaciones: 
	int aux: entero auxiliar que llevará la cuenta de cajas vacías, iniciando en 0
*/
int CajasVacias(cola* cajeras, int n){
	int aux=0;
	for (int i = 0; i < n; ++i){ // Iteración que recorre las cajas
		if(Empty(&cajeras[i])) // En caso de estar vacía, aumenta el contador
			aux++;
	}

	return aux;
}

/*
Descripción: Función encargada de indicar cuál es la primera caja vacía disponible
Recibe: cola* cajeras (arreglo dinámico que simula cada una de las cajas del banco), int n (entero que indica la cantidad de cajas)
Devuelve: int (entero que indica la posición de la primer caja disponible)
Observaciones: 
	int aux: auxiliar para saber la posición de la primer caja vacía, en caso de devolver -1, indica que no hay cajas vacías
*/
int checarVacia(cola* cajeras,int n){//Funcion que te devuelve la primer caja vacia que encuentra
	int aux=-1;
	for (int i = 0; i < n; ++i){
		if(Empty(&cajeras[i])){
			aux=i;
			break;
		}
	}

	return aux;
}



/*
Variables usadas en el programa:
	cola clientes: Cola que simulará la fila de los clientes
	cola usuarios: Cola que simulará la fila de los usuarios
	cola preferentes: Cola que simulará la fila de los preferentes
	cola* cajeras: Apuntador a cola que simulará un arreglo que contendrá las cajas del banco
	int n: Entero que indicará el número de cajas en el banco
	int t_cajeras: Entero que indicará el tiempo que tarda en atender cada caja
	int tc: Entero que indicará cada cuando llega un nuevo cliente
	int tu: Entero que indicará cada cuando llega un nuevo usuario
	int tp: Entero que indicará cada cuando llega un nuevo preferente
	int tiempo: Entero que simulará el tiempo
	int clientes_cont: Entero que llevará la cuenta de los clientes que llegan al banco
	int clientes_atend: Entero que indicará los clientes atendidos hasta el momento
	int cont_usu: Entero que permitirá saber cuantos clientes o preferentes se han atendido antes de atender a un usuario
	int cajera_num: Entero que guardará el número de caja a la cual debe ingresar el cliente
	int* ejecucion: Apuntador a entero que contendrá un arreglo que guardará el tiempo que lleva atendiendo cada cajera
	elemento e: Elemento auxiliar que ayudará a manejar los elementos entre colas
*/
int main(){

	cola clientes,usuarios,preferentes;
	cola* cajeras;
	int n,t_cajeras,tc,tu,tp,tiempo=0,clientes_cont=0,clientes_atend=0,cont_usu=0,cajera_num;
	int* ejecucion;
	elemento e;
	
	printf("Numero de cajeras: \n"); // Se pide el número de cajas en el banco
	scanf("%i",&n);
	
	cajeras=(cola*)malloc(n*sizeof(cola)); // Se crea un arreglo con las colas necesarias para las cajeras
	ejecucion=(int*)malloc(n*sizeof(int)); // Se crea un arreglo que contendrá el tiempo que llevan atendiendo
	
	printf("Tiempo para cajeras:\n"); // Se pide el tiempo que tardará en atender cada cajera
	scanf("%i",&t_cajeras);

	printf("Tiempo para clientes:\n"); // Se introduce el tiempo de llegada de los clientes
	scanf("%i",&tc);

	printf("Tiempo para usuarios:\n"); // Se introduce el tiempo de llegada de los usuarios
	scanf("%i",&tu);

	printf("Tiempo para preferentes:\n"); // Se introduce el tiempo de llegada de los preferentes
	scanf("%i",&tp);

	Initialize(&preferentes); // Se inician las colas necesarias
	Initialize(&clientes);
	Initialize(&usuarios);
	for (int i = 0; i < n; ++i){
		ejecucion[i]=0; // Se indica que el tiempo de atención iniciará en 0 en todos los casos
		Initialize(&cajeras[i]);
	}

	while(clientes_atend < 50){ // Iteración que simulará la atención de los clientes
		
		tiempo++; // Se incrementa el tiempo actual

		system("cls"); // Se limpia la pantalla por estética
		printf("Tiempo actual: %i\n\n", tiempo); // Se muestra el tiempo actual

		for (int i = 0; i < n; ++i){ // Iteración para revisar el estado de cada caja
			if(!Empty(&cajeras[i])) // En caso de tener un cliente, se incrementa en 1 el tiempo qeu llevan atendiendo
				ejecucion[i]++;
			if (t_cajeras == (ejecucion[i]*TIEMPO_BASE)){ // Si ya es tiempo de desencolar, se hace
				ejecucion[i]=0;
				e=Dequeue(&cajeras[i]);
				printf("Se atendio al cliente %i (que es %c) por la cajera %i \n",e.n,e.status,i+1);
				clientes_atend++;
			}
		}

		// IFs usados para insertar elementos a las colas si es que es tiempo de que llegue alguien más

		if((TIEMPO_BASE * tiempo) % tp == 0){ // En caso de que deba llegar un preferente
			clientes_cont++;
			e.n=clientes_cont;
			e.status='P';
			Queue(&preferentes,e);
			printf("Llego un Preferente\n");
		}

		if((TIEMPO_BASE * tiempo) % tc == 0){ // En caso de que deba llegar un cliente
			clientes_cont++;
			e.n=clientes_cont;
			e.status='C';
			Queue(&clientes,e);
			printf("Llego un Cliente\n");
		}
		
		if((TIEMPO_BASE * tiempo) % tu == 0){ // En caso de que deba llegar un usuario
			clientes_cont++;
			e.n=clientes_cont;
			e.status='U';
			Queue(&usuarios,e);
			printf("Llego un Usuario\n");
		}
		
		//Los preferentes tiene preferencia sobre cualquier otro usuario, por eso el while es hasta que ya no haya preferentes
		
		while(CajasVacias(cajeras,n)>0 && (!Empty(&preferentes) || !Empty(&clientes) || !Empty(&usuarios))){ 
			// Iteración que introducirá a los clientes en espera, siempre y cuando haya; a una caja vacía, siempre y cuando haya

			while(!Empty(&preferentes) && checarVacia(cajeras,n) != -1 && cont_usu < 5){
				// Mientras haya preferentes, haya alguna caja vacia y no hayan pasado mas de 5 preferentes o clientes sin pasar algún usuario
				e=Dequeue(&preferentes);// Se desencola de la cola original y se pasa a alguna cajera
				cajera_num=checarVacia(cajeras,n);
				Queue(&cajeras[cajera_num],e); 
				cont_usu++; // Se incremeta en contador para checar que no pasen más de 5 preferences o clientes sin que pase algún usuario
				printf("Preferente entro a la caja #%i\n",cajera_num+1);
			}

			while(!Empty(&clientes) && checarVacia(cajeras,n) != -1 && cont_usu < 5){	
				// Mientras haya clientes, haya alguna caja vacia y no hayan pasado mas de 5 preferentes o clientes sin pasar algún usuario
				e=Dequeue(&clientes); // Se desencola de la cola original y se pasa a alguna cajera
				cajera_num=checarVacia(cajeras,n);
				Queue(&cajeras[cajera_num],e);
				cont_usu++; // Se incremeta en contador para checar que no pasen más de 5 preferences o clientes sin que pase algún usuario
				printf("Cliente entro a la caja #%i\n",cajera_num+1);
			}
		
			while(!Empty(&usuarios) && checarVacia(cajeras,n) != -1){  // Mientras haya usuarios y haya alguna caja vacía
				e=Dequeue(&usuarios);// Se desencola de la cola original y se pasa a alguna cajera
				cajera_num=checarVacia(cajeras,n);
				Queue(&cajeras[cajera_num],e);
				cont_usu=0; // Se reinicia el contador dado que ya pasó un usuario
				if(!Empty(&preferentes) || !Empty(&clientes)) // En caso de que no estén vacías las otras dos filas, se reinicia la iteración principal
					break;
				printf("Usuario entro a la caja #%i\n",cajera_num+1);
			}

		}
		
		printf("\n");
		Mostrar(n,ejecucion,cajeras,preferentes,clientes,usuarios); // Se muestra en pantalla el estado general de las cajas y las filas
		//system("pause");
		Sleep(300);
			
	}

	//printf("Cerrado.\nSe atendieron %i clientes.\n",clientes_atend);

	Destroy(&preferentes); // Se destruyen las colas utilizadas
	Destroy(&clientes);
	Destroy(&usuarios);
	for (int i = 0; i < n; ++i)
		Destroy(&cajeras[i]);
	free(cajeras); // Se libera la memoria dinámica solicitada durante la ejecución
	free(ejecucion);
	
	return 1;
}