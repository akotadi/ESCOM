#include<stdio.h>
struct racional {
    int num, den;
};
typedef struct racional Racional;
/*Pre-condición: den  <> 0 */
Racional *crearRacional(int num, int den){
   Racional *nvo;
   if(den==0)
	return (Racional *)NULL;
   nvo=(Racional *)malloc(sizeof(Racional));
   if(!nvo){
	puts("no hay memoria para crear Racional ");
        return (Racional *)NULL;
   }  
   nvo->num=num;
   nvo->den=den;
   return nvo;
}
/* Pre-condición: r ha sido creado. */
void asignar(Racional * r, int num, int den);
/* Pre-condición: r ha sido creado. */
int  numerador( Racional *r){
	return r->num;
}
/* Pre-condición: r ha sido creado. */
int  denominador(Racional *r){
	return r->den;
}
/* Pre-condición:  r1 y r2 han sido creados. */
Racional *multiplicar(Racional *r1, Racional  *r2){
	return crearRacional(r1->num*r2->num,r1->den*r2->den);
}
int igual(Racional *r1, Racional  *r2){
        return r1->num*r2->den == r1->den*r2->num;
}
void imprimir(void *q){
	Racional *r=(Racional *)q;
	printf(" %d / %d ", r->num,r->den);
}
int main(){
Racional *r1,*r2, *res;
	r1=crearRacional(2,3);
	imprimir(r1);
	r2=crearRacional(3,2);
	imprimir(r2);
	res=multiplicar(r1, r2);
	imprimir(res);
}
