import gi
import sys
gi.require_version('Gtk', '3.0')
from gi.repository import Gtk
from Caja import Caja
import datetime
import os
import re
import ftplib
import telnetlib
import time
from SNMP import consultaSNMP

carpetaConf = "ConfFiles";
carpetaInventario = "Inventario"

class Inventario(Gtk.Window):
	def __init__(self):
		Gtk.Window.__init__(self, title="Inventario")
		self.set_border_width(10)
		self.set_default_size(-1, 350)
		self.vbox = Gtk.Box(orientation=Gtk.Orientation.VERTICAL, spacing=6)
		self.add(self.vbox)
		self.vbox.show()

		self.Campos = {}
		self.Campos["IP"] = Caja()
		self.Campos["IP"].AgregaDescr("IP: " );
		self.Campos["IP"].AgregaEntry("localhost" );
		self.vbox.pack_start( self.Campos["IP"].Box, False, False, 1 )
		
		self.Campos["Port"] = Caja()
		self.Campos["Port"].AgregaDescr("Puerto: " );
		self.Campos["Port"].AgregaEntry("161" );
		self.vbox.pack_start( self.Campos["Port"].Box, False, False, 1 )
		
		self.Campos["Comunidad"] = Caja()
		self.Campos["Comunidad"].AgregaDescr("Comunidad: " );
		self.Campos["Comunidad"].AgregaEntry("comunidadSNMP" );
		self.vbox.pack_start( self.Campos["Comunidad"].Box, False, False, 1 )
		
		self.Campos["Inventario"] = Caja()
		self.Campos["Inventario"].AgregaBoton("Inventario", self.botonInventario );
		self.vbox.pack_start( self.Campos["Inventario"].Box, False, False, 1 )
		
		self.Campos["ConfFile"] = Caja()
		self.Campos["ConfFile"].AgregaBoton("Archivo Configuracion", self.botonConfFile );
		self.vbox.pack_start( self.Campos["ConfFile"].Box, False, False, 1 )
		
		self.Campos["Guardar"] = Caja()
		self.Campos["Guardar"].AgregaBoton("Guardar", self.botonGuardar );
		self.vbox.pack_start( self.Campos["Guardar"].Box, False, False, 1 )
		
		self.Campos["Texto"] = Caja()
		self.Campos["Texto"].AgregarTextView();
		self.vbox.pack_start( self.Campos["Texto"].Box, False, False, 1 )
		
		self.tipo = 1;
		
		self.show_all()
		
	def botonInventario(self, button):
		self.tipo = 1
		texto = "Sistema: " + consultaSNMP( self.Campos["Comunidad"].GetEntryText(), self.Campos["IP"].GetEntryText(),self.Campos["Port"].GetEntryText(), "1.3.6.1.2.1.1.1.0")
		texto += "\n\n"
		texto += "Contacto: " + consultaSNMP( self.Campos["Comunidad"].GetEntryText(), self.Campos["IP"].GetEntryText(),self.Campos["Port"].GetEntryText(), "iso.3.6.1.2.1.1.4.0")
		
		numInterfaces = consultaSNMP( self.Campos["Comunidad"].GetEntryText(), self.Campos["IP"].GetEntryText(),self.Campos["Port"].GetEntryText(), "iso.3.6.1.2.1.2.1.0")
		
		texto += "\n\nNo. de interfaces de red: " + str(numInterfaces)+"\n\n"
		
		for i in range( 1, int(numInterfaces)+1 ):
			texto += "Nombre: " + consultaSNMP( self.Campos["Comunidad"].GetEntryText(), self.Campos["IP"].GetEntryText(),self.Campos["Port"].GetEntryText(), "iso.3.6.1.2.1.2.2.1.2."+str(i))
			texto += "\n"
			v = consultaSNMP( self.Campos["Comunidad"].GetEntryText(), self.Campos["IP"].GetEntryText(),self.Campos["Port"].GetEntryText(), "iso.3.6.1.2.1.2.2.1.6."+str(i))
			s = ""
			if v != "":
				v = int(v, 16)
				s = '{0:016x}'.format(v)
				s = ':'.join(re.findall(r'\w\w', s))
			texto += "MAC: " + s
			texto += "\n\n";
			
		self.Campos["Texto"].SetTextViewText(texto)
		
	def botonConfFile(self, button):
		self.tipo = 0
		HOST = self.Campos["IP"].GetEntryText()
		tn = telnetlib.Telnet( HOST, 23 )
		#tn.set_debuglevel(1)
		tn.read_until("User: ")
		tn.write("rcp\n")
		tn.read_until("Password: ")
		tn.write("rcp\n")
		time.sleep(1)
		tn.write("en \r\n conf \r\n service ftp \r\n copy run start \r\n exit \r\n  exit \r \n")
		time.sleep(2)
		tn.close()
		
		ftp = ftplib.FTP(HOST)
		ftp.login("rcp", "rcp")
		self.getFile(ftp,"startup-config")
		ftp.quit()
		
		fh = open("startup-config", "r") 
		self.Campos["Texto"].SetTextViewText(fh.read())
		fh.close() 
		os.remove("startup-config")
 
	
	def getFile(self, ftp, filename):
		try:
			ftp.retrbinary("RETR " + filename ,open(filename, 'wb').write)
		except:
			print "Error descargando FTP"
		
	def botonGuardar(self, button):
		fecha = datetime.datetime.now().strftime("%Y-%m-%d-%H:%M")
		if self.tipo == 0:
			if not os.path.exists(carpetaConf):
				os.makedirs(carpetaConf)
			nombre = "ConfFiles/Conf-"+str(self.Campos["IP"].GetEntryText())+"-" + fecha + ".txt"
			file = open(nombre,"w+")
			file.write( self.Campos["Texto"].GetTextViewText() );
			file.close();
		else:
			if not os.path.exists(carpetaInventario):
				os.makedirs(carpetaInventario)
			nombre = "Inventario/Inv-"+str(self.Campos["IP"].GetEntryText())+"-" + fecha + ".txt"
			file = open(nombre,"w+")
			file.write( self.Campos["Texto"].GetTextViewText() );
			file.close();
