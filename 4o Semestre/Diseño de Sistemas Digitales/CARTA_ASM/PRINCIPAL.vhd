----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    02:43:30 06/01/2018 
-- Design Name: 
-- Module Name:    main - Behavioral 
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
library WORK;
use IEEE.STD_LOGIC_1164.ALL;
use WORK.MI_PAQUETE.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity PRINCIPAL is
    Port ( CLK : in  STD_LOGIC;
           CLR : in  STD_LOGIC;
           INI : in  STD_LOGIC;
           DA : in  STD_LOGIC_VECTOR (7 downto 0);
			  QA : inout std_logic_vector(7 downto 0);
			  AN : inout STD_LOGIC_VECTOR (3 downto 0);
           DISPLAY : out  STD_LOGIC_VECTOR (6 downto 0));
end PRINCIPAL;

architecture Behavioral of PRINCIPAL is

	SIGNAL B : STD_LOGIC_VECTOR (3 DOWNTO 0);
	SIGNAL CODIGO : STD_LOGIC_VECTOR (6 DOWNTO 0);
	SIGNAL LB, EB, Z, LA, EA, EC : STD_LOGIC;

	BEGIN

	CONT : CONTADOR PORT MAP ( 
		DB => (OTHERS => '0'), 
		B => B, 
		CLK => CLK, 
		CLR => CLR, 
		LB => LB, 
		EB => EB 
	);
	
	CTRL : CONTROL PORT MAP ( 
		INI => INI, 
		CLK => CLK, 
		CLR => CLR, 
		Z => Z, 
		A0 => QA(0), 
		LB => LB, 
		EB => EB, 
		LA => LA, 
		EA => EA, 
		EC => EC 
	);
	
	BCD27SEG : BCD PORT MAP ( 
		B => B,
		CODIGO => CODIGO
	);
	
	REG : REGISTRO PORT MAP ( 
		CLK => CLK, 
		CLR => CLR, 
		LA => LA, 
		EA => EA, 
		D => DA, 
		A => QA 
	);
	
	Z <= NOT(QA(0) OR QA(1) OR QA(2) OR QA(3) OR QA(4) OR QA(5) OR QA(6) OR QA(7));
	
	DISPLAY <=
		CODIGO WHEN ( EC = '1' ) ELSE "1111110";
		
	CONT_AN : PROCESS(CLK, CLR)
		BEGIN
			IF( CLR = '1' ) THEN
				AN <= "1110";
			ELSIF RISING_EDGE(CLK) THEN
				AN <= TO_STDLOGICVECTOR( TO_BITVECTOR( AN ) ROL 1 );
			END IF;
	END PROCESS CONT_AN;

	

end Behavioral;
