#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

int esOperador(char c){
	return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
}

int esDigito(char c){
	return ('0' <= c && c <= '9') || c == '.';
}

int precedencia(char c){
	switch(c){
		case '^': return 3;
		case '*': case '/': return 2;
		case '+': case '-': return 1;
		case '(': return 0;
		default: return -1;
	}
}

double operacion(double operando1, double operando2, char operador){
	switch(operador){
		case '+': return operando1 + operando2;
		case '-': return operando1 - operando2;
		case '*': return operando1 * operando2;
		case '/': return operando1 / operando2;
		case '^': return pow(operando1, operando2);
		default: return 0;
	}
}

typedef struct pila{
	int pos;
	double A[100];
} pila;

pila * NuevaPila(){
	pila * p = calloc(1, sizeof(pila));
	p->pos = -1;
	return p;
}

int Vacia(pila * p){
	return p->pos < 0;
}

void Push(pila * p, double x){
	p->A[++p->pos] = x;
}

double Top(pila * p){
	return p->A[p->pos];
}

double Pop(pila * p){
	return p->A[p->pos--];
}

double evaluar(char * expresion){
	pila * operadores = NuevaPila();
	pila * operandos = NuevaPila();
	int tamano = strlen(expresion);
	int i = 0;
	double operando = 0;
	char operandoStr[100];
	double op1, op2;
	while(i < tamano){
		char actual = expresion[i];
		if(esDigito(actual)){
			int j = 0;
			while(i < tamano && esDigito(expresion[i])){
				operandoStr[j++] = expresion[i++];
			}
			operandoStr[j] = '\0';
			sscanf(operandoStr, "%lf", &operando);
			Push(operandos, operando);
			continue;
		}else if(actual == '('){
			Push(operadores, actual);
		}else if(actual == ')'){
			while(Top(operadores) != '('){
				op2 = Pop(operandos);
				op1 = Pop(operandos);
				Push(operandos, operacion(op1, op2, Pop(operadores)));
			}
			Pop(operadores);
		}else if(esOperador(actual)){
			while(!Vacia(operadores) && precedencia(Top(operadores)) >= precedencia(actual)){
				op2 = Pop(operandos);
				op1 = Pop(operandos);
				Push(operandos, operacion(op1, op2, Pop(operadores)));
			}
			Push(operadores, actual);
		}
		i++;
	}
	while(!Vacia(operadores)){
		op2 = Pop(operandos);
		op1 = Pop(operandos);
		Push(operandos, operacion(op1, op2, Pop(operadores)));
	}
	return Pop(operandos);
}

int main(){
	char expresion[100];
	printf("Introduce la expresion aritmetica: ");
	scanf("%s", expresion);
	printf("%0.10lf\n", evaluar(expresion));
	return 0;
}