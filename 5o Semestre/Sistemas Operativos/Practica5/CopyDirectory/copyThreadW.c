#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <windows.h>

typedef struct Information{
    char source[200];
    char destiny[200];
} info;

DWORD WINAPI copyDirectory(LPVOID lpParam){
	info *data = (info *)lpParam;
	
	WIN32_FIND_DATA wDataFile;  
	char aux[300];
	sprintf(aux, "%s\\*", data->source);

	int nThreads = 0;
	DWORD *idHilo = calloc(100, sizeof(DWORD));
	HANDLE *aThreads = calloc(100, sizeof(HANDLE));
	for(HANDLE hFile = FindFirstFile(aux, &wDataFile); hFile; FindNextFile(hFile, &wDataFile)){
		if(!strcmp(wDataFile.cFileName, ".") || !strcmp(wDataFile.cFileName, ".."))
			continue;
		info *data2 = calloc(1, sizeof(info));
		sprintf(data2->source, "%s\\%s", data->source, wDataFile.cFileName);
		sprintf(data2->destiny, "%s\\%s", data->destiny, wDataFile.cFileName);
		if(wDataFile.dwFileAttributes & FILE_ATTRIBUTE_DIRECTORY){
			CreateDirectory(data2->destiny, NULL);
			aThreads[nThreads] = CreateThread(NULL, 0, copyDirectory, data2, 0, &idHilo[nThreads]);
			SetThreadPriority(aThreads[nThreads], THREAD_PRIORITY_HIGHEST);
			nThreads++;
		}else{
			DWORD dwBytesRead = 0, dwBytesWritten = 0;
			HANDLE hFileSource, hFileDestiny;
			char text[100];

			hFileSource = CreateFile(data2->source, GENERIC_WRITE | GENERIC_READ, FILE_SHARE_WRITE, NULL, OPEN_EXISTING, FILE_ATTRIBUTE_NORMAL, NULL);
			ReadFile(hFileSource, text, 100, &dwBytesRead, NULL);

			hFileDestiny = CreateFile(data2->destiny, GENERIC_WRITE | GENERIC_READ, FILE_SHARE_READ | FILE_SHARE_WRITE, NULL, CREATE_NEW, FILE_ATTRIBUTE_NORMAL, NULL);
			WriteFile(hFileDestiny, text, strlen(text), &dwBytesWritten, NULL);

			CloseHandle(hFileSource);
			CloseHandle(hFileDestiny);
		}
	}
	
	for(int i = 0; i < nThreads; ++i){
		WaitForSingleObject(aThreads[i], INFINITE);
		CloseHandle(aThreads[i]);
	}
}

int main (){
	info data;

	printf("Ingrese la ruta original: \n");
	fflush(stdin);
	scanf ("%s", data.source);

	printf("\nIngrese la ruta destino: \n");
	fflush(stdin);
	scanf ("%s", data.destiny);

	if(CreateDirectory(data.destiny, NULL) == 0){
		printf("No se pudo crear el directorio %s.\n", data.destiny);
		exit(0);
	}
	
	DWORD idHilo;
	HANDLE mainThread;
	
	mainThread = CreateThread(NULL, 0, copyDirectory, &data, 0, &idHilo);
	SetThreadPriority(mainThread, THREAD_PRIORITY_HIGHEST);
	
	WaitForSingleObject(mainThread, INFINITE);
	CloseHandle(mainThread);

	return 0;
}