import gi
import sys
gi.require_version('Gtk', '3.0')
from gi.repository import Gtk
from Caja import Caja
from SNMP import consultaSNMP
from SNMP import setSNMP
from RRD import RRD
from Mail import Mail
import threading
import time
import os

carpeta = "RRD/"

class AnalysisWindow(Gtk.Window):
	def __init__(self, ip, comunidad, puerto, destinatarios, enviarMail):
		Gtk.Window.__init__(self, title="Analisis: " + ip)
		self.set_size_request(100,200)
		self.ip = ip
		self.comunidad = comunidad
		self.puerto = puerto
		self.destinatarios = destinatarios
		self.enviarMail = enviarMail

		self.set_border_width(10)

		self.bases = {}
		self.CajasImagen = {}
		self.nombresImagenes = {}
		self.estadosActuales = {}
		self.lineaBase = {}
		self.lineaBaseReady = {}
		self.lineaBaseSet = {}
		self.lineaBaseGo = {}
		self.estadoLineaBase = {}
		self.lineabaseParametro = {}

		self.vbox = Gtk.Box(orientation=Gtk.Orientation.VERTICAL, spacing=6)
		self.vbox.show()
	
		#ComboBox BoxOid
		self.ComboOid = Caja();
		self.ComboOid.AgregaDescr("OID: ");
		# lista =  [["CPU","iso.3.6.1.2.1.25.3.3.1.2.196608"],["Conexiones ssh","1.3.6.1.2.1.6.10.0"],["Conexiones salida tcp","1.3.6.1.2.1.6.11.0"],["SNMP","1.3.6.1.2.1.11.15.0"],["HTTP","1.3.6.1.2.1.4.9.0"],["FTP","iso.3.6.1.2.1.6.13.1.1.0.0.0.0.21.0.0.0.0.0"],["DNS","1.3.6.1.2.1.4.21.1.2.0.0.0.0"],["/iso/System/sysDescr","1.3.6.1.2.1.1.1.0"],["/iso/System/sysObjectID","1.3.6.1.2.1.1.2.0"],["/iso/System/sysContact","1.3.6.1.2.1.1.4.0"],["/iso/Interfaces/ifNumber","1.3.6.1.2.1.2.1.0"],["/iso/Interfaces/ifTable","1.3.6.1.2.1.2.2"],["/iso/SNMP/snmpInPkts","1.3.6.1.2.1.11.1.0"],["/iso/AT/atNetAddress","1.3.6.1.2.1.3.1.1.3"],["/iso/IP/ipDefaultTTL","1.3.6.1.2.1.4.2.0"],["/iso/IP/ipInReceives","1.3.6.1.2.1.4.3.0"],["/iso/IP/ipInHdrErrors","1.3.6.1.2.1.4.4.0"],["/iso/IP/ipInAddrErrors","1.3.6.1.2.1.4.5.0"],["/iso/IP/ipInUnknownProtos","1.3.6.1.2.1.4.7.0"],["/iso/IP/ipInDiscards","1.3.6.1.2.1.4.8.0"],["/iso/IP/ipInDelivers","1.3.6.1.2.1.4.9.0"],["/iso/IP/ipOutDiscards","1.3.6.1.2.1.4.11.0"],["/iso/ICMP/icmpInMsgs","1.3.6.1.2.1.5.1.0"],["/iso/ICMP/icmpInErrors","1.3.6.1.2.1.5.2.0"],["/iso/ICMP/icmpInDestUnreachs","1.3.6.1.2.1.5.3.0"],["/iso/ICMP/icmpInTimeExcds","1.3.6.1.2.1.5.4.0"],["/iso/ICMP/icmpOutMsgs","1.3.6.1.2.1.5.14.0"],["/iso/ICMP/icmpOutErrors","1.3.6.1.2.1.5.2.0"],["/iso/ICMP/icmpOutDestUnreachs","1.3.6.1.2.1.5.3.0"],["/iso/ICMP/icmpOutTimeExcds","1.3.6.1.2.1.5.4.0"],["/iso/TCP/tcpMaxConn","1.3.6.1.2.1.6.4.0"],["/iso/TCP/tcpActiveOpens","1.3.6.1.2.1.6.5.0"],["/iso/TCP/tcpPassiveOpens","1.3.6.1.2.1.6.6.0"],["/iso/TCP/tcpAttemptFails","1.3.6.1.2.1.6.7.0"],["/iso/TCP/tcpInSegs","1.3.6.1.2.1.6.10.0"],["/iso/TCP/tcpOutSegs","1.3.6.1.2.1.6.11.0"],["/iso/TCP/tcpInErrs","1.3.6.1.2.1.6.14.0"],["/iso/UDP/udpInDatagrams","1.3.6.1.2.1.7.1.0"],["/iso/UDP/udpNoPorts","1.3.6.1.2.1.7.2.0"],["/iso/UDP/udpInErrors","1.3.6.1.2.1.7.3.0"],["/iso/UDP/udpOutDatagrams","1.3.6.1.2.1.7.4.0"],["/iso/EGP/egpInMsgs","1.3.6.1.2.1.8.1.0"],["/iso/EGP/egpInErrors","1.3.6.1.2.1.8.2.0"],["/iso/EGP/egpOutMsgs","1.3.6.1.2.1.8.3.0"],["/iso/EGP/egpOutErrors","1.3.6.1.2.1.8.4.0"]]
		lista = [
			['Entrada ICMP', '1.3.6.1.2.1.5.1.0'],
			['Salida ICMP', '1.3.6.1.2.1.5.14.0'],
			['Entrada IP', '1.3.6.1.2.1.4.3.0'],
			['Salida IP', '1.3.6.1.2.1.4.10.0'],
			['Conexiones TCP', '1.3.6.1.2.1.6.9.0'],
			['Entrada TCP', '1.3.6.1.2.1.6.10.0'],
			['Salida TCP', '1.3.6.1.2.1.6.11.0'],
			['Entrada UDP', '1.3.6.1.2.1.7.1.0'],
			['Salida UDP', '1.3.6.1.2.1.7.4.0']
		]
		grupos = Gtk.ListStore(str, str)
		for x in lista:
			grupos.append( [x[1], x[0]] )
		self.ComboOid.AgregaCombo(grupos, self.on_name_combo_changed );
		self.vbox.pack_start(self.ComboOid.Box, False, False, 1)


		#Oid actual
		self.BoxOid = Caja();
		self.BoxOid.AgregaDescr("OID: ");
		self.BoxOid.AgregaEntry("iso.3.6.1.2.1.25.3.3.1.2.196608");
		self.BoxOid.AgregaBoton("Consulta", self.consulta)
		self.vbox.pack_start(self.BoxOid.Box, False, False, 1)

		#Graficar oid actual
		self.BoxGraficar = Caja();
		self.BoxGraficar.AgregaDescr("Nombre: ")
		self.BoxGraficar.AgregaEntry("")
		self.BoxGraficar.AgregaBoton("Agregar grafica", self.graficar);
		self.vbox.pack_start(self.BoxGraficar.Box, False, False, 1)

		self.BoxInfo = Caja();
		self.BoxInfo.AgregaEntry("");
		self.BoxInfo.AgregaBoton("Actualizar",self.update);
		self.vbox.pack_start(self.BoxInfo.Box, False, False, 0)

		self.CajalineaBaseReady = Caja();
		self.CajalineaBaseReady.AgregaDescr("Linea base ready: ")
		self.CajalineaBaseReady.AgregaEntry("0");
		self.CajalineaBaseReady.AgregaCheckButton();
		self.vbox.pack_start(self.CajalineaBaseReady.Box, False, False, 0)
		
		self.CajalineaBaseSet = Caja();
		self.CajalineaBaseSet.AgregaDescr("Linea base set: ")
		self.CajalineaBaseSet.AgregaEntry("0");
		self.vbox.pack_start(self.CajalineaBaseSet.Box, False, False, 0)
		
		self.CajalineaBaseGo = Caja();
		self.CajalineaBaseGo.AgregaDescr("Linea base go: ")
		self.CajalineaBaseGo.AgregaEntry("0");
		self.vbox.pack_start(self.CajalineaBaseGo.Box, False, False, 0)

		self.scrolled = Gtk.ScrolledWindow()
		self.scrolled.set_policy(Gtk.PolicyType.NEVER, Gtk.PolicyType.AUTOMATIC)
		self.add(self.scrolled)
		self.scrolled.add_with_viewport(self.vbox);
		
		d = threading.Thread(target=self.mainThread)
		d.setDaemon(True)
		d.start()
	
		self.show_all()

	
	def on_name_combo_changed(self, combo):
		tree_iter = combo.get_active_iter()
		if tree_iter is not None:
			model = combo.get_model()
			row_id, name = model[tree_iter][:2]
			self.BoxOid.SetEntryText(row_id);

	def consulta( self, button ):
		self.BoxInfo.SetEntryText(consultaSNMP( self.comunidad, self.ip, self.puerto, self.BoxOid.GetEntryText())) #Set default text

	def update(self, button):
		if setSNMP(self.comunidad, self.ip, self.puerto, self.BoxOid.GetEntryText(), self.BoxInfo.GetEntryText()) != self.BoxInfo.GetEntryText():
			self.BoxInfo.SetEntryText("Escritura fallida");

	def mainThread(self):
		while(1):
			try:
				for i in range(30):
					uno = threading.Thread(target = self.threadGrafica)
					uno.setDaemon(True)
					uno.start()
					uno.join()
					time.sleep(5)
				dos = threading.Thread(target = self.UpdateImagen)
				dos.setDaemon(True)
				dos.start()
				dos.join()
				for k, v in self.bases.items():
					self.CajasImagen[k].UpdateImagen( self.nombresImagenes[k] )
			except:
				print "Error en el mainThread: ", sys.exc_info()

	def UpdateImagen(self):
		for k, v in self.bases.items():
			if self.lineaBase[k]:
				self.bases[k].GraficarLineaBase( self.lineabaseParametro[k] )
			else:
				ret = self.bases[k].Graficar()
				try:
					ret = int(ret)
					if ret != self.estadosActuales[k]:
						self.estadosActuales[k] = ret
						if self.estadosActuales[k] == 1 and self.enviarMail:
							Mail().send( self.destinatarios, "Nueva falla encontrada en " + self.nombresImagenes[k], self.nombresImagenes[k] )
						elif self.enviarMail:
							Mail().send( self.destinatarios, "Fallas resueltas en " + self.nombresImagenes[k], self.nombresImagenes[k] )
					self.estadosActuales[k] = ret
				except:
					print "Error: " + str(ret), sys.exc_info()
			
	def threadGrafica(self):
			try:
				for k, v in self.bases.items():
					consulta = consultaSNMP( self.comunidad, self.ip, self.puerto, k )
					if consulta != -1:
						self.bases[k].InsertBase(consulta)
						consulta = int(consulta)
						if self.enviarMail and self.lineaBase[k]:
							if (consulta >= self.lineaBaseSet[k]) and (consulta < self.lineaBaseGo[k]) and (self.estadoLineaBase[k] == 0):
								self.bases[k].GraficarLineaBase( self.lineabaseParametro[k] )
								Mail().send( self.destinatarios, "Warning linea de base " + self.nombresImagenes[k], self.nombresImagenes[k] )
								self.estadoLineaBase[k] = 1;
							elif (consulta >= self.lineaBaseGo[k]) and (self.estadoLineaBase[k] <= 1):
								self.bases[k].GraficarLineaBase( self.lineabaseParametro[k] )
								Mail().send( self.destinatarios, "Falla linea de base " + self.nombresImagenes[k], self.nombresImagenes[k] )
								self.estadoLineaBase[k] = 2;
							elif (consulta <= self.lineaBaseReady[k]) and (self.estadoLineaBase[k]!=0):
								self.bases[k].GraficarLineaBase( self.lineabaseParametro[k] )
								Mail().send( self.destinatarios, "Falla resuelta " + self.nombresImagenes[k], self.nombresImagenes[k] )
								self.estadoLineaBase[k] = 0
			except:
				print "Error en el thread: ", sys.exc_info()

	def eliminarImagen(self, widget, oid):
		self.CajasImagen[oid].Hide()
		del self.CajasImagen[oid]
		del self.bases[oid]
		del self.nombresImagenes[oid]
		del self.estadosActuales[oid]

	def graficar( self, button ):
		nombre = self.BoxGraficar.GetEntryText()
		oid = self.BoxOid.GetEntryText()
		if  nombre == "":
			print "Nombre vacio no permitido"
			return
		if oid in self.bases:
			print "OID repetido no permitido" 
			return
		self.bases[oid] = RRD(nombre, self.ip+"-"+nombre)
		self.bases[oid].Graficar()
		self.nombresImagenes[oid] = carpeta+self.ip+"-"+nombre+".png"
		self.CajasImagen[ oid ] = Caja()
		self.CajasImagen[ oid ].AgregaBoton( "Eliminar grafica", self.eliminarImagen, oid )
		self.CajasImagen[ oid ].AgregaImagen( self.nombresImagenes[oid] )
		self.lineaBase[oid] = self.CajalineaBaseReady.Check.get_active()
		self.lineaBaseReady[oid] = int(self.CajalineaBaseReady.GetEntryText());
		self.lineaBaseSet[oid] = int(self.CajalineaBaseSet.GetEntryText());
		self.lineaBaseGo[oid] = int(self.CajalineaBaseGo.GetEntryText());
		self.lineabaseParametro[oid] = [self.lineaBaseReady[oid], self.lineaBaseSet[oid], self.lineaBaseGo[oid]]
		self.estadoLineaBase[oid] = 0
		self.estadosActuales[oid] = 0
		self.vbox.pack_start(self.CajasImagen[oid].Box, False, False, 0)