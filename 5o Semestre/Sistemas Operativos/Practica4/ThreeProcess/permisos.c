#include <stdio.h>
#include <sys/stat.h>

int main(void)
{
    char ruta[100] = "/home/akotadi/Documents/Escuela/5oSemestre/SistemasOperativos/Practica4/ThreeProcess/test.txt";

    printf("Modificando: %s\n", ruta);

    chmod (ruta, 100); // Ejecutar por propietario
    chmod (ruta, 040); // Leer por grupo
    chmod (ruta, 002); // Escribir por otros

    printf("Modificado Ejecutar por propietario(100), Leer por grupo(040), Escribir por otros(002)\n");

    return 0;
}