struct Stack {
        int *elementos;
        int tope;
};
typedef struct stack Stack;
Stack *crearStack(int n){
   Stack *nvo;
   if(n==0)
	return (Stack*)NULL;
   nvo=(Stack *)malloc(sizeof(Stack));
   if(!nvo){
	puts("no hay memoria para crear Stack");
        return (Stack *)NULL;
   }  
   nvo->tope=0;
   nvo->elementos=(int *)malloc(sizeof(int)*n);
   if(!nvo->elementos){
	puts("no hay memoria para crear elementos");
        return (Stack *)NULL;
   }  
   return nvo;
}
int pop(Stack *p){
        p->tope=p->tope-1;
	return p->elementos[p->tope];
}
void push(Stack *p, int dato){
	p->elementos[p->tope]=dato;
	p->tope=p->tope+1;
}
boolean estaVacia(Stack *p){
	return p->tope==0;
}
int top(Stack *p){
	return p->elementos[p->tope-1];
}
int main(){
	Stack *pila;
	pila=crearStack(5);
	push(pila, 1);
	push(pila, 2);
	push(pila, 3);
	pop(pila);
	pop(pila);	
}


