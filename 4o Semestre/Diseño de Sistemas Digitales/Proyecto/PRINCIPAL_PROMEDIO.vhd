----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    04:01:26 06/18/2018 
-- Design Name: 
-- Module Name:    PRINCIPAL_PROMEDIO - Behavioral 
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
library WORK;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use IEEE.NUMERIC_STD.ALL;
use WORK.MI_PAQUETE.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity PRINCIPAL_PROMEDIO is
    Port ( OSC : in  STD_LOGIC;
--			  CLK : in STD_LOGIC;
           CLR : in  STD_LOGIC;
           INI : in  STD_LOGIC;
           AN : inout  STD_LOGIC_VECTOR (3 downto 0);
           DISPLAY : out  STD_LOGIC_VECTOR (6 downto 0));
end PRINCIPAL_PROMEDIO;

architecture Behavioral of PRINCIPAL_PROMEDIO is

	SIGNAL CLK : STD_LOGIC := '0';
	SIGNAL SHE, Ln, EM, Z, LB, EB : STD_LOGIC;
	SIGNAL QB : STD_LOGIC_VECTOR (3 DOWNTO 0);
	SIGNAL Q, D : STD_LOGIC_VECTOR (14 DOWNTO 0);

	TYPE ARREGLO IS ARRAY(0 TO 7) OF INTEGER;
--	CONSTANT Numeros : ARREGLO := (6200, 4555, 829, 566, 1830, 2650, 7790, 1210); --3203
--	CONSTANT Numeros : ARREGLO := (7293, 649, 3851, 2795, 3001, 1439, 9301, 1001); --3666
--	CONSTANT Numeros : ARREGLO := (109, 597, 601, 5000, 9865, 2360, 4302, 3333); --3270
	CONSTANT Numeros : ARREGLO := (7431, 3610, 4685, 1519, 2018, 6375, 305, 5868); --3976
	
	BEGIN
	
	REDUCTOR : DIVISOR PORT MAP (
		OSC => OSC,
		CLR => CLR,
		CLK => CLK
	);
	
	CONT_PROMEDIO : CONTADOR PORT MAP ( 
		D => "1000", 
		Q => QB, 
		CLK => CLK, 
		CLR => CLR, 
		LB => LB, 
		EB => EB 
	);
	
	REG_PROMEDIO : REGISTRO_PROMEDIO PORT MAP (
		CLK => CLK,
		CLR => CLR,
		D => D,
		Q => Q,
		SHE => SHE,
		L => Ln
	);
	
	CTRL_PROMEDIO : CONTROL_PROMEDIO PORT MAP (
		CLK => CLK,
		CLR => CLR,
		INI => INI,
		LB => LB,
		EB => EB,
		Ln => Ln,
		EM => EM,
		SHE => SHE,
		Z => Z
	);
	
	BINARY_TO_BCD : PRINCIPAL PORT MAP (
		CLK => CLK,
      CLR => CLR,
      INI => EM,
      n => Q,
		AN => AN,
      DISPLAY => DISPLAY
	);
	
	D <= 
		( STD_LOGIC_VECTOR(TO_UNSIGNED(Numeros(CONV_INTEGER(QB)-1), 15)) + Q ) WHEN (QB > 0) ELSE
		(OTHERS => '0');
	
	Z <= NOT(QB(0) OR QB(1) OR QB(2) OR QB(3));

end Behavioral;

