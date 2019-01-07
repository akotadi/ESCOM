#include <stdio.h>
#include <windows.h>
#include <time.h>
#include "TADColaDin.c"



void Mostrar(){
	int n;
}



int main(){
	cola listos,finalizados;
	int tiempoTotal=0,size,esperar,cont;;
	elemento e,aux;

	printf("Numero de procesos: \n");//Se pide el  numero de procesos a realizar
	scanf("%i",&size);
	Initialize(&listos);
	Initialize(&finalizados);
	//Loop que sirve para poner cada una de las carac. de los procesos
	for (int i = 0; i < size; ++i){
		// Poner los elementos con sus carac en la cola listos
		printf("\nProceso: %i\n",i+1);
		printf("Nombre: ");
		scanf("%s",&e.nombre);
		printf("Actividad: ");
		scanf("%s",&e.actividad);
		printf("ID: ");
		scanf("%s",&e.id);
		printf("Tiempo: ");
		scanf("%i",&e.tiempo_ejecucion);

		Queue(&listos,e);
	}
	printf("\n");
	//Si se debe mostrar el tiempo como disminuye entonces hacemos un for de n veces con un sleep que espere solo una cantidad determinada y un print

	while(!Empty(&listos)){
		cont=0;

		e=Dequeue(&listos);//Se saca el proceso que este en la cola de procesos listos para ejecutarse

		esperar=e.tiempo_ejecucion;

		for (int i = 0; i < e.tiempo_ejecucion; ++i)
		{
			Sleep(300);
			//system("pause");
			system("cls");
			printf("Tiempo Total: %i\n\n",tiempoTotal+i+1);
			printf("Nombre: %s \nActividad: %s\nID: %s\nTiempo total en ejecucion: %i \n\n", e.nombre,e.actividad,e.id,tiempoTotal+i+1);
			if(!Empty(&finalizados)){
				aux=Front(&finalizados);
				printf("Nombre del ultimo proceso: %s\nID: %s\nTiempo total de ejecucion: %i\n\n",aux.nombre,aux.id,aux.tiempo_ejecucion);
			}
			else{
				printf("No hay procesos finalizados.\n\n\n\n");
			}
			if(!Empty(&listos)){
				aux=Front(&listos);
				printf("Nombre del proceso siguiente: %s\nID: %s\nTiempo para iniciar ejecucion: %i\n\n",aux.nombre,aux.id,e.tiempo_ejecucion-i);
			}
			else{
				printf("No hay mas procesos.\n\n\n\n");
			}
		}
		//printf("Nombre: %s Tiempo en ejecución: %i \n", e.nombre,e.tiempo_ejecucion);//Se imprime la informacion del procesos

		tiempoTotal = tiempoTotal + e.tiempo_ejecucion;//Se calcula el tiempo total que lleva el programa moviendo los procesos
		e.tiempo_ejecucion = tiempoTotal;//Se aniade el tiempo total al tiempo del elemento solo para funcionalidad de imprimir
		
		Queue(&finalizados,e);//Se inserta este proceso en la cola de procesos terminados
		
		/*if(!Empty(&listos)){
			e=Front(&listos);//Se saca SOLO la informacion del proceso siguiente a ejecutar
			printf("El sig. proceso es: %s \n",e.nombre);//Se imprime esa info
		}
		else{
			printf("Ya no hay procesos en la cola \n");
		}*/
			
		//Sleep(esperar);//Se espera el tiempo necesario que costaba hacer ese proceso
		
	}
	system("cls");
	printf("Lista de procesos realizados: \n");
	while(!Empty(&finalizados)){//Loop usado para imprimir todos los procesos que se realizaro{
		e=Dequeue(&finalizados);
		printf("\nProceso #%i\n\tNombre: %s\n \tID: %s\n \tTiempo Total de ejecucion: %i\n",size-Size(&finalizados),e.nombre,e.id,e.tiempo_ejecucion);
	}

	Destroy(&listos);
	Destroy(&finalizados);
	return 1;
}
