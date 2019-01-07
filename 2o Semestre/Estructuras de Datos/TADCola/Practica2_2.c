/*
Implementación Práctica 02: Simulación 2 - Ejecución de procesos en el sistema operativo
Por: #TeamYalja
Versión: 1.1

Descripción: Simular la ejecución de los procesos gestionados por el
sistema operativo en un equipo monoprocesador sin manejo
de prioridades.
	
Observaciones: 
*/


//LIBRERÍAS
#include <stdio.h>
#include <windows.h>
#include <time.h>
#include "TADColaDin.c"

//DEFINICIONES DE CONSTANTES
#define TIEMPO_BASE 1000



/*
Variables usadas en el programa:
	cola listos: Cola que manejará los procesos listos para su ejecución
	cola finalizados: Cola que manejará los procesos una vez terminados
	int tiempoTotal: Entero que simulará el tiempo
	int size: Entero que contendrá el número de procesos
	int checar: Entero que nos permitirá saber a donde se fue el último proceso ejecutado
	elemento e: Elemento auxiliar que nos permitirá manipular entre ambas colas
	elemento aux: Elemento auxiliar que nos ayudará a saber los procesos ejecutados y próximos a ejecutarse
*/
int main(){
	cola listos,finalizados;
	int tiempoTotal=0,size,checar=0;
	elemento e,aux;
	
	printf("Numero de procesos: \n"); // Se pide el  numero de procesos a realizar
	scanf("%i",&size);

	Initialize(&listos); // Se inician las colas que serán utilizadas
	Initialize(&finalizados);
	
	for (int i = 0; i < size; ++i){ // Iteración que sirve para poner cada una de las características de los procesos
		printf("\nProceso: %i\n",i+1);
		printf("Nombre: ");
		scanf("%s",&e.nombre);
		printf("Actividad: ");
		scanf("%s",&e.actividad);
		printf("ID: ");
		scanf("%s",&e.id);
		printf("Tiempo: ");
		scanf("%i",&e.tiempo_ejecucion);
		e.tiempo_ejecucion=e.tiempo_ejecucion*TIEMPO_BASE;


		Queue(&listos,e); // Una vez adquirida su información, se introduce en la cola de listos
	}
	printf("\n");
	
	while(!Empty(&listos)){ // Iteración que concluye al terminarse los elementos en la cola de listos
		
		//system("pause");
		Sleep(500);
		//Sleep(TIEMPO_BASE);
		tiempoTotal++;
		system("cls"); // Se limpia la pantalla por estética
		printf("Tiempo Total: %i\n\n",tiempoTotal); // Se muestra el tiempo total de ejecución al momento

		printf("Ultimo Ejecutado:\n"); // Se muestra el último proceso ejecutado en caso de haberlo
		if(checar==1){
			checar=0;
			aux=Final(&finalizados);
			printf("\tNombre: %s\n\tID: %s\n\tTermino su ejecucion en: %i\n\n",aux.nombre,aux.id,aux.tiempo_ejecucion);
		}
		else if (tiempoTotal>1&&checar==0)
		{
			aux=Final(&listos);
			printf("\tNombre: %s\n\tID: %s\n\tTiempo para concluir ejecucion: %i\n\n",aux.nombre,aux.id,aux.tiempo_ejecucion/1000);
		}

		e=Dequeue(&listos); // Se toma el siguiente proceso en la cola de listos
		e.tiempo_ejecucion= e.tiempo_ejecucion - 1000;
		printf("Proceso en ejecucion:\n"); // Se muestra su información
		printf("\tNombre: %s \n\tActividad: %s\n\tID: %s\n\tTiempo total en ejecucion: %i \n", e.nombre,e.actividad,e.id,tiempoTotal);
		
		if(e.tiempo_ejecucion == 0){ // En caso de haber concluído, se manda a finalizados
			checar=1;
			e.tiempo_ejecucion = tiempoTotal;
			printf("Proceso terminando\n\n");
			Queue(&finalizados,e);
		}
		else{ // En caso de no haber terminado, se vuelve a formar 
			Queue(&listos,e);
			printf("\n\n");
		}

		printf("Proceso siguiente:\n"); // Se busca el siguiente proceso en la fila
		if(!Empty(&listos)){
			e=Front(&listos);//Se saca SÓLO la informacion del proceso siguiente a ejecutar
			printf("\tNombre: %s\n\tID: %s\n\tTiempo para concluir ejecucion: %i\n\n",e.nombre,e.id,e.tiempo_ejecucion/1000); // Se muestra su información
		}
		else{
			printf("\tYa no hay procesos en la cola \n\n\n");  // En caso de que ya no haya más procesos
		}
	}
	
	system("cls");
	printf("\nLista de procesos realizados: \n"); // Se porcede a mostrar la información de todos los procesos una vez finalizados
	while(!Empty(&finalizados)){
		e=Dequeue(&finalizados);
		printf("\n%io Finalizado:\n\tNombre: %s\n \tID: %s\n \tTiempo total de ejecucion: %i\n",size-Size(&finalizados),e.nombre,e.id,e.tiempo_ejecucion);
	}

	Destroy(&listos); // Se destruyen las colas utilizadas
	Destroy(&finalizados);

	return 1;
}