----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    17:42:16 06/14/2018 
-- Design Name: 
-- Module Name:    DIVISOR - Behavioral 
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
use IEEE.STD_LOGIC_UNSIGNED.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity DIVISOR is
    Port ( OSC : in  STD_LOGIC;
           CLR : in  STD_LOGIC;
           CLK : inout  STD_LOGIC);
end DIVISOR;

architecture Behavioral of DIVISOR is

--	SIGNAL CONT : STD_LOGIC_VECTOR(24 DOWNTO 0);
	SIGNAL CONT : INTEGER RANGE 0 TO 97657; -- DATO DE 32 BITS SI NO SE DECLARA RANGO

begin

	PDIV : PROCESS(OSC, CLR, CLK)
		BEGIN
			IF (CLR = '1') THEN
			--	CONT <= (OTHERS => '0');
				CONT <= 0;
			ELSIF RISING_EDGE(OSC) THEN
				CONT <= CONT + 1;
			--	IF (CONT = TO_STDLOGICVECTOR(25000000)) THEN
			--	IF (CONT = 25000000) THEN
			--	IF (CONT = '1'&X"7D7840") THEN
				IF (CONT = 0) THEN
					CLK <= NOT CLK;
				END IF;
			END IF;
	END PROCESS PDIV;

end Behavioral;

