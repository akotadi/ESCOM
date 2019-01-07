/*
Implementaci�n Pr�ctica 01: Evaluaci�n de expresiones infijas.
Por: #TeamYalja
Versi�n: 2.2

Descripci�n: Con la implementaci�n del TAD Pila en C, (est�tica y din�mica) 
el programa valida y evalua una expresi�n infija considerando 
la precedencia de los operadores, tiene tres etapas:
	-Evaluaci�n de par�ntesis
	-Conversi�n a posfijo
	-Evaluaci�n de expresi�n posfija
	
Observaciones: El programa est� limitado a una expresi�n de 100 car�cteres 
contando letras (may�sculas �nicamente), operadores y par�ntesis; dada la 
limitaci�n de la computadora donde se ejecute, los resultados de las operaciones 
pueden no mostrarse adecuadamente, en caso de que haya divisiones puede 
indeterminarse si el dividendo es 0. S�lo pueden ingresarse n�meros naturales y 0.
*/


//LIBRER�AS
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include "TADPilaDin.c"


//DEFINICI�N DE FUNCIONES

/*
Descripci�n: Funci�n normal que checa que todos los par�ntesis est�n bien.
Recibe: pila *s (Referencia a la pila "s" a operar), char c[] (Arreglo que contiene la expresi�n ingresada por el usuario).
Devuelve:
Observaciones: En caso de haber un error en la expresi�n, la funci�n forzar� el cierre del programa.
*/
void checar(pila *s,char c[]){
    elemento e; // Elemento declarado como auxiliar en la funci�n
    int i;
    for(i=0; i < strlen(c) ;i++){ // Itineraci�n que terminar� al concluir la expresi�n
        if(c[i] == '('){
            e.c='(';
            Push(s,e);
        }else{
            if(c[i] == ')'){
                if(Empty(s)){ //empty(s) == TRUE (Pila vac�a)
                    printf("\nError: Se cerro un parentesis sin abrir otro");
                    exit(1);
                }else{
                    e=Pop(s);
                }  
            }
        }
    }
    if(Empty(s)){ //empty(s) == TRUE (Pila vac�a)
        printf("\nNumero correcto de parentesis");
    }else{ //empty(s) == FALSE (Pila con elementos a�n)
        printf("\nError en la expresion");
        exit(1);
    }
    return ;
}


/*
Descripci�n: Funci�n que cambia los elementos de una pila si es que uno de ellos tiene mayor o igual prioridad del que est� en el tope
Recibe: char c (Operando que se deber� cambiar), pila *s (Referencia a la pila "s" a operar), pila *guarda (Referencia a la pila "guarda" a operar)
Devuelve:
Observaciones: Esta funci�n es usada en la conversi�n a posfijo
*/
void cambio(char expre,pila* s,pila* guarda){
    elemento e; // Elemento declarado como auxiliar en la funci�n
    e=Pop(s);
    printf("%c",e.c);
    Push(guarda,e);
    e.c=expre;
    Push(s,e);
    return ;
}


/*
Descripci�n: Saca todos los valores de una pila y los mete a otra para que est�n invertidos
Recibe: pila *guarda (Referencia a la pila "guarda" a operar), pila *inver (Referencia a la pila "inver" a operar), 
	float *valores (Referencia al arreglo que contiene los valores de las variables a ocupar)
Devuelve:
Observaciones: Su principal funci�n es hacer m�s pr�ctica la evaluaci�n de la expresi�n
*/
void orden(pila* guarda,pila* inver,float* valores){
    elemento e; // Elemento declarado como auxiliar en la funci�n
    while(!Empty(guarda)){ // Itinireaci�n que durar� hasta que la pila est� vac�a
        e=Pop(guarda);
        if(e.c > 64 && e.c < 92){
        	e.n=valores[e.c-65];
		}		
        Push(inver,e);
    }
    return ;
}


/*
Descripci�n: Funci�n que realiza la operaci�n y mete el valor en una pila a parte
Recibe: elemento e (Operando a ocupar en la evaluaci�n), pila *sum (Referencia a la pila "sum" a operar)
Devuelve:
Observaciones:
*/
void eleccion(elemento e,pila* sum){ 
	elemento r; // Elemento declarado como auxiliar en la funci�n
	float x1=Pop(sum).n; // Variable auxiliar para realizar la operaci�n
	printf("\nVALOR 1: %f",x1);
	float x2=Pop(sum).n; // Variable auxiliar para realizar la operaci�n
	printf("\nVALOR 1: %f",x2);
	if(e.c == '+'){
        r.n= x2+x1;
        Push(sum,r); // Mete el valor de la operacion en otra pila
	}
    if(e.c == '-'){
        r.n= x2-x1;
    	Push(sum,r); // Mete el valor de la operacion en otra pila
    }
    if(e.c == '*'){
        r.n= x2*x1;
        Push(sum,r); // Mete el valor de la operacion en otra pila
    }
    if(e.c == '/'){
        r.n= x2/x1;
        Push(sum,r); // Mete el valor de la operacion en otra pila
    }
    if(e.c == '^'){
        r.n= pow(x2,x1);
        Push(sum,r); // Mete el valor de la operacion en otra pila
    }
	return ; 
}


/*
Descripci�n: Funci�n que checa los valores que hay en la pila para hacer las operaciones necesarias
Recibe: pila *s (Referencia a la pila "s" a operar), pila *inver (Referencia a la pila "inver" a operar), 
	float *valores (Referencia al arreglo que contiene los valores de las variables a ocupar)
Devuelve:
Observaciones: Hay 3 casos especiales a considerar:
	1.-Cuando en el tope hay 2 numeros seguidos en la pila, entonces el siguiente obligatoriamente tiene que ser un operador
	2.-Cuando el tope es un numero y el elemento que le sigue en la pila es un operador, entonces significa que ya hay un valor en la otra pila de valores
	3.-Cuando el tope de la pila es un operador significa que los dos operandos est�n en la otra pila
*/
void suma(pila* sum,pila* inver, float* valores){
	elemento e; // Elemento declarado como auxiliar en la funci�n
	if(!Empty(inver)){ // Itineraci�n que durar� hasta que la pila est� vac�a
		e=Pop(inver);
		if(e.c > 64 && e.c < 92){ // En caso de ser una letra
			Push(sum,e);
			suma(sum,inver,valores);
		}
		else{ // En caso de ser un operando
			eleccion(e,sum);
			printf("\nValor de la operacion: %f\n",Top(sum).n);
			suma(sum,inver,valores);
		}
	}else{ // Al vaciar la pila inicial, �nicamente queda el resultado en la secundaria
		e=Pop(sum);
		printf("\n\nResultado: %f",e.n);
	}
	return ;
}


/*
Descripci�n: Funci�n que regresa un valor dependiendo del operador que reciba
Recibe: char c (Operando al que se le establecer� un valor)
Devuelve: int (Un valor num�rico del 0 al 3)
Observaciones: Esto se usa para el posfijo ya que se necesita establecer prioridad de operadores para la entrada en la pila
*/
int regresaValor(char c){
	int k=0; // Elemento declarado como auxiliar en la funci�n
	if(c == '^'){ // Operando a manejar de mayor jerarqu�a
		k=3;
	}else{
		if(c == '*' || c == '/'){ 
			k=2;
		}else{
			if(c == '+' || c == '-'){
				k=1;
			}
		}
	} 
	return k; // En caso de ser un par�ntesis, se manejar� como un "0"
}


/*
Descripci�n: Funci�n que transforma la expresi�n normal a la expresi�n de tipo posfijo
Recibe: pila *pi (Referencia a la pila "pi" a operar), char c[] (Arreglo que contiene la expresi�n ingresada por el usuario),
	pila *guarda (Referencia a la pila "guarda" a operar)
Devuelve: float * (Referencia al arreglo que contiene los valores de las variables necesarias)
Observaciones: Durante la ejecuci�n se crear� un arreglo para las variables, adem�s se pedir� su valor una vez determinado cu�les se ocupar�n en la expresi�n.
*/
float* posfijo(pila* pi,char expre[], pila* guarda){ 
    elemento e; 
	int n;// Elemento declarado como auxiliar en la funci�n
    printf("\nExpresion en posfijo:\n");
    
    float* valores;
    valores=(float*)malloc(26*sizeof(float)); // Se crea un arreglo de 26 elementos para guardar los valores de las letras que sean introducidas en la expresi�n
    int j;
    for(j=0;j<26;j++){
        valores[j]=-1;// Se le pone -1 a cada espacio para saber qu� no se ha usado esa letra
    }
    
    int i;
    for(i =0; i< strlen(expre); i++){ // Itineraci�n que terminar� al concluir la expresi�n
        if(expre[i] == '(' ||expre[i] == '^'||expre[i] == '*' || expre[i] == '/'||expre[i] == '+' || expre[i] == '-'){ // Validaci�n para saber si es un elemento que va a ser agregado a la pila
            if(!Empty(pi) && expre[i] != '('){
				if(regresaValor(Top(pi).c) >= regresaValor(expre[i])){ // Si el valor que regresa es mayor o igual que el que esta hasta arriba entonces se produce un cambio
					cambio(expre[i],pi,guarda);
				}
				else{
					e.c=expre[i]; // Si es menor entoncs s�lo se pone arriba
                    Push(pi,e);
				}
            }
            else{ // Si el tope es nulo entonces s�lo se coloca
                e.c=expre[i];
                Push(pi,e);
            }
        }
        else{ // Si no es operador que vaya en la pila directamente entonces hay dos posibilidades
            if(expre[i] == ')'){ //Si es un par�ntesis que cierra entonces se hace pop de todos los elementos hasta que se encuentre el par�ntesis que abre
                while(Top(pi).c != '('){
                    e=Pop(pi);
                    printf("%c",e.c);
                    Push(guarda,e); // Se guarda en una pila auxiliar el elemento, ya que se ultilizar� posteriormente
                }
                e=Pop(pi); // Sirve para sacar el par�ntesis abierto que se queda dentro de la pila
            }
            else{ // Si es una letra, entonces se agrega directamente
                e.c=expre[i];
                printf("%c",expre[i]);
                Push(guarda,e);
                valores[expre[i]-65]=0; // Se cambia el valor en el arreglo de valores para saber que esa letra ser� usada
            }
        }
    }
    
    if(!Empty(pi)){ // Mientras la pila con los operadores no este vacia, se imprimir�n los que queden para que se termine la expresi�n
        while(!Empty(pi)){
            e=Pop(pi);
            printf("%c",e.c);
            Push(guarda,e);
        }
    }
    
    int k;
    
	/*while(n != 1 || n != 2){ // Pregunta est�tica para saber si se desea continuar con el programa
		printf("\n\nEvaluar expresion?");
	printf("\n1.- Si		2.- No (Salir)\n");
	scanf("%i",&n);
	if(n==1){ // Si se desea seguir con la ejecuci�n del programa
		*/for(k =0; k< 26;k++){ //Itineraci�n que sirve para introducir los valores a las variables que ser�n usadas
    	if(valores[k] != -1){
    		printf("\nIntroduzca el valor de %c: ",k+65);
            scanf("%f",&valores[k]);
		}
	}
    printf("\n");
    return valores;
	/*}
	else if (n == 2)// En caso de no requerirse, se fuerza el cierre
		exit(1);
	}*/
}



/*
Descripci�n: Funci�n usada para iniciar todas las pilas correspondientes
Recibe: Referencia a todas las pilas usadas en el programa
Devuelve:
Observaciones: Su principal funci�n es est�tica.
*/
void iniciarTodo(pila* s,pila* pi,pila* guarda,pila* inver,pila* sum){
	Initialize(s); 
    Initialize(pi); 
    Initialize(guarda); 
    Initialize(inver); 
	Initialize(sum); 
	return ;
}


/*
Descripci�n: Funci�n usada para destruir todas las pilas correspondientes
Recibe: Referencia a todas las pilas usadas en el programa
Devuelve:
Observaciones: Su principal funci�n es est�tica.
*/
void destruirTodo(pila* s,pila* pi,pila* guarda,pila* inver,pila* sum){
	Destroy(sum);
    Destroy(inver);
    Destroy(guarda);
    Destroy(pi);
    Destroy(s);
	return ;
}



/*
Variables usadas en el programa:
	Pilas:
		s.- Pila que servir� para checar parentesis
    	pi.- Pila que servir� para ayudar a guardar los operadores temporalmente en el posfijo
    	guarda.- Pila que servir� para guardar lo que se vaya mostrando de la expresi�n posfija
    	inver.- Pila que servir� para contendr� la expresi�n que fue mostrada, pero invertida
		sum.- Pila que servir� para realizar las operaciones finales
	float* valores.- Arreglo din�mico que contendr� los valores de las variables en la expresi�n
	char expreson[100].- Arreglo est�tico qe contendr� la expresi�n a evaluar
*/
int main() {
	
	//Declaraci�n de variables a ocupar
    pila s,pi,guarda,inver,sum;
    float* valores;
    char expresion[100];
    
    iniciarTodo(&s,&pi,&guarda,&inver,&sum); // Inicializa todas las pilas a ocupar
    
    printf("Introduce la cadena: ");
    scanf("%s",&expresion); 			// Se introduce la expresi�n a evaluar desde teclado
	printf("\n%s",expresion);
    
	checar(&s,expresion); // Verifica que la funci�n est� correctamente escrita en cuanto a par�ntesis
	
	printf("\n");
    valores=posfijo(&pi,expresion,&guarda); // Convierte la expresi�n a posfijo y a su vez se solicitan los valores de las variables desde teclado
    
    orden(&guarda,&inver,valores); // Invierte el orden de la pila para poder proceder a la evaluaci�n
    
	suma(&sum,&inver,valores); // Conociendo los valores, realizamos las operaciones para conocer el resultado
	
	destruirTodo(&s,&pi,&guarda,&inver,&sum); // Se destruyen las pilas ocupadas
	
	free(valores); // Se libera el arreglo din�mico de memoria
	
    return (EXIT_SUCCESS);
}
