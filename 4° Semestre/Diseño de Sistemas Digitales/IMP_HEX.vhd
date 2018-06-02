LIBRARY IEEE;

USE IEEE.STD_LOGIC_1164.ALL;

ENTITY IMP_HEX IS
	PORT(
		CLK, CLR, EN : IN STD_LOGIC;
		Q : INOUT STD_LOGIC_VECTOR(6 DOWNTO 0)
	);

	ATTRIBUTE PIN_NUMBERS OF IMP_HEX : ENTITY IS 
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
END IMP_HEX;

ARCHITECTURE PRACTICA OF IMP_HEX IS

	CONSTANT Symbol0 : STD_LOGIC_VECTOR(6 DOWNTO 0) := "0000001";--01
	CONSTANT Symbol1 : STD_LOGIC_VECTOR(6 DOWNTO 0) := "1001111";--4F
	CONSTANT Symbol2 : STD_LOGIC_VECTOR(6 DOWNTO 0) := "0010010";--12
	CONSTANT Symbol3 : STD_LOGIC_VECTOR(6 DOWNTO 0) := "0000110";--06
	CONSTANT Symbol4 : STD_LOGIC_VECTOR(6 DOWNTO 0) := "1001100";--4C
	CONSTANT Symbol5 : STD_LOGIC_VECTOR(6 DOWNTO 0) := "0100100";--24
	CONSTANT Symbol6 : STD_LOGIC_VECTOR(6 DOWNTO 0) := "0100000";--20
	CONSTANT Symbol7 : STD_LOGIC_VECTOR(6 DOWNTO 0) := "0001111";--0F
	CONSTANT Symbol8 : STD_LOGIC_VECTOR(6 DOWNTO 0) := "0000000";--00
	CONSTANT Symbol9 : STD_LOGIC_VECTOR(6 DOWNTO 0) := "0000100";--04
	CONSTANT SymbolA : STD_LOGIC_VECTOR(6 DOWNTO 0) := "0001000";--08
	CONSTANT Symbolb : STD_LOGIC_VECTOR(6 DOWNTO 0) := "1100000";--60
	CONSTANT SymbolC : STD_LOGIC_VECTOR(6 DOWNTO 0) := "0110001";--31
	CONSTANT Symbold : STD_LOGIC_VECTOR(6 DOWNTO 0) := "1000010";--42
	CONSTANT SymbolE : STD_LOGIC_VECTOR(6 DOWNTO 0) := "0110000";--30
	CONSTANT SymbolF : STD_LOGIC_VECTOR(6 DOWNTO 0) := "0111000";--38

	BEGIN

		PROCESS(CLK, CLR)
			BEGIN
				IF (CLR = '1') THEN
					Q <= Symbol0;
				ELSIF RISING_EDGE(CLK) THEN
					IF ( EN = '1' ) THEN
						CASE Q IS
							WHEN Symbol0 => Q <= Symbol1;
							WHEN Symbol1 => Q <= Symbol2;
							WHEN Symbol2 => Q <= Symbol3;
							WHEN Symbol3 => Q <= Symbol4;
							WHEN Symbol4 => Q <= Symbol5;
							WHEN Symbol5 => Q <= Symbol6;
							WHEN Symbol6 => Q <= Symbol7;
							WHEN Symbol7 => Q <= Symbol8;
							WHEN Symbol8 => Q <= Symbol9;
							WHEN Symbol9 => Q <= SymbolA;
							WHEN SymbolA => Q <= Symbolb;
							WHEN Symbolb => Q <= SymbolC;
							WHEN SymbolC => Q <= Symbold;
							WHEN Symbold => Q <= SymbolE;
							WHEN SymbolE => Q <= SymbolF;
							WHEN OTHERS => Q <= Symbol0;
						END CASE;
					END IF;
				END IF;
		END PROCESS;
END PRACTICA;