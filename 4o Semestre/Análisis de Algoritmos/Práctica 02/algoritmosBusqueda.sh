#Implementación Práctica 02: Análisis temporal y notación de orden (Algoritmos de búsqueda)
#Por: Git Gud (Equipo Arbol)
#Versión: 1.0

#Descripción: Script para la ejecución de las distintas búsquedas

#!/bin/bash   

#Arreglo de los diferentes tamaños del arreglo donde se efectuara la busqueda               
TAMANO=(100 1000 5000 10000 50000 100000 200000 400000 600000 800000 1000000 2000000 3000000 4000000 5000000 6000000 7000000 8000000 9000000 10000000) 

#Arreglo de los diferentes números a buscar
NUMEROS=(322486 14700764 3128036 6337399 61396 10393545 2147445644 1295390003 450057883 187645041 1980098116 152503 5000 1493283650 214826 1843349527 1360839354 2109248666 2147470852 0)

#Compilación de los diferentes programas según su método
gcc LinealSearch.c -o LinealSearch 							#Secuencial sencilla
gcc ThreadsLinealSearch.c -o ThreadsLinealSearch -pthread		#Secuencial con hilos
gcc BinarySearch.c -o BinarySearch 									#Binaria sencilla
gcc ThreadsBinarySearch.c -o ThreadsBinarySearch -pthread				#Binaria con hilos
gcc BSTSearch.c -o BSTSearch  						#Con ABB sencilla
gcc ThreadsBSTSearch.c -o ThreadsBSTSearch -pthread		#Con ABB con hilos

#BÚSQUEDA SECUENCIAL SIN HILOS
echo -e "BUSQUEDA SECUENCIAL SIN HILOS\n" >>Lineal.txt
for j in {0..19}
do
	for i in {0..19} 
	do 
		./LinealSearch "${TAMANO[i]}" "${NUMEROS[j]}" < SortedNumbers.txt >>Lineal.txt
	done
done

#BÚSQUEDA SECUENCIAL CON 2, 3 y 4 HILOS
for k in {2..4}
do
	echo -e "\nBUSQUEDA SECUENCIAL CON '$k' HILOS\n" >>Lineal.txt
	for j in {0..19}
	do
		for i in {0..19} 
		do 
			./ThreadsLinealSearch "${TAMANO[i]}" $k "${NUMEROS[j]}" < SortedNumbers.txt >>Lineal.txt
		done
	done
done

#BÚSQUEDA BINARIA SIN HILOS
echo -e "BUSQUEDA BINARIA SIN HILOS\n" >>Binary.txt
for j in {0..19}
do
	for i in {0..19} 
	do 
		./BinarySearch "${TAMANO[i]}" "${NUMEROS[j]}" < SortedNumbers.txt >>Binary.txt
	done
done

#BÚSQUEDA BINARIA CON 2, 3 y 4 HILOS
for k in {2..4}
do
	echo -e "\nBUSQUEDA BINARIA CON '$k' HILOS\n" >>Binary.txt
	for j in {0..19}
	do
		for i in {0..19} 
		do 
			./ThreadsBinarySearch "${TAMANO[i]}" $k "${NUMEROS[j]}" < SortedNumbers.txt >>Binary.txt
		done
	done
done

#BÚSQUEDA EN ABB SIN HILOS
echo -e "BUSQUEDA ARBOL BINARIO SIN HILOS\n" >>BST.txt
for j in {0..19}
do
	for i in {0..19} 
	do 
		./BSTSearch "${TAMANO[i]}" "${NUMEROS[j]}" < UnsortedNumbers.txt >>BST.txt
	done
done

#BÚSQUEDA EN ABB CON 2, 3 Y 4 HILOS
for k in {2..4}
do
	echo -e "\nBUSQUEDA ARBOL BINARIO CON '$k' HILOS\n" >>BST.txt
	for j in {0..19}
	do
		for i in {0..19} 
		do 
			./ThreadsBSTSearch "${TAMANO[i]}" $k "${NUMEROS[j]}" < UnsortedNumbers.txt >>BST.txt
		done
	done
done
