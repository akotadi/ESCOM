LIBRARY IEEE;

USE IEEE.STD_LOGIC_1164.ALL;

ENTITY REGISTRO IS 

	PORT( 
		CLK, CLR, LA, EA: IN STD_LOGIC;
		D : IN STD_LOGIC_VECTOR(7 DOWNTO 0);
		A : INOUT STD_LOGIC_VECTOR(7 DOWNTO 0)
	);

END REGISTRO;



ARCHITECTURE PRACTICA OF REGISTRO IS 

	BEGIN

	REG : PROCESS(CLK, CLR)
		BEGIN
			IF ( CLR = '1' ) THEN
				A <= (OTHERS => '0');
			ELSIF RISING_EDGE(CLK) THEN
				IF ( LA = '1' ) THEN
					A <= D;
				ELSIF ( EA = '1' ) THEN
					A <= TO_STDLOGICVECTOR(TO_BITVECTOR(A) SRL 1);
				ELSE
					A <= A;
				END IF;
			END IF;
	END PROCESS REG;

END PRACTICA;