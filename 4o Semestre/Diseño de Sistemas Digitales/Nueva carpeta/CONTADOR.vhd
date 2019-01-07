library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity CONTADOR is
    Port ( D : in  STD_LOGIC_VECTOR (3 downto 0);
           L : in  STD_LOGIC;
           E : in  STD_LOGIC;
           CLK : in  STD_LOGIC;
           CLR : in  STD_LOGIC;
           Q : INOUT  STD_LOGIC_VECTOR (3 downto 0));
end CONTADOR;

architecture PROGRAMA of CONTADOR is
begin
	PROC:process(CLK,CLR)
		begin
			if(CLR='1') then
				Q <= (others=>'0');
			elsif(rising_edge(CLK)) then
				if(L='1') then 
					Q <= D;
				elsif(E='1') then
					Q <= Q + 1;					
				end if;
			end if;
		end process;

end PROGRAMA;

