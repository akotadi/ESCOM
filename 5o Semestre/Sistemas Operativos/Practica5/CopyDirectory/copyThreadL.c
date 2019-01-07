#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <dirent.h>
#include <pthread.h>

typedef struct Information{
    char source[200];
    char destiny[200];
} info;

void* copyDirectory(void *lpParam){
	info *data = (info *)lpParam;	

	DIR *dirh;
	if (!(dirh = opendir(data->source)))
	{
		perror("opendir");
		exit(0);
	}

	struct dirent *aFile;
	int nThreads = 0;
	pthread_t *aThread = calloc(100, sizeof(pthread_t));
	while(aFile = readdir(dirh)){
		if(!strcmp(aFile->d_name, ".") || !strcmp(aFile->d_name, ".."))
			continue;
		info *data2 = calloc(1, sizeof(info));
		sprintf(data2->source, "%s/%s", data->source, aFile->d_name);
		sprintf(data2->destiny, "%s/%s", data->destiny, aFile->d_name);
		if(aFile->d_type & DT_DIR){
			mkdir(data2->destiny, 0777);
			pthread_create(&aThread[nThreads++], NULL, copyDirectory, (void*)data2);
		}else if(aFile->d_type & DT_REG){
			int fSource = open(data2->source, O_RDONLY);
			int fDestiny = open(data2->destiny, O_WRONLY | O_CREAT, S_IRUSR | S_IWUSR);
			if (fSource != -1)
			{
				char c;
				while (read(fSource, &c, sizeof (c) != 0))
				{
					write(fDestiny, &c, sizeof (c));
				}
			}else{
				perror("open");
				exit(0);
			}
			close(fDestiny);
			close(fSource);
		}
	}
	
	for(int i = 0; i < nThreads; ++i){
		pthread_join(aThread[i], NULL);
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

	mkdir(data.destiny, 0777);

	pthread_t mainThread;
	pthread_create(&mainThread, NULL, copyDirectory, (void*)&data);
	pthread_join(mainThread, NULL);
    
    return 0;
}