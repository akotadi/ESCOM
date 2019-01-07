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
           DB : in  STD_LOGIC_VECTOR (3 downto 0);
           B : inout  STD_LOGIC_VECTOR (3 downto 0);
           EB : in  STD_LOGIC;
           LB : in  STD_LOGIC);
end CONTADOR;

architecture Behavioral of CONTADOR is

begin
	CONT : process(CLK, CLR)
	begin
		if CLR = '1' then
			B <= (OTHERS => '0');
		elsif rising_edge(CLK) then
			if LB = '1' then
				B <= DB;
			else
				if EB = '1' then
					B <= B + 1;
				else
					B <= B;
				end if;
			end if;
		end if;
	end process CONT;

end Behavioral;