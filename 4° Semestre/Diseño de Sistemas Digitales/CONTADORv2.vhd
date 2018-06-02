LIBRARY IEEE;

USE IEEE.STD_LOGIC_1164.ALL;
USE IEEE.STD_LOGIC_UNSIGNED.ALL;
USE IEEE.STD_LOGIC_ARITH.ALL;

ENTITY CONTADORv2 IS 

	GENERIC( 
		N : INTEGER := 7
	);

	PORT( 
		CLK, CLR, EN, UD, L : IN STD_LOGIC;
		C : OUT STD_LOGIC;
		D : IN STD_LOGIC_VECTOR(N-1 DOWNTO 0);
		Q : INOUT STD_LOGIC_VECTOR(N-1 DOWNTO 0)
	);

	ATTRIBUTE PIN_NUMBERS OF CONTADORv2 : ENTITY IS 
		"CLK:1 "		&
		"D(6):2 "		&
		"D(5):3 "		&
		"D(4):4 "		&
		"D(3):5 "		&
		"D(2):6 "		&
		"D(1):7 "		&
		"D(0):8 "		&
		"EN:9 "			&
		"L:10 "			&
		"UD:11 "		&
		"CLR:13 "		&
		"Q(0):14 "		&
		"Q(1):15 "		&
		"Q(2):16 "		&
		"Q(3):17 "		&
		"Q(4):18 "		&
		"Q(5):19 "		&
		"Q(6):20 "		&
		"C:22";

END CONTADORv2;



ARCHITECTURE PRACTICA OF CONTADORv2 IS 

	BEGIN

	PROCESS(CLK, CLR)

		BEGIN

			IF( CLR = '1' ) THEN
				Q <= ( OTHERS => '0' );
				C <= '0';

			ELSIF RISING_EDGE(CLK) THEN

				IF ( EN = '1' ) THEN

					IF ( L = '1' ) THEN
						Q <= D;

					ELSE

						IF ( UD = '1' ) THEN
							Q <= Q + 1;
	
							IF ( Q = "1111111" ) THEN
								C <= '1';
							ELSE
								C <= '0';
							END IF;
	
						ELSIF ( UD = '0' ) THEN
							Q <= Q - 1;
	
							IF ( Q = "0000000" ) THEN
								C <= '1';
							ELSE
								C <= '0';
							END IF;
	
						END IF;

					END IF;
					
				END IF;

			END IF;

	END PROCESS;

END PRACTICA;