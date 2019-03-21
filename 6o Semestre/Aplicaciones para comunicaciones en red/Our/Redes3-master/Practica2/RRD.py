#!/usr/bin/env python

import rrdtool
import time
import random
import os

carpeta = "RRD/"

class RRD():
	def __init__( self, parametro, nombre = "Prac1" ):
		if not os.path.exists(carpeta):
   			os.makedirs(carpeta)
		self.nombre = carpeta+nombre
		self.parametro = parametro
		self.beginTime = int(time.time()) 
		self.CrearBase()
		r = lambda: random.randint(0,255)
		self.color = '#%02X%02X%02X' % (r(),r(),r())

	def CrearBase(self):
		valor = []
		valor.append("DS:"+self.parametro+":GAUGE:40:U:U")
		ret = rrdtool.create(self.nombre+".rrd",
						"--start",'N',
						"--step",'40s',
						valor,
						"RRA:AVERAGE:0.5:2:120",
						"RRA:HWPREDICT:20:0.1:0.0035:5:3",
						"RRA:SEASONAL:20:0.1:2",
						"RRA:DEVSEASONAL:20:0.1:2",
						"RRA:DEVPREDICT:20:4",
						"RRA:FAILURES:20:3:28:4")
		if ret:
			print rrdtool.error()

	def InsertBase(self, valor ):
		valor = "N:"+valor
		#print valor
		rrdtool.update(self.nombre+".rrd", valor)

	def Graficar(self):
		info = rrdtool.info(self.nombre+".rrd")
		rrdstep = int(info['step'])
		lastupdate = info['last_update']
		previosupdate = str(lastupdate - rrdstep - 1)

		ret = rrdtool.graph( self.nombre+".png",
						"--start",str(self.beginTime),
						"-t",self.parametro,
						'--slope-mode',
						"DEF:valor="+ self.nombre+".rrd" + ":" +self.parametro+":AVERAGE",
						"DEF:pred="	+ self.nombre+".rrd" + ":" + self.parametro + ":HWPREDICT",
						"DEF:dev="	+ self.nombre+".rrd" + ":" + self.parametro + ":DEVPREDICT",
						"DEF:fail="	+ self.nombre+".rrd" + ":" + self.parametro + ":FAILURES",
						'PRINT:fail:LAST:%1.0lf',
						"GPRINT:pred:LAST:\"%6.2lf%sW\"",
						"GPRINT:valor:LAST:\"%6.2lf%sW\"",
						"GPRINT:dev:LAST:\"%6.2lf%sW\"",
						"CDEF:upper=pred,dev,2,*,+",
						"CDEF:lower=pred,dev,2,*,-",

						"TICK:fail#FFFFFF:1.0:",
						"LINE3:valor"+self.color+":Actual",
						"LINE1:pred#FFFFFF:",
						"LINE1:upper#FFFFFF:",
						"LINE1:lower#FFFFFF:"
						)
		return ret[2][0]

	def GraficarLineaBase(self, listaLineaBase):
		ret = rrdtool.graph( self.nombre+".png",
						"--start",str(self.beginTime),
						"-t",self.parametro,
						'--slope-mode',
						"DEF:valor="+ self.nombre+".rrd" + ":" +self.parametro+":AVERAGE",
						"GPRINT:pred:LAST:\"%6.2lf%sW\"",
						"GPRINT:valor:LAST:\"%6.2lf%sW\"",
						"GPRINT:dev:LAST:\"%6.2lf%sW\"",

						"LINE3:valor"+self.color+":Actual",
						"LINE2:"+str(listaLineaBase[0])+"#14B229:Ready",
						"LINE2:"+str(listaLineaBase[1])+"#E5CB1F:Set",
						"LINE2:"+str(listaLineaBase[2])+"#ff0000:Go")


	def Dump(self):
		rrdtool.dump(self.nombre+".rrd",self.nombre+".xml")