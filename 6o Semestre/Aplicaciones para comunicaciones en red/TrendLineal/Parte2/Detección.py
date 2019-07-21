import sys
import rrdtool
import time
from Notify import send_alert_attached

ultima_lectura = int(rrdtool.last("../RRD/trend.rrd"))
tiempo_final = ultima_lectura
tiempo_inicial = tiempo_final - 3600

# tiempo_actual = int(time.time())
# tiempo_final = tiempo_actual - 86400
# tiempo_inicial = tiempo_final - 25920000

# print('select db to graph')
# archivo = input()
# archivo = 'source3'
archivo = '../RRD/trend'
last_read = int(rrdtool.last(archivo + '.rrd'))
# print(last_read)

# print('Insert Ready')
# nReady = input()
# print('Insert Set')
# nSet = input()
# print('Insert Go')
# nGo = input()

def detectar(archivo = 'source3', nDate = 1539659123, nReady = 50, nSet = 65, nGo = 80):
    ret = rrdtool.graphv( "../IMG/"+archivo+".png",
                     "--start", str(nDate),
                    "--end", str(nDate + 7600),

                    # Image settings
                    '--title', "CPU Usage",
                    # '--interlace', 
                    '--width=1240', '--height=400',
                    "--color","ARROW#009900",
                    '--vertical-label', "CPU used (%)",
                    '--lower-limit', '0',
                    '--upper-limit', '100',
                    '--border','0',
                    '--rigid',

                    # Load data
                    "DEF:load=" + archivo + ".rrd:CPUload:AVERAGE",
                    "DEF:loadCPU=" + archivo + ".rrd:CPUload:AVERAGE",
                    "DEF:loadCPUdate=" + archivo + ".rrd:CPUload:AVERAGE:start=1539659123",

                    # Data output
                    "LINE1:90",
                    "AREA:5#FF000022::STACK",
                    "AREA:5#FF000044::STACK",

                    "COMMENT: \\t\\t\\tNow \\t\\tMin \\t\\tMax \\t\\tAvg\\t\\tLast\\n",
                    "AREA:loadCPU#00FF00:CPU Used",
                    'GPRINT:loadCPU:LAST:%12.0lf%s',
                    'GPRINT:loadCPU:MIN:%10.0lf%s',
                    'GPRINT:loadCPU:MAX:%13.0lf%s',
                    'GPRINT:loadCPU:AVERAGE:%13.0lf%s',
                    'GPRINT:loadCPU:LAST:%13.0lf%s' + "\\n",
                    "COMMENT: \\n",

                    # Prediction data
                    'VDEF:D2=loadCPUdate,LSLSLOPE',
                    'VDEF:H2=loadCPUdate,LSLINT',
                    'CDEF:avg2=loadCPUdate,POP,D2,COUNT,*,H2,+',
                    'CDEF:abc2=avg2,90,100,LIMIT',
                    # 'CDEF:abc2=avg2,0,10,LIMIT',
                    'VDEF:minabc2=abc2,FIRST',
                    'VDEF:maxabc2=abc2,LAST',

                    # Prediction output
                    # "AREA:abc2#FFBB0077",
                    # "LINE2:abc2#FFBB00",
                    # "LINE1:avg2#ef0078:Trend since October 15 2018 22\\:05\\:23 :dashes=10",
                    # "GPRINT:minabc2: Reach 90% @ %c :strftime",
                    # "GPRINT:maxabc2: Reach 100% @ %c :strftime",
                    "COMMENT: \\n",

                    # Base line
                     # "CDEF:umbral25=loadCPU,25,LT,0,load,IF",
                     # "CDEF:umbral50=loadCPU,50,LT,0,load,IF",
                     # "CDEF:umbral75=loadCPU,75,LT,0,load,IF",
                     "CDEF:umbralReady=loadCPU,"+str(nReady)+",LT,0,load,IF",
                     "CDEF:umbralSet=loadCPU,"+str(nSet)+",LT,0,load,IF",
                     "CDEF:umbralGo=loadCPU,"+str(nGo)+",LT,0,load,IF",
                     "VDEF:cargaMAX=load,MAXIMUM",
                     "VDEF:cargaMIN=load,MINIMUM",
                     "VDEF:cargaSTDEV=load,STDEV",
                     "VDEF:cargaLAST=load,LAST",
                     # "AREA:umbral25#FCF650:Tráfico de carga mayor que 25",
                     # "AREA:umbral50#FF9F00:Tráfico de carga mayor que 50",
                     # "AREA:umbral75#FF0000:Tráfico de carga mayor que 75",
                     "AREA:umbralReady#FCF650:Tráfico de carga mayor que "+str(nReady),
                     "AREA:umbralSet#FF9F00:Tráfico de carga mayor que "+str(nSet),
                     "AREA:umbralGo#FF0000:Tráfico de carga mayor que "+str(nGo),
                    "COMMENT: \\n",
                     # "HRULE:25#C9C9C9:Umbral 1 - 25%",
                     # "HRULE:50#919191:Umbral 25 - 50%",
                     # "HRULE:75#3D3D3D:Umbral 50 - 75%",
                     "HRULE:"+str(nReady)+"#C9C9C9:Umbral 1 - "+str(nReady)+"%",
                     "HRULE:"+str(nSet)+"#919191:Umbral "+str(nReady)+" - "+str(nSet)+"%",
                     "HRULE:"+str(nGo)+"#3D3D3D:Umbral "+str(nSet)+" - "+str(nGo)+"%",
                    "COMMENT: \\n",
                     )

    print(ret['legend[4]'])
    # for key in ret['legend[4]']:
    #     print(key)
#    print(ret.keys())
#    print(ret.items())
#    print(ret[2][0])
    ultimo_valor = float(ret['legend[4]'])

    # if ultimo_valor > 70:
    #     send_alert_attached("Alerta: Sobrepasa Umbral Go - CPU Usage", archivo)
    #     print("Alerta: Sobrepasa Umbral Go - CPU Usage")

# detectar(archivo, str(1552504560), 55, 63, 70)
detectar()

