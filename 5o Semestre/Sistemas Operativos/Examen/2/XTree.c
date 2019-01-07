#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>

int main(int argc, char const *argv[])
{
	int id_proc[100], i, j, h;
	for (h = 0, i = 0; h < 5; ++h)
	{
		if ((id_proc[i] = fork()) == 0)
		{
			printf("Proceso %i\n", i);
			for (i++; i <= 5; ++i)
			{
				if ((id_proc[i] = fork()) == 0)
				{
					printf("Proceso %i\n", i);
					if (i == 4)
					{
						for (i++; i <= (10-h); ++i)
						{
							if ((id_proc[i] = fork()) == 0)
							{
								printf("Proceso %i\n", i);
								exit(0);
							}
						}
						for (j = 0; j < (6-h); ++j)
						{
							wait(0);
						}
					}
				}else{
					break;
				}
			}
			for (j = 0; j < 4; ++j)
			{
				wait(0);
			}
			exit(0);
		}
	}
	for (h = 0; h < 5; ++h)
	{
		wait(0);
	}
	return 0;
}