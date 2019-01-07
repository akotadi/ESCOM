#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main(int argc, char const *argv[])
{
	int id_proc, index = 0;
	for (int i = 0; i < 3; ++i)
	{
		if(fork() == 0){
			index += pow(2,i);
		}
		if (index > 4)
		{
			exit(0);
		}
	}
	// printf("%i\n",index);
	int aux1 = 0;
	if (index == 0 || index == 4)
	{
		for (int i = 0; i < 2; ++i)
		{
			if(fork() == 0){
				aux1 += pow(2,i);
			}
			if (aux1 > 2)
			{
				exit(0);
			}
		}
		// printf("%i:%i\n",index,aux1);
		int aux2 = 0;
		if (aux1 == 0 || aux1 == 2)
		{
			if (fork() == 0)
			{
				exit(0);
			}
			// printf("%i:%i:%i\n",index,aux1,aux2);
			int aux3 = 0;
			if (index == 0 && aux1 == 2)
			{
				if(fork() == 0){
					aux3++;
				}
				// printf("%i:%i:%i:%i\n",index,aux1,aux2,aux3);
			}else if (index == 4)
			{
				if (aux1 == 0)
				{
					if (fork() == 0)
					{
						// printf("%i:%i:%i:%i\n",index,aux1,aux2,++aux3);
						int aux4 = 0;
						for (int i = 0; i < 2; ++i)
						{
							if(fork() == 0){
								aux4 += pow(2,i);
							}
						}
						// printf("%i:%i:%i:%i:%i\n",index,aux1,aux2,aux3,aux4);
					}else{
						// printf("%i:%i:%i:%i\n",index,aux1,aux2,aux3);
					}
				}else if (aux1 == 2)
				{
					for (int i = 0; i < 3; ++i)
					{
						if(fork() == 0){
							aux3 += pow(2,i);
						}
						if (aux3 > 4)
						{
							exit(0);
						}
					}
					// printf("%i:%i:%i:%i\n",index,aux1,aux2,aux3);
					int aux4 = 0;
					if (aux3 == 2)
					{
						if (fork() == 0)
						{
							aux4++;
						}
						// printf("%i:%i:%i:%i:%i\n",index,aux1,aux2,aux3,aux4);
					}else if (aux3 == 4)
					{
						if (fork() == 0)
						{
							exit(0);

						}else{
							int aux5 = 0;
							for (int i = 0; i < 2; ++i)
							{
								if(fork() == 0){
									aux5 += pow(2,i);
								}
								if (aux5 > 2)
								{
									exit(0);
								}
							}
							// printf("%i:%i:%i:%i:%i:%i\n",index,aux1,aux2,aux3,aux4,aux5);
						}
					}
				}
			}
		}
	}
	else if (index == 2)
	{
		if (fork() == 0)
		{
			// printf("%i:%i\n",index,++aux1);
			int aux2 = 0;
			if (fork() == 0)
			{
				// printf("%i:%i:%i\n",index,aux1,++aux2);
				int aux3 = 0;
				if (fork() == 0)
				{
					exit(0);
				}else{
					// printf("%i:%i:%i:%i\n",index,aux1,aux2,aux3);
					int aux4 = 0;
					if (fork() == 0)
					{
						// printf("%i:%i:%i:%i:%i\n",index,aux1,aux2,aux3,++aux4);
						int aux5 = 0;
						if (fork() == 0)
						{
							aux5++;
						}
						// printf("%i:%i:%i:%i:%i:%i\n",index,aux1,aux2,aux3,aux4,aux5);
					}else{
						// printf("%i:%i:%i:%i:%i\n",index,aux1,aux2,aux3,aux4);
					}
				}
			}else{
				// printf("%i:%i:%i\n",index,aux1,aux2);
				int aux3 = 0;
				if (fork() == 0)
				{
					exit(0);
				}else{
					// printf("%i:%i:%i:%i\n",index,aux1,aux2,aux3);
					int aux4 = 0;
					for (int i = 0; i < 2; ++i)
					{
						if(fork() == 0){
							aux4 += pow(2,i);
						}
						if (aux4 > 2)
						{
							exit(0);
						}
					}
					// printf("%i:%i:%i:%i:%i\n",index,aux1,aux2,aux3,aux4);
					if (aux4 == 0 || aux4 == 2)
					{
						int aux5 = 0;
						if (fork() == 0)
						{
							aux5++;
						}
						// printf("%i:%i:%i:%i:%i:%i\n",index,aux1,aux2,aux3,aux4,aux5);
					}
				}
			}
		}else{
			// printf("%i:%i\n",index,aux1);	
		}
	}
	return 0;
}