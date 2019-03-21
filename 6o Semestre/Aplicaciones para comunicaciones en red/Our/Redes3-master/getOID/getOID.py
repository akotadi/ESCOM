import gi
import sys
gi.require_version('Gtk', '3.0')
from gi.repository import Gtk
from ComboBoxWindow import ComboBoxWindow
from Nodo import Nodo

#tipo 0 -> escalar no graficable
#tipo 1 -> escalar graficable
#tipo 2 -> tabla

iso = "1.3.6.1.2.1"
raiz = Nodo("Raiz","1.3.6.1.2.1","")
tipos = [["System", iso + ".1","Opciones sistema"],
		 ["Interfaces", iso + ".2", "Interfaces"],
		 ["SNMP", iso + ".11", "SNMP"],
		 ["AT",iso+".3","Address Translator"],
		 ["IP",iso+".4","IP"],
		 ["ICMP",iso+".5","ICMP"],
		 ["TCP",iso+".6","TCP"],
		 ["UDP",iso+".7","UDP"],
		 ["EGP",iso+".8","EGP"]]
#[1, "Interfaces", iso + ".2" ] ]
for x in tipos:
	raiz.addChildren( Nodo( x[0], x[1], x[2]) )

Hijo = raiz.hijos[0]
tipos = [ ["sysDescr", iso + ".1.1.0","Describe el sistema","0" ], ["sysObjectID", iso + ".1.2.0", "id", "0"], ["sysContact", iso + ".1.4.0", "Contacto", "0"] ]
#[1, "Interfaces", iso + ".2" ] ]
for x in tipos:
	Hijo.addChildren( Nodo( x[0], x[1], x[2],x[3] ) )

Hijo = raiz.hijos[1]
tipos = [ ["ifNumber", iso + ".2.1.0","Da el numero de interfaces","0" ], ["ifTable", "1.3.6.1.2.1.2.2", "Tabla de interfaces", "2"] ]
#[1, "Interfaces", iso + ".2" ] ]
for x in tipos:
	Hijo.addChildren( Nodo( x[0], x[1], x[2],x[3] ) )

Hijo = raiz.hijos[2]
tipos = [ ["snmpInPkts", iso + ".11.1.0","Da el numero de mensajes de entrada SNMP","1" ] ]
#[1, "Interfaces", iso + ".2" ] ]
for x in tipos:
	Hijo.addChildren( Nodo( x[0], x[1], x[2],x[3] ) )

Hijo = raiz.hijos[3]
tipos = [["atNetAddress" , "1.3.6.1.2.1.3.1.1.3" , "La direccion de red correspondiente a la direccion fisica","0"]]
for x in tipos:
	Hijo.addChildren( Nodo( x[0], x[1], x[2],x[3] ) )

Hijo = raiz.hijos[4]
tipos = [["ipDefaultTTL", iso+".4.2.0", "El tiempo de vida de datagramas IP generados","0"],
         ["ipInReceives", iso+".4.3.0", "El numero de datagramas recibidos","1"],
         ["ipInHdrErrors",iso+".4.4.0", "El numero datagramas entrantes con errores en el header","1"],
         ["ipInAddrErrors",iso+".4.5.0", "El numero de datagramas entrantes con error en la direccion destino","1"],
         ["ipInUnknownProtos",iso+".4.7.0","El numero de datagramos entrantes con protocolo desconocido","1"],
         ["ipInDiscards",iso+".4.8.0", "El numero de datagramas entrantes descartados en total","1"],
         ["ipInDelivers",iso+".4.9.0", "El numero de datagramas entrantes entregados exitosamente","1"],
         ["ipOutDiscards",iso+".4.11.0", "El numero de datagramas transmitidos pero descartados","1"]]
for x in tipos:
	Hijo.addChildren( Nodo( x[0], x[1], x[2],x[3] ) )

Hijo = raiz.hijos[5]
tipos = [["icmpInMsgs",iso+".5.1.0","Da el numero de mensajes icmp entrantes","1"],
		 ["icmpInErrors",iso+".5.2.0","Da el numero de mensajes icmp entrantes con error","1"],
		 ["icmpInDestUnreachs", iso+".5.3.0","Da el numero de mensajes icmp entrantes con destino inalcanzable","1"],
		 ["icmpInTimeExcds", iso+".5.4.0","Da el numero de mensajes icmp entrantes con tiempo excedido","1"],
		 ["icmpOutMsgs",iso+".5.14.0","Da el numero de mensajes icmp salientes","1"],
		 ["icmpOutErrors",iso+".5.2.0","Da el numero de mensajes icmp salientes con error","1"],
		 ["icmpOutDestUnreachs", iso+".5.3.0","Da el numero de mensajes icmp salientes con destino inalcanzable","1"],
		 ["icmpOutTimeExcds", iso+".5.4.0","Da el numero de mensajes icmp salientes con tiempo excedido","1"]]
for x in tipos:
	Hijo.addChildren( Nodo( x[0], x[1], x[2],x[3] ) )

Hijo = raiz.hijos[6]
tipos = [["tcpMaxConn",iso+".6.4.0","Maximo numero de conexiones tcp","1"],
		 ["tcpActiveOpens",iso+".6.5.0", "Numero de transiciones SYN-SENT -> CLOSED en las conexiones","1"],
		 ["tcpPassiveOpens",iso+".6.6.0","Numero de transiciones SYN-RCVD -> LISTEN en las conexiones","1"],
		 ["tcpAttemptFails",iso+".6.7.0","Numero de conexiones fallidas","1"],
		 ["tcpInSegs",iso+".6.10.0","Numero de segmentos recibidos","1"],
		 ["tcpOutSegs",iso+".6.11.0","Numero de segmentos enviados","1"],
		 ["tcpInErrs",iso+".6.14.0","Numero de segmento recibidos con error","1"]]
for x in tipos:
	Hijo.addChildren( Nodo( x[0], x[1], x[2],x[3] ) )

Hijo = raiz.hijos[7]
tipos = [["udpInDatagrams",iso+".7.1.0","Numero de datagramas UDP entrantes","1"],
         ["udpNoPorts",iso+".7.2.0","Numero de datagramas recibidos con un puerto sin aplicacion","1"],
         ["udpInErrors" , iso +".7.3.0","Numero de datagramas entrantes con error","1"],
         ["udpOutDatagrams",iso+".7.4.0","Numero de datagramas UDP salientes","1"]]
for x in tipos:
	Hijo.addChildren( Nodo( x[0], x[1], x[2],x[3] ) )

Hijo = raiz.hijos[8]
tipos = [["egpInMsgs", iso+".8.1.0","Numero de mensaje EGP recibidos sin error","1"],
         ["egpInErrors", iso+".8.2.0","Numero de mensaje EGP recibidos con error","1"],
         ["egpOutMsgs", iso+".8.3.0","Numero de mensaje EGP generados localmente sin error","1"],
         ["egpOutErrors", iso+".8.4.0","Numero de mensaje EGP generados localmente con error","1"]]
for x in tipos:
	Hijo.addChildren( Nodo( x[0], x[1], x[2],x[3] ) )



win = ComboBoxWindow(raiz)
win.connect("delete-event", Gtk.main_quit)
Gtk.main()