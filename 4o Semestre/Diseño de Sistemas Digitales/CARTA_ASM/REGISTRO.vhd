----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    01:52:44 06/01/2018 
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

entity REGISTRO is
    Port ( CLK : in  STD_LOGIC;
           CLR : in  STD_LOGIC;
           D : in  STD_LOGIC_VECTOR (7 downto 0);
           A : inout  STD_LOGIC_VECTOR (7 downto 0);
           LA : in  STD_LOGIC;
           EA : in  STD_LOGIC);
end REGISTRO;

ARCHITECTURE Behavioral OF REGISTRO IS 

	BEGIN

	REG : PROCESS(CLK, CLR)
		BEGIN
			IF ( CLR = '1' ) THEN
				A <= (OTHERS => '0');
			ELSIF RISING_EDGE(CLK) THEN
				IF ( LA = '1' ) THEN
					A <= D;
				ELSIF ( EA = '1' ) THEN
					A <= TO_STDLOGICVECTOR(TO_BITVECTOR(A) SRL 1);
				ELSE
					A <= A;
				END IF;
			END IF;
	END PROCESS REG;

END Behavioral;