#include <stdio.h>
#include <time.h>
#include <windows.h>
#include "TADColaDin.c"

#define TIEMPO_BASE 1000

void Mostrar(int n,int* ejecucion,cola* cajeras,cola preferentes,cola clientes,cola usuarios){
	elemento aux;

	//system("cls");
	
	for (int i = 0; i < n; ++i)
	{
		printf("Caja: %i\n",i+1);
		if(Empty(&cajeras[i])){
			printf("\tAtendiendo al cliente: Vacia\n");
			printf("\tTipo de cliente: -\n");
			printf("\tTiempo atendiendo: -\n");
		}
		else{
			aux=Front(&cajeras[i]);
			printf("\tAtendiendo al cliente: %i\n",aux.n);
			printf("\tTipo de cliente: %c\n",aux.status);
			printf("\tTiempo atendiendo: %i\n", ejecucion[i]);
		}
	}

	printf("\nFila Preferentes:\n");
	if(Empty(&preferentes)){
		printf("La fila esta vacia.\n\n");
	}
	else{
		aux=Front(&preferentes);
		printf("Sigue el cliente %i en la fila \n",aux.n);
		printf("Hay %i en la fila\n",Size(&preferentes));
	}


	printf("\nFila Clientes:\n");
	if(Empty(&clientes)){
		printf("La fila esta vacia.\n\n");
	}
	else{
		aux=Front(&clientes);
		printf("Sigue el cliente %i en la fila \n",aux.n);
		printf("Hay %i en la fila\n",Size(&clientes));
	}

	printf("\nFila Usuarios:\n");
	if(Empty(&usuarios)){
		printf("La fila esta vacia.\n\n");
	}
	else{
		aux=Front(&usuarios);
		printf("Sigue el cliente %i en la fila \n",aux.n);
		printf("Hay %i en la fila\n\n",Size(&usuarios));
	}
	//system("pause");
	//Sleep(300);
	return;
}

int CajasVacias(cola* c, int n){
	int aux=0;
	for (int i = 0; i < n; ++i){
		if(Empty(&c[i]))
			aux++;
	}

	return aux;
}

int checarVacia(cola* cajeras,int n){
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

	cola usuarios,clientes,preferentes;
	cola* cajeras;
	int n,aux,t_cajeras,tc,tu,tp,estado=1,tiempo=0,clientes_cont=0,clientes_atend=0,cont_usu=0,cajera_num,checar;
	int* ejecucion;
	elemento e;
	
	printf("Numero de cajeras: \n");
	scanf("%i",&n);
	
	ejecucion=(int*)malloc(n*sizeof(int));
	cajeras=(cola*)malloc(n*sizeof(cola));//Se crea un arreglo con las colas necesarias para las cajeras

	printf("Tiempo para cajeras:\n");//Se piden los tiempos correspondientes
	scanf("%i",&t_cajeras);

	printf("Tiempo para clientes:\n");//Se introduce el tiempo de llegada de los clientes
	scanf("%i",&tc);

	printf("Tiempo para usuarios:\n");//Se introduce el tiempo de llegada de los clientes
	scanf("%i",&tu);

	printf("Tiempo para preferentes:\n");//Se introduce el tiempo de llegada de los clientes
	scanf("%i",&tp);

	Initialize(&preferentes);
	Initialize(&clientes);
	Initialize(&usuarios);
	for (int i = 0; i < n; ++i){
		ejecucion[i]=0;
		Initialize(&cajeras[i]);
	}

	//while(estado==1){
	while(clientes_atend<20){
		tiempo++;
		system("cls");
		printf("Tiempo actual: %i\n\n", tiempo);
		//checar=0;

		for (int i = 0; i < n; ++i){
			if(!Empty(&cajeras[i]))
				ejecucion[i]++;
			if (t_cajeras == ejecucion[i]){//Se va recorriendo cada una de las cajas para ver si ya estan en tiempo de desencolar
				ejecucion[i]=0;
				e=Dequeue(&cajeras[i]);
				printf("Se atendio al cliente %i (que es %c) por la cajera %i \n",e.n,e.status,i+1);
				clientes_atend++;
			}
		}

		if((tiempo % tp) == 0){
			clientes_cont++;
			e.n=clientes_cont;
			e.status='P';
			Queue(&preferentes,e);
			printf("Llego un Preferente\n");
		}

		if((tiempo % tc) == 0){
			clientes_cont++;
			e.n=clientes_cont;
			e.status='C';
			Queue(&clientes,e);
			printf("Llego un Cliente\n");
		}
		
		if((tiempo % tu) == 0){
			clientes_cont++;
			e.n=clientes_cont;
			e.status='U';
			Queue(&usuarios,e);
			printf("Llego un Usuario\n");
		}
		aux=CajasVacias(cajeras,n);

		for (int i = CajasVacias(cajeras,n); i > 0; --i)
		{
			if (cont_usu<5)
			{
				if (!Empty(&preferentes))
				{
					e=Dequeue(&preferentes);
					cajera_num=checarVacia(cajeras,n);
					Queue(&cajeras[cajera_num],e);
					cont_usu++;
					printf("Preferente entro a la caja #%i\n",cajera_num+1);
				}
				else if(!Empty(&clientes)){
					e=Dequeue(&clientes); 
					cajera_num=checarVacia(cajeras,n);
					Queue(&cajeras[cajera_num],e);
					cont_usu++;
					printf("Cliente entro a la caja #%i\n",cajera_num+1);
				}
				else if (!Empty(&usuarios))
				{
					e=Dequeue(&usuarios);
					cajera_num=checarVacia(cajeras,n);
					Queue(&cajeras[cajera_num],e);
					cont_usu=0;
					printf("Usuario entro a la caja #%i\n",cajera_num+1);
				}
			}
			else{
				if (!Empty(&usuarios))
				{
					e=Dequeue(&usuarios);
					cajera_num=checarVacia(cajeras,n);
					Queue(&cajeras[cajera_num],e);
					cont_usu=0;
					printf("Usuario entro a la caja #%i\n",cajera_num+1);
				}
				else if (!Empty(&preferentes))
				{
					e=Dequeue(&preferentes);
					cajera_num=checarVacia(cajeras,n);
					Queue(&cajeras[cajera_num],e);
					printf("Preferente entro a la caja #%i\n",cajera_num+1);
				}
				else if(!Empty(&clientes)){
					e=Dequeue(&clientes); 
					cajera_num=checarVacia(cajeras,n);
					Queue(&cajeras[cajera_num],e);
					printf("Cliente entro a la caja #%i\n",cajera_num+1);
				}
			}
		}
		printf("\n");
		Mostrar(n,ejecucion,cajeras,preferentes,clientes,usuarios);
		//system("pause");
		Sleep(500);
		//system("cls");
		//Mostrar(n,tiempo,ejecucion,cajeras,preferentes,clientes,usuarios);

		/*for (int j = 0; j < n; ++j){
			if(Empty(&cajeras[j]))
				checar++;
		}

		if(checar == n && clientes_atend >= 100){
			printf("El banco ha cerrado \n");
			estado=0;
		}*/

		//Sleep(200);
	}


	Destroy(&preferentes);
	Destroy(&clientes);
	Destroy(&usuarios);
	for (int i = 0; i < n; ++i)
		Destroy(&cajeras[i]);
	free(cajeras);
	free(ejecucion);
	return 1;
}