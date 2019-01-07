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
           D : in  STD_LOGIC_VECTOR (30 downto 0);
           Q : inout  STD_LOGIC_VECTOR (30 downto 0);
           SHE : in STD_LOGIC;
           Ln : in STD_LOGIC;
           LU : in  STD_LOGIC;
           LD : in STD_LOGIC;
           LC : in STD_LOGIC;
           LM : in STD_LOGIC);
end REGISTRO;

ARCHITECTURE Behavioral OF REGISTRO IS 

	BEGIN

	REG : PROCESS(CLK, CLR)
		BEGIN
			IF ( CLR = '1' ) THEN
				Q <= (OTHERS => '0');
			ELSIF RISING_EDGE(CLK) THEN
				IF ( SHE = '1' ) THEN
					Q <= TO_STDLOGICVECTOR(TO_BITVECTOR(Q) SLL 1);
				ELSE
					IF ( Ln = '1' ) THEN
						Q(14 DOWNTO 0) <= D(14 DOWNTO 0);
					ELSE
						Q(14 DOWNTO 0) <= Q(14 DOWNTO 0);
					END IF;
					IF ( LU = '1')  THEN
						Q(18 DOWNTO 15) <= D(18 DOWNTO 15);
					ELSE
						Q(18 DOWNTO 15) <= Q(18 DOWNTO 15);
					END IF;
					IF ( LD = '1' ) THEN
						Q(22 DOWNTO 19) <= D(22 DOWNTO 19);
					ELSE
						Q(22 DOWNTO 19) <= Q(22 DOWNTO 19);
					END IF;
					IF ( LC = '1' ) THEN
						Q(26 DOWNTO 23) <= D(26 DOWNTO 23);
					ELSE
						Q(26 DOWNTO 23) <= Q(26 DOWNTO 23);
					END IF;
					IF ( LM = '1' ) THEN
						Q(30 DOWNTO 27) <= D(30 DOWNTO 27);
					ELSE
						Q(30 DOWNTO 27) <= Q(30 DOWNTO 27);
					END IF;
				END IF;
			END IF;
	END PROCESS REG;

END Behavioral;