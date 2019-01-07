segment .data
	Title db  'Intercalador de Cadenas.', 0xD, 0xA
	SizeTitle equ $ - Title

	Prompt1 db '	Primer cadena: '
	SizePrompt1 equ $- Prompt1

	Prompt2 db '	Segunda cadena: '
	SizePrompt2 equ $- Prompt2

	Prompt3 db '	Tercera cadena: '
	SizePrompt3 equ $- Prompt3

	ResultPrompt db 0xD, 0xA, 'Resultado: '
	SizeResPrompt equ $- ResultPrompt

	handle 		db 0
	leido 		db 0

segment .bss
	string1 	resb 50
	string2 	resb 50
	string3 	resb 50
	result 		resb 151

segment .text
	global		_start
	extern		_GetStdHandle@4
	extern		_WriteConsoleA@20
	extern		_ReadConsoleA@20
	extern		_ExitProcess@4

_start:
    push dword dword -11
    call _GetStdHandle@4
    mov [handle], EAX
    push dword 0
    push dword leido
    push dword SizeTitle
    push dword Title
    push dword [handle]
    call _WriteConsoleA@20

GetFirstString:
    
	AskString1:
		push dword dword -11
		call _GetStdHandle@4
		mov [handle], EAX
		push dword 0
		push dword leido
		push dword SizePrompt1
		push dword Prompt1
		push dword [handle]
		call _WriteConsoleA@20

	ReadString1:
		push dword dword -10
		call _GetStdHandle@4
		mov [handle], EAX
		push dword 0
		push dword leido
		push dword 50
		push dword string1
		push dword [handle]
		call _ReadConsoleA@20

GetSecondString:
    
	AskString2:
		push dword dword -11
		call _GetStdHandle@4
		mov [handle], EAX
		push dword 0
		push dword leido
		push dword SizePrompt2
		push dword Prompt2
		push dword [handle]
		call _WriteConsoleA@20

	ReadString2:
		push dword dword -10
		call _GetStdHandle@4
		mov [handle], EAX
		push dword 0
		push dword leido
		push dword 50
		push dword string2
		push dword [handle]
		call _ReadConsoleA@20

GetThirdString:
    
	AskString3:
		push dword dword -11
		call _GetStdHandle@4
		mov [handle], EAX
		push dword 0
		push dword leido
		push dword SizePrompt3
		push dword Prompt3
		push dword [handle]
		call _WriteConsoleA@20

	ReadString3:
		push dword dword -10
		call _GetStdHandle@4
		mov [handle], EAX
		push dword 0
		push dword leido
		push dword 50
		push dword string3
		push dword [handle]
		call _ReadConsoleA@20

MoveString:
	mov EAX, string1
	mov EBX, string2
	mov EDI, string3
	mov ESI, result

Intercalate:

	MoveString1:
		mov DL, byte[EAX]
		cmp DL, 0xD
		je MoveString2

		mov byte[ESI], DL
		inc EAX
		inc ESI

	MoveString2:
		mov DL, byte[EBX]
		cmp DL, 0xD
		je MoveString3

		mov byte[ESI], DL
		inc EBX
		inc ESI

	MoveString3:
		mov DL, byte[EDI]
		cmp DL, 0xD
		je ShowResult

		mov byte[ESI], DL
		inc EDI
		inc ESI
		jmp MoveString1


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
	push dword 151
	push dword result
	push dword [handle]
	call _WriteConsoleA@20

SecureExit:
	push dword 0
	call _ExitProcess@4