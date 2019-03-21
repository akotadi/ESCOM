import gi
import sys
gi.require_version('Gtk', '3.0')
from gi.repository import Gtk
from Caja import Caja

class ComboBoxWindow(Gtk.Window):
	def __init__(self, arbol):
		Gtk.Window.__init__(self, title="Obtener OID")
		self.pilaBoxes = []
		self.raiz = arbol
		self.set_border_width(10)
		self.boxInfo = None
		self.info = None				

		self.vbox = Gtk.Box(orientation=Gtk.Orientation.VERTICAL, spacing=6)
		self.add(self.vbox)
		self.vbox.show()							

		#Oid actual
		self.BoxOid = Gtk.HBox(False, 0)
		self.LabelOid = Gtk.Label("OID: ")
		self.BoxOid.pack_start( self.LabelOid, False, False, 1 );
		self.TextOid = Gtk.Entry()
		self.TextOid.set_text("comunidadSNMP") #Set default text
		self.TextOid.set_max_length(50)
		self.BoxOid.pack_start(self.TextOid, True, True, 0)
		self.TextOid.show()
		self.LabelOid.show()		
		self.vbox.pack_start(self.BoxOid, False, False, 1)		

		self.Agregar(arbol)		
		self.show_all()

	def Agregar(self, nodo):
		grupos = Gtk.ListStore(str, str)
		for x in nodo.hijos:
			grupos.append( [x.oid, x.nombre] )
		
		self.pilaBoxes.append( Caja() )
		self.vbox.pack_start(self.pilaBoxes[-1].Box, False, False, 1)
		self.pilaBoxes[-1].AgregaCombo(grupos, self.on_name_combo_changed)
		
	def get_active_text(self, combobox):
		model = combobox.get_model()
		active = combobox.get_active()
		if active < 0:
			return None
		return model[active][1]

	def consulta( self, button ):
		if self.info:
			self.info.set_text(consultaSNMP(self.EntryCom.get_text(),self.EntryIP.get_text(), self.TextOid.get_text())) #Set default text

	def update(self, button, comunidad, ip, oid):
		print setSNMP(comunidad, ip, oid, self.info.get_text())

	def on_name_combo_changed(self, combo):
		tree_iter = combo.get_active_iter()
		if tree_iter is not None:
			model = combo.get_model()
			row_id, name = model[tree_iter][:2]
			while name != self.get_active_text(self.pilaBoxes[-1].Combo):
				self.pilaBoxes[-1].Hide()
				self.pilaBoxes.pop()
			
			getNodo, nivel = self.raiz.BuscarInOrden( row_id )
			self.pilaBoxes[-1].UpdateDescr( getNodo.Descripcion )
			if getNodo.hijos:
				self.Agregar(getNodo)
			else:
				if self.boxInfo:
					self.boxInfo.hide()
					self.boxInfo.destroy()
					self.info.hide()
					self.info.destroy()

				self.TextOid.set_text(row_id)
							
				if( (int)(getNodo.tipo) == 1 ):
					self.checkInfo = Gtk.CheckButton()
					opc = getNodo.activo
					if opc == True:
						self.checkInfo.set_active(True)
					else:
						self.checkInfo.set_active(False)
					self.checkInfo.connect("toggled", self.Toggled, row_id)	
					self.checkInfo.show()					


	def Toggled(self, widget, oid):
		getNodo, nivel = self.raiz.BuscarInOrden( oid )
		getNodo.activo = 1 - int( getNodo.activo )
		if getNodo.activo == True:
			self.modificarGrafica( 1, getNodo.oid, getNodo.nombre, getNodo.Descripcion )
		else:
			self.modificarGrafica( 0, getNodo.oid)