#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include "TADPilaDin.c"

//Funcion normal que checa que todos los parentesis esten bien
void checar(pila *s,char c[]){
    elemento e;
    int i;
    for(i=0; i < strlen(c) ;i++){
        if(c[i] == '('){
            e.c='(';
            Push(s,e);
        }else{
            if(c[i] == ')'){
                if(Empty(s)){ //empty(s) == TRUE
                    printf("\nError: Se cerro un parentesis sin abrir otro");
                    exit(1);
                }else{
                    e=Pop(s);
                }  
            }
        }
    }
    if(Empty(s)){ //empty(s) == TRUE
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
    e=Pop(s);
    printf("%c",e.c);
    Push(guarda,e);
    e.c=expre;
    Push(s,e);
    return ;
}
//Saca todos los valores de una pila y los mete a otra para que esten invertidos
void orden(pila* guarda,pila* inver,float* valores){
    elemento e;
    while(!Empty(guarda)){
        e=Pop(guarda);
        if(e.c > 64){
        	e.n=valores[e.c-65];
		}		
        Push(inver,e);
    }
	printf("TERMINO BIEN BR0");   //(A+B)*(W/C)+F-J-A*(B/I)
    return ;
}

//	Funcion que realiza la operacion y mete el valor en una pila a parte
void eleccion(elemento e,pila* sum){ 
	elemento r;
	float x1=Pop(sum).n;
	printf("\nVALOR 1: %f",x1);
	float x2=Pop(sum).n;
	printf("\nVALOR 1: %f",x2);
	if(e.c == '+'){
        r.n= x2+x1;
        Push(sum,r);// Mete el valor de la operacion en otra pila
	}
    if(e.c == '-'){
        r.n= x2-x1;
    	Push(sum,r);
    }
    if(e.c == '*'){
        r.n= x2*x1;
        Push(sum,r);
    }
    if(e.c == '/'){
        r.n= x2/x1;
        Push(sum,r);
    }
    if(e.c == '^'){
        r.n= pow(x2,x1);
        Push(sum,r);
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
	if(!Empty(inver)){
		e=Pop(inver);
		if(e.c > 64){
			Push(sum,e);
			suma(sum,inver,valores);
		}
		else{
			eleccion(e,sum);
			printf("\nValor de la operacion: %f",Top(sum).n);
			suma(sum,inver,valores);
		}
	}else{
		e=Pop(sum);
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
            if(!Empty(pi) && expre[i] != '('){
				if(regresaValor(Top(pi).c) >= regresaValor(expre[i])){ //Si el valor que regresa es mayor o igual que el que esta hasta arriba entonces se produce un cambio
					cambio(expre[i],pi,guarda);
				}
				else{
					e.c=expre[i];// Si es menor entoncs solo se pone arriba
                    Push(pi,e);
				}
            }
            else{// Si el tope es nulo entonces solo se coloca
                e.c=expre[i];
                Push(pi,e);
            }
        }
        else{//Si no es operador que vaya en la pila entonces
            if(expre[i] == ')'){ //Si es un parentesis que cierra entonces se hace pop de todos los elementos hasta que se encuentre el parentesis que abre
                while(Top(pi).c != '('){
                    e=Pop(pi);
                    printf("%c",e.c);//Se muestran en pantalla los elementos
                    Push(guarda,e);//Se guarda en una pila auxiliar el elemento, ya que se ultilizara para hacer la suma despues
                }
                e=Pop(pi);//Sirve para sacar el parentesis abierto que se queda ahi
            }
            else{//Si es una letra
                e.c=expre[i];
                printf("%c",expre[i]);
                Push(guarda,e);
                valores[expre[i]-65]=0;//Se cambia el valor en el arreglo de valores para saber que esa letra sera usada
            }
        }
    }
    
    if(!Empty(pi)){//Mientras la pila con los operadores no este vacia, se imprimiran los que queden para que se termine la expresion
        while(!Empty(pi)){
            e=Pop(pi);
            printf("%c",e.c);
            Push(guarda,e);
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
	Initialize(s);// checar parentesis
    Initialize(pi); //ayudar a guardar los operadores en el posfijo
    Initialize(guarda);//guarda lo que se vaya mostrando
    Initialize(inver);//guarda la pila de guarda pero invertida
	Initialize(sum);//realiza las operaciones finales
	return ;
}
//Funcion usada para destruir todas las pilas correspondientes
void destruirTodo(pila* s,pila* pi,pila* guarda,pila* inver,pila* sum){
	Destroy(sum);
    Destroy(inver);
    Destroy(guarda);
    Destroy(pi);
    Destroy(s);
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
