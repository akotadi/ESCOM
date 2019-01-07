----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    18:06:24 06/14/2018 
-- Design Name: 
-- Module Name:    registro_promedio - Behavioral 
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

entity registro_promedio is
	 Generic(
		N : integer
	 );
    Port ( clk : in  STD_LOGIC;
           clr : in  STD_LOGIC;
			  L : in STD_LOGIC;
			  SHE : in STD_LOGIC;
           Q : inout  STD_LOGIC_VECTOR (N-1 downto 0);
           D : in  STD_LOGIC_VECTOR (N-1 downto 0));
end registro_promedio;

architecture Behavioral of registro_promedio is

begin
	process(clk, clr)
	begin
		if clr = '1' then
			Q <= (others => '0');
		elsif rising_edge(clk) then
			if L = '1' then
				Q <= D;
			else
				if SHE = '1' then
					Q <= to_stdlogicvector(to_bitvector(Q) srl 3);
				else
					Q <= Q;
				end if;
			end if;
		end if;
	end process;

end Behavioral;

