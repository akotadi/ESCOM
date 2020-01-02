LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;

ENTITY sum_res_forloop IS
	GENERIC (
		N : INTEGER :=4
	);

	Port ( 
		A : IN STD_LOGIC_VECTOR (N-1 DOWNTO 0);
		B : IN STD_LOGIC_VECTOR (N-1 DOWNTO 0);
		AINVERT, BINVERT : IN STD_LOGIC;
		OP : IN STD_LOGIC_VECTOR (1 DOWNTO 0);
		RES : OUT STD_LOGIC_VECTOR (N-1 DOWNTO 0);
		ZF, CF, NF, OV : OUT STD_LOGIC
	);
END sum_res_forloop;

ARCHITECTURE PROGRAMA OF sum_res_forloop IS

	BEGIN

		PUA :PROCESS(A, B, BINVERT)
		VARIABLE C : STD_LOGIC_VECTOR (N DOWNTO 0) := "00000";
		VARIABLE P, G, MUXA, MUXB : STD_LOGIC_VECTOR (N-1 DOWNTO 0);
		VARIABLE PK, T2, T3, Z : STD_LOGIC;
		BEGIN
			C(0) := BINVERT;
			Z := '0';

			FOR I IN 0 TO N-1 LOOP

				MUXA(I) := A(I) XOR AINVERT;
				MUXB(I) := B(I) XOR BINVERT;

				CASE OP IS
					WHEN "00" =>
						RES <= MUXA AND MUXB;
						Z := Z OR (MUXA(I) AND MUXB(I));
						NF <= MUXA(I) AND MUXB(I);
					WHEN "01" =>
						RES <= MUXA OR MUXB;
						Z := Z OR (MUXA(I) OR MUXB(I));
						NF <= MUXA(I) OR MUXB(I);
					WHEN "10" =>
						RES <= MUXA XOR MUXB;
						Z := Z OR (MUXA(I) XOR MUXB(I));
						NF <= MUXA(I) XOR MUXB(I);
					WHEN OTHERS =>
						P(I) := MUXA(I) XOR MUXB(I);
						G(I) := MUXA(I) AND MUXB(I);
						RES(I) <= MUXA(I) XOR MUXB(I) XOR C(I);
						Z := Z OR (MUXA(I) XOR MUXB(I) XOR C(I));
						NF <= MUXA(I) XOR MUXB(I) XOR C(I);

						T2 := '0';
						FOR J IN 0 TO I-1 LOOP
							PK := '1';
							FOR K IN J+1 TO I LOOP
								PK := PK AND P(K);
							END LOOP;
							T2 := T2 OR (G(J) AND PK);
						END LOOP;

						T3 := '1';
						FOR L IN 0 TO I LOOP
							T3 := T3 AND P(L);
						END LOOP;
						T3 := T3 AND C(0);

						C(I+1) := G(I) OR T2 OR T3;
				END CASE;
			END LOOP;

			OV <= C(N) XOR C(N-1);
			CF <= C(N);
			ZF <= NOT Z;
		END PROCESS PUA;

END PROGRAMA;