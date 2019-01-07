--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   18:56:15 06/20/2018
-- Design Name:   
-- Module Name:   /home/ise/PROYECTOv4/PROMEDIOv2TB.vhd
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
 
ENTITY PROMEDIOv2TB IS
END PROMEDIOv2TB;
 
ARCHITECTURE behavior OF PROMEDIOv2TB IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT PRINCIPAL_PROMEDIO
    PORT(
         CLK : IN  std_logic;
         CLR : IN  std_logic;
         INI : IN  std_logic;
         AN : INOUT  std_logic_vector(3 downto 0);
         Q : INOUT  std_logic_vector(14 downto 0);
         D : INOUT  std_logic_vector(14 downto 0);
         QB : INOUT  std_logic_vector(3 downto 0);
         SHE : INOUT  std_logic;
         Ln : INOUT  std_logic;
         EM : INOUT  std_logic;
         Z : INOUT  std_logic;
         LB : INOUT  std_logic;
         EB : INOUT  std_logic;
         DISPLAY : OUT  std_logic_vector(6 downto 0)
        );
    END COMPONENT;
    

   --Inputs
   signal CLK : std_logic := '0';
   signal CLR : std_logic := '0';
   signal INI : std_logic := '0';

	--BiDirs
   signal AN : std_logic_vector(3 downto 0);
   signal Q : std_logic_vector(14 downto 0);
   signal D : std_logic_vector(14 downto 0);
   signal QB : std_logic_vector(3 downto 0);
   signal SHE : std_logic;
   signal Ln : std_logic;
   signal EM : std_logic;
   signal Z : std_logic;
   signal LB : std_logic;
   signal EB : std_logic;

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
          Q => Q,
          D => D,
          QB => QB,
          SHE => SHE,
          Ln => Ln,
          EM => EM,
          Z => Z,
          LB => LB,
          EB => EB,
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
