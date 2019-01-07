#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include "TADPilaDin.c"

void checar(pila *s,char c[]){
    elemento e;
    int i;
    for(i=0; i < strlen(c) ;i++){
        if(c[i] == '('){
            e.c='(';
            Push(s,e);
        }else{
            if(c[i] == ')'){
                if(Empty(s)){
                    printf("\nError: Se cerro un parentesis sin abrir otro");
                    exit(1);
                }else{
                    e=Pop(s);
                }  
            }
        }
    }
    if(Empty(s)){
        printf("\nNumero correcto de parentesis");
    }else{
        printf("\nError en la expresion");
        exit(1);
    }
    return ;
}

void cambio(char expre,pila* s,pila* guarda){
    elemento e;
    e=Pop(s);
    printf("%c",e.c);
    Push(guarda,e);
    e.c=expre;
    Push(s,e);
    return ;
}

void orden(pila* guarda,pila* inver,float* valores){
    elemento e;
    while(!Empty(guarda)){
        e=Pop(guarda);
        if(e.c > 64 && e.c < 92){
        	e.n=valores[e.c-65];
		}		
        Push(inver,e);
    }
    return ;
}

void eleccion(elemento e,pila* sum){ 
	elemento r;
	float x1=Pop(sum).n;
	printf("\nVALOR 1: %f",x1);
	float x2=Pop(sum).n;
	printf("\nVALOR 1: %f",x2);
	if(e.c == '+'){
        r.n= x2+x1;
        Push(sum,r);
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

void suma(pila* sum,pila* inver, float* valores){
	elemento e;
	if(!Empty(inver)){
		e=Pop(inver);
		if(e.c > 64 && e.c < 92){
			Push(sum,e);
			suma(sum,inver,valores);
		}
		else{
			eleccion(e,sum);
			printf("\nValor de la operacion: %f\n",Top(sum).n);
			suma(sum,inver,valores);
		}
	}else{
		e=Pop(sum);
		printf("\n\nResultado: %f",e.n);
	}
	return ;
}

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

float* posfijo(pila* pi,char expre[], pila* guarda){ 
    elemento e; 
	int n;
    printf("\nExpresion en posfijo:\n");
    float* valores;
    valores=(float*)malloc(26*sizeof(float));
    int j;
    for(j=0;j<26;j++){
        valores[j]=-1;
    }
    int i;
    for(i =0; i< strlen(expre); i++){
        if(expre[i] == '(' ||expre[i] == '^'||expre[i] == '*' || expre[i] == '/'||expre[i] == '+' || expre[i] == '-'){
            if(!Empty(pi) && expre[i] != '('){
				if(regresaValor(Top(pi).c) >= regresaValor(expre[i]))
					cambio(expre[i],pi,guarda);
				else{
					e.c=expre[i];
                    Push(pi,e);
				}
            }
            else{
                e.c=expre[i];
                Push(pi,e);
            }
        }
        else{
            if(expre[i] == ')'){
                while(Top(pi).c != '('){
                    e=Pop(pi);
                    printf("%c",e.c);
                    Push(guarda,e);
                }
                e=Pop(pi);
            }
            else{
                e.c=expre[i];
                printf("%c",expre[i]);
                Push(guarda,e);
                valores[expre[i]-65]=0; 
            }
        }
    }
    if(!Empty(pi)){
        while(!Empty(pi)){
            e=Pop(pi);
            printf("%c",e.c);
            Push(guarda,e);
        }
    }
    int k;
	while(n != 1 || n != 2){
		printf("\n\nEvaluar expresion?");
	printf("\n1.- Si		2.- No (Salir)\n");
	scanf("%i",&n);
	if(n==1){
		for(k =0; k< 26;k++){
    	if(valores[k] != -1){
    		printf("\nIntroduzca el valor de %c: ",k+65);
            scanf("%f",&valores[k]);
		}
	}
    printf("\n");
    return valores;
	}
	else if (n == 2)
		exit(1);
	}
}

void iniciarTodo(pila* s,pila* pi,pila* guarda,pila* inver,pila* sum){
	Initialize(s); 
    Initialize(pi); 
    Initialize(guarda); 
    Initialize(inver); 
	Initialize(sum); 
	return ;
}

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
    char expresion[100];
    iniciarTodo(&s,&pi,&guarda,&inver,&sum);
    printf("Introduce la cadena: ");
    scanf("%s",&expresion); 	
	printf("\n%s",expresion);
	checar(&s,expresion); 
	printf("\n");
    valores=posfijo(&pi,expresion,&guarda);
    orden(&guarda,&inver,valores); 
	suma(&sum,&inver,valores); 
	destruirTodo(&s,&pi,&guarda,&inver,&sum);
	free(valores);
    return (EXIT_SUCCESS);
}
