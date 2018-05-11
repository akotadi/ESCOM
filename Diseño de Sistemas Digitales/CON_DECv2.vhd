LIBRARY IEEE;

USE IEEE.STD_LOGIC_1164.ALL;
USE IEEE.STD_LOGIC_ARITH.ALL;
USE IEEE.STD_LOGIC_UNSIGNED.ALL;

ENTITY CON_DECv2 IS 	

	GENERIC( 
		N : INTEGER := 7 
	);

	PORT( 
		CLK, CLR : IN STD_LOGIC; 
		D, D_INPUT, Q1, Q2 : INOUT STD_LOGIC;
		UNI : INOUT STD_LOGIC_VECTOR(3 DOWNTO 0);
		DEC : INOUT STD_LOGIC_VECTOR(2 DOWNTO 0)
	);

	ATTRIBUTE PIN_NUMBERS OF CON_DECv2 : ENTITY IS 
		"CLK:1 "		&
		"D_INPUT:4 "	&
		"D:21 "			&
		"Q1:22 "		&
		"Q2:23 "		&
		"CLR:13 "		&
		"UNI(0):14 "	&
		"UNI(1):15 "	&
		"UNI(2):16 "	&
		"UNI(3):17 "	&
		"DEC(0):18 "	&
		"DEC(1):29 "	&
		"DEC(2):20";
END CON_DECv2;



ARCHITECTURE PRACTICA OF CON_DECv2 IS 

	SIGNAL EN : STD_LOGIC;

	BEGIN
	
	EN <= Q1 XOR Q2;

	D_ASIG : PROCESS(CLK, CLR)
		BEGIN
			IF( CLR = '1' ) THEN
				D <= '0';
			ELSIF RISING_EDGE(CLK) THEN
				D <= D_INPUT;
			END IF;
	END PROCESS D_ASIG;

	ASIG : PROCESS(CLK, CLR)
		BEGIN
			IF( CLR = '1' ) THEN
				Q1 <= '0';
				Q2 <= '0';
			ELSIF RISING_EDGE(CLK) THEN
				Q1 <= D;
				Q2 <= D;
			END IF;
	END PROCESS ASIG;

	CONT : PROCESS(CLK, CLR)
		BEGIN
			IF( CLR = '1' ) THEN
				UNI <= (OTHERS => '0');
				DEC <= (OTHERS => '0');
			ELSIF RISING_EDGE(CLK) THEN
				IF ( EN = '1' ) THEN
					IF UNI = "1001" THEN
						UNI <= (OTHERS => '0');
						DEC <= DEC + 1;
					ELSE
						UNI <= UNI + 1;
					END IF;
				END IF;
			END IF;
	END PROCESS CONT;

END PRACTICA;