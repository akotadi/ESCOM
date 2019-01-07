----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    02:07:09 06/01/2018 
-- Design Name: 
-- Module Name:    contador - Behavioral 
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
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity CONTADOR is
    Port ( CLK : in  STD_LOGIC;
           CLR : in  STD_LOGIC;
           D : in  STD_LOGIC_VECTOR (3 downto 0);
           Q : inout  STD_LOGIC_VECTOR (3 downto 0);
           EB : in  STD_LOGIC;
           LB : in  STD_LOGIC);
end CONTADOR;

architecture Behavioral of CONTADOR is

BEGIN
	CONT : PROCESS(CLK, CLR)
		BEGIN
			IF ( CLR = '1' ) THEN
				Q <= (OTHERS => '0');
			ELSIF rising_edge(CLK) THEN
				IF ( LB = '1' ) THEN
					Q <= D;
				ELSE
					IF ( EB = '1' ) THEN
						Q <= Q - 1;
					ELSE
						Q <= Q;
					END IF;
				END IF;
			END IF;
	END PROCESS CONT;

end Behavioral;