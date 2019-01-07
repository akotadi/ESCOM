----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    02:07:09 06/01/2018 
-- Design Name: 
-- Module Name:    SUMADOR - Behavioral 
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

entity SUMADOR is
    Port ( RESULT : out  STD_LOGIC_VECTOR (3 downto 0);
           N1 : in  STD_LOGIC_VECTOR (3 downto 0);
           N2 : in  STD_LOGIC_VECTOR (3 downto 0));
end SUMADOR;

architecture Behavioral of SUMADOR is

BEGIN

	SUM : PROCESS(N1, N2)
		BEGIN
			RESULT <= N1 + N2;
	END PROCESS SUM;

end Behavioral;