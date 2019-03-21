#!/usr/bin/env python

import rrdtool
import time
import random

class rrdBase():
	def __init__( self, lista, nombre = "Prac1" ):
		self.nombre = nombre
		self.lista = lista
		self.beginTime = int(time.time()) 
		self.CrearBase()


	def CrearBase(self):
		valor = []
		for x in self.lista:
			valor.append("DS:"+x[0]+":GAUGE:60:U:U")
		ret = rrdtool.create(self.nombre+".rrd",
						 "--start",'N',
						 "--step",'30s',
						 valor,
						 "RRA:AVERAGE:0.5:2:120",
						 "RRA:AVERAGE:0.5:1:120")
		if ret:
			print rrdtool.error()

	#tipo 0: agregar
	#tipo 1: eliminar
	def UpdateBase(self, tipo, nombre ):
		if tipo == 0:
			aux = "DS:"+nombre+":GAUGE:60:U:U"
			print aux
			ret = rrdtool.tune("Prac1.rrd",
								aux)
		if tipo == 1:
			ret = rrdtool.tune("Prac1.rrd",
								"DEL:"+nombre)
		rrdtool.dump(self.nombre+".rrd",self.nombre+".xml")
		if ret:
			print rrdtool.error()

	def InsertBase(self,  valor ):
		valor = "N:"+":".join(valor)
		#print valor
		rrdtool.update(self.nombre+".rrd", valor)
		rrdtool.dump(self.nombre+".rrd",self.nombre+".xml")

	def Graficar(self):
		defi = []
		lines = []
		r = lambda: random.randint(0,255)
		for x in self.lista:
			color = '#%02X%02X%02X' % (r(),r(),r())
			defi.append("DEF:"+x[0]+"="+self.nombre+".rrd"+":"+x[0]+":AVERAGE")
			lines.append("LINE1:"+x[0]+color+":"+x[2])

		ret = rrdtool.graph( "net.png",
						"--start",str(self.beginTime),
	#                   "--end","N",
						defi,
						lines)

	def Dump(self):
		rrdtool.dump(self.nombre+".rrd",self.nombre+".xml")
# lista = [("inOctets","1.3.6.1.2.1.2.2.1.10.1","In traffic"),("outOctets","1.3.6.1.2.1.2.2.1.16.1","Out traffic")]
# base = rrdBase(lista)
# base.CrearBase()
# base.InsertBase(["123","789"])
# base.Graficar()