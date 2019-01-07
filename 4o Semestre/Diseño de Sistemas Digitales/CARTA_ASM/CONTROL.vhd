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
    Port ( CLK : in  STD_LOGIC;
           CLR : in  STD_LOGIC;
           INI : in  STD_LOGIC;
           A0 : in  STD_LOGIC;
           Z : in  STD_LOGIC;
           LA : out  STD_LOGIC;
           LB : out  STD_LOGIC;
           EA : out  STD_LOGIC;
           EB : out  STD_LOGIC;
           EC : out  STD_LOGIC);
end CONTROL;

architecture Behavioral of CONTROL is

	TYPE ESTADOS IS (A, B, C);

	SIGNAL EDO_ACT, EDO_SGTE : ESTADOS;

	BEGIN
	
	AFSM: PROCESS (EDO_ACT, INI, Z, A0)
	
		BEGIN
			LA <= '0';
			LB <= '0';
			EA <= '0';
			EB <= '0';
			EC <= '0';
		CASE EDO_ACT IS
			WHEN A =>	
				LB <= '1';
				IF( INI = '0' )THEN
					LA <= '1';
					EDO_SGTE <= A;
				ELSE
					EDO_SGTE <= B;
				END IF;
			WHEN B =>	
				EA <= '1';
				IF( Z = '0' )THEN
					IF ( A0 = '0' ) THEN
						EDO_SGTE <= B;
					ELSE
						EB <= '1';
						EDO_SGTE <= B;
					END IF;					
				ELSE
					EDO_SGTE <= C;	
				END IF;
			WHEN C =>	
				EC <= '1';
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

end Behavioral;
