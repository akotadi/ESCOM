--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   01:16:58 06/05/2018
-- Design Name:   
-- Module Name:   /home/ise/PROYECTO/CARTA_TB.vhd
-- Project Name:  PROYECTO
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: CARTA
-- 
-- Dependencies:
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
--
-- Notes: 
-- This testbench has been automatically generated using types std_logic and
-- std_logic_vector for the ports of the unit under test.  Xilinx recommends
-- that these types always be used for the top-level I/O of a design in order
-- to guarantee that the testbench will bind correctly to the post-implementation 
-- simulation model.
--------------------------------------------------------------------------------
LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
 
-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--USE ieee.numeric_std.ALL;
 
ENTITY CARTA_TB IS
END CARTA_TB;
 
ARCHITECTURE behavior OF CARTA_TB IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT CARTA
    PORT(
         CLK : IN  std_logic;
         CLR : IN  std_logic;
         INI : IN  std_logic;
         DA : IN  std_logic_vector(7 downto 0);
         AN : OUT  std_logic_vector(3 downto 0);
         DISPLAY : OUT  std_logic_vector(6 downto 0);
         QA : INOUT  std_logic_vector(7 downto 0)
        );
    END COMPONENT;
    

   --Inputs
   signal CLK : std_logic := '0';
   signal CLR : std_logic := '0';
   signal INI : std_logic := '0';
   signal DA : std_logic_vector(7 downto 0) := (others => '0');

	--BiDirs
   signal QA : std_logic_vector(7 downto 0);

 	--Outputs
   signal AN : std_logic_vector(3 downto 0);
   signal DISPLAY : std_logic_vector(6 downto 0);

   -- Clock period definitions
   constant CLK_period : time := 10 ns;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: CARTA PORT MAP (
          CLK => CLK,
          CLR => CLR,
          INI => INI,
          DA => DA,
          AN => AN,
          DISPLAY => DISPLAY,
          QA => QA
        );

   -- Clock process definitions
   CLK_process :process
   begin
		CLK <= '0';
		wait for CLK_period/2;
		CLK <= '1';
		wait for CLK_period/2;
   end process;
 

   -- Stimulus process
   stim_proc: process
   begin		
      -- hold reset state for 100 ns.
      wait for CLK_period;
		CLR <= '1';
		wait for CLK_period*3;
		CLR <= '0';
		INI <= '0';
		DA <= "11010100";
      wait for CLK_period*2;
		INI <= '1';
      wait for CLK_period*20;
		wait;

      -- insert stimulus here 

      wait;
   end process;

END;
