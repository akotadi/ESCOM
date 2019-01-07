#include <stdio.h>
#include <time.h>
#include <windows.h>
#include "TADColaDin.c"

#define TIEMPO_BASE 1000

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

int main(){

	cola clientes,usuarios,preferentes;
	cola* cajeras;
	int tca,tc,tu,tp,tiempo=0,clientes_cont=0,clientes_atend=0,cont_usu=0,n,cajera_num;
	elemento e;

	printf("Numero de cajeras: \n");
	scanf("%i",&n);
	
	cajeras=(cola*)malloc(n*sizeof(cola));//Se crea un arreglo con las colas necesarias para las cajeras

	printf("Tiempo para cajeras:\n");//Se piden los tiempos correspondientes
	scanf("%i",&tca);

	printf("Tiempo para clientes:\n");//Se introduce el tiempo de llegada de los clientes
	scanf("%i",&tc);

	printf("Tiempo para usuarios:\n");//Se introduce el tiempo de llegada de los clientes
	scanf("%i",&tu);

	printf("Tiempo para preferentes:\n");//Se introduce el tiempo de llegada de los clientes
	scanf("%i",&tp);

	Initialize(&preferentes);
	Initialize(&clientes);
	Initialize(&usuarios);

	while(clientes_atend < 7){
		Sleep(TIEMPO_BASE);
		tiempo++;
		//printf("Trono aqui 0\n");
		if((TIEMPO_BASE*tiempo) % tca   == 0){ // Revisa si ya es tiempo de desencolar las cajas
			for (int i = 0; i < n; ++i){
				//printf("Es tiempo para la cajera no. %i\n", i+1);
				if (!Empty(&cajeras[i])){//Si no esta vacia, entonces desencola
					//printf("La cajera no. %i NO esta vacia\n", i+1);
					e=Dequeue(&cajeras[i]);
					printf("Se atendio al cliente %i (que es %c) por la cajera %i \n",e.n,e.status,i+1);
					clientes_atend++;
					printf("Trono aqui 1\n");
				}
			}
		}
		//printf("Trono aqui 1\n");
		/*if(clientes_atend == 10){
			break;
		}*/
		
		//Los preferentes tiene preferencia sobre cualquier otro usuario, por eso el while es hasta que ya no haya preferentes
		while(!Empty(&preferentes) && checarVacia(cajeras,n) != -1 && cont_usu < 5){//Mientras haya preferentes, haya alguna caja vacia y no hayan pasado mas de 5 pref o clientes sin pasar algun usuario
			e=Dequeue(&preferentes);
			cajera_num=checarVacia(cajeras,n);
			Queue(&cajeras[cajera_num],e);//Se desencola de la cola original y se pasa a alguna cajera
			cont_usu++;//Se incremeta en contador para checar que no pasen mas de 5 preferences o clientes sin que pase algun usuario
			printf("preferente entro a caja no. %i\n",cajera_num+1);
		}

		while(!Empty(&clientes) && checarVacia(cajeras,n) != -1 && cont_usu < 5){	//Lo mismo que el otro
			e=Dequeue(&clientes); // NO SE QUE CHINGADOS SUCEDE AQUI CAGAJOOOOOO
			cajera_num=checarVacia(cajeras,n);
			Queue(&cajeras[cajera_num],e);
			cont_usu++;
			printf("cliente entro a caja no. %i\n",cajera_num+1);
		}
		//printf("Trono aqui 3\n");
		while(!Empty(&usuarios) && checarVacia(cajeras,n) != -1){//Lo mismo
			e=Dequeue(&usuarios);
			cajera_num=checarVacia(cajeras,n);
			Queue(&cajeras[cajera_num],e);
			//llega aqui despues de que ya hayan pasado 5 preferentes o clientes entonces el contador vuelve a cero
			cont_usu=0;
			if(!Empty(&preferentes) || !Empty(&clientes))
				break;
			printf("usuario entro a caja no. %i\n",cajera_num+1);
		}
		//printf("Trono aqui 4\n");
		//IFs usados para insertar elementos a las colas si es que es tiempo de que llegue alguien mas
		if((TIEMPO_BASE * tiempo) % tc == 0){
			clientes_cont++;
			e.n=clientes_cont;
			e.status='C';
			Queue(&clientes,e);
			printf("Se metio uno a clientes\n");
		}
		//printf("Trono aqui 5\n");
		if((TIEMPO_BASE * tiempo) % tu  == 0){
			clientes_cont++;
			e.n=clientes_cont;
			e.status='U';
			Queue(&usuarios,e);
			printf("Se metio uno a usuarios\n");
		}
		//printf("Trono aqui 6\n");
		if((TIEMPO_BASE * tiempo) % tp  == 0){
			clientes_cont++;
			e.n=clientes_cont;
			e.status='P';
			Queue(&preferentes,e);
			printf("Se metio uno a preferentes\n");
		}
		//printf("Trono aqui 7\n");
	}

	for (int i = 0; i < n; ++i){
		Destroy(&cajeras[i]);
	}

	Destroy(&preferentes);
	Destroy(&clientes);
	Destroy(&usuarios);
	free(cajeras);
	return 1;
}