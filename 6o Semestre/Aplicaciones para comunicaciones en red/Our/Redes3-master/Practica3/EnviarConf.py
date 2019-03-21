import gi
import sys
import ftplib
import os
import telnetlib
import time
gi.require_version('Gtk', '3.0')
from gi.repository import Gtk
from Caja import Caja

class EnviarConf(Gtk.Window):
	def __init__(self):
		Gtk.Window.__init__(self, title="Practica 3")
		self.set_border_width(10)

		self.vbox = Gtk.Box(orientation=Gtk.Orientation.VERTICAL, spacing=6)
		self.add(self.vbox)
		self.vbox.show()

		self.Campos = {}
		
		self.Campos["IP"] = Caja()
		self.Campos["IP"].AgregaDescr("IP: " );
		self.Campos["IP"].AgregaEntry("" );
		self.vbox.pack_start( self.Campos["IP"].Box, False, False, 1 )
		
		self.Campos["File"] = Caja()
		self.Campos["File"].AgregaDescr("Seleccionar archivo: " );
		self.Campos["File"].AgregaBoton("Agregar", self.seleccionarArchivo);
		self.Campos["File"].AgregaEntry("");
		self.vbox.pack_start( self.Campos["File"].Box, False, False, 1 )
		
		self.Campos["Submit"] = Caja()
		self.Campos["Submit"].AgregaBoton("Enviar", self.enviarArchivo );
		self.vbox.pack_start( self.Campos["Submit"].Box, False, False, 1 )
		
		self.show_all()
		
	def seleccionarArchivo(self, button):
		dialog = Gtk.FileChooserDialog("Seleccione un archivo", self, Gtk.FileChooserAction.OPEN, (Gtk.STOCK_CANCEL, Gtk.ResponseType.CANCEL, Gtk.STOCK_OPEN, Gtk.ResponseType.OK))
		self.add_filters(dialog)
		response = dialog.run()
		if response == Gtk.ResponseType.OK:
				self.Campos["File"].SetEntryText(dialog.get_filename());
			#print("File selected: " + dialog.get_filename())
		elif response == Gtk.ResponseType.CANCEL:
			print("Cancel clicked")
		dialog.destroy()
		
	def add_filters(self, dialog):
		filter_any = Gtk.FileFilter()
		filter_any.set_name("Any files")
		filter_any.add_pattern("*")
		dialog.add_filter(filter_any)
	
	def enviarArchivo( self, button ):
		ip = self.Campos["IP"].GetEntryText()
		archivo = self.Campos["File"].GetEntryText()
		try:
			ftp = ftplib.FTP(ip)
			ftp.login("rcp", "rcp")
			self.upload(ftp,archivo)
			ftp.quit()
			
			tn = telnetlib.Telnet( ip, 23 )
			#tn.set_debuglevel(1)
			tn.read_until("User: ")
			tn.write("rcp\n")
			tn.read_until("Password: ")
			tn.write("rcp\n")
			time.sleep(1)
			tn.write("en \r\n conf \r\n service ftp \r\n copy running-config startup-config \r\n copy recibido startup-config \r\n exit \r\n  exit \r \n")
			time.sleep(2)
			tn.close()
		except:
			print "Error"
			print sys.exc_info()
		
	def upload(self, ftp, file):
		ext = os.path.splitext(file)[1]
		print ext
		try:
			#if ext in (".txt", ".htm", ".html"):
			#	ftp.storlines("STOR recibido", open(file))
			#else:
				ftp.storbinary("STOR recibido", open(file, "rb"), 1024)
		except:
			print "Error"
