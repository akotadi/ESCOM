#Implementación Práctica 01: Pruebas a posteriori (Algoritmos de Ordenamiento)
#Por: Git Gud (Equipo Arbol)
#Versión: 1.0

#Descripción: Funciones Auxiliares del Algoritmo de Ordenamiento por medio de un Árbol Binario de Búsqueda (ABB)
	
#Observaciones: 

#!/bin/bash  
#Arreglo de las cantidades de numeros a ordenar                       
NUMEROS=(100 1000 5000 10000 50000 100000 200000 400000 600000 800000 1000000 2000000 3000000 4000000 5000000 6000000 7000000 8000000 9000000 10000000)
#Arreglo de métodos 
METODOS=(bubble bubbleO insertion selection shell tree)
#Ciclo para la compilación de los programas
for i in {0..5} 
do
	if [ $i == 4 ] 
	then
		gcc ${METODOS[i]}.c tiempo.c -o ${METODOS[i]} -lm 
	else
		gcc ${METODOS[i]}.c tiempo.c -o ${METODOS[i]}
	fi
done
#Ordenamiento con el método burbuja
for i in {0..8} 
do
		./${METODOS[0]} ${NUMEROS[i]} <numeros10millones.txt >>${METODOS[0]}.txt
done
#Ordenamiento con el método burbuja optimizado
for i in {0..8} 
do
		./${METODOS[1]} ${NUMEROS[i]} <numeros10millones.txt >>${METODOS[1]}.txt
done
#Ordenamiento con el método de inserción
for i in {0..9} 
do
		./${METODOS[2]} ${NUMEROS[i]} <numeros10millones.txt >>${METODOS[2]}.txt
done
#Ordenamiento con el método de selección
for i in {0..8} 
do
		./${METODOS[3]} ${NUMEROS[i]} <numeros10millones.txt >>${METODOS[3]}.txt
done
#Ordenamiento con el método shell
for i in {0..19} 
do
		./${METODOS[4]} ${NUMEROS[i]} <numeros10millones.txt >>${METODOS[4]}.txt
done
#Ordenamiento con el método de árbol
for i in {0..19} 
do
		./${METODOS[5]} ${NUMEROS[i]} <numeros10millones.txt >>${METODOS[5]}.txt
done

