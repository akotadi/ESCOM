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

entity convertidor is
    Port ( Q : in  STD_LOGIC_VECTOR (3 downto 0);
           S : out  STD_LOGIC_VECTOR (6 downto 0));
end convertidor;

architecture Behavioral of convertidor is
type arreglo is array(0 to 9) of std_logic_vector (6 downto 0);
constant simbolos : arreglo := ("0000001", "1001111", "0010010", "0000110", "1001100", "0100100", "0100000", "0001111", "0000000", "0000100");

begin
	S <= simbolos(conv_integer(Q)) when Q < 10 else
		  "1111111";

end Behavioral;

