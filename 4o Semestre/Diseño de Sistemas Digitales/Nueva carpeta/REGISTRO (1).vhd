library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity REGISTRO is
    Port ( D : in  STD_LOGIC_VECTOR (7 downto 0);
           L : in  STD_LOGIC;
           E : in  STD_LOGIC;
           CLK : in  STD_LOGIC;
           CLR : in  STD_LOGIC;
           Q : INOUT  STD_LOGIC_VECTOR (7 downto 0));
end REGISTRO;

architecture PROGRAMA of REGISTRO is

begin
	PROC:process(CLK,CLR)
		begin
			if(CLR='1') then
				Q<=(others=>'0');
			elsif(rising_edge(CLK)) then
				if(L='1') then 
					Q <= D;
				elsif(E='1') then
					Q <= TO_STDLOGICVECTOR(TO_BITVECTOR(Q) srl 1);					
				end if;
			end if;
		end process;

end PROGRAMA;

