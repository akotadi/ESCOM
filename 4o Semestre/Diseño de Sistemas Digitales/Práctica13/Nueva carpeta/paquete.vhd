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

package paquete is

	component registro is
		 Generic (
			N : integer
		 );
		 Port ( clk : in  STD_LOGIC;
				  clr : in  STD_LOGIC;
				  Q : inout  STD_LOGIC_VECTOR (N+15 downto 0);
				  D : in  STD_LOGIC_VECTOR (N+15 downto 0);
				  SHE : in  STD_LOGIC;
				  Ln : in  STD_LOGIC;
				  Lu : in  STD_LOGIC;
				  Ld : in  STD_LOGIC;
				  Lc : in  STD_LOGIC;
				  Lm : in STD_LOGIC);
	end component;
	
	component comparador is
		 Port ( A : in  STD_LOGIC_VECTOR (3 downto 0);
				  B : in  STD_LOGIC_VECTOR (3 downto 0);
				  mayor : out  STD_LOGIC);
	end component;
	
	component sumador is
		 Generic (
				  N : integer
		 );
		 Port ( A : in  STD_LOGIC_VECTOR (N-1 downto 0);
				  B : in  STD_LOGIC_VECTOR (N-1 downto 0);
				  suma : out  STD_LOGIC_VECTOR (N-1 downto 0));
	end component;
	
	component contador is
		 Generic (
				  N : integer
		 );
		 Port ( clk : in  STD_LOGIC;
				  clr : in  STD_LOGIC;
				  DOWN : in STD_LOGIC;
				  L : in STD_LOGIC;
				  Q : inout  STD_LOGIC_VECTOR (N-1 downto 0);
				  D : in  STD_LOGIC_VECTOR (N-1 downto 0));
	end component;
	
	component fsm is
		 Port ( clk : in  STD_LOGIC;
				  clr : in  STD_LOGIC;
				  ini : in  STD_LOGIC;
				  Z : in  STD_LOGIC;
				  Cu : in  STD_LOGIC;
				  Cd : in  STD_LOGIC;
				  Cc : in  STD_LOGIC;
				  Cm : in  STD_LOGIC;
				  Li : out  STD_LOGIC;
				  DOWNi : out  STD_LOGIC;
				  Su : out  STD_LOGIC;
				  Sd : out  STD_LOGIC;
				  Sc : out  STD_LOGIC;
				  Sm : out  STD_LOGIC;
				  SHE : out  STD_LOGIC;
				  Ln : out  STD_LOGIC;
				  Lu : out  STD_LOGIC;
				  Ld : out  STD_LOGIC;
				  Lc : out  STD_LOGIC;
				  Lm : out  STD_LOGIC;
				  Em : out  STD_LOGIC);
	end component;
	
	component convertidor is
		 Port ( Q : in  STD_LOGIC_VECTOR (3 downto 0);
				  S : out  STD_LOGIC_VECTOR (6 downto 0));
	end component;
	
	component registro_promedio is
		 Generic(
			N : integer
		 );
		 Port ( clk : in  STD_LOGIC;
				  clr : in  STD_LOGIC;
				  L : in STD_LOGIC;
				  SHE : in STD_LOGIC;
				  Q : inout  STD_LOGIC_VECTOR (N-1 downto 0);
				  D : in  STD_LOGIC_VECTOR (N-1 downto 0));
	end component;
	
	component BinaryToBCD is
		 Generic (
				N : integer
		 );
		 Port ( clk : in  STD_LOGIC;
				  clr : in  STD_LOGIC;
				  ini : in  STD_LOGIC;
				  Number : in  STD_LOGIC_VECTOR (N-1 downto 0);
				  Ready : out STD_LOGIC;
				  SalidaU, SalidaD, SalidaC, SalidaM : out STD_LOGIC_VECTOR(6 downto 0));
	end component;
	
	component fsm2 is
		 Port ( clk : in  STD_LOGIC;
				  clr : in  STD_LOGIC;
				  ini : in  STD_LOGIC;
				  LOADi : out STD_LOGIC;
				  DOWNi : out  STD_LOGIC;
				  ResetSum : out STD_LOGIC;
				  LoadSum : out  STD_LOGIC;
				  Ready : out  STD_LOGIC;
				  Divide : out  STD_LOGIC;
				  Z : in  STD_LOGIC);
	end component;

end paquete;

package body paquete is

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
 
end paquete;
