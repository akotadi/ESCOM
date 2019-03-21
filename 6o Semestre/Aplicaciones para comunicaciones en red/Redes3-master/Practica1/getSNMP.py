"""
SNMPv1
++++++

Send SNMP GET request using the following options:

	* with SNMPv1, community 'public'
	* over IPv4/UDP
	* to an Agent at demo.snmplabs.com:161
	* for two instances of SNMPv2-MIB::sysDescr.0 MIB object,

Functionally similar to:

| $ snmpget -v1 -c public localhost SNMPv2-MIB::sysDescr.0

"""#
from pysnmp.hlapi import *
from pysnmp.smi import builder
from pysnmp.smi import builder, view, compiler, rfc1902

def consultaSNMP(comunidad,host,oid):
		mibBuilder = builder.MibBuilder()
		mibViewController = view.MibViewController(mibBuilder)
		compiler.addMibCompiler(mibBuilder, sources=['file:///usr/local/lib/python2.7/dist-packages/pysnmp/smi/mibs',
																						 'http://mibs.snmplabs.com/asn1/@mib@'])
		mibBuilder.loadModules('RFC1213-MIB','IF-MIB')
		objectIdentity = rfc1902.ObjectIdentity(oid).resolveWithMib(mibViewController)

		errorIndication, errorStatus, errorIndex, varBinds = next(
				getCmd(SnmpEngine(),
							 CommunityData(comunidad),
							 UdpTransportTarget((host, 161)),
							 ContextData(),
							 #ObjectType(ObjectIdentity(oid))))
							 ObjectType( objectIdentity )))

		if errorIndication:
				print(errorIndication)
		elif errorStatus:
				print('%s at %s' % (errorStatus.prettyPrint(),errorIndex and varBinds[int(errorIndex) - 1][0] or '?'))
		else:

				for varBind in varBinds:
						varB=(' = '.join([x.prettyPrint() for x in varBind]))
						resultado= " ".join(varB.split()[2:])
		return resultado
