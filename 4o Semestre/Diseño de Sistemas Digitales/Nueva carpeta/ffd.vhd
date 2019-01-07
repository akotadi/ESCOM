library ieee;
use ieee.std_logic_1164.all;

entity FFD is
port(
	d, clk, clr, J, K :in std_logic;
	SEL : IN STD_LOGIC;
	DISPLAY : out std_logic_VECTOR(6 DOWNTO 0)
	);
end FFD;

ARCHITECTURE PROGRAMA OF FFD IS
SIGNAL QD, QJK, Q : STD_LOGIC; 
BEGIN
	PFFD : PROCESS( CLK, CLR )
	BEGIN
		IF( CLR = '1' )THEN
			QD <= '0';
		ELSIF( CLK'EVENT AND CLK = '1' )THEN
			QD <= D;
		END IF;
	END PROCESS PFFD;

	PFFJK : PROCESS( CLK, CLR )
	BEGIN
		IF( CLR = '1' ) THEN
			QJK <= '0';
		ELSIF( CLK'EVENT AND CLK = '1' )THEN
			QJK <= (NOT K AND QJK) OR (J AND NOT QJK);
		END IF;
	END PROCESS PFFJK;

	WITH SEL SELECT 
		Q <= QD WHEN '0',
			 QJK WHEN OTHERS;

	DISPLAY <= 	"0000001" WHEN( Q = '0' )ELSE 
				"1001111";

END PROGRAMA;
