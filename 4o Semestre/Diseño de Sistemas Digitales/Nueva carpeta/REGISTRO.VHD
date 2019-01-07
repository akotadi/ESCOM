library ieee;
use ieee.std_logic_1164.all;

entity registro is
	port(
		D: in std_logic_vector(3 downto 0);
		clk, clr, Ds: in std_logic;
		op:in std_logic_vector(1 downto 0);
		Q: inout std_logic_vector(3 downto 0)
	);
end registro;

architecture ecuaciones of registro is
begin
	A: process(clk,clr)
		begin
			if(clr='1')then
				Q <= "0000";
			elsif(clk'event and clk='1')then
				Q(0)<= (not op(1) and not op(0) and q(0)) or
					   (not op(1) and op(0)     and d(0)) or
					   (op(1)     and not op(0) and ds)   or
					   (op(1)     and op(0)     and q(1));

				Q(1)<= (not op(1) and not op(0) and q(1)) or
					   (not op(1) and op(0)     and d(1)) or
					   (op(1)     and not op(0) and Q(0)) or
					   (op(1)     and op(0)     and q(2));

   				Q(2)<= (not op(1) and not op(0) and q(2)) or
					   (not op(1) and op(0)     and d(2)) or
					   (op(1)     and not op(0) and Q(1)) or
					   (op(1)     and op(0)     and q(3));

    			Q(3)<= (not op(1) and not op(0) and q(3)) or
					   (not op(1) and op(0)     and d(3)) or
					   (op(1)     and not op(0) and Q(2)) or
					   (op(1)     and op(0)     and DS);

			end if;
		end process A;
	
end ecuaciones;


