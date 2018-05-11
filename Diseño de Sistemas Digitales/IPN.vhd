LIBRARY IEEE;

USE IEEE.STD_LOGIC_1164.ALL;

ENTITY IPN IS 

	-- Permite agregar constantes genéricas que sirven para parametrizar el diseño
	GENERIC( 
		N : INTEGER := 3
	);

	PORT( 
		CLK, CLR: IN STD_LOGIC;
		DISPLAY: OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
		AN: INOUT STD_LOGIC_VECTOR(2 DOWNTO 0)
	);

END IPN;



ARCHITECTURE TEST OF IPN IS 

	CONSTANT SymbolI : STD_LOGIC_VECTOR(6 DOWNTO 0) := "1001111";
	CONSTANT SymbolP : STD_LOGIC_VECTOR(6 DOWNTO 0) := "0011000";
	CONSTANT Symboln : STD_LOGIC_VECTOR(6 DOWNTO 0) := "1101010";
	CONSTANT Q0 : STD_LOGIC_VECTOR(2 DOWNTO 0) := "110";
	CONSTANT Q1 : STD_LOGIC_VECTOR(2 DOWNTO 0) := "101";

	BEGIN

	PROCESS(CLK, CLR)

		--VARIABLE AN : STD_LOGIC_VECTOR(2 DOWNTO 0);

		BEGIN
			IF( CLR = '1' ) THEN
				AN <= Q0;

			-- FALLING_EDGE
			ELSIF RISING_EDGE(CLK) THEN

				-- ROR - ROTATION RIGHT
				-- ROL - ROTATION LEFT
				AN <= TO_STDLOGICVECTOR(TO_BITVECTOR(AN) ROL 1);

				--AN(0) := AN(2);

				--AN(0)<=AN(2);
				--AN(1)<=AN(0);
				--AN(2)<=AN(1);
				-- No son variables per se, se copian al driver y al terminar se asignan
			END IF;
	END PROCESS;

	DISPLAY <=
			SymbolI WHEN AN = Q0 ELSE
			SymbolP WHEN AN = Q1 ELSE
			Symboln;

END TEST;