segment .data
    Title db  'Calculadora basica, introduce los operandos: ', 0xA 
    SizeTitle equ $ - Title
  
    Prompt1     db  10,'A: ',0
    SizePrompt1 equ $ - Prompt1
  
    Prompt2     db  'B: ',0
    SizePrompt2 equ $ - Prompt2
  
    SumPrompt     db 10,'1: A + B',10,0
    SizeSumPrompt equ $ - SumPrompt
  
    SubPrompt     db '2: A - B',10,0   
    SizeSubPrompt equ $ - SubPrompt
  
    MulPrompt     db '3: A x B',10,0   
    SizeMulPrompt equ $ - MulPrompt
  
    DivPrompt     db '4: A / B',10,0   
    SizeDivPrompt equ $ - DivPrompt
  
    OpPrompt db 10, 'OP: ',0
    SizeOpPrompt    equ $ - OpPrompt
  
    ResultPrompt    db 10,'Resultado: ',0
    SizeResPrompt   equ $ - ResultPrompt
  
    NotOpPrompt     db 10,'Opcion no valida',10,0
    SizeNotOpPrompt equ $ - NotOpPrompt
  
    NewLine      db      10,10,0
    SizeNewLine     equ     $ - NewLine

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

_start:

GetNumbers:
  ShowPrompt:
    mov EAX, 4
    mov EBX, 1
    mov ECX, Title
    mov EDX, SizeTitle
    int 0x80

  ShowAndGetANumber:
    mov EAX, 4
    mov EBX, 1
    mov ECX, Prompt1
    mov EDX, SizePrompt1
    int 0x80

    mov EAX, 3
    mov EBX, 0
    mov ECX, NumberA
    mov EDX, 8
    int 0x80

  ShowAndGetBNumber:
    mov EAX, 4
    mov EBX, 1
    mov ECX, Prompt2
    mov EDX, SizePrompt2
    int 0x80
  
    mov EAX, 3
    mov EBX, 0
    mov ECX, NumberB
    mov EDX, 8
    int 0x80

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
    cmp byte[ESI], 0xA
    jne strToInt
    ret

GetOperation:
    mov EAX, 4
    mov EBX, 1
    mov ECX, SumPrompt
    mov EDX, SizeSumPrompt
    int 0x80

    mov EAX, 4
    mov EBX, 1
    mov ECX, SubPrompt
    mov EDX, SizeSubPrompt
    int 0x80
    
    mov EAX, 4
    mov EBX, 1
    mov ECX, MulPrompt
    mov EDX, SizeMulPrompt
    int 0x80
    
    mov EAX, 4
    mov EBX, 1
    mov ECX, DivPrompt
    mov EDX, SizeDivPrompt
    int 0x80
    
    mov EAX, 4
    mov EBX, 1
    mov ECX, OpPrompt 
    mov EDX, SizeOpPrompt 
    int 0x80
  
  GetTheOperation:    
    mov EAX, 3
    mov EBX, 0
    mov ECX, OptionNumber
    mov EDX, 2
    int 0x80

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
    mov EAX, 4
    mov EBX, 1
    mov ECX, NotOpPrompt
    mov EDX, SizeNotOpPrompt
    int 0x80
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
    mov EAX, 4
    mov EBX, 1
    mov ECX, ResultPrompt
    mov EDX, SizeResPrompt
    int 0x80

    mov EAX, 4
    mov EBX, 1
    mov ECX, ResultNumber
    mov EDX, 8
    int 0x80

SecureExit:
    mov EAX, 4
    mov EBX, 1
    mov ECX, NewLine  
    mov EDX, SizeNewLine  
    int 0x80 

    mov EAX, 1
    mov EBX, 0
    int 0x80