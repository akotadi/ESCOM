#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

#define TRUE 1
#define FALSE 0
typedef char boolean;

typedef struct elemento{//Se necesita un char para guardar las letras y un float para los valores
    char c;
    float n;
} elemento;

typedef struct nodo{
    elemento e;
    struct nodo *abajo;
}nodo;

typedef struct pila{
    nodo* top;
} pila;

void initialize(pila *s){
    s->top=NULL;
    return ;
}

void push(elemento e,pila *s){
    nodo* aux;
    aux=(nodo*)malloc(sizeof(nodo));
    aux->abajo=s->top;
    s->top=aux;
    aux->e=e;
    return ;
}


elemento pop(pila *s){
    elemento e;
    nodo* aux;
    e=s->top->e;
    aux=s->top;
    s->top=s->top->abajo;
    free(aux);
    return e;
}

boolean empty(pila *s){
    if(s->top == NULL){
        return TRUE;
    }
    return FALSE;
}

int size(pila *s){
    int k=0;
    nodo* aux;
    aux=s->top;
        while(aux != NULL){
            aux=aux->abajo;
            k++;
        }
        return k;
}


elemento top(pila *s){
    elemento e;
    e=s->top->e;
    return e;
}


void destroy (pila *s){
    nodo* aux;
    while(s->top != NULL){
        aux=s->top->abajo;
        free(s->top);
        s->top=aux;
    }
    return ;
}
//****************************************************************************************              P   I   L   A            *************************************************************************************************
//Funcion normal que checa que todos los parentesis esten bien
void checar(pila *s,char c[]){
    elemento e;
    int i;
    for(i=0; i < strlen(c) ;i++){
        if(c[i] == '('){
            e.c='(';
            push(e,s);
        }else{
            if(c[i] == ')'){
                if(empty(s)){ //empty(s) == TRUE
                    printf("\nError: Se cerro un parentesis sin abrir otro");
                    exit(1);
                }else{
                    e=pop(s);
                }  
            }
        }
    }
    if(empty(s)){ //empty(s) == TRUE
        printf("\nNumero correcto de parentesis");
    }else{
        printf("\nError en la expresion");
        exit(1);
    }
    return ;
}
//Funcion que cambia los elementos de una pila si es que uno de ellos tiene mayor o igual prioridad del que esta en el tope
//La pregunta de cual tiene mayor prioridad se hace en la funcion de posfijo
void cambio(boolean expre,pila* s,pila* guarda){
    elemento e;
    e=pop(s);
    printf("%c",e.c);
    push(e,guarda);
    e.c=expre;
    push(e,s);
    return ;
}
//Saca todos los valores de una pila y los mete a otra para que esten invertidos
void orden(pila* guarda,pila* inver,float* valores){
    elemento e;
    while(!empty(guarda)){
        e=pop(guarda);
        if(e.c > 64){
        	e.n=valores[e.c-65];
		}		
        push(e,inver);
    }
	printf("TERMINO BIEN BR0");   //(A+B)*(W/C)+F-J-A*(B/I)
    return ;
}

//	Funcion que realiza la operacion y mete el valor en una pila a parte
void eleccion(elemento e,pila* sum){ 
	elemento r;
	float x1=pop(sum).n;
	printf("\nVALOR 1: %f",x1);
	float x2=pop(sum).n;
	printf("\nVALOR 1: %f",x2);
	if(e.c == '+'){
        r.n= x2+x1;
        push(r,sum);// Mete el valor de la operacion en otra pila
	}
    if(e.c == '-'){
        r.n= x2-x1;
    	push(r,sum);
    }
    if(e.c == '*'){
        r.n= x2*x1;
        push(r,sum);
    }
    if(e.c == '/'){
        r.n= x2/x1;
        push(r,sum);
    }
    if(e.c == '^'){
        r.n= pow(x2,x1);
        push(r,sum);
    }
	return ; 
}

/*
	Funcion que checa los valores que hay en la pila para hacer las operaciones necesarias
	Hay 3 casos especiales a considerar:
	1.-Cuando en el tope hay 2 numeros seguidos en la pila, entonces el siguiente obligatoriamente tiene que ser un operador
	2.-Cuando el tope es un numero y el elemento que le sigue en la pila es un operador, entonces significa que ya hay un valor en la otra pila de valores
	3.-Cuando el tope de la pila es un operador significa que los dos operandos estan en la otra pila
*/


void suma(pila* sum,pila* inver, float* valores){
	elemento e;
	if(!empty(inver)){
		e=pop(inver);
		if(e.c > 64){
			push(e,sum);
			suma(sum,inver,valores);
		}
		else{
			eleccion(e,sum);
			printf("\nValor de la operacion: %f",top(sum).n);
			suma(sum,inver,valores);
		}
	}else{
		e=pop(sum);
		printf("\nResultado: %f",e.n);
	}
	return ;
}

//Funcion que regresa un valor dependiendo del operador que reciba
//Esto se usa para el posfijo ya que se necesita establecer prioridad de operadores para la entrada en la pila
int regresaValor(char c){
	int k=0;
	if(c == '^'){
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
	return k;
}

//Funcion que transforma la expresion normal a la expresion de tipo posfijo
float* posfijo(pila* pi,char expre[], pila* guarda){ 
    elemento e;
    printf("\n");
    
    
    float* valores;
    valores=(float*)malloc(26*sizeof(float)); // Se crea un arreglo de 26 elementos para guardar los valores de las letras que sean introducidas en la expresion
    int j;
    for(j=0;j<26;j++){
        valores[j]=-1;// Se le pone -1 a cada espacio para saber que no se ha usado esa letra
    }
    
    int i;
    for(i =0; i< strlen(expre); i++){ // for para ir checando toda la cadena
        if(expre[i] == '(' ||expre[i] == '^'||expre[i] == '*' || expre[i] == '/'||expre[i] == '+' || expre[i] == '-'){ //Validacion para saber si es un elemento que va a ser agregado a la pila
            if(!empty(pi) && expre[i] != '('){
				if(regresaValor(top(pi).c) >= regresaValor(expre[i])){ //Si el valor que regresa es mayor o igual que el que esta hasta arriba entonces se produce un cambio
					cambio(expre[i],pi,guarda);
				}
				else{
					e.c=expre[i];// Si es menor entoncs solo se pone arriba
                    push(e,pi);
				}
            }
            else{// Si el tope es nulo entonces solo se coloca
                e.c=expre[i];
                push(e,pi);
            }
        }
        else{//Si no es operador que vaya en la pila entonces
            if(expre[i] == ')'){ //Si es un parentesis que cierra entonces se hace pop de todos los elementos hasta que se encuentre el parentesis que abre
                while(top(pi).c != '('){
                    e=pop(pi);
                    printf("%c",e.c);//Se muestran en pantalla los elementos
                    push(e,guarda);//Se guarda en una pila auxiliar el elemento, ya que se ultilizara para hacer la suma despues
                }
                e=pop(pi);//Sirve para sacar el parentesis abierto que se queda ahi
            }
            else{//Si es una letra
                e.c=expre[i];
                printf("%c",expre[i]);
                push(e,guarda);
                valores[expre[i]-65]=0;//Se cambia el valor en el arreglo de valores para saber que esa letra sera usada
            }
        }
    }
    
    if(!empty(pi)){//Mientras la pila con los operadores no este vacia, se imprimiran los que queden para que se termine la expresion
        while(!empty(pi)){
            e=pop(pi);
            printf("%c",e.c);
            push(e,guarda);
        }
    }
    
    int k;
    for(k =0; k< 26;k++){//For que sirve para introducir los datos a las eltras que seran usadas
    	if(valores[k] != -1){
    		printf("\nIntroduzca el valor de %c: ",k+65);
            scanf("%f",&valores[k]);
		}
	}
    printf("\n");
    
    return valores;
}

//Funcion usada para iniciar todas las pilas correspondientes
void iniciarTodo(pila* s,pila* pi,pila* guarda,pila* inver,pila* sum){
	initialize(s);// checar parentesis
    initialize(pi); //ayudar a guardar los operadores en el posfijo
    initialize(guarda);//guarda lo que se vaya mostrando
    initialize(inver);//guarda la pila de guarda pero invertida
	initialize(sum);//realiza las operaciones finales
	return ;
}
//Funcion usada para destruir todas las pilas correspondientes
void destruirTodo(pila* s,pila* pi,pila* guarda,pila* inver,pila* sum){
	destroy(sum);
    destroy(inver);
    destroy(guarda);
    destroy(pi);
    destroy(s);
	return ;
}


int main() {
    pila s,pi,guarda,inver,sum;
    float* valores;
    char expresion[50];
    
    iniciarTodo(&s,&pi,&guarda,&inver,&sum);
    
    printf("Introduce la cadena: ");
    scanf("%s",&expresion);

    printf("%s",expresion);
    checar(&s,expresion);
	
    valores=posfijo(&pi,expresion,&guarda);
    
    orden(&guarda,&inver,valores);
    //DESPUES DE ORDENARLOS BIEN ENTONCES YA SE MANDA AL SUMA 
	suma(&sum,&inver,valores);
	
	destruirTodo(&s,&pi,&guarda,&inver,&sum);
    return (EXIT_SUCCESS);
}
