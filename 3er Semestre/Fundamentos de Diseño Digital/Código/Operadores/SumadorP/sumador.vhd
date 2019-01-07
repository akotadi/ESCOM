LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;

ENTITY SUMA IS
	PORT(
		A,B: in STD_LOGIC_VECTOR(3 downto 0);
		S: out STD_LOGIC_VECTOR(3 downto 0);
		Cout: out STD_LOGIC
		);

  
	ATTRIBUTE PIN_NUMBERS OF SUMA: ENTITY IS
	"S(0):18 S(1):17 S(2):16 S(3):15 Cout:14";
END SUMA;

ARCHITECTURE BEHAVIORAL OF SUMA IS
signal C: std_logic_vector(2 downto 0);
ATTRIBUTE synthesis_off OF C: SIGNAL IS true;
begin
	S(0)<=A(0)XOR B(0);
	C(0)<=A(0) AND B(0);
	S(1)<=(A(1)XOR B(1)) XOR C(0);
	C(1)<=(A(1) AND B(1)) OR (C(0) AND (A(1) XOR B(1)));
	S(2)<=(A(2) XOR B(2)) XOR C(1);
	C(2)<=(A(2) AND B(2)) OR (C(1) AND (A(2) XOR B(2)));
	S(3)<=(A(3) XOR B(3)) XOR C(2);
	Cout<=(A(3) AND B(3)) OR (C(2) AND (A(3) XOR B(3)));

	END BEHAVIORAL;