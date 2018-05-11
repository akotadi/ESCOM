LIBRARY IEEE;

USE IEEE.STD_LOGIC_1164.ALL;

ENTITY CONTADOR IS 

	GENERIC( 
		N : INTEGER := 7 
	);

	PORT( 
		CLK, CLR, EN: IN STD_LOGIC;
		Q : INOUT STD_LOGIC_VECTOR(N-1 DOWNTO 0)
	);

	ATTRIBUTE PIN_NUMBERS OF CONTADOR : ENTITY IS 
		"CLK:1 "		&
		"EN:9 "			&
		"Q(0):14 "		&
		"Q(1):15 "		&
		"Q(2):16 "		&
		"Q(3):17 "		&
		"Q(4):18 "		&
		"Q(5):19 "		&
		"Q(6):20 "		&
		"CLR:13";

END CONTADOR;



ARCHITECTURE PRACTICA OF CONTADOR IS 

	BEGIN

	PROCESS(CLK, CLR)

		VARIABLE AUX : STD_LOGIC;

		BEGIN
			IF( CLR = '1' ) THEN
				Q <= ( OTHERS => '0' );

			ELSIF RISING_EDGE(CLK) THEN
				FOR I IN 0 TO N-1 LOOP
					AUX := EN;
					FOR J IN 0 TO I-1 LOOP
						AUX := AUX AND Q(J);
					END LOOP;
					Q(I) <= Q(I) XOR AUX;
				END LOOP;
			END IF;
	END PROCESS;

END PRACTICA;