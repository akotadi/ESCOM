#!/usr/bin/env python
from path import *
import rrdtool
ret = rrdtool.create(rrdpath+rrdname,
                     "--start",'N',
                     "--step",'40',

                     # "DS:inoctets:COUNTER:600:U:U",
                     "DS:outoctets:COUNTER:300:U:U",

                     # "RRA:AVERAGE:0.5:1:2016",
                     "RRA:AVERAGE:0.5:1:10",

                     # RRA:HWPREDICT:<row count>:<alpha>:<beta>:<period>
                    #RRA:HWPREDICT:rows:alpha:beta:seasonal period[:rra - num]
                     # "RRA:HWPREDICT:1000:0.1:0.0035:288:3",
                     "RRA:HWPREDICT:50:0.1:0.0035:2:3",
                    #RRA:SEASONAL:seasonal period:gamma:rra-num
                     # "RRA:SEASONAL:288:0.1:2",
                     "RRA:SEASONAL:2:0.1:2",
                    #RRA:DEVSEASONAL:seasonal period:gamma:rra-num
                     # "RRA:DEVSEASONAL:288:0.1:2",
                     "RRA:DEVSEASONAL:2:0.1:2",
                    #RRA:DEVPREDICT:rows:rra-num
                     # "RRA:DEVPREDICT:1000:4",
                     "RRA:DEVPREDICT:50:4",
                    #RRA:FAILURES:rows:threshold:window length:rra-num
                     # "RRA:FAILURES:288:7:9:4"
                     "RRA:FAILURES:12:1:1:4"
                     )

#HWPREDICT rra-num is the index of the SEASONAL RRA.
#SEASONAL rra-num is the index of the HWPREDICT RRA.
#DEVPREDICT rra-num is the index of the DEVSEASONAL RRA.
#DEVSEASONAL rra-num is the index of the HWPREDICT RRA.
#FAILURES rra-num is the index of the DEVSEASONAL RRA.

if ret:
    print (rrdtool.error())

