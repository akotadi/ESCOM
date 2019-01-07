Library IEEE;   
Use IEEE.STD_LOGIC_1164.ALL; 
USE IEEE.STD_LOGIC_ARITH.ALL;
USE IEEE.STD_LOGIC_UNSIGNED.ALL;
   

Entity SensorCounter is
    Port (
            CLK, CLR: in STD_LOGIC;  
	    UNI: OUT STD_LOGIC_VECTOR(3 DOWNTO 0);
            DEC: OUT STD_LOGIC_VECTOR(2 DOWNTO 0);
            Sensors: in STD_LOGIC_VECTOR(1 downto 0)
    );

	ATTRIBUTE PIN_NUMBERS OF SensorCounter : ENTITY IS
		"CLK:1          "   	&
		"CLR:13         "	&
    		"Sensors(0):2   "	&
		"Sensors(1):3   "	&
		"DEC(2):20      "	&
		"DEC(1):19      "	&
		"DEC(0):18      "	&
		"UNI(3):17      "	&
		"UNI(2):16      "	&
		"UNI(1):15      "	&
		"UNI(0):14      ";
End SensorCounter;  

Architecture Behave of SensorCounter is 
    Type States is (q0, q1, q2, q3, q4, q5, q6, q7);  
    Signal CurrentState, NextState: States;
	Signal CounterPeople: STD_LOGIC_VECTOR(1 downto 0);
    Begin 
			FiniteStateMachine : Process(CurrentState, Sensors)
            Begin
                Case CurrentState is
                    When q0 => 
                        Case Sensors is
                            When "00" => 
				NextState <= q0;
				CounterPeople <="00";
                            When "01" => 
				NextState <= q4;
				CounterPeople <="00";
                            When "10" => 
				NextState <= q1;
				CounterPeople <="00";
                            When OTHERS => 
				NextState <= q7;
				CounterPeople <="00";
                        End Case;
		    When q1 => 
                        Case Sensors is
                            When "00" => 
				NextState <= q0;
				CounterPeople <="00";
                            When "01" => 
				NextState <= q4;
				CounterPeople <="00";
                            When "10" => 
				NextState <= q1;
				CounterPeople <="00";
                            When OTHERS => 
				NextState <= q2;
				CounterPeople <="00";
                        End Case;
		    When q2 => 
                        Case Sensors is
                            When "00" => 
				NextState <= q0;
				CounterPeople <="00";
                            When "01" => 
				NextState <= q3;
				CounterPeople <="00";
                            When "10" => 
				NextState <= q1;
				CounterPeople <="00";
                            When OTHERS => 
				NextState <= q2;
				CounterPeople <="00";
                        End Case;
		   When q3 => 
                        Case Sensors is
                            When "00" => 
				NextState <= q0;
				CounterPeople <="01";
                            When "01" => 
				NextState <= q3;
				CounterPeople <="00";
                            When "10" => 
				NextState <= q1;
				CounterPeople <="01";
                            When OTHERS => 
				NextState <= q2;
				CounterPeople <="00";
                        End Case;
		   When q4 => 
                        Case Sensors is
                            When "00" => 
				NextState <= q0;
				CounterPeople <="00";
                            When "01" => 
				NextState <= q4;
				CounterPeople <="00";
                            When "10" => 
				NextState <= q1;
				CounterPeople <="00";
                            When OTHERS => 
				NextState <= q5;
				CounterPeople <="00";
                        End Case;
		   When q5 => 
                        Case Sensors is
                            When "00" => 
				NextState <= q0;
				CounterPeople <="00";
                            When "01" => 
				NextState <= q4;
				CounterPeople <="00";
                            When "10" => 
				NextState <= q6;
				CounterPeople <="00";
                            When OTHERS => 
				NextState <= q5;
				CounterPeople <="00";
                        End Case;
		   When q6 => 
                        Case Sensors is
                            When "00" => 
				NextState <= q0;
				CounterPeople <="10";
                            When "01" => 
				NextState <= q4;
				CounterPeople <="10";
                            When "10" => 
				NextState <= q6;
				CounterPeople <="00";
                            When OTHERS => 
				NextState <= q5;
				CounterPeople <="00";
                        End Case;
		   When q7 => 
                        Case Sensors is
                            When "00" => 
				NextState <= q0;
				CounterPeople <="00";
                            When "01" => 
				NextState <= q4;
				CounterPeople <="00";
                            When "10" => 
				NextState <= q1;
				CounterPeople <="00";
                            When OTHERS => 
				NextState <= q7;
			        CounterPeople <="00";
                        End Case;
                End Case;
            End Process FiniteStateMachine;

		    PROCESS(CLK, CLR)
			BEGIN
				IF CLR = '1' THEN
					CurrentState <=q0;
				ELSIF RISING_EDGE(CLK) THEN
					CurrentState <= NextState;
				END IF;
			END PROCESS;

			COUNTER_DECADA: PROCESS(CLK, CLR)
			BEGIN
				IF CLR = '1' THEN
					UNI <= (OTHERS => '0');
					DEC <= (OTHERS => '0');
				ELSIF RISING_EDGE(CLK) THEN
					IF CounterPeople = "00" THEN
						UNI <= UNI;
						DEC <= DEC;
					END IF;

					IF CounterPeople = "01" THEN
						UNI <= UNI + 1;

						IF UNI = "1001" THEN
							UNI <= "0000";
							DEC <= DEC + 1;
						END IF;
					END IF;

					IF CounterPeople = "10" THEN
						UNI <= UNI - 1;

						IF UNI = "0000" THEN
							UNI <= "1001";
							DEC <= DEC - 1;
						END IF;
					END IF;
				END IF;
			END PROCESS COUNTER_DECADA;
    
End Behave;