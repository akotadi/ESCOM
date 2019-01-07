
--  CYPRESS NOVA XVL Structural Architecture

--  JED2VHD Reverse Assembler - 6.3 IR 35


--    VHDL File: practica6.vhd

--    Date: Sun Sep 24 21:00:02 2017

--  Disassembly from Jedec file for: c22v10

--  Device Ordercode is: PALCE22V10-15PC
library ieee;
use ieee.std_logic_1164.all;

library primitive;
use primitive.primitive.all;


-- Beginning Test Bench Header

ENTITY compuertas IS
    PORT (
	                   a :    in std_logic ;
	                   b :    in std_logic ;
	                   c :    in std_logic ;
	                   d :    in std_logic ;
	                   e :    in std_logic ;
	                cand : inout std_logic ;
	                 cor : inout std_logic ;
	                cxor : inout std_logic ;
	               cnand : inout std_logic ;
	                cnor : inout std_logic ;
	               cxnor : inout std_logic
    );
END compuertas;

-- End of Test Bench Header

ARCHITECTURE DSMB of compuertas is

	signal jed_node1	: std_logic:='0' ; -- e
	signal jed_node2	: std_logic:='0' ; -- d
	signal jed_node3	: std_logic:='0' ; -- c
	signal jed_node4	: std_logic:='0' ; -- b
	signal jed_node5	: std_logic:='0' ; -- a
	signal jed_node6	: std_logic:='0' ;
	signal jed_node7	: std_logic:='0' ;
	signal jed_node8	: std_logic:='0' ;
	signal jed_node9	: std_logic:='0' ;
	signal jed_node10	: std_logic:='0' ;
	signal jed_node11	: std_logic:='0' ;
	signal jed_node12	: std_logic:='0' ;
	signal jed_node13	: std_logic:='0' ;
	signal jed_node16	: std_logic:='0' ;
	signal jed_node17	: std_logic:='0' ;
	signal jed_node20	: std_logic:='0' ;
	signal jed_node21	: std_logic:='0' ;
	signal jed_node24	: std_logic:='0' ;

	for all: c22v10m use entity primitive.c22v10m (sim);

SIGNAL  one:std_logic:='1';
SIGNAL  zero:std_logic:='0';
SIGNAL  jed_oept_1:std_logic:='0';
--Attribute PIN_NUMBERS of e:SIGNAL is "0001";

SIGNAL  jed_oept_2:std_logic:='0';
--Attribute PIN_NUMBERS of d:SIGNAL is "0002";

SIGNAL  jed_oept_3:std_logic:='0';
--Attribute PIN_NUMBERS of c:SIGNAL is "0003";

SIGNAL  jed_oept_4:std_logic:='0';
--Attribute PIN_NUMBERS of b:SIGNAL is "0004";

SIGNAL  jed_oept_5:std_logic:='0';
--Attribute PIN_NUMBERS of a:SIGNAL is "0005";

SIGNAL  jed_oept_14:std_logic:='0';
SIGNAL  jed_sum_14,jed_fb_14:std_logic:='0';
--Attribute PIN_NUMBERS of cor:SIGNAL is "0014";

SIGNAL  jed_oept_15:std_logic:='0';
SIGNAL  jed_sum_15,jed_fb_15:std_logic:='0';
--Attribute PIN_NUMBERS of cnand:SIGNAL is "0015";

SIGNAL  jed_oept_18:std_logic:='0';
SIGNAL  jed_sum_18,jed_fb_18:std_logic:='0';
--Attribute PIN_NUMBERS of cxor:SIGNAL is "0018";

SIGNAL  jed_oept_19:std_logic:='0';
SIGNAL  jed_sum_19,jed_fb_19:std_logic:='0';
--Attribute PIN_NUMBERS of cxnor:SIGNAL is "0019";

SIGNAL  jed_oept_22:std_logic:='0';
SIGNAL  jed_sum_22,jed_fb_22:std_logic:='0';
--Attribute PIN_NUMBERS of cand:SIGNAL is "0022";

SIGNAL  jed_oept_23:std_logic:='0';
SIGNAL  jed_sum_23,jed_fb_23:std_logic:='0';
--Attribute PIN_NUMBERS of cnor:SIGNAL is "0023";

SIGNAL  jed_oept_25:std_logic:='0';
SIGNAL  jed_node25,jed_sum_25:std_logic:='0';
SIGNAL  jed_oept_26:std_logic:='0';
SIGNAL  jed_node26,jed_sum_26:std_logic:='0';

BEGIN
jed_node1 <= e ;
jed_node2 <= d ;
jed_node3 <= c ;
jed_node4 <= b ;
jed_node5 <= a ;
Mcell_14:c22v10m
generic map(comb,
   invt,
   xpin,
   	15 ns, --tpd
	15 ns, --tea
	15 ns, --ter
	8 ns, --tco
	10 ns, --ts
	0 ns, --th
	6 ns, --twh
	6 ns, --twl
	4 ns, --tcf
	15 ns, --taw
	12 ns, --tar
	20 ns, --tap
	20 ns  --tspr
)
port map(
     d=>jed_sum_14,
     clk=>jed_node1,
     oe=>jed_oept_14,
     ss=>jed_sum_26,
     ar=>jed_sum_25,
     y=>cor,
     fb=>jed_fb_14
   );

Mcell_15:c22v10m
generic map(comb,
   invt,
   xpin,
   	15 ns, --tpd
	15 ns, --tea
	15 ns, --ter
	8 ns, --tco
	10 ns, --ts
	0 ns, --th
	6 ns, --twh
	6 ns, --twl
	4 ns, --tcf
	15 ns, --taw
	12 ns, --tar
	20 ns, --tap
	20 ns  --tspr
)
port map(
     d=>jed_sum_15,
     clk=>jed_node1,
     oe=>jed_oept_15,
     ss=>jed_sum_26,
     ar=>jed_sum_25,
     y=>cnand,
     fb=>jed_fb_15
   );

Mcell_18:c22v10m
generic map(comb,
   ninv,
   xpin,
   	15 ns, --tpd
	15 ns, --tea
	15 ns, --ter
	8 ns, --tco
	10 ns, --ts
	0 ns, --th
	6 ns, --twh
	6 ns, --twl
	4 ns, --tcf
	15 ns, --taw
	12 ns, --tar
	20 ns, --tap
	20 ns  --tspr
)
port map(
     d=>jed_sum_18,
     clk=>jed_node1,
     oe=>jed_oept_18,
     ss=>jed_sum_26,
     ar=>jed_sum_25,
     y=>cxor,
     fb=>jed_fb_18
   );

Mcell_19:c22v10m
generic map(comb,
   ninv,
   xpin,
   	15 ns, --tpd
	15 ns, --tea
	15 ns, --ter
	8 ns, --tco
	10 ns, --ts
	0 ns, --th
	6 ns, --twh
	6 ns, --twl
	4 ns, --tcf
	15 ns, --taw
	12 ns, --tar
	20 ns, --tap
	20 ns  --tspr
)
port map(
     d=>jed_sum_19,
     clk=>jed_node1,
     oe=>jed_oept_19,
     ss=>jed_sum_26,
     ar=>jed_sum_25,
     y=>cxnor,
     fb=>jed_fb_19
   );

Mcell_22:c22v10m
generic map(comb,
   ninv,
   xpin,
   	15 ns, --tpd
	15 ns, --tea
	15 ns, --ter
	8 ns, --tco
	10 ns, --ts
	0 ns, --th
	6 ns, --twh
	6 ns, --twl
	4 ns, --tcf
	15 ns, --taw
	12 ns, --tar
	20 ns, --tap
	20 ns  --tspr
)
port map(
     d=>jed_sum_22,
     clk=>jed_node1,
     oe=>jed_oept_22,
     ss=>jed_sum_26,
     ar=>jed_sum_25,
     y=>cand,
     fb=>jed_fb_22
   );

Mcell_23:c22v10m
generic map(comb,
   ninv,
   xpin,
   	15 ns, --tpd
	15 ns, --tea
	15 ns, --ter
	8 ns, --tco
	10 ns, --ts
	0 ns, --th
	6 ns, --twh
	6 ns, --twl
	4 ns, --tcf
	15 ns, --taw
	12 ns, --tar
	20 ns, --tap
	20 ns  --tspr
)
port map(
     d=>jed_sum_23,
     clk=>jed_node1,
     oe=>jed_oept_23,
     ss=>jed_sum_26,
     ar=>jed_sum_25,
     y=>cnor,
     fb=>jed_fb_23
   );

jed_node25<=jed_sum_25;
jed_node26<=jed_sum_26;
jed_oept_14<=(one);

jed_sum_14<= ((not(jed_node1) and not(jed_node2) and not(jed_node3)
 and not(jed_node4) and not(jed_node5)));

jed_oept_15<=(one);

jed_sum_15<= (((jed_node1) and (jed_node2) and (jed_node3) and (jed_node4)
 and (jed_node5)));

jed_oept_18<=(one);

jed_sum_18<= (((jed_node1) and (jed_node2) and (jed_node3) and (jed_node4)
 and (jed_node5)) or
((jed_node1) and (jed_node2) and (jed_node3) and not(jed_node4)
 and not(jed_node5)) or
((jed_node1) and (jed_node2) and not(jed_node3) and (jed_node4)
 and not(jed_node5)) or
((jed_node1) and (jed_node2) and not(jed_node3) and not(jed_node4)
 and (jed_node5)) or
((jed_node1) and not(jed_node2) and (jed_node3) and (jed_node4)
 and not(jed_node5)) or
((jed_node1) and not(jed_node2) and (jed_node3) and not(jed_node4)
 and (jed_node5)) or
((jed_node1) and not(jed_node2) and not(jed_node3) and (jed_node4)
 and (jed_node5)) or
((jed_node1) and not(jed_node2) and not(jed_node3) and not(jed_node4)
 and not(jed_node5)) or
(not(jed_node1) and (jed_node2) and (jed_node3) and (jed_node4)
 and not(jed_node5)) or
(not(jed_node1) and (jed_node2) and (jed_node3) and not(jed_node4)
 and (jed_node5)) or
(not(jed_node1) and (jed_node2) and not(jed_node3) and (jed_node4)
 and (jed_node5)) or
(not(jed_node1) and (jed_node2) and not(jed_node3) and not(jed_node4)
 and not(jed_node5)) or
(not(jed_node1) and not(jed_node2) and (jed_node3) and (jed_node4)
 and (jed_node5)) or
(not(jed_node1) and not(jed_node2) and (jed_node3) and not(jed_node4)
 and not(jed_node5)) or
(not(jed_node1) and not(jed_node2) and not(jed_node3)
 and (jed_node4) and not(jed_node5)) or
(not(jed_node1) and not(jed_node2) and not(jed_node3)
 and not(jed_node4) and (jed_node5)));

jed_oept_19<=(one);

jed_sum_19<= ((not(jed_node1) and not(jed_node2) and not(jed_node3)
 and not(jed_node4) and not(jed_node5)) or
((jed_node1) and (jed_node2) and (jed_node3) and (jed_node4)
 and not(jed_node5)) or
((jed_node1) and (jed_node2) and (jed_node3) and not(jed_node4)
 and (jed_node5)) or
((jed_node1) and (jed_node2) and not(jed_node3) and (jed_node4)
 and (jed_node5)) or
((jed_node1) and (jed_node2) and not(jed_node3) and not(jed_node4)
 and not(jed_node5)) or
((jed_node1) and not(jed_node2) and (jed_node3) and (jed_node4)
 and (jed_node5)) or
((jed_node1) and not(jed_node2) and (jed_node3) and not(jed_node4)
 and not(jed_node5)) or
((jed_node1) and not(jed_node2) and not(jed_node3) and (jed_node4)
 and not(jed_node5)) or
((jed_node1) and not(jed_node2) and not(jed_node3) and not(jed_node4)
 and (jed_node5)) or
(not(jed_node1) and (jed_node2) and (jed_node3) and (jed_node4)
 and (jed_node5)) or
(not(jed_node1) and (jed_node2) and (jed_node3) and not(jed_node4)
 and not(jed_node5)) or
(not(jed_node1) and (jed_node2) and not(jed_node3) and (jed_node4)
 and not(jed_node5)) or
(not(jed_node1) and (jed_node2) and not(jed_node3) and not(jed_node4)
 and (jed_node5)) or
(not(jed_node1) and not(jed_node2) and (jed_node3) and (jed_node4)
 and not(jed_node5)) or
(not(jed_node1) and not(jed_node2) and (jed_node3) and not(jed_node4)
 and (jed_node5)) or
(not(jed_node1) and not(jed_node2) and not(jed_node3)
 and (jed_node4) and (jed_node5)));

jed_oept_22<=(one);

jed_sum_22<= (((jed_node1) and (jed_node2) and (jed_node3) and (jed_node4)
 and (jed_node5)));

jed_oept_23<=(one);

jed_sum_23<= ((not(jed_node1) and not(jed_node2) and not(jed_node3)
 and not(jed_node4) and not(jed_node5)));

end DSMB;
