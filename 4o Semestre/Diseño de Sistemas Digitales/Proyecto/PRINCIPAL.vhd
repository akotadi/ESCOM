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
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use WORK.MI_PAQUETE.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity PRINCIPAL is
    Port ( CLK : In STD_LOGIC;
           CLR : in  STD_LOGIC;
           INI : in  STD_LOGIC;
           n : in  STD_LOGIC_VECTOR (14 downto 0);
		   AN : inout STD_LOGIC_VECTOR (3 downto 0);
           DISPLAY : out  STD_LOGIC_VECTOR (6 downto 0));
end PRINCIPAL;

architecture Behavioral of PRINCIPAL is

	SIGNAL SHE, Ln, LU, LD, LC, LM, EM, SU, SD, SC, SM, CU, CD, CC, CM, Z, LB, EB : STD_LOGIC;
	SIGNAL CODIGO : STD_LOGIC_VECTOR (6 DOWNTO 0);
	SIGNAL ChargeU, ChargeD, ChargeC, ChargeM, SumaU, SumaD, SumaC, SumaM, QB, SEL : STD_LOGIC_VECTOR (3 DOWNTO 0);
	SIGNAL Q : STD_LOGIC_VECTOR (30 DOWNTO 0);

	BEGIN

	REG : REGISTRO PORT MAP ( 
		CLK => CLK, 
		CLR => CLR, 
		Ln => Ln, 
		LU => LU, 
		LD => LD, 
		LC => LC, 
		LM => LM, 
		SHE => SHE, 
		D(14 downto 0) => n,
		D(18 downto 15) => ChargeU,
		D(22 downto 19) => ChargeD,
		D(26 downto 23) => ChargeC,
		D(30 downto 27) => ChargeM,
		Q =>  Q
	);

	CONT : CONTADOR PORT MAP ( 
		D => "1111", 
		Q => QB, 
		CLK => CLK, 
		CLR => CLR, 
		LB => LB, 
		EB => EB 
	);

	SumaU <= Q(18 DOWNTO 15) + "0011";
	
	SumaD <= Q(22 DOWNTO 19) + "0011";
	
	SumaC <= Q(26 DOWNTO 23) + "0011";
	
	SumaM <= Q(30 DOWNTO 27) + "0011";

	CU <=
		'1' WHEN (Q(18 DOWNTO 15) > "0100") ELSE
		'0';
		
	CD <=
		'1' WHEN (Q(22 DOWNTO 19) > "0100") ELSE
		'0';
		
	CC <=
		'1' WHEN (Q(26 DOWNTO 23) > "0100") ELSE
		'0';
		
	CM <=
		'1' WHEN (Q(30 DOWNTO 27) > "0100") ELSE
		'0';

	BCD27SEG : BCD PORT MAP ( 
		B => SEL,
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
		CM => CM,
		Ln => Ln,
		LU => LU,
		LD => LD,
		LC => LC,
		LM => LM,
		LB => LB,
		EB => EB,
		SU => SU,
		SD => SD,
		SC => SC,
		SM => SM,
		SHE => SHE,
		EM => EM
	);
	
	Z <= NOT(QB(0) OR QB(1) OR QB(2) OR QB(3));

	ChargeU <=
		SumaU WHEN (SU = '1') ELSE (OTHERS => '0');

	ChargeD <=
		SumaD WHEN (SD = '1') ELSE (OTHERS => '0');

	ChargeC <=
		SumaC WHEN (SC = '1') ELSE (OTHERS => '0');
		
	ChargeM <=
		SumaM WHEN (SM = '1') ELSE (OTHERS => '0');
	
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

	MUX : PROCESS(AN, Q)
		BEGIN
			CASE AN IS
				WHEN "1110" => SEL <= Q(18 downto 15);
				WHEN "1101" => SEL <= Q(22 downto 19);
				WHEN "1011" => SEL <= Q(26 downto 23);
				WHEN "0111" => SEL <= Q(30 downto 27);
				WHEN OTHERS => SEL <= "0000";
			END CASE;
	END PROCESS MUX;

end Behavioral;
