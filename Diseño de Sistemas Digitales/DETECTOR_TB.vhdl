LIBRARY IEEE;

USE IEEE.STD_LOGIC_1164.ALL;

ENTITY DETECTOR_TB IS
END DETECTOR_TB;

ARCHITECTURE TRY OF DETECTOR_TB IS
	COMPONENT DETECTOR
		PORT(
			CLK, CLR, X: IN STD_LOGIC;
			AN: OUT STD_LOGIC;
			DISPLAY: OUT STD_LOGIC_VECTOR(6 DOWNTO 0)
		);
	END COMPONENT;

	SIGNAL CLK, CLR, X, AN, Y, Qa, Qb : STD_LOGIC;
	SIGNAL DISPLAY : STD_LOGIC_VECTOR(6 DOWNTO 0);

	BEGIN
		REGISTRO : DETECTOR PORT MAP (CLK => CLK, CLR => CLR, X => X, DISPLAY => DISPLAY, AN => AN);

		PROCESS
			BEGIN
				CLK <= '0';
				WAIT FOR 3 NS;
				CLK <= '1';
				WAIT FOR 3 NS;
		END PROCESS;

		PROCESS
			BEGIN
				CLR <= '1';
				WAIT FOR 5 NS;
				CLR <= '0';
				WAIT FOR 50 NS;
		END PROCESS;

		PROCESS
			BEGIN
				X <= '1';
				WAIT FOR 4 NS;
				X <= '1';
				WAIT FOR 6 NS;
				X <= '1';
				WAIT FOR 6 NS;
				X <= '1';
				WAIT FOR 6 NS;
				X <= '0';
				WAIT FOR 6 NS;
				X <= '1';
				WAIT FOR 6 NS;
				X <= '1';
				WAIT FOR 6 NS;
				X <= '0';
				WAIT FOR 6 NS;
		END PROCESS;

		-- ASSERT FALSE REPORT "REACHED END OF TEST";
END TRY;