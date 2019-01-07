----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    00:38:12 06/13/2018 
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

entity contador is
    Generic (
           N : integer := 8
	 );
    Port ( clk : in  STD_LOGIC;
           clr : in  STD_LOGIC;
			  DOWN : in STD_LOGIC;
			  L : in STD_LOGIC;
           Q : inout  STD_LOGIC_VECTOR (N-1 downto 0) := (others => '0');
           D : in  STD_LOGIC_VECTOR (N-1 downto 0));
end contador;

architecture Behavioral of contador is

begin
	process(clk, clr)
	begin
		if clr = '1' then
			Q <= (OTHERS => '0');
		elsif rising_edge(clk) then
			if L = '1' then
				Q <= D;
			else
				if DOWN = '1' then
					Q <= Q - 1;
				else
					Q <= Q;
				end if;
			end if;
		end if;
	end process;

end Behavioral;

