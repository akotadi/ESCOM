library ieee;
use ieee.std_logic_1164.all;

entity variables is
	Port(
		A:in std_logic;
		B:in std_logic;
		C:in std_logic;
		D:in std_logic;
		C0:out std_logic;
		C1:out std_logic;
		A1:out std_logic;
		C2:out std_logic;
		B1:out std_logic;
		C11:out std_logic;
		D1:out std_logic
		);

	ATTRIBUTE PIN_NUMBERS OF variables: ENTITY IS
	"C0:14 C1:15 A1:16 C2:17 B1:18 C11:19 D1:20";
end variables;

architecture HAMMING of variables is
	begin
		C0<= ((NOT A) AND B) OR ((NOT A) AND D) OR (B AND D);
		C1<= (NOT(A XOR C) AND D) OR ((A XOR C) AND B);
		A1<= A;
		C2<= (B AND D) OR (B AND (NOT C)) OR ((NOT C) AND D);
		B1<= B;
		C11<= C;
		D1<= D;
end HAMMING;
