#include <stdio.h>
#define PESOBIT(bpos) 1<<bpos
#define CONSULTARBIT(var,bpos) (*(unsigned*)&var & PESOBIT(bpos))?1:0
#define PONE_1(var,bpos) *(unsigned*)&var |= PESOBIT(bpos)
#define PONE_0(var,bpos) *(unsigned*)&var &= ~(PESOBIT(bpos))
#define CAMBIA(var,bpos) *(unsigned*)&var ^= PESOBIT(bpos)

int main(void)
{
	int i,n_bits; //Auxiliares
	unsigned char numero=0; //Variable de tipo char (byte)

	//Determinar la longitud de los bits a operar
	printf("Número de bits\n");
	n_bits=sizeof(numero)*8;
	printf("%2d bits",n_bits);	
	printf("\n");
	
	//Revisar el valor de cada bit
	printf("Valor de los bits\n");
	for (i=n_bits-1; i>=0; i--)
	printf("%2d",CONSULTARBIT(numero,i));
	printf("\t%u\n",numero);

	//Modificar el valor de algunos bits
	printf("Modificar el valor de los bits\n");
	PONE_1(numero,0); 		//1 en Bit 0
	PONE_1(numero,3); 		//1 en Bit 3
	PONE_1(numero,5); 		//1 en Bit 5
	PONE_0(numero,5); 		//0 en Bit 5
	
	CAMBIA(numero,0);		//Negar Bit 0
	CAMBIA(numero,3);		//Negar Bit 3
	CAMBIA(numero,7);		//Negar Bit 7
	CAMBIA(numero,0);		//Negar Bit 0

	//Revisar el valor de cada bit
	printf("Valor de los bits\n");
	for (i=n_bits-1; i>=0; i--)
	printf("%2d",CONSULTARBIT(numero,i));
	printf("\t%u\n",numero);
}