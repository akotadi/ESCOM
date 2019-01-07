--
--	Package File Template
--
--	Purpose: This package defines supplemental types, subtypes, 
--		 constants, and functions 
--
--   To use any of the example code shown below, uncomment the lines and modify as necessary
--

library IEEE;
use IEEE.STD_LOGIC_1164.all;

package MI_PAQUETE is

-- type <new_type> is
--  record
--    <type_name>        : std_logic_vector( 7 downto 0);
--    <type_name>        : std_logic;
-- end record;
--
-- Declare constants
--
-- constant <constant_name>		: time := <time_unit> ns;
-- constant <constant_name>		: integer := <value;
--
-- Declare functions and procedure
--
-- function <function_name>  (signal <signal_name> : in <type_declaration>) return <type_declaration>;
-- procedure <procedure_name> (<type_declaration> <constant_name>	: in <type_declaration>);
--


	COMPONENT DIVISOR is
		 Port ( OSC : in  STD_LOGIC;
				  CLR : in  STD_LOGIC;
				  CLK : inout  STD_LOGIC);
	END COMPONENT DIVISOR;
	
	COMPONENT REGISTRO is
		 Port ( CLK : in  STD_LOGIC;
				  CLR : in  STD_LOGIC;
				  D : in  STD_LOGIC_VECTOR (30 downto 0);
				  Q : inout  STD_LOGIC_VECTOR (30 downto 0);
				  SHE : in STD_LOGIC;
				  Ln : in STD_LOGIC;
				  LU : in  STD_LOGIC;
				  LD : in STD_LOGIC;
				  LC : in STD_LOGIC;
				  LM : in STD_LOGIC);
	END COMPONENT REGISTRO;

	COMPONENT CONTADOR is
	    Port ( CLK : in  STD_LOGIC;
	           CLR : in  STD_LOGIC;
	           D : in  STD_LOGIC_VECTOR (3 downto 0);
	           Q : inout  STD_LOGIC_VECTOR (3 downto 0);
	           EB : in  STD_LOGIC;
	           LB : in  STD_LOGIC);
	END COMPONENT CONTADOR;

	COMPONENT SUMADOR is
	    Port ( RESULT : out  STD_LOGIC_VECTOR (3 downto 0);
	           N1 : in  STD_LOGIC_VECTOR (3 downto 0);
	           N2 : in  STD_LOGIC_VECTOR (3 downto 0));
	END COMPONENT SUMADOR;

	COMPONENT COMPARADOR is
	    Port ( RESULT : out  STD_LOGIC;
	           N1 : in  STD_LOGIC_VECTOR (3 downto 0);
	           N2 : in  STD_LOGIC_VECTOR (3 downto 0));
	END COMPONENT COMPARADOR;

	COMPONENT BCD is
		 Port ( B : in  STD_LOGIC_VECTOR (3 downto 0);
				  CODIGO : out  STD_LOGIC_VECTOR (6 downto 0));
	END COMPONENT BCD;

	COMPONENT CONTROL is
		Port ( 
			CLK : in  STD_LOGIC;
			CLR : in  STD_LOGIC;
			INI : in  STD_LOGIC;
			Z : in  STD_LOGIC;
			CU : in STD_LOGIC;
			CD : in STD_LOGIC;
			CC : in STD_LOGIC;
			CM : in STD_LOGIC;
			Ln : out  STD_LOGIC;
			LU : out  STD_LOGIC;
			LD : out  STD_LOGIC;
			LC : out  STD_LOGIC;
			LM : out  STD_LOGIC;
			LB : out  STD_LOGIC;
			EB : out  STD_LOGIC;
			SU : out  STD_LOGIC;
			SD : out  STD_LOGIC;
			SC : out  STD_LOGIC;
			SM : out  STD_LOGIC;
			SHE : out  STD_LOGIC;
			EM : out STD_LOGIC
		);
	END COMPONENT CONTROL;
	
	COMPONENT REGISTRO_PROMEDIO is
		 Port ( CLK : in  STD_LOGIC;
				  CLR : in  STD_LOGIC;
				  D : in  STD_LOGIC_VECTOR (14 downto 0);
				  Q : inout  STD_LOGIC_VECTOR (14 downto 0);
				  SHE : in STD_LOGIC;
				  L : in STD_LOGIC);
	END COMPONENT REGISTRO_PROMEDIO;
	
	COMPONENT CONTROL_PROMEDIO is
		 Port ( CLK : in  STD_LOGIC;
				  CLR : in  STD_LOGIC;
				  INI : in  STD_LOGIC;
				  LB : out STD_LOGIC;
				  EB : out  STD_LOGIC;
				  Ln : out  STD_LOGIC;
				  EM : out  STD_LOGIC;
				  SHE : out  STD_LOGIC;
				  Z : in  STD_LOGIC);
	END COMPONENT CONTROL_PROMEDIO;
	
	COMPONENT PRINCIPAL is
		 Port ( CLK : In STD_LOGIC;
				  CLR : in  STD_LOGIC;
				  INI : in  STD_LOGIC;
				  n : in  STD_LOGIC_VECTOR (14 downto 0);
				AN : inout STD_LOGIC_VECTOR (3 downto 0);
				  DISPLAY : out  STD_LOGIC_VECTOR (6 downto 0));
	END COMPONENT PRINCIPAL;

end MI_PAQUETE;

package body MI_PAQUETE is

---- Example 1
--  function <function_name>  (signal <signal_name> : in <type_declaration>  ) return <type_declaration> is
--    variable <variable_name>     : <type_declaration>;
--  begin
--    <variable_name> := <signal_name> xor <signal_name>;
--    return <variable_name>; 
--  end <function_name>;

---- Example 2
--  function <function_name>  (signal <signal_name> : in <type_declaration>;
--                         signal <signal_name>   : in <type_declaration>  ) return <type_declaration> is
--  begin
--    if (<signal_name> = '1') then
--      return <signal_name>;
--    else
--      return 'Z';
--    end if;
--  end <function_name>;

---- Procedure Example
--  procedure <procedure_name>  (<type_declaration> <constant_name>  : in <type_declaration>) is
--    
--  begin
--    
--  end <procedure_name>;
 
end MI_PAQUETE;
