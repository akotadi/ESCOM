import gi
import sys
gi.require_version('Gtk', '3.0')
from gi.repository import Gtk

class Caja():
	def __init__(self):
		self.Combo = Gtk.ComboBox()
		self.Descr = Gtk.Label()
		self.Box = Gtk.HBox(False, 0)
		self.Box.show()

	def AgregaCombo(self, opciones, funcion):
		self.Combo = Gtk.ComboBox.new_with_model(opciones)
		self.Combo.connect("changed", funcion)
		self.Combo.set_entry_text_column(1)
		renderer_text = Gtk.CellRendererText()
		self.Combo.pack_start(renderer_text, True)
		self.Combo.add_attribute(renderer_text, "text", 1)
		self.Box.pack_start( self.Combo, False, False, 1 );
		self.Combo.show()
		self.Combo.set_active(0)

	def AgregaDescr(self, Texto ):
		self.Descr = Gtk.Label(Texto)
		self.Descr.show()

	def UpdateDescr(self, Texto):
		self.Descr.hide()
		self.Descr.destroy()
		self.Descr = Gtk.Label(Texto)
		self.Box.pack_start( self.Descr, False, False, 1 );
		self.Descr.show()

	def Hide(self):
		self.Combo.hide()
		self.Combo.destroy()
		self.Descr.hide()
		self.Descr.destroy()
		self.Box.hide()
		self.Box.destroy()