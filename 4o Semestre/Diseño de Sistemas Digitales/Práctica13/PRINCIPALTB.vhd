--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   18:32:58 06/14/2018
-- Design Name:   
-- Module Name:   /home/ise/PROYECTOv2/PRINCIPALTB.vhd
-- Project Name:  PROYECTOv2
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: PRINCIPAL
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
 
ENTITY PRINCIPALTB IS
END PRINCIPALTB;
 
ARCHITECTURE behavior OF PRINCIPALTB IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT PRINCIPAL
    PORT(
         CLK : IN  std_logic;
         CLR : IN  std_logic;
         INI : IN  std_logic;
         n : IN  std_logic_vector(7 downto 0);
         Qout : INOUT  std_logic_vector(7 downto 0);
         AN : INOUT  std_logic_vector(3 downto 0);
         DISPLAY : OUT  std_logic_vector(6 downto 0)
        );
    END COMPONENT;
    

   --Inputs
   signal CLK : std_logic := '0';
   signal CLR : std_logic := '0';
   signal INI : std_logic := '0';
   signal n : std_logic_vector(7 downto 0) := (others => '0');

	--BiDirs
   signal Qout : std_logic_vector(7 downto 0);
   signal AN : std_logic_vector(3 downto 0);

 	--Outputs
   signal DISPLAY : std_logic_vector(6 downto 0);
   -- No clocks detected in port list. Replace <clock> below with 
   -- appropriate port name 
 
   constant <clock>_period : time := 10 ns;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: PRINCIPAL PORT MAP (
          CLK => CLK,
          CLR => CLR,
          INI => INI,
          n => n,
          Qout => Qout,
          AN => AN,
          DISPLAY => DISPLAY
        );

   -- Clock process definitions
   clock_process :process
   begin
		clock <= '0';
		wait for clock_period/2;
		clock <= '1';
		wait for clock_period/2;
   end process;
 

   -- Stimulus process
   stim_proc: process
   begin		
      -- hold reset state for 100 ns.
      CLR <= '1';
      wait for 100 ns;
      CLR <= '0';
      n <= "10110101";
      INI <= '0';
      wait for clock_period;
      INI <= '1';
      wait for clock_period*100;
      wait;

      -- insert stimulus here 

      wait;
   end process;

END;
