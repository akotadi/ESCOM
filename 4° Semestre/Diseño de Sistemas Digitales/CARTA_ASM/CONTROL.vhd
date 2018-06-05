LIBRARY IEEE;

USE IEEE.STD_LOGIC_1164.ALL;

ENTITY CONTROL IS 

	PORT( 
		CLK, CLR, INI, Z, A0: IN STD_LOGIC;
		LB, EB, LA, EA, EC : OUT STD_LOGIC
	);

END CONTROL;

ARCHITECTURE UNIDAD OF CONTROL IS

	TYPE ESTADOS IS (A, B, C);

	SIGNAL EDO_ACT, EDO_SGTE : ESTADOS;

	BEGIN
	
	AFSM: PROCESS (EDO_ACT, INI, Z, A0)
	
		BEGIN
			LA <= '0';
			EA <= '0';
			LB <= '0';
			EB <= '0';
			EC <= '0';

			CASE EDO_ACT IS
				WHEN A =>	LB <= '1';
					IF( INI = '0' )THEN
						LA <= '1';
						EDO_SGTE <= A;
					ELSE
						EDO_SGTE <= B;
					END IF;
				WHEN B =>	EA <= '1';
					IF( Z = '1' )THEN
						EDO_SGTE <= C;						
					ELSE
						IF ( A0 = '1' ) THEN
							EB <= '1';
							EDO_SGTE <= B;
						ELSE
							EDO_SGTE <= B;
						END IF;
					END IF;
				WHEN C =>	EC <= '1';
					IF( INI = '0' )THEN
						EDO_SGTE <= A;		
					ELSE
						EDO_SGTE <= C;				
					END IF;
			END CASE;
	END PROCESS AFSM;
		
	TRANSICION : PROCESS( CLK, CLR )

		BEGIN
			IF( CLR = '1' )THEN
				EDO_ACT <= A;
			ELSIF RISING_EDGE(CLK) THEN
				EDO_ACT <= EDO_SGTE;
			END IF;

	END PROCESS TRANSICION;

END UNIDAD;
