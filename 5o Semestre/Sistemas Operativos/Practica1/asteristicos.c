/*#include <stdio.h>

void main(){
  int Longi,i;
  float mitaD, newLongi;
printf("Numero de asteriscos en la horizontal principal:\n");
scanf("%d",&Longi);
newLongi=(Longi/2);

mitaD=(Longi/4);


for(i=0; i<=mitaD;i++){
  printf(" ");
}
printf("*");
for (i = 0; i <= (mitaD*2); i++) {
    printf(" ");
}
printf("*\n");


for(i=0; i<mitaD;i++){
  printf(" ");
}
printf("*");
for (i = 0; i <= (mitaD*2); i++) {
    printf(" ");
}
printf("*\n");
}*/

#include<stdio.h>
int main(){
  int n,i,j, horizontal, parRombos;

  printf("Numero de asteriscos en la horizontal principal:\n");
  scanf("%d",&horizontal);
  parRombos=horizontal/2;
  n=(horizontal/4)+1; //n=niveles arriba de la diagonal
printf("%d\n", n);
  printf("\n");

  for(i=1;i<=n;i++){

      printf("\t");
      for(j=1;j<=n-i;j++) //un espacio menos         /*1er rombo*/
          printf(" ");
          for(j=1;j<=(2*i)-1;j++)//dos "*" mas
            printf("*");

    printf("\t");
    for(j=1;j<=n-i;j++) //un espacio menos
        printf(" ");                                  /*2do rombo*/
    for(j=1;j<=2*i-1;j++)//dos "*" mas
      printf("*");     //despues de los espacios
  printf("\n");
  }

  for(i=n-1;i>=1;i--){

    printf("\t");
    for(j=1;j<=n-i;j++) //un espacio mas
      printf(" ");
    for(j=1;j<=2*i-1;j++)//dos "*" menos
      printf("*");

    printf("\t");
    for(j=1;j<=n-i;j++)//un espacio mas
      printf(" ");
    for(j=1;j<=2*i-1;j++)//dos "*" menos
      printf("*");     //despues de los espacios
  printf("\n");
  }
  return 0;
}
