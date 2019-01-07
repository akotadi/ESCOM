----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    18:06:24 06/18/2018 
-- Design Name: 
-- Module Name:    registro_promedio - Behavioral 
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

entity REGISTRO_PROMEDIO is
    Port ( CLK : in  STD_LOGIC;
           CLR : in  STD_LOGIC;
           D : in  STD_LOGIC_VECTOR (14 downto 0);
           Q : inout  STD_LOGIC_VECTOR (14 downto 0);
           SHE : in STD_LOGIC;
           L : in STD_LOGIC);
end REGISTRO_PROMEDIO;

ARCHITECTURE Behavioral OF REGISTRO_PROMEDIO IS 

	BEGIN

	REG : PROCESS(CLK, CLR)
		BEGIN
			IF ( CLR = '1' ) THEN
				Q <= (OTHERS => '0');
			ELSIF RISING_EDGE(CLK) THEN
				IF ( L = '1' ) THEN
					Q <= D;
				ELSE
					IF ( SHE = '1' ) THEN
						Q <= TO_STDLOGICVECTOR(TO_BITVECTOR(Q) SRL 3);
					ELSE
						Q <= Q;
					END IF;
				END IF;
			END IF;
	END PROCESS REG;

END Behavioral;

