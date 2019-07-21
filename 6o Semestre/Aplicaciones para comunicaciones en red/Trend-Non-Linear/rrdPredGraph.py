import time
import rrdtool
from path import *

title="Deteccion de comportamiento anomalo, valor de Alpha 0.1"
endDate = rrdtool.last(rrdpath + rrdname) #ultimo valor del XML
begDate = endDate - 2000

# tiempo_actual = int(time.time())
# tiempo_final = tiempo_actual - 86400
# tiempo_inicial = tiempo_final -25920000


while 1 :
    rrdtool.tune(rrdpath + rrdname, '--alpha', '0.1')
    # rrdtool.tune(rrdpath + rrdname, '--window-length', '5')
    # rrdtool.tune(rrdpath + rrdname, '--failure-threshold', '3')
    ret = rrdtool.graph(pngpath + "testNetPalphaBajoFallas.png",
                            '--start', str(begDate), 
                            '--end', str(endDate), 
                            '--title=' + title,
                            '--width=1240', '--height=400',
                            "--color","ARROW#009900",
                            '--vertical-label', "Bytes/s",
                            '--border','0',
                            '--rigid',
                            '--slope-mode',

                            # "DEF:obs=" + rrdpath + rrdname + ":inoctets:AVERAGE",
                            "DEF:obs=" + rrdpath + rrdname + ":outoctets:AVERAGE",
                            # "DEF:pred=" + rrdpath + rrdname + ":inoctets:HWPREDICT",
                            "DEF:pred=" + rrdpath + rrdname + ":outoctets:HWPREDICT",
                            # "DEF:dev=" + rrdpath + rrdname + ":inoctets:DEVPREDICT",
                            "DEF:dev=" + rrdpath + rrdname + ":outoctets:DEVPREDICT",
                            # "DEF:fail=" + rrdpath + rrdname + ":inoctets:FAILURES",
                            "DEF:fail=" + rrdpath + rrdname + ":outoctets:FAILURES",

                        #"RRA:DEVSEASONAL:1d:0.1:2",
                        #"RRA:DEVPREDICT:5d:5",
                        #"RRA:FAILURES:1d:7:9:5""
                            "CDEF:scaledobs=obs,8,*",
                            "CDEF:upper=pred,dev,2,*,+",
                            "CDEF:lower=pred,dev,2,*,-",
                            "CDEF:scaledupper=upper,8,*",
                            "CDEF:scaledlower=lower,8,*",
                            "CDEF:scaledpred=pred,8,*",

                        "TICK:fail#ffffa0:1.0:Failures\\n",

                        "LINE1:scaledobs#00FF00:Average bits out\\n",

                        "LINE3:scaledpred#FF00FF:Prediccion\\n",
                        #"LINE1:outoctets#0000FF:Out traffic",
                        #
                        # "LINE1:scaledupper#ff0000:Upper Bound Average bits in",
                        "LINE2:scaledupper#ff0000:Upper Bound Average bits out\\n",
                        # "LINE1:scaledlower#0000FF:Lower Bound Average bits in"
                        "LINE2:scaledlower#0000FF:Lower Bound Average bits out\\n"
                        )

    time.sleep(30)
