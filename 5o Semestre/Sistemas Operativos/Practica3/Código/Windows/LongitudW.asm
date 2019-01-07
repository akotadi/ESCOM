segment .data
    Tittle db 'Longitud de cadena.', 0xD, 0xA
    SizeTittle equ $- Tittle

    Prompt1 db 'Ingresa cadena: '
    SizePrompt1 equ $- Prompt1

    LengthPrompt db 0xD, 0xA,'Longitud de cadena: '
    SizeLenPrompt equ $- LengthPrompt

    i 			db 0
    longitud 		dd 0
    leido 		db 0
    handle 		dd 0

segment .bss
    string 		resb 101
    result 		resb 1000
    lengthString 	resb 4

segment .text
    global _start
    extern    _GetStdHandle@4
    extern    _WriteConsoleA@20
    extern    _ReadConsoleA@20
    extern    _ExitProcess@4

_start:
    mov EDI, result
    mov byte[i], 0
    mov dword[longitud], 0

    push dword dword -11
    call _GetStdHandle@4
    mov [handle], EAX
    push dword 0
    push dword leido
    push dword SizeTittle
    push dword Tittle
    push dword [handle]
    call _WriteConsoleA@20

AskString:
    push dword dword -11
    call _GetStdHandle@4
    mov [handle], EAX
    push dword 0
    push dword leido
    push dword SizePrompt1
    push dword Prompt1
    push dword [handle]
    call _WriteConsoleA@20

    push dword dword -10
    call _GetStdHandle@4
    mov [handle], EAX
    push dword 0
    push dword leido
    push dword 101
    push dword string
    push dword [handle]
    call _ReadConsoleA@20

    mov ESI, string
    call CopyString
    je Show

CopyString:
    mov DL, byte[ESI]
    mov byte[EDI], DL
    inc EDI
    inc ESI
    inc dword[longitud]
    cmp byte[ESI], 0xD
    jne CopyString
    ret

Show:

    mov AX, word[longitud]
    mov BX, 10
    mov CX, 0
    mov DX, 0
    mov EDI, lengthString

IntToString:
    mov BX, 10
    div BX
    add DX, '0'
    push DX
    inc CX
    mov DX, 0
    cmp AX, 0
    jne IntToString

SaveDigits:
    pop DX
    mov [EDI], DX
    inc EDI
    dec CX
    cmp CX, 0
    jne SaveDigits

ShowLength:
    push dword dword -11
    call _GetStdHandle@4
    mov [handle], EAX
    push dword 0
    push dword leido
    push dword SizeLenPrompt
    push dword LengthPrompt
    push dword [handle]
    call _WriteConsoleA@20

    push dword dword -11
    call _GetStdHandle@4
    mov [handle], EAX
    push dword 0
    push dword leido
    push dword 4
    push dword lengthString
    push dword [handle]
    call _WriteConsoleA@20

SecureExit:
    push dword 0
    call _ExitProcess@4