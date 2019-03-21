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
						"--step",'20s',
						valor,
						"RRA:AVERAGE:0.5:2:120",
						"RRA:HWPREDICT:20:0.1:0.0035:5:3",
						"RRA:SEASONAL:20:0.1:2",
						"RRA:DEVSEASONAL:20:0.1:2",
						"RRA:DEVPREDICT:20:4",
						"RRA:FAILURES:20:3:28:4")
		if ret:
			print (rrdtool.error())

	def InsertBase(self, valor ):
		valor = "N:"+valor
		#print (valor)
		rrdtool.update(self.nombre+".rrd", valor)

	def Graficar(self, listaLineaBase):
		info = rrdtool.info(self.nombre+".rrd")
		rrdstep = int(info['step'])
		lastupdate = info['last_update']
		previosupdate = str(lastupdate - rrdstep - 1)

		ret = rrdtool.graph( self.nombre+".png",
						"--start",str(self.beginTime),
						# "-t",self.parametro,
						'--slope-mode',

	                    # Image settings
	                    '--title', self.parametro,
	                    # '--interlace', 
	                    '--width=1240', '--height=400',
	                    "--color","ARROW#009900",
	                    '--vertical-label', self.parametro + " used (%)",
	                    '--lower-limit', '0',
	                    '--upper-limit', '100',
	                    '--border','0',
	                    '--rigid',

	                    "DEF:valor="+ self.nombre+".rrd" + ":" +self.parametro+":AVERAGE",
						"DEF:pred="	+ self.nombre+".rrd" + ":" + self.parametro + ":HWPREDICT",
						"DEF:dev="	+ self.nombre+".rrd" + ":" + self.parametro + ":DEVPREDICT",
						"DEF:fail="	+ self.nombre+".rrd" + ":" + self.parametro + ":FAILURES",
						'PRINT:fail:LAST:%1.0lf',
						# "GPRINT:pred:LAST:\"%6.2lf%sW\"",
						# "GPRINT:valor:LAST:\"%6.2lf%sW\"",
						# "GPRINT:dev:LAST:\"%6.2lf%sW\"",
						"CDEF:upper=pred,dev,2,*,+",
						"CDEF:lower=pred,dev,2,*,-",
						"TICK:fail#FDD017:1.0:Fallas",

	                    # Load data
	                    "DEF:load=" + self.nombre + ".rrd:" + self.parametro + ":AVERAGE",
	                    "DEF:load" + self.parametro + "=" + self.nombre + ".rrd:" + self.parametro + ":AVERAGE",
	                    "DEF:load" + self.parametro + "date=" + self.nombre + ".rrd:" + self.parametro + ":AVERAGE:start=" + str(self.beginTime),

	                    # Data output
	                    "LINE1:" + str(listaLineaBase[2] - 10) + "",
	                    "AREA:5#FF000022::STACK",
	                    "AREA:5#FF000044::STACK",

	                    "COMMENT: \\t\\t\\tNow \\t\\tMin \\t\\tMax \\t\\tAvg\\t\\tLast\\n",
	                    "AREA:load" + self.parametro + "#00FF00:CPU Used",
	                    "GPRINT:load" + self.parametro + ":LAST:%12.0lf%s",
	                    "GPRINT:load" + self.parametro + ":MIN:%10.0lf%s",
	                    "GPRINT:load" + self.parametro + ":MAX:%13.0lf%s",
	                    "GPRINT:load" + self.parametro + ":AVERAGE:%13.0lf%s",
	                    "GPRINT:load" + self.parametro + ":LAST:%13.0lf%s" + "\\n",
	                    "COMMENT: \\n",

	                    # Prediction data
	                    "VDEF:D2=load" + self.parametro + "date,LSLSLOPE",
	                    "VDEF:H2=load" + self.parametro + "date,LSLINT",
	                    "CDEF:avg2=load" + self.parametro + "date,POP,D2,COUNT,*,H2,+",
	                    "CDEF:abc2=avg2,90,100,LIMIT",
	                    # "CDEF:abc2=avg2,0,10,LIMIT",
	                    "VDEF:minabc2=abc2,FIRST",
	                    "VDEF:maxabc2=abc2,LAST",

	                    # Prediction output
	                    "AREA:abc2#FFBB0077",
	                    "LINE2:abc2#FFBB00",
	                    "LINE1:avg2#FFBB00:Trend since " + str(self.beginTime) + " :dashes=10",
	                    "GPRINT:minabc2: Reach 90% @ %c :strftime",
	                    "GPRINT:maxabc2: Reach 100% @ %c :strftime",
	                    "COMMENT: \\n",

	                    # Base line
	                     # "CDEF:umbral25=load" + self.parametro + ",25,LT,0,load,IF",
	                     # "CDEF:umbral50=load" + self.parametro + ",50,LT,0,load,IF",
	                     # "CDEF:umbral75=load" + self.parametro + ",75,LT,0,load,IF",
	                     "CDEF:umbralReady=load" + self.parametro + ","+str(listaLineaBase[0])+",LT,0,load,IF",
	                     "CDEF:umbralSet=load" + self.parametro + ","+str(listaLineaBase[1])+",LT,0,load,IF",
	                     "CDEF:umbralGo=load" + self.parametro + ","+str(listaLineaBase[2])+",LT,0,load,IF",
	                     "VDEF:cargaMAX=load,MAXIMUM",
	                     "VDEF:cargaMIN=load,MINIMUM",
	                     "VDEF:cargaSTDEV=load,STDEV",
	                     "VDEF:cargaLAST=load,LAST",
	                     # "AREA:umbral25#FCF650:Tráfico de carga mayor que 25",
	                     # "AREA:umbral50#FF9F00:Tráfico de carga mayor que 50",
	                     # "AREA:umbral75#FF0000:Tráfico de carga mayor que 75",
	                     "AREA:umbralReady#FCF650:Tráfico de carga mayor que "+str(listaLineaBase[0]),
	                     "AREA:umbralSet#FF9F00:Tráfico de carga mayor que "+str(listaLineaBase[1]),
	                     "AREA:umbralGo#FF0000:Tráfico de carga mayor que "+str(listaLineaBase[2]),
	                    "COMMENT: \\n",
	                     # "HRULE:25#C9C9C9:Umbral 1 - 25%",
	                     # "HRULE:50#919191:Umbral 25 - 50%",
	                     # "HRULE:75#3D3D3D:Umbral 50 - 75%",
	                     "HRULE:"+str(listaLineaBase[0])+"#C9C9C9:Umbral 1 - "+str(listaLineaBase[0])+"%",
	                     "HRULE:"+str(listaLineaBase[1])+"#919191:Umbral "+str(listaLineaBase[0])+" - "+str(listaLineaBase[1])+"%",
	                     "HRULE:"+str(listaLineaBase[2])+"#3D3D3D:Umbral "+str(listaLineaBase[1])+" - "+str(listaLineaBase[2])+"%",
	                    "COMMENT: \\n",

						# "TICK:fail#FDD017:1.0:Fallas",
						# "AREA:valor#00FF00:Actual",
						# "LINE1:pred#FF00FF:Prediccion",
						# # "LINE1:upper#ff0000:Upper Bound",
						# # "LINE1:lower#0000FF:Lower Bound")
						# "LINE2:"+str(listaLineaBase[0])+"#14B229:Ready",
						# "LINE2:"+str(listaLineaBase[1])+"#E5CB1F:Set",
						# "LINE2:"+str(listaLineaBase[2])+"#ff0000:Go"
						)
		# return int(ret['legend[4]'])
		print("\n\n")
		# print (ret[2][0])
		# print (ret[2])
		print (ret)
		print("\n\n")
		return ret[2][0]

	def GraficarLineaBase(self, listaLineaBase):
		ret = rrdtool.graph( self.nombre+".png",
						"--start",str(self.beginTime),
						# "-t",self.parametro,
						'--slope-mode',

	                    # Image settings
	                    '--title', self.parametro,
	                    # '--interlace', 
	                    '--width=1240', '--height=400',
	                    "--color","ARROW#009900",
	                    '--vertical-label', self.parametro + " used (%)",
	                    '--lower-limit', '0',
	                    '--upper-limit', '100',
	                    '--border','0',
	                    '--rigid',

	                    "DEF:valor="+ self.nombre+".rrd" + ":" +self.parametro+":AVERAGE",
						"DEF:pred="	+ self.nombre+".rrd" + ":" + self.parametro + ":HWPREDICT",
						"DEF:dev="	+ self.nombre+".rrd" + ":" + self.parametro + ":DEVPREDICT",
						"DEF:fail="	+ self.nombre+".rrd" + ":" + self.parametro + ":FAILURES",
						'PRINT:fail:LAST:%1.0lf',
						# "GPRINT:pred:LAST:\"%6.2lf%sW\"",
						# "GPRINT:valor:LAST:\"%6.2lf%sW\"",
						# "GPRINT:dev:LAST:\"%6.2lf%sW\"",
						"CDEF:upper=pred,dev,2,*,+",
						"CDEF:lower=pred,dev,2,*,-",
						"TICK:fail#FDD017:1.0:Fallas",

	                    # Load data
	                    "DEF:load=" + self.nombre + ".rrd:" + self.parametro + ":AVERAGE",
	                    "DEF:load" + self.parametro + "=" + self.nombre + ".rrd:" + self.parametro + ":AVERAGE",
	                    "DEF:load" + self.parametro + "date=" + self.nombre + ".rrd:" + self.parametro + ":AVERAGE:start=" + str(self.beginTime),

	                    # Data output
	                    "LINE1:" + str(listaLineaBase[2] - 10) + "",
	                    "AREA:5#FF000022::STACK",
	                    "AREA:5#FF000044::STACK",

	                    "COMMENT: \\t\\t\\tNow \\t\\tMin \\t\\tMax \\t\\tAvg\\t\\tLast\\n",
	                    "AREA:load" + self.parametro + "#00FF00:CPU Used",
	                    "GPRINT:load" + self.parametro + ":LAST:%12.0lf%s",
	                    "GPRINT:load" + self.parametro + ":MIN:%10.0lf%s",
	                    "GPRINT:load" + self.parametro + ":MAX:%13.0lf%s",
	                    "GPRINT:load" + self.parametro + ":AVERAGE:%13.0lf%s",
	                    "GPRINT:load" + self.parametro + ":LAST:%13.0lf%s" + "\\n",
	                    "COMMENT: \\n",

	                    # Prediction data
	                    "VDEF:D2=load" + self.parametro + "date,LSLSLOPE",
	                    "VDEF:H2=load" + self.parametro + "date,LSLINT",
	                    "CDEF:avg2=load" + self.parametro + "date,POP,D2,COUNT,*,H2,+",
	                    "CDEF:abc2=avg2,90,100,LIMIT",
	                    # "CDEF:abc2=avg2,0,10,LIMIT",
	                    "VDEF:minabc2=abc2,FIRST",
	                    "VDEF:maxabc2=abc2,LAST",

	                    # Prediction output
	                    "AREA:abc2#FFBB0077",
	                    "LINE2:abc2#FFBB00",
	                    "LINE1:avg2#FFBB00:Trend since " + str(self.beginTime) + " :dashes=10",
	                    "GPRINT:minabc2: Reach 90% @ %c :strftime",
	                    "GPRINT:maxabc2: Reach 100% @ %c :strftime",
	                    "COMMENT: \\n",

	                    # Base line
	                     # "CDEF:umbral25=load" + self.parametro + ",25,LT,0,load,IF",
	                     # "CDEF:umbral50=load" + self.parametro + ",50,LT,0,load,IF",
	                     # "CDEF:umbral75=load" + self.parametro + ",75,LT,0,load,IF",
	                     "CDEF:umbralReady=load" + self.parametro + ","+str(listaLineaBase[0])+",LT,0,load,IF",
	                     "CDEF:umbralSet=load" + self.parametro + ","+str(listaLineaBase[1])+",LT,0,load,IF",
	                     "CDEF:umbralGo=load" + self.parametro + ","+str(listaLineaBase[2])+",LT,0,load,IF",
	                     "VDEF:cargaMAX=load,MAXIMUM",
	                     "VDEF:cargaMIN=load,MINIMUM",
	                     "VDEF:cargaSTDEV=load,STDEV",
	                     "VDEF:cargaLAST=load,LAST",
	                     # "AREA:umbral25#FCF650:Tráfico de carga mayor que 25",
	                     # "AREA:umbral50#FF9F00:Tráfico de carga mayor que 50",
	                     # "AREA:umbral75#FF0000:Tráfico de carga mayor que 75",
	                     "AREA:umbralReady#FCF650:Tráfico de carga mayor que "+str(listaLineaBase[0]),
	                     "AREA:umbralSet#FF9F00:Tráfico de carga mayor que "+str(listaLineaBase[1]),
	                     "AREA:umbralGo#FF0000:Tráfico de carga mayor que "+str(listaLineaBase[2]),
	                    "COMMENT: \\n",
	                     # "HRULE:25#C9C9C9:Umbral 1 - 25%",
	                     # "HRULE:50#919191:Umbral 25 - 50%",
	                     # "HRULE:75#3D3D3D:Umbral 50 - 75%",
	                     "HRULE:"+str(listaLineaBase[0])+"#C9C9C9:Umbral 1 - "+str(listaLineaBase[0])+"%",
	                     "HRULE:"+str(listaLineaBase[1])+"#919191:Umbral "+str(listaLineaBase[0])+" - "+str(listaLineaBase[1])+"%",
	                     "HRULE:"+str(listaLineaBase[2])+"#3D3D3D:Umbral "+str(listaLineaBase[1])+" - "+str(listaLineaBase[2])+"%",
	                    "COMMENT: \\n",

						# "TICK:fail#FDD017:1.0:Fallas",
						# "AREA:valor#00FF00:Actual",
						# "LINE1:pred#FF00FF:Prediccion",
						# "LINE2:"+str(listaLineaBase[0])+"#14B229:Ready",
						# "LINE2:"+str(listaLineaBase[1])+"#E5CB1F:Set",
						# "LINE2:"+str(listaLineaBase[2])+"#ff0000:Go"
						)
		print("\n\n")
		print (ret[2][0])
		print (ret[2])
		print (ret)
		print("\n\n")


	def Dump(self):
		rrdtool.dump(self.nombre+".rrd",self.nombre+".xml")