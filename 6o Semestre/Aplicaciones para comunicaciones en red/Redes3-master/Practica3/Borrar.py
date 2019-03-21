import gi
import sys
import os
from datetime import datetime
gi.require_version('Gtk', '3.0')
from gi.repository import Gtk
from Caja import Caja

class Borrar(Gtk.Window):
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
		
		self.Campos["Fecha"] = Caja()
		self.Campos["Fecha"].AgregaDescr("Fecha (DD-MM-AAAA HH:MM): " );
		self.Campos["Fecha"].AgregaEntry("" );
		self.vbox.pack_start( self.Campos["Fecha"].Box, False, False, 1 )
		
		self.Campos["Check"] = Caja()
		self.Campos["Check"].AgregaDescr("Inventario/Configuracion (checked/not checked): " );
		self.Campos["Check"].AgregaCheckButton( );
		self.vbox.pack_start( self.Campos["Check"].Box, False, False, 1 )
		
		self.Campos["Submit"] = Caja()
		self.Campos["Submit"].AgregaBoton("Borrar", self.enviarArchivo );
		self.vbox.pack_start( self.Campos["Submit"].Box, False, False, 1 )
		
		self.show_all()
		
	def enviarArchivo( self, button ):
		checked = self.Campos["Check"].Check.get_active()
		ip = self.Campos["IP"].GetEntryText()
		fecha = self.Campos["Fecha"].GetEntryText()
		
		carpeta = "ConfFiles"
		if checked:
			carpeta = "Inventario"
		carpeta = "./"+carpeta
		carpeta = os.path.abspath(carpeta)
		files = [f for f in os.listdir(carpeta)]
		
		tmp = fecha.split(" ")
		date = tmp[0].split("-")
		time = tmp[1].split(":")
		orig = datetime( int(date[2]), int(date[1]), int(date[0]), int( time[0] ), int(time[1]) )
		
		for archivo in files:
			token = archivo.split('-')
			if ip != token[1]:
				continue
			tmp = (token[5].split(".")[0]).split(":")
			comparar = datetime( int( token[2] ), int(token[3]), int(token[4]), int(tmp[0]), int(tmp[1]) )
			archivo = os.path.abspath(carpeta+"/"+archivo)
			if comparar < orig:
				print "Eliminado: " + archivo
				os.remove(archivo)
