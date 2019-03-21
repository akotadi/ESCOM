import gi
import sys
import filecmp
from difflib import Differ
gi.require_version('Gtk', '3.0')
from gi.repository import Gtk
from Caja import Caja
from Mail import Mail

class Comparar(Gtk.Window):
	def __init__(self):
		Gtk.Window.__init__(self, title="Practica 3")
		self.set_border_width(10)

		self.vbox = Gtk.Box(orientation=Gtk.Orientation.VERTICAL, spacing=6)
		self.add(self.vbox)
		self.vbox.show()

		self.Campos = {}
		
		self.Campos["File1"] = Caja()
		self.Campos["File1"].AgregaDescr("Seleccionar archivo 1: " );
		self.Campos["File1"].AgregaBoton("Agregar 1", self.seleccionarArchivo, 1);
		self.Campos["File1"].AgregaEntry("");
		self.vbox.pack_start( self.Campos["File1"].Box, False, False, 1 )
		
		self.Campos["File2"] = Caja()
		self.Campos["File2"].AgregaDescr("Seleccionar archivo 2: " );
		self.Campos["File2"].AgregaBoton("Agregar 2", self.seleccionarArchivo, 2);
		self.Campos["File2"].AgregaEntry("");
		self.vbox.pack_start( self.Campos["File2"].Box, False, False, 1 )
		
		self.Campos["Email"] = Caja()
		self.Campos["Email"].AgregaDescr("Email: " );
		self.Campos["Email"].AgregaEntry("" );
		self.vbox.pack_start( self.Campos["Email"].Box, False, False, 1 )
		
		self.Campos["Submit"] = Caja()
		self.Campos["Submit"].AgregaBoton("Comparar", self.compararArchivos );
		self.vbox.pack_start( self.Campos["Submit"].Box, False, False, 1 )
		
		self.Campos["Texto"] = Caja()
		self.Campos["Texto"].AgregarTextView();
		self.vbox.pack_start( self.Campos["Texto"].Box, False, False, 1 )
		
		self.show_all()
		
	def seleccionarArchivo(self, button, num):
		dialog = Gtk.FileChooserDialog("Seleccione un archivo", self, Gtk.FileChooserAction.OPEN, (Gtk.STOCK_CANCEL, Gtk.ResponseType.CANCEL, Gtk.STOCK_OPEN, Gtk.ResponseType.OK))
		self.add_filters(dialog)
		response = dialog.run()
		if response == Gtk.ResponseType.OK:
			if num == 1:
				self.Campos["File1"].SetEntryText(dialog.get_filename());
			else:
				self.Campos["File2"].SetEntryText(dialog.get_filename());
			#print("File selected: " + dialog.get_filename())
		elif response == Gtk.ResponseType.CANCEL:
			print("Cancel clicked")
		dialog.destroy()
		
	def add_filters(self, dialog):
		filter_any = Gtk.FileFilter()
		filter_any.set_name("Any files")
		filter_any.add_pattern("*")
		dialog.add_filter(filter_any)
	
	def compararArchivos( self, button ):
		archivo1 = self.Campos["File1"].GetEntryText()
		archivo2 = self.Campos["File2"].GetEntryText()
		with open(archivo1) as f1:
			f1_text = f1.read()
		with open(archivo2) as f2:
			f2_text = f2.read()
		comparacion = filecmp.cmp(archivo1, archivo2) 
		if comparacion:
			self.Campos["Texto"].SetTextViewText("No hay diferencias")
			return
		d = Differ()
		result = list(d.compare(f1_text.splitlines(1), f2_text.splitlines(1)))
		self.Campos["Texto"].SetTextViewText(''.join(result))
		mail = self.Campos["Email"].GetEntryText()
		if mail != "":
			Mail().send( mail, "Diferencia entre archivos", "Diferencia entre los archivos " + archivo1 + " y " + archivo2 + "\n\n" + ''.join(result) )
			
