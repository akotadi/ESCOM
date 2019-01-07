----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    00:19:21 06/13/2018 
-- Design Name: 
-- Module Name:    registro - Behavioral 
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

entity registro is
	 Generic (
		N : integer
	 );
    Port ( clk : in  STD_LOGIC;
           clr : in  STD_LOGIC;
           Q : inout  STD_LOGIC_VECTOR (N+15 downto 0);
           D : in  STD_LOGIC_VECTOR (N+15 downto 0);
           SHE : in  STD_LOGIC;
           Ln : in  STD_LOGIC;
           Lu : in  STD_LOGIC;
           Ld : in  STD_LOGIC;
           Lc : in  STD_LOGIC;
			  Lm : in STD_LOGIC);
end registro;

architecture Behavioral of registro is

begin
	process(clk, clr)
	begin
		if clr = '1' then
			Q <= (others => '0');
		elsif rising_edge(clk) then
			if SHE = '1' then
				Q <= to_stdlogicvector(to_bitvector(Q) sll 1);
			else
				if Ln = '1' then
					Q(N-1 downto 0) <= D(N-1 downto 0);
				else
					Q(N-1 downto 0) <= Q(N-1 downto 0);
				end if;
				if Lu = '1' then
					Q(N+3 downto N) <= D(N+3 downto N);
				else
					Q(N+3 downto N) <= Q(N+3 downto N);
				end if;
				if Ld = '1' then
					Q(N+7 downto N+4) <= D(N+7 downto N+4);
				else
					Q(N+7 downto N+4) <= Q(N+7 downto N+4);
				end if;
				if Lc = '1' then
					Q(N+11 downto N+8) <= D(N+11 downto N+8);
				else
					Q(N+11 downto N+8) <= Q(N+11 downto N+8);
				end if;
				if Lm = '1' then
					Q(N+15 downto N+12) <= D(N+15 downto N+12);
				else
					Q(N+15 downto N+12) <= Q(N+15 downto N+12);
				end if;
			end if;
		end if;
	end process;

end Behavioral;

