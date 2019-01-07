----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    00:42:37 06/13/2018 
-- Design Name: 
-- Module Name:    main - Behavioral 
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
use IEEE.NUMERIC_STD.ALL;
use WORK.paquete.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity BinaryToBCD is
	 Generic (
			N : integer
	 );
    Port ( clk : in  STD_LOGIC;
           clr : in  STD_LOGIC;
           ini : in  STD_LOGIC;
           Number : in  STD_LOGIC_VECTOR (N-1 downto 0);
			  Ready : out STD_LOGIC;
			  SalidaU, SalidaD, SalidaC, SalidaM : out STD_LOGIC_VECTOR(6 downto 0));
end BinaryToBCD;

architecture Behavioral of BinaryToBCD is

signal BCD : STD_LOGIC_VECTOR(N+15 downto 0);

signal SHE, Ln, Lu, Ld, Lc, Lm, Em : STD_LOGIC;
signal Su, Sd, Sc, Sm : STD_LOGIC;
signal Z : STD_LOGIC;

signal Cu, Cd, Cc, Cm : STD_LOGIC;

signal sumaU, sumaD, sumaC, sumaM : STD_LOGIC_VECTOR(3 downto 0);
signal U, D, C, M : STD_LOGIC_VECTOR(3 downto 0);

signal DOWNi, Li : STD_LOGIC;
signal Qi : STD_LOGIC_VECTOR(3 downto 0);

signal DISP : STD_LOGIC_VECTOR (6 downto 0);

begin
	reg : registro generic map(
		N => N
	) port map(
		clk => clk,
		clr => clr,
		Q => BCD,
		D(N-1 downto 0) => Number,
		D(N+3 downto N) => U,
		D(N+7 downto N+4) => D,
		D(N+11 downto N+8) => C,
		D(N+15 downto N+12) => M,
		SHE => SHE,
		Ln => Ln,
		Lu => Lu,
		Ld => Ld,
		Lc => Lc,
		Lm => Lm
	);
	
	compU : comparador port map(
		A => BCD(N+3 downto N),
		B => "0100",
		mayor => Cu
	);
	
	compD : comparador port map(
		A => BCD(N+7 downto N+4),
		B => "0100",
		mayor => Cd
	);
	
	compC : comparador port map(
		A => BCD(N+11 downto N+8),
		B => "0100",
		mayor => Cc
	);
	
	compM : comparador port map(
		A => BCD(N+15 downto N+12),
		B => "0100",
		mayor => Cm
	);
	
	sumU : sumador generic map(
		N => 4
	)
	port map(
		A => BCD(N+3 downto N),
		B => "0011",
		suma => sumaU
	);
	
	sumD : sumador generic map(
		N => 4
	)
	port map(
		A => BCD(N+7 downto N+4),
		B => "0011",
		suma => sumaD
	);
	
	sumC : sumador generic map(
		N => 4
	)
	port map(
		A => BCD(N+11 downto N+8),
		B => "0011",
		suma => sumaC
	);
	
	sumM : sumador generic map(
		N => 4
	)
	port map(
		A => BCD(N+15 downto N+12),
		B => "0011",
		suma => sumaM
	);
	
	cont : contador generic map(
		N => 4
	)
	port map(
		clk => clk,
		clr => clr,
		DOWN => DOWNi,
		L => Li,
		Q => Qi,
		D => STD_LOGIC_VECTOR(TO_UNSIGNED(N, 4))
	);
	
	control : fsm port map(
		clk => clk,
		clr => clr,
		ini => ini,
		Z => Z,
		Cu => Cu,
		Cd => Cd,
		Cc => Cc,
		Cm => Cm,
		Li => Li,
		DOWNi => DOWNi,
		Su => Su,
		Sd => Sd,
		Sc => Sc,
		Sm => Sm,
		SHE => SHE,
		Ln => Ln,
		Lu => Lu,
		Ld => Ld,
		Lc => Lc,
		Lm => Lm,
		Em => Em
	);
	
	convU : convertidor port map(
		Q => BCD(N+3 downto N),
		S => SalidaU
	);
	
	convD : convertidor port map(
		Q => BCD(N+7 downto N+4),
		S => SalidaD
	);
	
	convC : convertidor port map(
		Q => BCD(N+11 downto N+8),
		S => SalidaC
	);
	
	convM : convertidor port map(
		Q => BCD(N+15 downto N+12),
		S => SalidaM
	);
	
	Z <= not(Qi(0) or Qi(1) or Qi(2) or Qi(3));
	
	U <= (others => '0') when Su = '0' else
			sumaU;
	D <= (others => '0') when Sd = '0' else
			sumaD;
	C <= (others => '0') when Sc = '0' else
			sumaC;
	M <= (others => '0') when Sm = '0' else
			sumaM;
			
	Ready <= Em;

end Behavioral;

