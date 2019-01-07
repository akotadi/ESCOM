segment .data
    Final db  'Final del contador', 0xD, 0xA
    SizeFinal equ $ - Final

    NewLine      db      "" ,0xA
    SizeNewLine     equ     $ - NewLine

    leido db 0
    handle dd 0

segment .bss
    i resb 1

segment .text
    global _start
    extern _GetStdHandle@4
    extern _WriteConsoleA@20
    extern _ReadConsoleA@20
    extern _ExitProcess@4

_start:
    mov AL, '0'

Iteration:

  ShowValue:
    mov [i], AL

    push dword dword -11
    call _GetStdHandle@4
    mov [handle], EAX
    push dword 0
    push dword leido
    push dword 1
    push dword i
    push dword [handle]
    call _WriteConsoleA@20

  ShowLine:
    push dword dword -11
    call _GetStdHandle@4
    mov [handle], EAX
    push dword 0
    push dword leido
    push dword 1
    push dword NewLine
    push dword [handle]
    call _WriteConsoleA@20

  Increase:
    mov AL, [i]
    inc AL

  CheckFinal:
    cmp AL, 58
    je  ShowPrompt
    jmp Iteration

ShowPrompt:
    push dword dword -11
    call _GetStdHandle@4
    mov [handle], EAX
    push dword 0
    push dword leido
    push dword SizeFinal
    push dword Final
    push dword [handle]
    call _WriteConsoleA@20
    je  SecureExit

SecureExit:
   push dword 0
    call _ExitProcess@4