--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   21:47:26 06/14/2018
-- Design Name:   
-- Module Name:   /home/ise/xilinx/practica13/simulacion.vhd
-- Project Name:  practica13
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: BinaryToBCD
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

 
ENTITY simulacion IS
END simulacion;
 
ARCHITECTURE behavior OF simulacion IS 
component promedio is
    Port ( clk : in  STD_LOGIC;
           clr : in  STD_LOGIC;
           ini : in  STD_LOGIC;
			  AN : inout STD_LOGIC_VECTOR(3 downto 0) := "1110";
           DISPLAY : out  STD_LOGIC_VECTOR (6 downto 0));
end component;
   --Inputs
   signal clk : std_logic := '0';
   signal clr : std_logic := '0';
   signal ini : std_logic := '0';

	--BiDirs
   signal AN : std_logic_vector(3 downto 0);

 	--Outputs
   signal display : std_logic_vector(6 downto 0);

   -- Clock period definitions
   constant clk_period : time := 10 ns;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: promedio PORT MAP (
          clk => clk,
          clr => clr,
          ini => ini,
          AN => AN,
          display => display
        );

   -- Clock process definitions
   clk_process :process
   begin
		clk <= '0';
		wait for clk_period/2;
		clk <= '1';
		wait for clk_period/2;
   end process;
 

   -- Stimulus process
   stim_proc: process
   begin		
      -- hold reset state for 100 ns.
      wait for 7.5 ns;	

      ini <= '1';

      wait;
   end process;

END;
