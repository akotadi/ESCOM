struct pila{
	int tope;
	int * eltos;
}

typedef structu pila Pila;

Pila * creaPila(int n){
	Pila * nvo;
	nvo=(Pila*)malloc(sizeof(Pila));
	nvo->tope=0;
	nvo->eltos=(int*)malloc(sizeof(int)*n);
	return nvo;
}

void push(Pila *p, int dato){
	p->eltos[p->tope]=dato;
	p->tope=p->tope+1;
}

int pop(Pila *p){
	p->tope=p->tope-1;
	return p->eltos[p->tope];
}

int top(Pila *p){
	return p->eltos[p->tope-1];
}

int estaVacia(Pila *p){
	return p->tope==0;
}