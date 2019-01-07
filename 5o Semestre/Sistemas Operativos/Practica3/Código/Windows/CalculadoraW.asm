segment .data
    Title db  'Calculadora basica, introduce los operandos: ', 0xD, 0xA
    SizeTitle equ $ - Title
  
    Prompt1     db  'A: '
    SizePrompt1 equ $ - Prompt1
  
    Prompt2     db  'B: '
    SizePrompt2 equ $ - Prompt2
  
    SumPrompt db 10,'1: A + B',0xD, 0xA
    SizeSumPrompt equ $ - SumPrompt
  
    SubPrompt     db '2: A - B',0xD, 0xA
    SizeSubPrompt equ $ - SubPrompt
  
    MulPrompt     db '3: A x B',0xD, 0xA
    SizeMulPrompt equ $ - MulPrompt
  
    DivPrompt     db '4: A / B',0xD, 0xA
    SizeDivPrompt equ $ - DivPrompt
  
    OpPrompt db 10, 'OP: ',0
    SizeOpPrompt    equ $ - OpPrompt
  
    ResultPrompt    db 'Resultado: '
    SizeResPrompt   equ $ - ResultPrompt
  
    NotOpPrompt     db 'Opcion no valida', 0xD, 0xA
    SizeNotOpPrompt equ $ - NotOpPrompt
  
    NewLine      db      0xD, 0xA
    SizeNewLine     equ     $ - NewLine

    leido db 0
    handle dd 0

    A dd 0
    B dd 0
    Result dd 0
  
segment .bss
    OptionNumber  resb    2
    NumberA       resb    8
    NumberB       resb    8
    ResultNumber  resb    8
  
segment .text
    global _start
    extern    _GetStdHandle@4
    extern    _WriteConsoleA@20
    extern    _ReadConsoleA@20
    extern    _ExitProcess@4

_start:

GetNumbers:
  ShowPrompt:
    push dword dword -11
    call _GetStdHandle@4
    mov [handle], EAX
    push dword 0
    push dword leido
    push dword SizeTitle
    push dword Title
    push dword [handle]
    call _WriteConsoleA@20

  ShowAndGetANumber:
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
    push dword 8
    push dword NumberA
    push dword [handle]
    call _ReadConsoleA@20

  ShowAndGetBNumber:
    push dword dword -11
    call _GetStdHandle@4
    mov [handle], EAX
    push dword 0
    push dword leido
    push dword SizePrompt2
    push dword Prompt2
    push dword [handle]
    call _WriteConsoleA@20

    push dword dword -10
    call _GetStdHandle@4
    mov [handle], EAX
    push dword 0
    push dword leido
    push dword 8
    push dword NumberB
    push dword [handle]
    call _ReadConsoleA@20

  mov EBX, 10

  mov ESI, NumberA
  mov EAX, 0
  call strToInt
  mov [A], EAX

  mov ESI, NumberB
  mov EAX, 0
  call strToInt
  mov [B], EAX

  jmp GetOperation

strToInt:
  mul EBX
  mov ECX, 0
  mov CL, byte[ESI]
  sub CL, '0'
  add EAX, ECX
  inc ESI
  cmp byte[ESI], 0xD
  jne strToInt
  ret


GetOperation:
    push dword dword -11
    call _GetStdHandle@4
    mov [handle], EAX
    push dword 0
    push dword leido
    push dword SizeSumPrompt
    push dword SumPrompt
    push dword [handle]
    call _WriteConsoleA@20

    push dword dword -11
    call _GetStdHandle@4
    mov [handle], EAX
    push dword 0
    push dword leido
    push dword SizeSubPrompt
    push dword SubPrompt
    push dword [handle]
    call _WriteConsoleA@20

    push dword dword -11
    call _GetStdHandle@4
    mov [handle], EAX
    push dword 0
    push dword leido
    push dword SizeMulPrompt
    push dword MulPrompt
    push dword [handle]
    call _WriteConsoleA@20

    push dword dword -11
    call _GetStdHandle@4
    mov [handle], EAX
    push dword 0
    push dword leido
    push dword SizeDivPrompt
    push dword DivPrompt
    push dword [handle]
    call _WriteConsoleA@20

    push dword dword -11
    call _GetStdHandle@4
    mov [handle], EAX
    push dword 0
    push dword leido
    push dword SizeOpPrompt
    push dword OpPrompt
    push dword [handle]
    call _WriteConsoleA@20
  
  GetTheOperation:    
    push dword dword -10
    call _GetStdHandle@4
    mov [handle], EAX
    push dword 0
    push dword leido
    push dword 2
    push dword OptionNumber
    push dword [handle]
    call _ReadConsoleA@20

SelectOperation:

    mov AH, [OptionNumber]
    sub AH, '0'
  
  ChooseOperator:
    cmp AH, 1
    je Sum
  
    cmp AH, 2
    je Sub
  
    cmp AH, 3
    je Mul
  
    cmp AH, 4
    je Div
  
  ShowNoValidOperation:
    push dword dword -11
    call _GetStdHandle@4
    mov [handle], EAX
    push dword 0
    push dword leido
    push dword SizeNotOpPrompt
    push dword NotOpPrompt
    push dword [handle]
    call _WriteConsoleA@20
    jmp SecureExit

DoTheRealOperation:
    Sum:
        mov EAX, [A]
        add EAX, [B]
        mov EDI, ResultNumber
        mov ECX, 0
        mov EDX, 0
        call intToStr
      
    Sub:
        mov EAX, [A]
        sub EAX, [B]
        mov EDI, ResultNumber
        mov ECX, 0
        mov EDX, 0
        call intToStr
      
    Mul:  
        mov EAX, [A]
        mov EBX, [B]
        mul EBX
        mov EDI, ResultNumber
        mov ECX, 0
        mov EDX, 0
        call intToStr
      
    Div:  
        mov EAX, [A]
        mov EBX, [B]
        mov EDX, 0
        div EBX
        mov EDI, ResultNumber
        mov ECX, 0
        mov EDX, 0
        call intToStr

intToStr:
    mov EBX, 10
    div EBX
    add EDX, '0'
    push EDX
    inc ECX
    mov EDX, 0
    cmp EAX, 0
    jne intToStr
    je saveDigits
    ret

saveDigits:
    pop EDX
    mov [EDI], EDX
    inc EDI
    dec ECX
    cmp ECX, 0
    jne saveDigits

ShowResult:
    push dword dword -11
    call _GetStdHandle@4
    mov [handle], EAX
    push dword 0
    push dword leido
    push dword SizeResPrompt
    push dword ResultPrompt
    push dword [handle]
    call _WriteConsoleA@20

    push dword dword -11
    call _GetStdHandle@4
    mov [handle], EAX
    push dword 0
    push dword leido
    push dword 8
    push dword ResultNumber
    push dword [handle]
    call _WriteConsoleA@20

SecureExit:
    push dword 0
    call _ExitProcess@4