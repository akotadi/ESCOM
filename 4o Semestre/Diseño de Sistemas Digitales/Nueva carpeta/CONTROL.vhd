library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity CONTROL is
    Port ( LA, EA, LB, EB, EC : OUT  STD_LOGIC;
           A0 : in  STD_LOGIC;
           Z : in  STD_LOGIC;
           CLK : in  STD_LOGIC;
           CLR : in  STD_LOGIC;
           INI : IN  STD_LOGIC
			  );
end CONTROL;

architecture UNIDAD of CONTROL is
TYPE ESTADOS IS (E0, E1, E2);
SIGNAL EDO_ACT, EDO_SGTE : ESTADOS;
begin
	AUTOMATA: process(EDO_ACT, INI, Z, A0)
		begin
			LA <= '0';
			EA <= '0';
			LB <= '0';
			EB <= '0';
			EC <= '0';

			CASE EDO_ACT IS
				WHEN E0 =>	LB <= '1';
					IF( INI = '0' )THEN
						LA <= '1';
					ELSE
						EDO_SGTE <= E1;
					END IF;
				WHEN E1 =>	EA <= '1';
					IF( Z = '1' )THEN
						EDO_SGTE <= E2;						
					ELSE
						IF (A0 =1) THEN
							EB<='1';
						END IF;
					END IF;
				WHEN E2 =>	EC <= '1';
					IF( INI = '0' )THEN
						EDO_SGTE <= E0;						
					END IF;
			END CASE;
		end process;
		
	TRANSICION : PROCESS( CLK, CLR )
	BEGIN
		IF( CLR = '1' )THEN
			EDO_ACT <= E0;
		ELSIF( RISING_EDGE(CLK) )THEN
			EDO_ACT <= EDO_SGTE;
		END IF;
	END PROCESS TRANSICION;

end CONTROL;

