import sys
import rrdtool
import time
path = "./source3.rrd"
ultima_lectura = int(rrdtool.last("./source3.rrd"))
tiempo_final = ultima_lectura + 5000
tiempo_inicial = tiempo_final - 100

#3600 = 1 hours 

begintest = 1539659123
end = "1552503540"

ret = rrdtool.graphv( "deteccion2.png",
                    #"--start",str(tiempo_inicial),
                    "--start",str(begintest),
                    "--end",str(begintest + 30000),
                    "--vertical-label=Porcentaje",
                    '--lower-limit', '0',
                    '--upper-limit', '100',
                    

                    "DEF:carga="+path+":CPUload:AVERAGE",
                    "VDEF:m=carga,LSLSLOPE",
                    "VDEF:b=carga,LSLINT",
                    'CDEF:predline=carga,POP,m,COUNT,*,b,+',
                    'CDEF:maxlimit=predline,90,100,LIMIT',
                    'VDEF:upperminpoint=maxlimit,FIRST',
                    'VDEF:uppermaxpoint=maxlimit,LAST',
                    
                    "LINE2:predline#ef0078",

                    "AREA:carga#00FF00:CPU Used",

                    "AREA:maxlimit#0077FF77",
                    "LINE2:maxlimit#0077FF",
                    "GPRINT:upperminpoint:  Reach 60% @ %c \\n:strftime",
                    "GPRINT:uppermaxpoint:  Reach 90% @ %c \\n:strftime"
                    )
#print (ret)
print(ret.keys())
print(ret.items())