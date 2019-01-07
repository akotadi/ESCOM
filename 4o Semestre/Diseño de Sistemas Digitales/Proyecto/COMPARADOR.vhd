----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    02:07:09 06/01/2018 
-- Design Name: 
-- Module Name:    COMPARADOR - Behavioral 
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

entity COMPARADOR is
    Port ( RESULT : out  STD_LOGIC;
           N1 : in  STD_LOGIC_VECTOR (3 downto 0);
           N2 : in  STD_LOGIC_VECTOR (3 downto 0));
end COMPARADOR;

architecture Behavioral of COMPARADOR is

BEGIN

	COMP : PROCESS(N1, N2)
		BEGIN
			IF (N1 > N2) THEN
				RESULT <= '1';
			ELSE
				RESULT <= '0';
			END IF;
	END PROCESS COMP;

end Behavioral;