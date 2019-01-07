section .data                               ;Segmento de datos
    cadena db "hola",0xA,0xD                ;Cadena en donde se contendra el valor del contador a imprimir
    salto db 0xA,0xD                        ;Salto de linea a imprimir
    len equ $ - salto                       ;Longitud de salto

section .bss                                ;Segmento de datos
    valor resb 1                            ;Espacio en memoria para la variable valor

section .text                               ;Segmento de codigo
    global _start                           ;Punto de entrada al programa (usando en el enlazados Id)

_start:                                     ;Inicio del programa
    mov eax,0                               ;Iniciamos contador en cero
    mov [valor],eax                         ;asignamos valor = eax
    JMP comparar                            ;destino a comparar

comparar:                                   ;Me sirve para imprimir
    mov eax,[valor]                         ;asignamos eax = valor
    add eax,'0'                             ;sumar '0' para convertir de decimal a ASCII
    mov [cadena],eax                        ;asignamos cadena = eax
    mov eax,4                               ;Numero de llamada al sistema "sys_write"
    mov ebx,1                               ;Salida estandar
    mov ecx,cadena                          ;Cadena a escribir
    mov edx,2                               ;Longitud de cadena                   
    int 0x80                                ;Interrupcion de llamadas al sistema del kernel de Linux
    mov eax,4                               ;Numero de llamada al sistema "sys_write"
    mov ebx,1                               ;Salida estandar
    mov ecx,salto                           ;Salto a escribir
    mov edx,len                             ;Longitud del salto
    int 0x80                                ;Interrupcion de llamadas al sistema del kernel de Linux
    mov eax,[valor]                         ;eax = valor
    mov ebx,9                               ;Hasta 9 es el limite
    CMP eax,ebx                             ;eax es el valor de mi contador, comparamos si ya hemos llegado a 9
    JE salir                                ;si ya llegamos, vamos a salir
    JLE incrementar                         ;si aun no hemos llegado, incrementamos y repetimos
    
incrementar:                                ;Sirve para incrementar el contador
    mov eax,[valor]                         ;eax = valor
    inc eax                                 ;incrementamos eax
    mov [valor],eax                         ;valor = eax
    JMP comparar                            ;destino a comparar

salir:                                      ;Salir del programa
    mov eax,1                               ;Numero de llamada al sistema "sys_exit"
    int 0x80                                ;Interrupcion de llamadas al sistema del kernel de Linux