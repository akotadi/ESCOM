----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    02:43:30 06/01/2018 
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
use WORK.MI_PAQUETE.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity PRINCIPAL is
    Port ( CLK : in  STD_LOGIC;
           CLR : in  STD_LOGIC;
           INI : in  STD_LOGIC;
           n : in  STD_LOGIC_VECTOR (7 downto 0);
		   Qout : inout std_logic_vector(7 downto 0);
		   AN : inout STD_LOGIC_VECTOR (3 downto 0);
           DISPLAY : out  STD_LOGIC_VECTOR (6 downto 0));
end PRINCIPAL;

architecture Behavioral of PRINCIPAL is

	SIGNAL SHE, Ln, LU, LD, LC, EM, SU, SD, SC, CU, CD, CC, Z, LB, EB : STD_LOGIC;
	SIGNAL CODIGO : STD_LOGIC_VECTOR (6 DOWNTO 0);
	SIGNAL ChargeU, ChargeD, ChargeC, SumaU, SumaD, SumaC, QB, SEL : STD_LOGIC_VECTOR (3 DOWNTO 0);
	SIGNAL Q : STD_LOGIC_VECTOR (19 DOWNTO 0);

	BEGIN

	REG : REGISTRO PORT MAP ( 
		CLK => CLK, 
		CLR => CLR, 
		Ln => Ln, 
		LU => LU, 
		LD => LD, 
		LC => LC, 
		SHE => SHE, 
		D(7 downto 0) => n,
		D(11 downto 8) => ChargeU,
		D(15 downto 12) => ChargeD,
		D(19 downto 16) => ChargeC,
		Q =>  Q
	);

	CONT : CONTADOR PORT MAP ( 
		D => "1000", 
		Q => QB, 
		CLK => CLK, 
		CLR => CLR, 
		LB => LB, 
		EB => EB 
	);

	SumU : SUMADOR PORT MAP (
		RESULT => SumaU,
		N1 => Q(11 downto 8),
		N2 => "0011"
	);

	SumD : SUMADOR PORT MAP (
		RESULT => SumaD,
		N1 => Q(15 downto 12),
		N2 =>  "0011"
	);

	SumC : SUMADOR PORT MAP (
		RESULT => SumaC,
		N1 => Q(19 downto 16),
		N2 =>  "0011"
	);

	CompU : COMPARADOR PORT MAP (
		RESULT => CU,
		N1 => Q(11 downto 8),
		N2 => "0100"
	);

	CompD : COMPARADOR PORT MAP (
		RESULT => CD,
		N1 => Q(15 downto 12),
		N2 =>  "0100"
	);

	CompC : COMPARADOR PORT MAP (
		RESULT => CC,
		N1 => Q(19 downto 16),
		N2 =>  "0100"
	);

	BCD27SEG : BCD PORT MAP ( 
		B => B,
		CODIGO => CODIGO
	);
	
	CTRL : CONTROL PORT MAP ( 
		INI => INI, 
		CLK => CLK, 
		CLR => CLR, 
		Z => Z, 
		CU => CU,
		CD => CD,
		CC => CC,
		Ln => Ln,
		LU => LU,
		LD => LD,
		LC => LC,
		LB => LB,
		EB => EB,
		SU => SU,
		SD => SD,
		SC => SC,
		SHE => SHE,
		EM => EM
	);

	Qout <= Q(7 DOWNTO 0);
	
	Z <= NOT(QB(0) OR QB(1) OR QB(2) OR QB(3));

	ChargeU <=
		SumaU WHEN (SU = '1') ELSE (OTHERS => '0');

	ChargeD <=
		SumaD WHEN (SD = '1') ELSE (OTHERS => '0');

	ChargeC <=
		SumaC WHEN (SC = '1') ELSE (OTHERS => '0');
	
	DISPLAY <=
		CODIGO WHEN (EM = '1') ELSE "1111110";
		
	CONT_AN : PROCESS(CLK, CLR)
		BEGIN
			IF( CLR = '1' ) THEN
				AN <= "1110";
			ELSIF RISING_EDGE(CLK) THEN
				AN <= TO_STDLOGICVECTOR( TO_BITVECTOR( AN ) ROL 1 );
			END IF;
	END PROCESS CONT_AN;

	MUX : PROCESS(AN)
		BEGIN
			CASE AN IS
				WHEN "1110" => CODIGO <= Q(11 downto 8);
				WHEN "1101" => CODIGO <= Q(15 downto 12);
				WHEN "1011" => CODIGO <= Q(19 downto 16);
				WHEN OTHERS => CODIGO <= "0000";
			END CASE;
	END PROCESS MUX;

end Behavioral;
