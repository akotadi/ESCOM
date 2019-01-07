library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
USE WORK.PAQUETE.ALL;

entity CARTA is
    Port ( CLK : in  STD_LOGIC;
           CLR : in  STD_LOGIC;
           INI : in  STD_LOGIC;
           D : in  STD_LOGIC_VECTOR (7 downto 0);
           DISPLAY : out  STD_LOGIC_VECTOR (6 downto 0));
end CARTA;

architecture PROGRAMA of CARTA is
SIGNAL LA, EA, LB, EB, EC, Z : STD_LOGIC;
SIGNAL A : STD_LOGIC_VECTOR(7 DOWNTO 0);
SIGNAL B : STD_LOGIC_VECTOR(3 DOWNTO 0);

begin


	REG : REGISTRO PORT MAP( D, LA, EA, CLK, CLR, A );
	CONT : CONTADOR PORT MAP( X"0", LB, EB, CLK, CLR, B );
	
	Z <= '1' WHEN( A = X"00" )ELSE '0';
	
	CTRL: CONTROL PORT MAP(
			LA, EA, LB, EB, EC, A(0), Z, CLK, CLR, INI
	);

end PROGRAMA;

