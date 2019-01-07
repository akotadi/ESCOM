#include <stdio.h>
#include <windows.h>

HANDLE *aProcess, *aThreads;
DWORD *aPIDs;

char* putTabs(int nTabs){
	nTabs++;
	char *sTabs = calloc((nTabs + 1)*2, sizeof(char));
	memset(sTabs, '\t', nTabs*2);
	sTabs[nTabs*2] = '\0';
	return sTabs;
}

void startChilds(int* nChilds, int* nLevel, char **argv){
	for(int i = 0; i < *nChilds; ++i){
		STARTUPINFO si;
		PROCESS_INFORMATION pi;
		ZeroMemory(&si, sizeof(si));
		si.cb = sizeof(si);
		ZeroMemory(&pi, sizeof(pi));
		char args[100];
		if (*nLevel == 0)
		{
			sprintf(args, "%s %d %d", argv[0], *nLevel + 1, 5);
		}else
		{
			sprintf(args, "%s %d %d", argv[0], *nLevel + 1, 3);
		}
		CreateProcess(NULL, args, NULL, NULL, FALSE, 0, NULL, NULL, &si, &pi);
		aProcess[i] = pi.hProcess;
		aThreads[i] = pi.hThread;
		aPIDs[i] = pi.dwProcessId;
		printf("Creating %s%d from %d\n", putTabs(*nLevel), aPIDs[i], GetCurrentProcessId());
	}
}

void endChilds(int* nChilds){
	for(int i = 0; i < *nChilds; ++i){
		WaitForSingleObject(aProcess[i], INFINITE);
		CloseHandle(aProcess[i]);
		CloseHandle(aThreads[i]);
	}
}

int main(int argc, char *argv[]){

	int nLevel, nChilds;
	nLevel = atoi(argv[1]);
	nChilds = atoi(argv[2]);

	if (nLevel == 0)
	{
		printf("Action\t\tMain\t\tFirst\t\tSecond\t\tThird\n\n");
	}else if (nLevel > 3)
	{
		exit(0);
	}

	printf("Start %s%d\n", putTabs(nLevel), GetCurrentProcessId());

	if (nLevel < 3)
	{
		aProcess = calloc(nChilds, sizeof(HANDLE));
		aThreads = calloc(nChilds, sizeof(HANDLE));
		aPIDs = calloc(nChilds, sizeof(DWORD));

		startChilds(&nChilds, &nLevel, argv);

		endChilds(&nChilds);

		printf("End %s%d\n", putTabs(nLevel), GetCurrentProcessId());

		free(aProcess);
		free(aThreads);
		free(aPIDs);
	}

	return 0;
}