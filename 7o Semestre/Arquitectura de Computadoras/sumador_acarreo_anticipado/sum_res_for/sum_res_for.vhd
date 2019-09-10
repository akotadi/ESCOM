LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;

ENTITY sum_res_for IS
	GENERIC (
		N : INTEGER :=4
	);

	Port ( 
		A : IN STD_LOGIC_VECTOR (N-1 DOWNTO 0);
		B : IN STD_LOGIC_VECTOR (N-1 DOWNTO 0);
		BINVERT : IN STD_LOGIC;
		S : OUT STD_LOGIC_VECTOR (N-1 DOWNTO 0);
		Cn : OUT STD_LOGIC
	);
END sum_res_for;

ARCHITECTURE PROGRAMA OF sum_res_for IS

SIGNAL C : STD_LOGIC_VECTOR(N DOWNTO 0);
SIGNAL EB : STD_LOGIC_VECTOR(N-1 DOWNTO 0);

	BEGIN
		C(0) <= BINVERT;

		CICLO : FOR I IN 0 TO N-1 GENERATE
			EB(I) <= B(I) XOR BINVERT;
			S(I) <= A(I) XOR EB(I) XOR C(I);
			C(I+1) <= (A(I) AND EB(I)) OR (A(I) AND C(I)) OR (EB(I) AND C(I));
		END GENERATE;

		Cn <= C(N);

END PROGRAMA;