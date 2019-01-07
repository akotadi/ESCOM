LIBRARY IEEE;

USE IEEE.STD_LOGIC_1164.ALL;
USE IEEE.STD_LOGIC_UNSIGNED.ALL;

ENTITY CONTADORv3 IS 

	GENERIC( 
		N : INTEGER := 10
	);

	PORT( 
		CLK, CLR, EN: IN STD_LOGIC;
		Q : INOUT STD_LOGIC_VECTOR(N-1 DOWNTO 0)
	);

	ATTRIBUTE PIN_NUMBERS OF CONTADORv3 : ENTITY IS 
		"CLK:1 "		&
		"EN:2 "			&
		"Q(0):22 "		&
		"Q(1):23 "		&
		"Q(2):14 "		&
		"Q(3):15 "		&
		"Q(4):16 "		&
		"Q(5):17 "		&
		"Q(6):18 "		&
		"Q(7):19 "		&
		"Q(8):20 "		&
		"Q(9):21 "		&
		"CLR:13";

END CONTADORv3;



ARCHITECTURE PRACTICA OF CONTADORv3 IS 

	BEGIN

	PROCESS(CLK, CLR)

		BEGIN
			IF( CLR = '1' ) THEN
				Q <= ( OTHERS => '0' );

			ELSIF RISING_EDGE(CLK) THEN

				IF ( EN = '1' ) THEN
					Q <= Q + 1;
				ELSE
					Q <= Q;
				END IF;

			END IF;
	END PROCESS;

END PRACTICA;