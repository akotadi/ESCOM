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
           D : in  STD_LOGIC_VECTOR (19 downto 0);
           Q : inout  STD_LOGIC_VECTOR (19 downto 0);
           SHE : in STD_LOGIC;
           Ln : in STD_LOGIC;
           LU : in  STD_LOGIC;
           LD : in STD_LOGIC;
           LC : in STD_LOGIC);
end REGISTRO;

ARCHITECTURE Behavioral OF REGISTRO IS 

	BEGIN

	REG : PROCESS(CLK, CLR)
		BEGIN
			IF ( CLR = '1' ) THEN
				Q <= (OTHERS => '0');
			ELSIF RISING_EDGE(CLK) THEN
				IF ( Ln = '1' OR LU = '1' OR LD = '1' OR LC = '1' ) THEN
					IF Ln = '1' THEN
						Q(7 DOWNTO 0) <= D(7 DOWNTO 0);
					ELSE
						Q(7 DOWNTO 0) <= Q(7 DOWNTO 0);
					END IF;
					IF LU = '1' THEN
						Q(11 DOWNTO 8) <= D(11 DOWNTO 8);
					ELSE
						Q(11 DOWNTO 8) <= Q(11 DOWNTO 8);
					END IF;
					IF LD = '1' THEN
						Q(15 DOWNTO 12) <= D(15 DOWNTO 12);
					ELSE
						Q(15 DOWNTO 12) <= Q(15 DOWNTO 12);
					END IF;
					IF LC = '1' THEN
						Q(19 DOWNTO 16) <= D(19 DOWNTO 16);
					ELSE
						Q(19 DOWNTO 16) <= Q(19 DOWNTO 16);
					END IF;
				ELSIF ( SHE = '1' ) THEN
					Q <= TO_STDLOGICVECTOR(TO_BITVECTOR(Q) SLL 1);
				ELSE
					Q <= Q;
				END IF;
			END IF;
	END PROCESS REG;

END Behavioral;