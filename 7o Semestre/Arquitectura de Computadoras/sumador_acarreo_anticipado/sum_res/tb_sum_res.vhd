LIBRARY IEEE;

USE IEEE.STD_LOGIC_1164.ALL;

ENTITY tb_sum_res IS
END tb_sum_res;

ARCHITECTURE TRY OF tb_sum_res IS
	COMPONENT sum_res
		Port ( 
			A: IN STD_LOGIC_VECTOR (3 DOWNTO 0);
			B: IN STD_LOGIC_VECTOR (3 DOWNTO 0);
			BINVERT: IN STD_LOGIC;
			S: OUT STD_LOGIC_VECTOR (3 DOWNTO 0);
			Cn: OUT STD_LOGIC
		);
	END COMPONENT;

	SIGNAL BINVERT, Cn : STD_LOGIC;
	SIGNAL A : STD_LOGIC_VECTOR(3 DOWNTO 0);
	SIGNAL B : STD_LOGIC_VECTOR(3 DOWNTO 0);
	SIGNAL S : STD_LOGIC_VECTOR(3 DOWNTO 0);

	BEGIN
		REGISTRO : sum_res PORT MAP (BINVERT => BINVERT, Cn => Cn, A => A, B => B, S => S);

		stim_proc: process
		begin
			A <= "0101";
			B <= "0011";
			BINVERT <= '0';
			wait for 100 ns;

			A <= "0101";
			B <= "0011";
			BINVERT <= '1';
			wait for 100 ns;

			 ASSERT FALSE REPORT "REACHED END OF TEST"severity note;
			--  Wait forever; this will finish the simulation.
			wait;
		end process;
END TRY;