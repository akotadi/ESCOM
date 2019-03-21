import gi
import sys
gi.require_version('Gtk', '3.0')
from gi.repository import Gtk

class Caja():
	def __init__(self):
		self.Combo = Gtk.ComboBox()
		self.Descr = Gtk.Label()
		self.Box = Gtk.HBox(False, 0)
		self.Entry = Gtk.Entry()
		self.Boton = Gtk.Button()
		self.Imagen = Gtk.Image()
		self.Check = Gtk.CheckButton()
		self.Box.show()

	def AgregaCombo(self, opciones, funcion):
		self.Combo = Gtk.ComboBox.new_with_model(opciones)
		self.Combo.connect("changed", funcion)
		self.Combo.set_entry_text_column(1)
		renderer_text = Gtk.CellRendererText()
		self.Combo.pack_start(renderer_text, True)
		self.Combo.add_attribute(renderer_text, "text", 1)
		self.Box.pack_start( self.Combo, True, True, 0 );
		self.Combo.show()
		#self.Combo.set_active(0)

	def AgregaDescr(self, Texto ):
		self.Descr = Gtk.Label(Texto)
		self.Box.pack_start( self.Descr, True, True, 0 );
		self.Descr.show()

	def UpdateDescr(self, Texto):
		self.Descr.hide()
		self.Descr.destroy()
		self.Descr = Gtk.Label(Texto)
		self.Box.pack_start( self.Descr, True, True, 0 );
		self.Descr.show()

	def AgregaEntry(self, Texto):
		self.Entry = Gtk.Entry()
		self.Entry.set_text(Texto) #Set default text
		self.Entry.set_max_length(1000)
		self.Box.pack_start(self.Entry, True, True, 0)
		self.Entry.show()

	def GetEntryText(self):
		return self.Entry.get_text()

	def SetEntryText(self, Texto):
		return self.Entry.set_text(Texto)

	def AgregaBoton(self, Texto, funcion, parametros = None):
		self.Boton = Gtk.Button()
		if parametros:
			self.Boton.connect("clicked", funcion, parametros)
		else:
			self.Boton.connect("clicked", funcion)
		self.Boton.set_label(Texto)
		self.Boton.show()
		self.Box.pack_start(self.Boton, False, False, 1)

	def AgregaImagen(self, ruta):
		self.Imagen.set_from_file(ruta)
		self.Imagen.show()
		self.Box.pack_start(self.Imagen, True, True, 0)

	def UpdateImagen(self, ruta):
		self.Imagen.set_from_file(ruta)

	def AgregaCheckButton(self):
		self.Check.show()
		self.Box.pack_start(self.Check, False, False, 1)


	def Hide(self):
		self.Combo.hide()
		self.Combo.destroy()
		self.Descr.hide()
		self.Descr.destroy()
		self.Entry.hide()
		self.Entry.destroy()
		self.Boton.hide()
		self.Boton.destroy()
		self.Check.hide()
		self.Check.destroy()
		self.Imagen.hide()
		self.Imagen.destroy()
		self.Box.hide()
		self.Box.destroy()