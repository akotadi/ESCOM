import gi
import sys
gi.require_version('Gtk', '3.0')
from gi.repository import Gtk
from Caja import Caja
from getSNMP import consultaSNMP
from setSNMP import setSNMP
from rrdCreate import rrdBase
import thread
import time

class ComboBoxWindow(Gtk.Window):
	def __init__(self, arbol):
		Gtk.Window.__init__(self, title="Practica 1")
		self.pilaBoxes = []
		self.raiz = arbol
		self.set_border_width(10)
		self.boxInfo = None
		self.info = None
		self.Base = rrdBase(["time","Default","iso"])
		self.listaGrafica = []

		self.vbox = Gtk.Box(orientation=Gtk.Orientation.VERTICAL, spacing=6)
		self.add(self.vbox)
		self.vbox.show()

		self.image = Gtk.Image()
		self.image.set_from_file("net.png")
		self.image.show()
		self.vbox.pack_start(self.image, True, True, 1)

		#Entrada de la IP
		self.BoxIp = Gtk.HBox(False, 0)
		self.LabelIP = Gtk.Label("Ip host: ")
		self.BoxIp.pack_start( self.LabelIP, False, False, 1 );
		self.EntryIP = Gtk.Entry()
		self.EntryIP.set_text("localhost") #Set default text
		self.EntryIP.set_max_length(50)
		self.BoxIp.pack_start(self.EntryIP, True, True, 0)
		self.EntryIP.show()
		self.LabelIP.show()
		self.vbox.pack_start(self.BoxIp, False, False, 1)

		#Entrada comunidad SNMP
		self.BoxCom = Gtk.HBox(False, 0)
		self.LabelCom = Gtk.Label("Comunidad: ")
		self.BoxCom.pack_start( self.LabelCom, False, False, 1 );
		self.EntryCom = Gtk.Entry()
		self.EntryCom.set_text("comunidadSNMP") #Set default text
		self.EntryCom.set_max_length(50)
		self.BoxCom.pack_start(self.EntryCom, True, True, 0)
		self.EntryCom.show()
		self.LabelCom.show()
		self.vbox.pack_start(self.BoxCom, False, False, 1)
 
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
		self.buttonOid = Gtk.Button()
		self.buttonOid.connect("clicked", self.consulta )
		self.buttonOid.set_label("Consulta")
		self.BoxOid.pack_start(self.buttonOid, True, True, 0)
		self.vbox.pack_start(self.BoxOid, False, False, 1)

		#Graficar oid actual
		self.BoxGraficar = Gtk.HBox(False, 0)
		self.LabelGraficar = Gtk.Label("Nombre: ")
		self.BoxGraficar.pack_start( self.LabelGraficar, False, False, 1 );
		self.NombreOid = Gtk.Entry()
		self.NombreOid.set_max_length(50)
		self.BoxGraficar.pack_start(self.NombreOid, True, True, 0)
		self.NombreOid.show()
		self.LabelGraficar.show()

		self.LabelGraficarDesc = Gtk.Label("Desc: ")
		self.BoxGraficar.pack_start( self.LabelGraficarDesc, False, False, 1 );
		self.DescOid = Gtk.Entry()
		self.DescOid.set_max_length(50)
		self.BoxGraficar.pack_start(self.DescOid, True, True, 0)
		self.DescOid.show()
		self.LabelGraficarDesc.show()

		self.buttonAddOid = Gtk.Button()
		self.buttonAddOid.connect("clicked", self.graficaExterna)
		self.buttonAddOid.set_label("Agregar")
		self.BoxGraficar.pack_start(self.buttonAddOid, True, True, 0)

		self.buttonRemoveOid = Gtk.Button()
		self.buttonRemoveOid.connect("clicked", self.graficaExterna )
		self.buttonRemoveOid.set_label("Eliminar")
		self.BoxGraficar.pack_start(self.buttonRemoveOid, True, True, 0)

		self.vbox.pack_start(self.BoxGraficar, False, False, 1)

		thread.start_new_thread(self.threadGrafica,())
		thread.start_new_thread(self.threadSetImagen,())

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

				self.boxInfo = Gtk.HBox(False, 0);
				self.info = Gtk.Entry()
				self.info.set_text(consultaSNMP(self.EntryCom.get_text(),self.EntryIP.get_text(), row_id)) #Set default text
				self.info.set_max_length(500)
				self.boxInfo.pack_start( self.info, True, True, 0 );
				if( (int)(getNodo.tipo) == 1 ):
					self.checkInfo = Gtk.CheckButton()
					opc = getNodo.activo
					if opc == True:
						self.checkInfo.set_active(True)
					else:
						self.checkInfo.set_active(False)
					self.checkInfo.connect("toggled", self.Toggled, row_id)
					self.boxInfo.pack_start( self.checkInfo, True, True, 0 );
					self.checkInfo.show()

				# Create a new button
				self.buttonUpdate = Gtk.Button()
				# Connect the "clicked" signal of the button to our callback
				self.buttonUpdate.connect("clicked", self.update,self.EntryCom.get_text(),self.EntryIP.get_text(), row_id )
				self.buttonUpdate.set_label("Actualizar")
				self.boxInfo.pack_start( self.buttonUpdate, True, True, 0 );

				self.vbox.pack_start(self.boxInfo, False, False, 1)

				self.buttonUpdate.show()
				self.info.show()
				self.boxInfo.show()

	def threadSetImagen(self):
		while(1):
			try:
				self.image.set_from_file("net.png")
				time.sleep(30)
			except:
				print "Error en el thread threadSetImagen: " + sys.exc_info()

	def threadGrafica(self):
		while(1):
			try:
				if self.listaGrafica:
					insert = []
					for item in self.listaGrafica:
						insert.append(consultaSNMP( self.EntryCom.get_text(),self.EntryIP.get_text(), item[1] ))
					self.Base.InsertBase(insert)
					self.Base.Graficar()
					self.Base.Dump()
				time.sleep(1)
			except:
				print "Error en el thread: " + sys.exc_info()

	def updateGrafica(self):
		if len(self.listaGrafica) > 0:
			self.Base = rrdBase( self.listaGrafica )

	def graficaExterna( self, button ):
		if button.get_label() == "Agregar":
			self.modificarGrafica(True, self.TextOid.get_text(), self.NombreOid.get_text(), self.DescOid.get_text())
		else:
			self.modificarGrafica(False, self.TextOid.get_text(), self.NombreOid.get_text())

	def modificarGrafica( self, tipo, oid, nombre="", desc = "" ):
		if tipo == True:
			res = [item for item in self.listaGrafica if item[1] == oid or item[0] == nombre]
			if res: return
			self.listaGrafica.append( tuple([nombre, oid, desc]) )
			self.updateGrafica()
		else:
			res = [item for item in self.listaGrafica if item[1] == oid or item[0] == nombre]
			if res:
				self.listaGrafica.remove( res[0] )
				self.updateGrafica()


	def Toggled(self, widget, oid):
		getNodo, nivel = self.raiz.BuscarInOrden( oid )
		getNodo.activo = 1 - int( getNodo.activo )
		if getNodo.activo == True:
			self.modificarGrafica( 1, getNodo.oid, getNodo.nombre, getNodo.Descripcion )
		else:
			self.modificarGrafica( 0, getNodo.oid)