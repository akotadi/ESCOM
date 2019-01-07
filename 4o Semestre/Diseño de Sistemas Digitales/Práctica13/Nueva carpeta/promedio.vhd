----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    17:44:45 06/14/2018 
-- Design Name: 
-- Module Name:    promedio - Behavioral 
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
use WORK.paquete.ALL;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity promedio is
	 Generic (
		bits : integer := 15
	 );
    Port ( clk : in  STD_LOGIC;
           clr : in  STD_LOGIC;
           ini : in  STD_LOGIC;
			  AN : inout STD_LOGIC_VECTOR(3 downto 0) := "1110";
           DISPLAY : out  STD_LOGIC_VECTOR (6 downto 0));
end promedio;

architecture Behavioral of promedio is

signal freq : STD_LOGIC_VECTOR(13 downto 0);
signal reloj : STD_LOGIC;

signal DOWNi, LOADi : STD_LOGIC;
signal Qi : STD_LOGIC_VECTOR(3 downto 0);

signal ResetSum, LoadSum, Divide : STD_LOGIC;
signal ResultSum, QSum, DSum : STD_LOGIC_VECTOR(bits-1 downto 0);

signal Ready1, Ready2, Z : STD_LOGIC;

signal Dato : STD_LOGIC_VECTOR(bits-1 downto 0);

signal DISP, U, D, C, M : STD_LOGIC_VECTOR(6 downto 0);

type arreglo is array(0 to 7) of integer;
--constant Data : arreglo := (6200, 4555, 829, 566, 1830, 2650, 7790, 1210); --3203
constant Data : arreglo := (7431, 3610, 4685, 1519, 2018, 6375, 305, 5868); --3976

begin
	reductor : contador generic map(
		N => 14
	)
	port map(
		clk => clk,
		clr => clr,
		DOWN => '1',
		L => '0',
		Q => freq,
		D => (others => '0')
	);
	
	reloj <= freq(13);
	
	cont : contador generic map(
		N => 4
	)
	port map(
		clk => reloj,
		clr => clr,
		DOWN => DOWNi,
		L => LOADi,
		Q => Qi,
		D => "1000"
	);
	
	reg : registro_promedio generic map(
		N => bits
	)
	port map(
		clk => reloj,
		clr => clr,
		L => LoadSum,
		SHE => Divide,
		Q => QSum,
		D => Dsum
	);
	
	Dato <= STD_LOGIC_VECTOR(TO_UNSIGNED(Data(CONV_INTEGER(Qi)-1), bits)) when Qi > 0 else
			  (others => '0');
	
	sum : sumador generic map(
		N => bits
	) port map (
		A => QSum,
		B => Dato,
		suma => ResultSum
	);
	
	control : fsm2 port map(
		clk => reloj,
		clr => clr,
		ini => ini,
		Z => Z,
		LOADi => LOADi,
		DOWNi => DOWNi,
		ResetSum => ResetSum,
		LoadSum => LoadSum,
		Ready => Ready1,
		Divide => Divide
	);
	
	B2BCD : BinaryToBCD generic map(
		N => bits
	) port map(
		clk => reloj,
		clr => clr,
		ini => Ready1,
		Number => QSum,
		Ready => Ready2,
		SalidaU => U,
		SalidaD => D,
		SalidaC => C,
		SalidaM => M
	);
	
	DSum <= ResultSum when ResetSum = '0' else
			  (others => '0');
			  
	Z <= not(Qi(0) or Qi(1) or Qi(2) or Qi(3));
	
	process(reloj, clr)
	begin
		if clr = '1' then
			AN <= "1110";
		elsif rising_edge(reloj) then
			AN <= to_stdlogicvector(to_bitvector(AN) rol 1);
		end if;
	end process;
	
	DISP <= U when AN = "1110" else
		     D when AN = "1101" else
		     C when AN = "1011" else
		     M when AN = "0111" else
		     "1111111";
	
	DISPLAY <= DISP when Ready1 = '1' and Ready2 = '1' else
				  "1111110";

end Behavioral;

