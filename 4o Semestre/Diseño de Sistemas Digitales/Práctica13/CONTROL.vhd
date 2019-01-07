----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    02:26:06 06/01/2018 
-- Design Name: 
-- Module Name:    fsm - Behavioral 
-- Project Name: 
-- Target Devices: 
-- Tool versions: 
-- Description: 
--
-- Dependencies: 
--
-- Revision: 
-- Revision 0.01 - File Created
-- Additional Comments: 
--
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity CONTROL is
	Port ( 
		CLK : in  STD_LOGIC;
		CLR : in  STD_LOGIC;
		INI : in  STD_LOGIC;
		Z : in  STD_LOGIC;
		CU : in STD_LOGIC;
		CD : in STD_LOGIC;
		CC : in STD_LOGIC;
		Ln : out  STD_LOGIC;
		LU : out  STD_LOGIC;
		LD : out  STD_LOGIC;
		LC : out  STD_LOGIC;
		LB : out  STD_LOGIC;
		EB : out  STD_LOGIC;
		SU : out  STD_LOGIC;
		SD : out  STD_LOGIC;
		SC : out  STD_LOGIC;
		SHE : out  STD_LOGIC;
		EM : out STD_LOGIC
	);
end CONTROL;

architecture Behavioral of CONTROL is

	TYPE ESTADOS IS (A, B, C);

	SIGNAL EDO_ACT, EDO_SGTE : ESTADOS;

	BEGIN
	
	AFSM: PROCESS (EDO_ACT, INI, Z, CU, CD, CC)
	
		BEGIN
			SHE <= '0';
			Ln <= '0';
			LU <= '0';
			LD <= '0';
			LC <= '0';
			SU <= '0'; -- Carga 0's
			SD <= '0';
			SC <= '0';
			LB <= '0';
			EB <= '0';
			EM <= '0';
		CASE EDO_ACT IS
			WHEN A =>	
				LU <= '1';
				LD <= '1';
				LC <= '1';
				SU <= '0'; -- Carga 0's
				SD <= '0';
				SC <= '0';
				LB <= '1';
				IF ( INI = '0' ) THEN
					Ln <= '1';
					EDO_SGTE <= A;
				ELSE
					EDO_SGTE <= B;
				END IF;
			WHEN B =>	
				IF ( Z = '0' ) THEN
					IF ( CU = '1' ) THEN
						LU <= '1';
					ELSE
						LU <= '0';
					END IF;	
					IF ( CD = '1' ) THEN
						LD <= '1';
					ELSE
						LD <= '0';
					END IF;	
					IF ( CC = '1' ) THEN
						LC <= '1';
					ELSE
						LC <= '0';
					END IF;
					IF ( CU = '0' AND CD = '0' AND CC = '0' ) THEN
						SHE <= '1';
						EB <= '1';
					ELSE
						SHE <= '1';
						EB <= '0';
					END IF;
				ELSE
					EDO_SGTE <= C;	
				END IF;
			WHEN C =>	
				EM <= '1';
				IF ( INI = '0' ) THEN
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

end Behavioral;
