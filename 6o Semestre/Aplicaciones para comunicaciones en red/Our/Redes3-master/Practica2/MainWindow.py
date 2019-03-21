import gi
import sys
gi.require_version('Gtk', '3.0')
from gi.repository import Gtk
from Caja import Caja
from AnalysisWindow import AnalysisWindow
import thread
import time

class MainWindow(Gtk.Window):
	def __init__(self):
		Gtk.Window.__init__(self, title="Observium 2.0")
		self.set_border_width(10)
		self.ventanas = []

		self.vbox = Gtk.Box(orientation=Gtk.Orientation.VERTICAL, spacing=6)
		self.add(self.vbox)
		self.vbox.show()

		self.Campos = {}
		self.Campos["ip"] = Caja()
		self.Campos["ip"].AgregaDescr("Ip host: ");
		self.Campos["ip"].AgregaEntry("localhost");
		self.vbox.pack_start( self.Campos["ip"].Box, False, False, 1 )

		self.Campos["comunidad"] = Caja()
		self.Campos["comunidad"].AgregaDescr("Comunidad: ");
		self.Campos["comunidad"].AgregaEntry("public");
		self.vbox.pack_start( self.Campos["comunidad"].Box, False, False, 1 )

		self.Campos["puerto"] = Caja()
		self.Campos["puerto"].AgregaDescr("Puerto: ");
		self.Campos["puerto"].AgregaEntry("161");
		self.vbox.pack_start( self.Campos["puerto"].Box, False, False, 1 )

		self.Campos["email"] = Caja()
		self.Campos["email"].AgregaDescr("Email: ");
		self.Campos["email"].AgregaEntry("jmch.7795@gmail.com");
		self.Campos["email"].AgregaCheckButton();
		self.vbox.pack_start( self.Campos["email"].Box, False, False, 1 )

		self.Campos["boton"] = Caja()
		self.Campos["boton"].AgregaBoton("Analizar", self.nuevoAnalisis);
		self.vbox.pack_start( self.Campos["boton"].Box, False, False, 1 )	
		
		self.show_all()

	def nuevoAnalisis(self, button):
		self.ventanas.append( AnalysisWindow(self.Campos["ip"].GetEntryText(), self.Campos["comunidad"].GetEntryText(), self.Campos["puerto"].GetEntryText(), self.Campos["email"].GetEntryText().split(";"), self.Campos["email"].Check.get_active()) );
		
		