LIBRARY IEEE;

USE IEEE.STD_LOGIC_1164.ALL;

ENTITY IMP_CONT IS
	PORT(
		CLK, CLR, EN : IN STD_LOGIC;
		Q : INOUT STD_LOGIC_VECTOR(6 DOWNTO 0)
	);

	ATTRIBUTE PIN_NUMBERS OF IMP_CONT : ENTITY IS 
		"CLK:1 "		&
		"EN:2 "			&
		"CLR:13 "		&
		"Q(0):15 "		&
		"Q(1):16 "		&
		"Q(2):17 "		&
		"Q(3):18 "		&
		"Q(4):19 "		&
		"Q(5):20 "		&
		"Q(6):21";
END IMP_CONT;

ARCHITECTURE PRACTICA OF IMP_CONT IS

	CONSTANT Symbol1 : STD_LOGIC_VECTOR(6 DOWNTO 0) := "1001111";
	CONSTANT Symbol2 : STD_LOGIC_VECTOR(6 DOWNTO 0) := "0010010";
	CONSTANT Symbol3 : STD_LOGIC_VECTOR(6 DOWNTO 0) := "0000110";
	CONSTANT Symbol4 : STD_LOGIC_VECTOR(6 DOWNTO 0) := "1001100";
	CONSTANT Symbol5 : STD_LOGIC_VECTOR(6 DOWNTO 0) := "0100100";
	CONSTANT Symbol6 : STD_LOGIC_VECTOR(6 DOWNTO 0) := "0100000";

	BEGIN

		PROCESS(CLK, CLR)
			BEGIN
				IF (CLR = '1') THEN
					Q <= Symbol1;
				ELSIF RISING_EDGE(CLK) THEN
					IF ( EN = '1' ) THEN
						CASE Q IS
							WHEN Symbol1 => Q <= Symbol2;
							WHEN Symbol2 => Q <= Symbol3;
							WHEN Symbol3 => Q <= Symbol4;
							WHEN Symbol4 => Q <= Symbol5;
							WHEN Symbol5 => Q <= Symbol6;
							WHEN OTHERS => Q <= Symbol1;
						END CASE;
					END IF;
				END IF;
		END PROCESS;
END PRACTICA;