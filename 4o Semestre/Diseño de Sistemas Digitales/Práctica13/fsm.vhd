----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    00:54:37 06/13/2018 
-- Design Name: 
-- Module Name:    fsm - Behavioral 
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

entity fsm is
    Port ( clk : in  STD_LOGIC;
           clr : in  STD_LOGIC;
           ini : in  STD_LOGIC;
           Z : in  STD_LOGIC;
           Cu : in  STD_LOGIC;
           Cd : in  STD_LOGIC;
           Cc : in  STD_LOGIC;
			  Cm : in STD_LOGIC;
           Li : out  STD_LOGIC;
           DOWNi : out  STD_LOGIC;
           Su : out  STD_LOGIC;
           Sd : out  STD_LOGIC;
           Sc : out  STD_LOGIC;
			  Sm : out STD_LOGIC;
           SHE : out  STD_LOGIC;
           Ln : out  STD_LOGIC;
           Lu : out  STD_LOGIC;
           Ld : out  STD_LOGIC;
           Lc : out  STD_LOGIC;
			  Lm : out STD_LOGIC;
           Em : out  STD_LOGIC);
end fsm;

architecture Behavioral of fsm is

type estado is(a, b, c, d);
signal actual, siguiente : estado;

begin
	process(actual, ini, Z, Cu, Cd, Cc, Cm)
	begin
		Li <= '0';
		DOWNi <= '0';
		Su <= '1';
		Sd <= '1';
		Sc <= '1';
		Sm <= '1';
		SHE <= '0';
		Ln <= '0';
		Lu <= '0';
		Ld <= '0';
		Lc <= '0';
		Lm <= '0';
		Em <= '0';
		case actual is
			when a =>
				Li <= '1';
				Lu <= '1';
				Ld <= '1';
				Lc <= '1';
				Lm <= '1';
				Su <= '0';
				Sd <= '0';
				Sc <= '0';
				Sm <= '0';
				if ini = '0' then
					Ln <= '1';
					siguiente <= a;
				else
					siguiente <= b;
				end if;
			when b =>
				if Z = '0' then
					if Cu = '1' then
						Lu <= '1';
					end if;
					if Cd = '1' then
						Ld <= '1';
					end if;
					if Cc = '1' then
						Lc <= '1';
					end if;
					if Cm = '1' then
						Lm <= '1';
					end if;
					if Cu = '0' and Cd = '0' and Cc = '0' and Cm = '0' then
						SHE <= '1';
						DOWNi <= '1';
						siguiente <= b;
					else
						siguiente <= c;
					end if;
				else
					siguiente <= d;
				end if;
			when c =>
				SHE <= '1';
				DOWNi <= '1';
				siguiente <= b;
			when d =>
				Em <= '1';
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

