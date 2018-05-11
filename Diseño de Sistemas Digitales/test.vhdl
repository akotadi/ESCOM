LIBRARY IEEE;

USE IEEE.STD_LOGIC_1164.ALL;

ENTITY TEST IS 
	PORT( 
		CLK, CLR, ES: IN STD_LOGIC;
		D: IN STD_LOGIC_VECTOR(6 DOWNTO 0);
		OP: IN STD_LOGIC_VECTOR(1 DOWNTO 0);
		Q: INOUT STD_LOGIC_VECTOR(6 DOWNTO 0)
	);

END TEST;



ARCHITECTURE PRACTICA OF TEST IS 

	BEGIN

	PREG : PROCESS(CLK, CLR)
		BEGIN
			IF( CLR = '1' ) THEN
				Q <= ( OTHERS => '0' ); -- PARA TODAS LAS OPCIONES, LLENAR CON '0'
			--	Q <= X"0"; HEXADECIMAL
			--	Q <= "0000000"; VECTOR DE BITS
			--	Q(0) <= '0'; BIT A BIT
			--	Q(1) <= '0';
			--	Q(2) <= '0';
			--	Q(3) <= '0';
			--	Q(4) <= '0';
			--	Q(5) <= '0';
			--	Q(6) <= '0';

			ELSIF( CLK'EVENT AND CLK = '1') THEN

				FOR I IN 6 DOWNTO 0 LOOP
					IF( I = 0 ) THEN
						Q(I) <= ( Q(I) AND ( NOT OP(1) ) AND ( NOT OP(0) ) ) OR 
						( D(I) AND ( NOT OP(1) ) AND OP(0) ) OR 
						( ES AND OP(1) AND ( NOT OP(0) ) ) OR 
						( Q(I+1) AND OP(1) AND OP(0) );
					ELSIF( I = 6 ) THEN
						Q(I) <= ( Q(I) AND ( NOT OP(1) ) AND ( NOT OP(0) ) ) OR 
						( D(I) AND ( NOT OP(1) ) AND OP(0) ) OR 
						( Q(I-1) AND OP(1) AND ( NOT OP(0) ) ) OR 
						( ES AND OP(1) AND OP(0) );
					ELSE
						Q(I) <= ( Q(I) AND ( NOT OP(1) ) AND ( NOT OP(0) ) ) OR 
						( D(I) AND ( NOT OP(1) ) AND OP(0) ) OR 
						( Q(I-1) AND OP(1) AND ( NOT OP(0) ) ) OR 
						( Q(I+1) AND OP(1) AND OP(0) );
					END IF;						
				END LOOP;
			END IF;
			
		END PROCESS PREG;

END PRACTICA;