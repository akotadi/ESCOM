LIBRARY IEEE;

USE IEEE.STD_LOGIC_1164.ALL;

ENTITY REGISTROSv6 IS 

	-- Permite agregar constantes genéricas que sirven para parametrizar el diseño
	GENERIC( 
		N : INTEGER := 7 
	);

	PORT( 
		CLK, CLR, ES: IN STD_LOGIC;
		D: IN STD_LOGIC_VECTOR(N-1 DOWNTO 0);
		OP: IN STD_LOGIC_VECTOR(1 DOWNTO 0);
		Q: INOUT STD_LOGIC_VECTOR(N-1 DOWNTO 0)
	);

END REGISTROSv6;



ARCHITECTURE PRACTICA OF REGISTROSv6 IS 

	BEGIN

	PREG : PROCESS(CLK, CLR)
		BEGIN
			IF( CLR = '1' ) THEN
				Q <= ( OTHERS => '0' );

			-- FALLING_EDGE
			ELSIF RISING_EDGE(CLK) THEN

				CASE OP IS
					WHEN "00" => Q <= Q;
					WHEN "01" => Q <= D;
					WHEN "10" => 
						Q <= TO_STDLOGICVECTOR(TO_BITVECTOR(Q) SLL 1);
						Q(0) <= ES;
					WHEN OTHERS => 
						Q <= TO_STDLOGICVECTOR(TO_BITVECTOR(Q) SRL 1);
						Q(N-1) <= ES;
				END CASE;
			END IF;
		END PROCESS PREG;

END PRACTICA;