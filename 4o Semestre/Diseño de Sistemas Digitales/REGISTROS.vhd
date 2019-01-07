LIBRARY IEEE;

USE IEEE.STD_LOGIC_1164.ALL;

ENTITY REGISTROS IS 
	PORT( 
		CLK, CLR, ES: IN STD_LOGIC;
		D: IN STD_LOGIC_VECTOR(6 DOWNTO 0);
		OP: IN STD_LOGIC_VECTOR(1 DOWNTO 0);
		Q: INOUT STD_LOGIC_VECTOR(6 DOWNTO 0)
	);

	ATTRIBUTE PIN_NUMBERS OF REGISTROS : ENTITY IS
		"CLK:1 "		&
		"ES:2 "			&
		"D(6):3 "		&
		"D(5):4 "		&
		"D(4):5 "		&
		"D(3):6 "		&
		"D(2):7 "		&
		"D(1):8 "		&
		"D(0):9 "		&
		"OP(0):10 "		&
		"OP(1):11 "		&
		"CLR:13 "		&
		"Q(0):14 "		&
		"Q(1):15 "		&
		"Q(2):16 "		&
		"Q(3):17 "		&
		"Q(4):18 "		&
		"Q(5):19 "		&
		"Q(6):20";

END REGISTROS;



ARCHITECTURE PRACTICA OF REGISTROS IS 

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