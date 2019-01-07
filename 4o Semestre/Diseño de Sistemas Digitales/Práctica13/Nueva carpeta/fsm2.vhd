----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    18:09:29 06/14/2018 
-- Design Name: 
-- Module Name:    fsm2 - Behavioral 
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

entity fsm2 is
    Port ( clk : in  STD_LOGIC;
           clr : in  STD_LOGIC;
           ini : in  STD_LOGIC;
			  LOADi : out STD_LOGIC;
           DOWNi : out  STD_LOGIC;
			  ResetSum : out STD_LOGIC;
           LoadSum : out  STD_LOGIC;
           Ready : out  STD_LOGIC;
           Divide : out  STD_LOGIC;
           Z : in  STD_LOGIC);
end fsm2;

architecture Behavioral of fsm2 is

type estado is(a, b, c, d);
signal actual, siguiente : estado;

begin
	process(actual, ini, Z)
	begin
		LOADi <= '0';
		DOWNi <= '0';
		ResetSum <= '0';
		LoadSum <= '0';
		Ready <= '0';
		Divide <= '0';
		case actual is
			when a=>
				LOADi <= '1';
				LoadSum <= '1';
				ResetSum <= '1';
				if ini = '1' then
					siguiente <= b;
				else
					siguiente <= a;
				end if;
			when b=>
				if Z = '1' then
					Divide <= '1';
					siguiente <= c;
				else
					DOWNi <= '1';
					LoadSum <= '1';
					siguiente <= b;
				end if;
			when c=>
				siguiente <= d;
			when d=>
				Ready <= '1';
				if ini = '0' then
					siguiente <= a;
				else
					siguiente <= d;
				end if;
		end case;
	end process;
	
	process(clk, clr)
	begin
		if clr = '1' then
			actual <= a;
		elsif rising_edge(clk) then
			actual <= siguiente;
		end if;
	end process;

end Behavioral;

