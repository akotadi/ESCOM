--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   20:42:49 06/20/2018
-- Design Name:   
-- Module Name:   /home/ise/PROYECTOv4/PROMEDIOv3.vhd
-- Project Name:  PROYECTOv4
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: PRINCIPAL_PROMEDIO
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
 
ENTITY PROMEDIOv3 IS
END PROMEDIOv3;
 
ARCHITECTURE behavior OF PROMEDIOv3 IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT PRINCIPAL_PROMEDIO
    PORT(
         CLK : IN  std_logic;
         CLR : IN  std_logic;
         INI : IN  std_logic;
         AN : INOUT  std_logic_vector(3 downto 0);
         DISPLAY : OUT  std_logic_vector(6 downto 0)
        );
    END COMPONENT;
    

   --Inputs
   signal CLK : std_logic := '0';
   signal CLR : std_logic := '0';
   signal INI : std_logic := '0';

	--BiDirs
   signal AN : std_logic_vector(3 downto 0);

 	--Outputs
   signal DISPLAY : std_logic_vector(6 downto 0);

   -- Clock period definitions
   constant CLK_period : time := 10 ns;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: PRINCIPAL_PROMEDIO PORT MAP (
          CLK => CLK,
          CLR => CLR,
          INI => INI,
          AN => AN,
          DISPLAY => DISPLAY
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
      CLR <= '1';
      wait for 100 ns;	
		CLR <= '0';
		INI <= '0';
		wait for CLK_period;
		INI <= '1';
      wait for CLK_period*100;

      -- insert stimulus here 

      wait;
   end process;

END;
