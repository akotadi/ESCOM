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

	COMPONENT REGISTRO IS 

		PORT( 
			CLK, CLR, LA, EA: IN STD_LOGIC;
			D : IN STD_LOGIC_VECTOR(7 DOWNTO 0);
			A : INOUT STD_LOGIC_VECTOR(7 DOWNTO 0)
		);

	END COMPONENT REGISTRO;

	COMPONENT CONTADOR IS 

		PORT( 
			CLK, CLR, LB, EB : IN STD_LOGIC;
			DB : IN STD_LOGIC_VECTOR(3 DOWNTO 0);
			B : INOUT STD_LOGIC_VECTOR(3 DOWNTO 0)
		);

	END COMPONENT CONTADOR;

	COMPONENT BCD IS 

		PORT( 
			CODIGO : OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
			B : INOUT STD_LOGIC_VECTOR(3 DOWNTO 0)
		);

	END COMPONENT BCD;

	COMPONENT CONTROL IS 

		PORT( 
			CLK, CLR, INI, Z, A0: IN STD_LOGIC;
			LB, EB, LA, EA, EC : OUT STD_LOGIC
		);

	END COMPONENT CONTROL;

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
