LIBRARY IEEE;

--Frecuencia arriba de 90 Hz, preferible arriba de 150 Hz

USE IEEE.STD_LOGIC_1164.ALL;

ENTITY HEY IS 

	PORT( 
		CLK, CLR: IN STD_LOGIC;
		DISPLAY: OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
		AN: INOUT STD_LOGIC_VECTOR(2 DOWNTO 0)
	);

	ATTRIBUTE PIN_NUMBERS OF HEY : ENTITY IS
		"CLK:1 "			&
		"CLR:13 "			&
		"DISPLAY(0):15 "	&
		"DISPLAY(1):16 "	&
		"DISPLAY(2):17 "	&
		"DISPLAY(3):18 "	&
		"DISPLAY(4):19 "	&
		"DISPLAY(5):20 "	&
		"DISPLAY(6):21 "	&
		"AN(2):22 "			&
		"AN(1):23 "			&
		"AN(0):14";

END HEY;



ARCHITECTURE PRACTICA OF HEY IS 

	CONSTANT SymbolH : STD_LOGIC_VECTOR(6 DOWNTO 0) := "1001000";
	CONSTANT SymbolE : STD_LOGIC_VECTOR(6 DOWNTO 0) := "0110000";
	CONSTANT Symboly : STD_LOGIC_VECTOR(6 DOWNTO 0) := "1001100";
	CONSTANT Q0 : STD_LOGIC_VECTOR(2 DOWNTO 0) := "110";
	CONSTANT Q1 : STD_LOGIC_VECTOR(2 DOWNTO 0) := "101";

	BEGIN

	PROCESS(CLK, CLR)

		SIGNAL AN : STD_LOGIC_VECTOR(2 DOWNTO 0);

		BEGIN
			IF( CLR = '1' ) THEN
				AN <= Q0;

			-- FALLING_EDGE
			ELSIF RISING_EDGE(CLK) THEN

				-- ROR - ROTATION RIGHT
				-- ROL - ROTATION LEFT
				AN <= TO_STDLOGICVECTOR(TO_BITVECTOR(AN) ROL 1);

				--AN(0) := AN(2);

	--			AN(0)<=AN(2);
	--			AN(1)<=AN(0);
	--			AN(2)<=AN(1);
				-- No son variables per se, se copian al driver y al terminar se asignan
			END IF;
	END PROCESS;

	DISPLAY <=
			Symboly WHEN AN = Q0 ELSE
			SymbolE WHEN AN = Q1 ELSE
			SymbolH;

END PRACTICA;