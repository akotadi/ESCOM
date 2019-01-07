#include <stdio.h>

int main(int argc, char const *argv[])
{
	for (int i = 0; i < 255; ++i)
	{
		printf("%c\n",i);
		putc(i,stdout);
		printf("\n");
	}
	return 0;
}