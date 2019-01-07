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


	component REGISTRO is
		 Port ( CLK : in  STD_LOGIC;
				  CLR : in  STD_LOGIC;
				  D : in  STD_LOGIC_VECTOR (7 downto 0);
				  A : inout  STD_LOGIC_VECTOR (7 downto 0);
				  LA : in  STD_LOGIC;
				  EA : in  STD_LOGIC);
	end component REGISTRO;

	component CONTADOR is
		 Port ( CLK : in  STD_LOGIC;
				  CLR : in  STD_LOGIC;
				  DB : in  STD_LOGIC_VECTOR (3 downto 0);
				  B : inout  STD_LOGIC_VECTOR (3 downto 0);
				  EB : in  STD_LOGIC;
				  LB : in  STD_LOGIC);
	end component CONTADOR;

	component BCD is
		 Port ( B : in  STD_LOGIC_VECTOR (3 downto 0);
				  CODIGO : out  STD_LOGIC_VECTOR (6 downto 0));
	end component BCD;

	component CONTROL is
		 Port ( CLK : in  STD_LOGIC;
				  CLR : in  STD_LOGIC;
				  INI : in  STD_LOGIC;
				  A0 : in  STD_LOGIC;
				  Z : in  STD_LOGIC;
				  LA : out  STD_LOGIC;
				  LB : out  STD_LOGIC;
				  EA : out  STD_LOGIC;
				  EB : out  STD_LOGIC;
				  EC : out  STD_LOGIC);
	end component CONTROL;

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
