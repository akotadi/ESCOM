
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

int numero(int i);
char* crear(char*, int);
char** crearMatriz(int, int);
void dato(char*, int);
void imprimir(char*, int);
void menu(char*, char**, char**, int, int, int, int, int);
void liberar(char*, char**, char**, int, int);
void generar(char* ,char**,int ,int ,int);
void mostrar(char** L1,int p,int l);
void unir(char**L1,char** L2,int p1,int p2, int l1, int l2);
void concatena(char**L1,char** L2,int p2,int p1, int, int);
void potencia(char**,int ,int);
void curp();
void repetido(char* E,char** L,int i, int p, int l);
void resta(char**L1,char** L2,int p1,int p2, int l1, int l2);
int falso(char* L1, char* L2, int l);

main()
{
	int i, p1, l1, p2, l2;
	char *E;
	char **L1, **L2;
	menu(E, L1, L2, i, p1, l1, p2, l2);
}

void menu(char *E, char **L1, char **L2, int i, int p1, int l1, int p2, int l2)
{
int z, x;
	printf("\n\n					MENU\n");
	do
	{
		printf("\n\n");
		printf("1= Ingresar Alfabeto\n");
		printf("2= Generar Lenguaje L1\n");
		printf("3= Generar Lenguaje L2\n");
		printf("4= Generar Lenguaje Lu (Union)\n");
		printf("5= Generar Lenguaje Lc (Concatenacion)\n");
		printf("6= Generar Lenguaje Lp (Potencia)\n");
		printf("7= Mostrar\n");
		printf("8= Generear CURP\n");
		printf("9= Generear Diferencia L1-L2\n");
		printf("0= Salir");
		printf("\n");
		scanf("%d", &z);

		if(z==1)
		{
			i=numero(i);
			E=crear(E, i);
			dato(E, i);
		}
		
		if(z==2)
		{
			printf("Ingrese numero de palabras= ");
			scanf("%d", &p1);
			printf("Ingrese longuitud= ");
			scanf("%d", &l1);
			L1=crearMatriz(p1, l1);
			generar(E, L1, i, p1, l1);
			printf("\n\n");
			mostrar(L1, p1, l1);
			
		}
		
		if(z==3)
		{
			printf("Ingrese numero de palabras= ");
			scanf("%d", &p2);
			printf("Ingrese longuitud= ");
			scanf("%d", &l2);
			L2=crearMatriz(p2, l2);
			generar(E, L2, i, p2, l2);
			printf("\n\n");
			mostrar(L2, p2, l2);
		}
		
		if(z==4)
			unir(L1, L2, p1, p2, l1, l2);
			
		if(z==5)
			concatena(L1, L2, p1, p2, l1, l2);
			
		if(z==6)
		{
				printf("Seleccione Lenguaje= \n1= L1\n2= L2\n");
				scanf("%d", &x);
				if(x==1)
					potencia(L1, p1, l1);
				if(x==2)
					potencia(L2, p2, l2);
		}
	
			
		if(z==7)
		{
			printf("\n");
			mostrar(L1, p1, l1);
			printf("\n");
			mostrar(L2, p2, l2);
		}
		
		if(z==8)
			curp();
		if(z==9)
			resta(L1, L2, p1, p2, l1, l2);
		
	}while(z!=0);
	liberar(E, L1, L2, p1, p2);
}	


void generar(char* E,char** L, int i, int p, int l)
{
	int n, x, j;  
    int hora = time(NULL);  
    srand(hora);  
        for(x=0;x<p;x++)  
        {  
			for(j=0;j<l;j++)  
        	{ 	
               n= rand()%(i);  
               L[x][j]=E[n];
           }
        }
    repetido(E, L, i, p, l);
}

void repetido(char* E,char** L,int i, int p, int l)
{
	int m, j, k, n;
		for(m=0;m<p;m++)
		{
			for(j=0;j<p;j++)
			{
				if(m==j)
				{
					break;
				}
					
				if(falso(L[m], L[j], l) == 0 )
				{
					for(k=0;k<l;k++)
					{
						n=rand()%(i);
						L[m][k]=E[n];
					}
					repetido(E, L, i, p, l);
				}
			}
		}
	
}

int falso(char* L1, char* L2, int l)
{
	int i, x=0;
	for(i=0;i<l;i++)
	{
		if(L1[i]==L2[i])
			x++;
	}
	
	if(x==l)
		return 0;
	else
		return 1;
	
}


char** crearMatriz(int p, int l)
{
	int i;
	char **A;
	A=(char**)malloc((p)*sizeof(char*));
	if(A==NULL)
	{
		printf("No hay suficiente memoria\n");
		exit(0);
	}
	for(i=0;i<p;i++)
	{
		A[i]=(char*)malloc((l)*sizeof(char));
		if(A[i]==NULL)
		{
			printf("No hay suficiente memoria\n");
			exit(0);
		}
	}
return A;
}


void liberar(char *E, char **L1, char **L2, int p1, int p2)
{
	int i, j;
	free(E);
	
	for(i=0;i<p1;i++)
		free(L1[i]);
		
	for(j=0;j<p2;j++)
		free(L2[j]);
	
	free(L1);
	free(L2);
	
	printf("\n\n...Liberando Memoria...\n");
}


int numero(int i)
{
	printf("Ingrese Dimension= ");
	scanf("%d", &i);
return i;
}


char* crear(char* E, int i)
{
	E=(char*)malloc(sizeof(char)*i);
	if(E==NULL)
	{
		printf("No hay sufciente memoria\n");
		exit(0);
	}
	return E;
}


void mostrar(char **L1,int p,int l)
{
	int i, j;
	printf("El lenguaje es=\n");
	for(i=0;i<p;i++)
	{
		for(j=0;j<l;j++)
			printf("%c", L1[i][j]);
		printf(" ");
	}
}


void dato(char *E, int i)
{
	int x;
	printf("Ingrese Elementos= ");
		getchar();
		scanf("%[^\n]", E);
	
}


void imprimir(char *E, int i)
{
	int x;
	for(x=0;x<i;x++)
	{
		printf("%c ",E[x]);
	}
	printf("\n");
}


void unir(char**L1,char** L2,int p1,int p2, int l1,int l2)
{
	int i, j, k, m, x;
	printf("\n\nEl Lenguaje L1UL2=\n");
	for(i=0;i<p1;i++)
	{
		for(k=0;k<l1;k++)
			printf("%c", L1[i][k]);
		printf(" ");
	}
	
	for(i=0;i<p2;i++)
	{
		x=0;
		for(j=0;j<p1;j++)
		{
			if(falso(L2[i], L1[j], l1) == 0 )
			{
				x=1;
			}			
		}
		if(x==0)
		{
			for(m=0;m<l2;m++)
					printf("%c", L2[i][m]);
				printf(" ");
		}
	}		
}


void concatena(char**L1,char** L2,int p1,int p2, int l1, int l2)
{
	printf("\n\nEl Lenguaje L1*L2=\n");
	int i, j, k, m;
	printf("\n");
	for(i=0;i<p1;i++)
	{
		for(j=0;j<p2;j++)
			{
				for(m=0;m<l1;m++)
					printf("%c", L1[i][m]);
				for(m=0;m<l2;m++)
					printf("%c", L2[j][m]);	
				printf(" ");
			}
		printf("\n");
	}
}


void resta(char**L1,char** L2,int p1,int p2, int l1, int l2)
{
	int i, j, k, m;
	printf("\n\nEl Lenguaje L1-L2=\n");
	for(i=0;i<p1;i++)
	{
		k=0;
		for(j=0;j<p2;j++)
		{
			if(falso(L1[i], L2[j], l1) == 0 )
			{
				k=1;
			}			
		}
		if(k==0)
		{
			for(m=0;m<l1;m++)
					printf("%c", L1[i][m]);
				printf(" ");
		}
	}
}


void potencia(char**L1, int p1, int l1)
{
	int i, j, z, m, k, l, n, x;
	printf("Ingrese Potencia= ");
	scanf("%d", &z);

		if(z>0)
		{
			if(z==1)
			{
				for(i=0;i<p1;i++)
				{
					for(k=0;k<l1;k++)
						printf("%c", L1[i][k]);
					printf(" ");
				}
			}
			
			if(z==2)
			{
				for(i=0;i<p1;i++)
				{
					for(j=0;j<p1;j++)
					{
						for(m=0;m<l1;m++)
							printf("%c", L1[i][m]);
						for(m=0;m<l1;m++)
							printf("%c", L1[j][m]);	
						printf(" ");
					}
				printf("\n");
				}
			}
			
			if(z==3)
			{
				for(i=0;i<p1;i++)
				{
					for(j=0;j<p1;j++)
					{
						for(k=0;k<p1;k++)
						{
							for(m=0;m<l1;m++)
								printf("%c", L1[i][m]);
							for(m=0;m<l1;m++)
								printf("%c", L1[j][m]);	
							for(m=0;m<l1;m++)
								printf("%c", L1[k][m]);	
						printf(" ");
						}
					}
				printf("\n");
				}
			}
			
			if(z==4)
			{
				for(i=0;i<p1;i++)
				{
					for(j=0;j<p1;j++)
					{
						for(k=0;k<p1;k++)
						{
							for(l=0;l<p1;l++)
							{
								for(m=0;m<l1;m++)
									printf("%c", L1[l][m]);
								for(m=0;m<l1;m++)
									printf("%c", L1[k][m]);	
								for(m=0;m<l1;m++)
									printf("%c", L1[j][m]);	
								for(m=0;m<l1;m++)
									printf("%c", L1[i][m]);
								printf(" ");
							}
						}
					}
				printf("\n");
				}
			}
			
			if(z==5)
			{
				for(i=0;i<p1;i++)
				{
					for(j=0;j<p1;j++)
					{
						for(k=0;k<p1;k++)
						{
							for(l=0;l<p1;l++)
							{
								for(n=0;n<p1;n++)
								{
									for(m=0;m<l1;m++)
										printf("%c", L1[n][m]);
									for(m=0;m<l1;m++)
										printf("%c", L1[l][m]);	
									for(m=0;m<l1;m++)
										printf("%c", L1[k][m]);	
									for(m=0;m<l1;m++)
										printf("%c", L1[j][m]);
										for(m=0;m<l1;m++)
										printf("%c", L1[i][m]);
									printf(" ");
								}
							}
						}
					}
				printf("\n");
				}
			}
		}

		if(z==0)
			printf("(Lambnda)\n");
			
		if(z<0)
		{
			z=(-1)*z;
			if(z==1)
			{
				for(i=0;i<p1;i++)
				{
					for(m=(l1-1);m>-1;m--)
						printf("%c", L1[i][m]);
					printf(" ");
				}
			}
			
			if(z==2)
			{
				for(i=0;i<p1;i++)
				{
					for(j=0;j<p1;j++)
					{
						for(m=(l1-1);m>-1;m--)
							printf("%c", L1[j][m]);	
						for(m=(l1-1);m>-1;m--)
							printf("%c", L1[i][m]);
						printf(" ");
					}
				printf("\n");
				}
			}
			
			if(z==3)
			{
				for(i=0;i<p1;i++)
				{
					for(j=0;j<p1;j++)
					{
						for(k=0;k<p1;k++)
						{
							for(m=(l1-1);m>-1;m--)
								printf("%c", L1[k][m]);
							for(m=(l1-1);m>-1;m--)
								printf("%c", L1[j][m]);	
							for(m=(l1-1);m>-1;m--)
								printf("%c", L1[i][m]);	
						printf(" ");
						}
					}
				printf("\n");
				}
			}
			
			if(z==4)
			{
				for(i=0;i<p1;i++)
				{
					for(j=0;j<p1;j++)
					{
						for(k=0;k<p1;k++)
						{
							for(l=0;l<p1;l++)
							{
								for(m=(l1-1);m>-1;m--)
									printf("%c", L1[i][m]);
								for(m=(l1-1);m>-1;m--)
									printf("%c", L1[j][m]);	
								for(m=(l1-1);m>-1;m--)
									printf("%c", L1[k][m]);	
								for(m=(l1-1);m>-1;m--)
									printf("%c", L1[l][m]);
								printf(" ");
							}
						}
					}
				printf("\n");
				}
			}
			
			if(z==5)
			{
				for(i=0;i<p1;i++)
				{
					for(j=0;j<p1;j++)
					{
						for(k=0;k<p1;k++)
						{
							for(l=0;l<p1;l++)
							{
								for(n=0;n<p1;n++)
								{
									for(m=(l1-1);m>-1;m--)
										printf("%c", L1[i][m]);
									for(m=(l1-1);m>-1;m--)
										printf("%c", L1[j][m]);	
									for(m=(l1-1);m>-1;m--)
										printf("%c", L1[k][m]);	
									for(m=(l1-1);m>-1;m--)
										printf("%c", L1[l][m]);
										for(m=(l1-1);m>-1;m--)
										printf("%c", L1[n][m]);
									printf(" ");
								}
							}
						}
					}
				printf("\n");
				}
			}
		}
}


void curp()
{
	int nombre, dia, mes, ano, genero, estado, c1, c2, c3, n1, n2;
	char dias[3], meses[3], anos[3];
	char HM[3]="HM";
	char Nombres[20][5] = {{"AABS"},{"BOMC"},{"CAEJ"},{"DOAY"},{"ESRA"},{"FEDR"},{"GABJ"},{"HEVE"},{"JAMS"},{"LORJ"},{"MARS"},{"RERR"},{"RILA"},{"RISE"},{"ROMJ"},{"VISA"},{"CARL"},{"SABM"},{"ROLM"},{"FEGI"}};	
	char Estados[33][3]={{"AS"},{"BC"},{"BS"},{"CC"},{"CL"},{"CM"},{"CS"},{"CH"},{"DF"},{"DG"},{"GT"},{"GR"},{"HG"},{"JC"},{"MC"},{"MN"},{"MS"},{"NT"},{"NL"},{"OC"},{"PL"},{"QT"},{"QR"},{"SP"},{"SL"},{"SR"},{"TC"},{"TS"},{"TL"},{"VZ"},{"YN"},{"ZS"},{"NE"}};
    printf("\n");
    char Consonates[]={"BDFGHIJKLMNPQRSTVWXYZ"};
    
    nombre= rand()%(20);
    estado= rand()%(33);
    dia=rand()%(31);
    dia=dia+1;
    mes=rand()%(12);
    mes=mes+1;
    ano=rand()%(100);
    genero=rand()%(2);
    c1=rand()%20;
    c2=rand()%20;
    c3=rand()%20;
    n1=rand()&9;
    n2=rand()&9;
    
    if(dia<10)
    {
    	itoa(dia,dias,10); 
    	dias[1]=dias[0];
    	dias[0]='0';
    	dias[2]='\0';
	}
    else
    	itoa(dia,dias,10); 
    
    if(mes<10)
    {
    	itoa(mes,meses,10); 
    	meses[1]=meses[0];
    	meses[0]='0';
    	meses[2]='\0';
	}
    else
    	itoa(mes,meses,10); 

	if(ano<10)
    {
 		itoa(ano,anos,10); 
    	anos[1]=anos[0];
    	anos[0]='0';
		anos[2]='\0';
	}
	
    else
    	itoa(ano,anos,10);


    printf("%s%s%s%s%c%s%c%c%c%d%d", Nombres[nombre], dias, meses, anos, HM[genero], Estados[estado],Consonates[c1],Consonates[c2],Consonates[c3], n1, n2);
}
