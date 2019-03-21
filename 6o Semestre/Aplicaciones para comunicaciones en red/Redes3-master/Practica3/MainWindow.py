import gi
import sys
gi.require_version('Gtk', '3.0')
from gi.repository import Gtk
from Caja import Caja
from Inventario import Inventario
from Comparar import Comparar
from EnviarConf import EnviarConf
from Borrar import Borrar

class MainWindow(Gtk.Window):
	def __init__(self):
		Gtk.Window.__init__(self, title="Practica 3")
		self.set_border_width(10)
		self.ventanas = []

		self.vbox = Gtk.Box(orientation=Gtk.Orientation.VERTICAL, spacing=6)
		self.add(self.vbox)
		self.vbox.show()

		self.Campos = {}
		self.Campos["Inventario"] = Caja()
		self.Campos["Inventario"].AgregaBoton("Inventario", self.showInventario);
		self.vbox.pack_start( self.Campos["Inventario"].Box, False, False, 1 )

		self.Campos["Borrar"] = Caja()
		self.Campos["Borrar"].AgregaBoton("Borrar", self.showBorrar);
		self.vbox.pack_start( self.Campos["Borrar"].Box, False, False, 1 )

		self.Campos["Comparar"] = Caja()
		self.Campos["Comparar"].AgregaBoton("Comparar", self.showComparar);
		self.vbox.pack_start( self.Campos["Comparar"].Box, False, False, 1 )

		self.Campos["Enviar"] = Caja()
		self.Campos["Enviar"].AgregaBoton("Enviar", self.showEnviar);
		self.vbox.pack_start( self.Campos["Enviar"].Box, False, False, 1 )
		
		self.show_all()

	def showInventario( self, button ):
		self.inventario = Inventario()
		self.inventario.show()
	
	def showBorrar( self, button ):
		self.borrar = Borrar()
		self.borrar.show()
	
	def showComparar( self, button ):
		self.comparar = Comparar()
		self.comparar.show()
	
	def showEnviar( self, button ):
		self.enviarConf = EnviarConf()
		self.enviarConf.show()
		
