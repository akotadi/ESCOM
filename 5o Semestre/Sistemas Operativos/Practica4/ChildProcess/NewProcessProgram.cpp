#include <windows.h>
#include <stdio.h>

int main(int argc, char *argv[])
{
	STARTUPINFO si;
	PROCESS_INFORMATION pi;
	int i;
	ZeroMemory(&si, sizeof(si));
	si.cb = sizeof(si);
	ZeroMemory(&pi, sizeof(pi));
	if (argc != 2)
	{
		printf("User: %s Nombre_programa_hijo\n", argv[0]);
		return 0;
	}

	// Creación proceso hijo
	if (!CreateProcess(NULL, argv[1], NULL, NULL, FALSE, 0, NULL, NULL, &si, &pi))
	{
		printf("Fallo al invocar CreateProcess(%d)\n", GetLastError());
		return 0;
	}

	// Proceso padre
	printf("Soy el padre\n");
	WaitForSingleObject(pi.hProcess, INFINITE);

	// Terminación controlada del proceso e hilo asociado de ejecución
	CloseHandle(pi.hProcess);
	CloseHandle(pi.hThread);
}