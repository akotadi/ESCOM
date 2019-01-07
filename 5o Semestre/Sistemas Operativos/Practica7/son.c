#include<windows.h>			//Programa hijo
#include<stdio.h>

int main()
{
	HANDLE hSemaforo;
	int i =1;
	
	//Apertura del semaforo
	if((hSemaforo = OpenSemaphore(SEMAPHORE_ALL_ACCESS, FALSE, "Semaforo")) == NULL)
	{
		printf("Falla al invocar OpenSemaphore: %d\n", GetLastError());
		return -1;
	}
	
	while(i < 4)
	{
		//Prueba del semaforo 
		WaitForSingleObject(hSemaforo, INFINITE);
		
		// Sección crítica 
		printf("Soy el hijo entrando %i de 3 veces al semaforo\n", i);
		Sleep(5000);
		
		//Liberación del semaforo
		if((!ReleaseSemaphore(hSemaforo,1,NULL)))
		{
			printf("Falla al invocar ReleaseSemaphore: %d\n", GetLastError());
		}
		Sleep(5000);
		i++;
	}
}
