LIBRARY IEEE;

USE IEEE.STD_LOGIC_1164.ALL;

ENTITY tb_sum_res_forloop IS
END tb_sum_res_forloop;

ARCHITECTURE TRY OF tb_sum_res_forloop IS
	COMPONENT sum_res_forloop
		Port ( 
			A : IN STD_LOGIC_VECTOR (3 DOWNTO 0);
			B : IN STD_LOGIC_VECTOR (3 DOWNTO 0);
			AINVERT, BINVERT : IN STD_LOGIC;
			OP : IN STD_LOGIC_VECTOR (1 DOWNTO 0);
			RES : OUT STD_LOGIC_VECTOR (3 DOWNTO 0);
			ZF, CF, NF, OV : OUT STD_LOGIC
		);
	END COMPONENT;

	SIGNAL AINVERT, BINVERT, ZF, CF, NF, OV : STD_LOGIC;
	SIGNAL A, B, RES : STD_LOGIC_VECTOR(3 DOWNTO 0);
	SIGNAL OP : STD_LOGIC_VECTOR(1 DOWNTO 0);

	BEGIN
		REGISTRO : sum_res_forloop PORT MAP (AINVERT => AINVERT, BINVERT => BINVERT, OP => OP, 
			CF => CF, NF => NF, OV => OV, ZF => ZF, A => A, B => B, RES => RES);

		stim_proc: process
		begin
			-- 5
			A <= "0101";
			-- -2
			B <= "1110";

			-- SUMA
			AINVERT <= '0';
			BINVERT <= '0';
			OP <= "11";
			wait for 100 ns;

			-- RESTA
			AINVERT <= '0';
			BINVERT <= '1';
			OP <= "11";
			wait for 100 ns;

			-- AND
			AINVERT <= '0';
			BINVERT <= '0';
			OP <= "00";
			wait for 100 ns;

			-- NAND
			AINVERT <= '1';
			BINVERT <= '1';
			OP <= "01";
			wait for 100 ns;

			-- OR
			AINVERT <= '0';
			BINVERT <= '0';
			OP <= "01";
			wait for 100 ns;

			-- NOR
			AINVERT <= '1';
			BINVERT <= '1';
			OP <= "00";
			wait for 100 ns;

			-- XOR
			AINVERT <= '0';
			BINVERT <= '0';
			OP <= "10";
			wait for 100 ns;

			-- XNOR
			AINVERT <= '0';
			BINVERT <= '1';
			OP <= "10";
			wait for 100 ns;

			-- 5
			A <= "0101";
			-- 7
			B <= "0111";

			-- SUMA
			AINVERT <= '0';
			BINVERT <= '0';
			OP <= "11";
			wait for 100 ns;

			-- 5
			A <= "0101";
			-- 5
			B <= "0101";

			-- RESTA
			AINVERT <= '0';
			BINVERT <= '1';
			OP <= "11";
			wait for 100 ns;

			-- NAND
			AINVERT <= '1';
			BINVERT <= '1';
			OP <= "01";
			wait for 100 ns;

			 ASSERT FALSE REPORT "REACHED END OF TEST"severity note;
			--  Wait forever; this will finish the simulation.
			wait;
		end process;
END TRY;