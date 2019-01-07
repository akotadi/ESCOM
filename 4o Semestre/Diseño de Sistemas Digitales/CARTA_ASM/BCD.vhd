----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    02:15:13 06/01/2018 
-- Design Name: 
-- Module Name:    convertidor - Behavioral 
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
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity BCD is
    Port ( B : in  STD_LOGIC_VECTOR (3 downto 0);
           CODIGO : out  STD_LOGIC_VECTOR (6 downto 0));
end BCD;

architecture Behavioral of BCD is
	
	CONSTANT Symbol0 : STD_LOGIC_VECTOR(6 DOWNTO 0) := "0000001";--01
	CONSTANT Symbol1 : STD_LOGIC_VECTOR(6 DOWNTO 0) := "1001111";--4F
	CONSTANT Symbol2 : STD_LOGIC_VECTOR(6 DOWNTO 0) := "0010010";--12
	CONSTANT Symbol3 : STD_LOGIC_VECTOR(6 DOWNTO 0) := "0000110";--06
	CONSTANT Symbol4 : STD_LOGIC_VECTOR(6 DOWNTO 0) := "1001100";--4C
	CONSTANT Symbol5 : STD_LOGIC_VECTOR(6 DOWNTO 0) := "0100100";--24
	CONSTANT Symbol6 : STD_LOGIC_VECTOR(6 DOWNTO 0) := "0100000";--20
	CONSTANT Symbol7 : STD_LOGIC_VECTOR(6 DOWNTO 0) := "0001111";--0F
	CONSTANT Symbol8 : STD_LOGIC_VECTOR(6 DOWNTO 0) := "0000000";--00
	CONSTANT SymbolX : STD_LOGIC_VECTOR(6 DOWNTO 0) := "1111110";--7E

	BEGIN

	CONV_COD : PROCESS(B)
		BEGIN
			CASE B IS
				WHEN "0000" => CODIGO <= Symbol0;
				WHEN "0001" => CODIGO <= Symbol1;
				WHEN "0010" => CODIGO <= Symbol2;
				WHEN "0011" => CODIGO <= Symbol3;
				WHEN "0100" => CODIGO <= Symbol4;
				WHEN "0101" => CODIGO <= Symbol5;
				WHEN "0110" => CODIGO <= Symbol6;
				WHEN "0111" => CODIGO <= Symbol7;
				WHEN "1000" => CODIGO <= Symbol8;
				WHEN OTHERS => CODIGO <= Symbol0;
			END CASE;
	END PROCESS CONV_COD;

end Behavioral;