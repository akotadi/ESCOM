LIBRARY IEEE;

USE IEEE.STD_LOGIC_1164.ALL;

ENTITY REGISTROSv3 IS 
	PORT( 
		CLK, CLR, ES: IN BIT;
		D: IN BIT_VECTOR(6 DOWNTO 0);
		OP: IN STD_LOGIC_VECTOR(1 DOWNTO 0);
		Q: INOUT BIT_VECTOR(6 DOWNTO 0)
	);

	ATTRIBUTE PIN_NUMBERS OF REGISTROSv3 : ENTITY IS
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

END REGISTROSv3;



ARCHITECTURE PRACTICA OF REGISTROSv3 IS 

	BEGIN

	PREG : PROCESS(CLK, CLR)
		BEGIN
			IF( CLR = '1' ) THEN
				Q <= ( OTHERS => '0' );
			--	Q <= X"0";
			--	Q <= "0000000";
			--	Q(0) <= '0';
			--	Q(1) <= '0';
			--	Q(2) <= '0';
			--	Q(3) <= '0';
			--	Q(4) <= '0';
			--	Q(5) <= '0';
			--	Q(6) <= '0';

			ELSIF( CLK'EVENT AND CLK = '1') THEN

				CASE OP IS
					WHEN "00" => Q <= Q;
					WHEN "01" => Q <= D;
					WHEN "10" => 
						Q <= Q SLL 1;
						Q(0) <= ES;
					WHEN OTHERS => 
						Q <= Q SRL 1;
						Q(6) <= ES;
				END CASE;
			END IF;
		END PROCESS PREG;

END PRACTICA;