LIBRARY IEEE;

USE IEEE.STD_LOGIC_1164.ALL;
USE IEEE.STD_LOGIC_ARITH.ALL;
USE IEEE.STD_LOGIC_UNSIGNED.ALL;

ENTITY SENSORES IS 

	PORT( 
		CLK, CLR : IN STD_LOGIC; 
		SENSOR : IN STD_LOGIC_VECTOR(1 DOWNTO 0);
		UNI : OUT STD_LOGIC_VECTOR(3 DOWNTO 0);
		DEC : OUT STD_LOGIC_VECTOR(2 DOWNTO 0)
	);

	ATTRIBUTE PIN_NUMBERS OF SENSORES : ENTITY IS 
		"CLK:1 "		&
		"SENSOR(0):2 "	&
		"SENSOR(1):3 "	&
		"CLR:13 "		&
		"UNI(0):14 "	&
		"UNI(1):15 "	&
		"UNI(2):16 "	&
		"UNI(3):17 "	&
		"DEC(0):18 "	&
		"DEC(1):29 "	&
		"DEC(2):20";
END SENSORES;



ARCHITECTURE PRACTICA OF SENSORES IS 

	TYPE States IS (q0, q1, q2, q3, q4, q5, q6, q7);

	SIGNAL SAL : STD_LOGIC_VECTOR(1 DOWNTO 0);
	SIGNAL CurrentState, NextState : States;

	CONSTANT Increase : STD_LOGIC_VECTOR(1 DOWNTO 0) := "01";
	CONSTANT Decrease : STD_LOGIC_VECTOR(1 DOWNTO 0) := "10";
	CONSTANT Stay : STD_LOGIC_VECTOR(1 DOWNTO 0) := "00";

	BEGIN

	FSM : PROCESS(CurrentState, SENSOR)
		BEGIN
			CASE CurrentState IS
				WHEN q0 => 
					CASE SENSOR IS
						WHEN "00" => 
							NextState <= q0;
							SAL <= Stay;
						WHEN "01" => 
							NextState <= q4;
							SAL <= Stay;
						WHEN "10" => 
							NextState <= q1;
							SAL <= Stay;
						WHEN OTHERS => 
							NextState <= q7;
							SAL <= Stay;
					END CASE;
				WHEN q1 => 
					CASE SENSOR IS
						WHEN "00" => 
							NextState <= q0;
							SAL <= Stay;
						WHEN "01" => 
							NextState <= q4;
							SAL <= Stay;
						WHEN "10" => 
							NextState <= q1;
							SAL <= Stay;
						WHEN OTHERS => 
							NextState <= q2;
							SAL <= Stay;
					END CASE;
				WHEN q2 => 
					CASE SENSOR IS
						WHEN "00" => 
							NextState <= q0;
							SAL <= Stay;
						WHEN "01" => 
							NextState <= q3;
							SAL <= Stay;
						WHEN "10" => 
							NextState <= q1;
							SAL <= Stay;
						WHEN OTHERS => 
							NextState <= q2;
							SAL <= Stay;
					END CASE;
				WHEN q3 => 
					CASE SENSOR IS
						WHEN "00" => 
							NextState <= q0;
							SAL <= Increase;
						WHEN "01" => 
							NextState <= q3;
							SAL <= Stay;
						WHEN "10" => 
							NextState <= q1;
							SAL <= Increase;
						WHEN OTHERS => 
							NextState <= q2;
							SAL <= Stay;
					END CASE;
				WHEN q4 => 
					CASE SENSOR IS
						WHEN "00" => 
							NextState <= q0;
							SAL <= Stay;
						WHEN "01" => 
							NextState <= q4;
							SAL <= Stay;
						WHEN "10" => 
							NextState <= q1;
							SAL <= Stay;
						WHEN OTHERS => 
							NextState <= q5;
							SAL <= Stay;
					END CASE;
				WHEN q5 => 
					CASE SENSOR IS
						WHEN "00" => 
							NextState <= q0;
							SAL <= Stay;
						WHEN "01" => 
							NextState <= q4;
							SAL <= Stay;
						WHEN "10" => 
							NextState <= q6;
							SAL <= Stay;
						WHEN OTHERS => 
							NextState <= q5;
							SAL <= Stay;
					END CASE;
				WHEN q6 => 
					CASE SENSOR IS
						WHEN "00" => 
							NextState <= q0;
							SAL <= Decrease;
						WHEN "01" => 
							NextState <= q4;
							SAL <= Decrease;
						WHEN "10" => 
							NextState <= q6;
							SAL <= Stay;
						WHEN OTHERS => 
							NextState <= q5;
							SAL <= Stay;
					END CASE;
				WHEN q7 => 
					CASE SENSOR IS
						WHEN "00" => 
							NextState <= q0;
							SAL <= Stay;
						WHEN "01" => 
							NextState <= q4;
							SAL <= Stay;
						WHEN "10" => 
							NextState <= q1;
							SAL <= Stay;
						WHEN OTHERS => 
							NextState <= q7;
							SAL <= Stay;
					END CASE;
			END CASE;
	END PROCESS FSM;
	
	ASIG : PROCESS(CLK, CLR)
		BEGIN
			IF( CLR = '1' ) THEN
				CurrentState <= q0;
			ELSIF RISING_EDGE(CLK) THEN
				CurrentState <= NextState;
			END IF;
	END PROCESS ASIG;

	CONT : PROCESS(CLK, CLR)
		BEGIN
			IF( CLR = '1' ) THEN
				UNI <= (OTHERS => '0');
				DEC <= (OTHERS => '0');
			ELSIF RISING_EDGE(CLK) THEN
				CASE SAL IS
					WHEN Stay => 
						UNI <= UNI;
						DEC <= DEC;
					WHEN Increase =>
						IF UNI = "1001" THEN
							UNI <= (OTHERS => '0');
							DEC <= DEC + 1;
						ELSE
							UNI <= UNI + 1;
						END IF;
					WHEN Decrease =>
						IF UNI = "0000" THEN
							UNI <= "1001";
							DEC <= DEC - 1;
						ELSE
							UNI <= UNI - 1;
						END IF;
				END CASE;
			END IF;
	END PROCESS CONT;

END PRACTICA;