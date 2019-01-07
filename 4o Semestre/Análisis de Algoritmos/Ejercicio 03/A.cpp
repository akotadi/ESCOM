


for(i = 1;i<n;i++){				
	for(j=0;j<n-1;j++){			
		temp = A[j];			
		A[j] = A[j+1];			
		A[j+1] = temp;			
	}							
}			

------------------------

polinomio = 0;									
for (int i = 0; i <= n; ++i)					
{
	polinomio = polinomio * z + A[n-i];			
}		

------------------------

for i = 1 to n do 									 
{
	for j = 1 to n do{								
		c[i,j] = 0;									
		for k = 1 to n do{							
			c[i,j] = c[i,j] + A[i,k] * B[k,j];		
		}											
	}												
} 			


------------------------

anterior = 1;							
actual = 1;								
while(n>2){								
	aux = anterior + actual;			
	anterior = actual;					
	actual = aux;						
	n = n-1;							
}		

------------------------

for (int i = n-1, j = 0; i >= 0; --i, j++)		
{
	s2[j] = s[i];								
}												
for (int i = 0; i < n; ++i)						 
{
	s[i] = s2[i];								
}			