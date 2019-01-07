library ieee;
use ieee.std_logic_1164.all;

entity variables is
	Port(
		a,b:in std_logic_vector(3 downto 0);
		salida:out std_logic_vector(3 downto 0);
		cout:out std_logic
		);


end variables;

architecture sumacompleta of variables is
	signal c:std_logic_vector(2 downto 0);	
	attribute synthesis_off of c: signal is true;
	begin
		salida(0) <= a(0) xor b(0);
		c(0) <= a(0) and b(0);
		salida(1) <= (a(1) xor b(1)) xor c(0);
		c(1) <= (a(1) and b(1)) or (c(0) and (a(1) xor b(1)));
		salida(2) <= (a(2) xor b(2)) xor c(1);
		c(2) <= (a(2) and b(2)) or (c(1) and (a(2) xor b(2)));
		salida(3) <= (a(3) xor b(3)) xor c(2);
		cout <= (a(3) and b(3)) or (c(2) and (a(3) xor b(3)));
end sumacompleta;

--architecture restacompleta of variables is
--	signal c:std_logic_vector(2 downto 0);
--	attribute synthesis_off of c: signal is true;
--	begin
--		salida(0) <= a(0) xor b(0);
--		c(0) <= not a(0) and b(0);
--		salida(1) <= (a(1) xor b(1)) xor c(0); 
--		c(1) <= (not a(1) and c(0)) or (not a(1) and b(1)) or (b(1) and c(0));
--		salida(2) <= (a(2) xor b(2)) xor c(1);
--		c(2) <= (not a(2) and c(1)) or (not a(2) and b(2)) or (b(2) and c(1));
--		salida(3) <= (a(3) xor b(3)) xor c(2);
--		cout <= (not a(3) and c(2)) or (not a(3) and b(3)) or (b(3) and c(2));
--end restacompleta;



--OTRA MANERA, tambien compila
--library IEEE;
--use IEEE.STD_LOGIC_1164.ALL;
--entity variables is
--	Port(
--		a0:in std_logic;
--		a1:in std_logic;
--		a2:in std_logic;
--		a3:in std_logic;
--		b0:in std_logic;
--		b1:in std_logic;
--		b2:in std_logic;
--		b3:in std_logic;
--		cin0:in std_logic;
--		cin1:in std_logic;
--		cin2:in std_logic;
--		cin3:in std_logic;
--		salida0:out std_logic;--ya sea sumador o restador
--		salida1:out std_logic;--suma/resta
--		salida2:out std_logic;--suma/resta
--		salida3:out std_logic;--suma/resta
--		cout3:out std_logic
--		);
--end variables;

--architecture sumador of variables is
--	signal cout:std_logic_vector(2 downto 0);	
--	attribute synthesis_off of cout: signal is true;	
--	begin
--		salida0<=(a0 xor b0);-- xor cin0);
--		cout(0)<=(a0 and b0);-- or ((a0 xor b0)and cin0));
--		salida1<=(a1 xor b1 xor cout(0));
--		cout(1)<=((b1 and cout(0)) or (a1 and b1) or (a1 and cout(0)));
--		salida2<=(a2 xor b2 xor cout(1));
--		cout(2)<=((b2 and cout(1)) or (a2 and b2) or (a2 and cout(1)));		
--		salida3<=(a2 xor b3 xor cout(2));
--		cout3<=((b3 and cout(2)) or (a3 and b3) or (a3 and cout(2)));
--end sumador;
		
--architecture restador of variables is
--	signal cout:std_logic_vector(2 downto 0);	
--	attribute synthesis_off of cout: signal is true;	

--	begin
--		salida0<=(a0 xor b0 xor cin0);
--		cout(0)<=(((not a0) and cin0) or ((not a0) and b0) or (b0 and cin0));
--		salida1<=(a1 xor b1 xor cout(0));
--		cout(1)<=(((not a1) and cout(0)) or ((not a1) and b1) or (b1 and cout(0)));
--		salida2<=(a2 xor b2 xor cout(1));
--		cout(2)<=(((not a2) and cout(1)) or ((not a2) and b2) or (b2 and cout(1)));
--		salida3<=(a3 xor b3 xor cout(2));
--		cout3<=((not a3 and cout(2)) or (not a3 and b3) or (b3 and cout(2)));
--	end restador;

