LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;

PACKAGE PAQUETE IS

COMPONENT REGISTRO IS
    PORT ( D : IN  STD_LOGIC_VECTOR (7 DOWNTO 0);
           L : IN  STD_LOGIC;
           E : IN  STD_LOGIC;
           CLK : IN  STD_LOGIC;
           CLR : IN  STD_LOGIC;
           Q : INOUT  STD_LOGIC_VECTOR (7 DOWNTO 0));
end COMPONENT;

COMPONENT CONTADOR IS
    PORT ( D : IN  STD_LOGIC_VECTOR (3 DOWNTO 0);
           L : IN  STD_LOGIC;
           E : IN  STD_LOGIC;
           CLK : IN  STD_LOGIC;
           CLR : IN  STD_LOGIC;
           Q : INOUT  STD_LOGIC_VECTOR (3 DOWNTO 0));
end COMPONENT;

COMPONENT CONTROL IS
    PORT ( LA, EA, LB, EB, EC : OUT  STD_LOGIC;
           A0 : IN  STD_LOGIC;
           Z : IN  STD_LOGIC;
           CLK : IN  STD_LOGIC;
           CLR : IN  STD_LOGIC;
           INI : IN  STD_LOGIC
			  );
end COMPONENT;

end PAQUETE;

PACKAGE body PAQUETE IS

 
end PAQUETE;
