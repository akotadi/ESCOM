#include  <stdio.h>
#include  <string.h>
#include  <sys/types.h>
#include <unistd.h> 

#define   MAX_COUNT  10
#define   BUF_SIZE   100

void  main(void)
{
	pid_t  pid;
	int    i;
	char   buf[BUF_SIZE];

	for (int i = 0; i < 3; ++i)
	{
		fork();
	}
	sprintf(buf, "This line is from actual %d, with father %d\n", getpid(), getppid());
	write(1, buf, strlen(buf)); 
}