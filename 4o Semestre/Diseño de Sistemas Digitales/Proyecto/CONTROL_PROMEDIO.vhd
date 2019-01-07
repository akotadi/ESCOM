----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    18:09:29 06/18/2018 
-- Design Name: 
-- Module Name:    fsm2 - Behavioral 
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

entity CONTROL_PROMEDIO is
    Port ( CLK : in  STD_LOGIC;
           CLR : in  STD_LOGIC;
           INI : in  STD_LOGIC;
			  LB : out STD_LOGIC;
           EB : out  STD_LOGIC;
           Ln : out  STD_LOGIC;
           EM : out  STD_LOGIC;
           SHE : out  STD_LOGIC;
           Z : in  STD_LOGIC);
end CONTROL_PROMEDIO;

architecture Behavioral of CONTROL_PROMEDIO is

	TYPE ESTADOS IS (A, B, C, D);

	SIGNAL EDO_ACT, EDO_SGTE : ESTADOS;

	BEGIN
	
	AFSM : PROCESS(EDO_ACT, INI, Z)
	BEGIN
		LB <= '0';
		EB <= '0';
		Ln <= '0';
		EM <= '0';
		SHE <= '0';
		CASE EDO_ACT is
			WHEN A =>
				LB <= '1';
				EB <= '0';
				Ln <= '0';
				SHE <= '0';
				IF ( INI = '1' ) THEN
					EDO_SGTE <= B;
				ELSE
					EDO_SGTE <= A;
				END IF;
			WHEN B=>
				IF ( Z = '1' ) THEN
					SHE <= '1';
					EDO_SGTE <= C;
				ELSE
					EB <= '1';
					Ln <= '1';
					EDO_SGTE <= B;
				END IF;
			WHEN C =>
				EDO_SGTE <= D;
			WHEN D =>
				EM <= '1';
				IF INI = '0' THEN
					EM <= '0';
					EDO_SGTE <= A;
				ELSE
					EDO_SGTE <= D;
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

