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

def consultaSNMP(comunidad, host, puerto, oid):
		mibBuilder = builder.MibBuilder()
		mibViewController = view.MibViewController(mibBuilder)
		compiler.addMibCompiler(mibBuilder, sources=['file:///usr/local/lib/python2.7/dist-packages/pysnmp/smi/mibs',
																						 'http://mibs.snmplabs.com/asn1/@mib@'])
		mibBuilder.loadModules('RFC1213-MIB','IF-MIB')
		objectIdentity = rfc1902.ObjectIdentity(oid).resolveWithMib(mibViewController)

		errorIndication, errorStatus, errorIndex, varBinds = next(
				getCmd(SnmpEngine(),
							 CommunityData(comunidad),
							 UdpTransportTarget((host, puerto), timeout = 1.5, retries=0),
							 ContextData(),
							 ObjectType( objectIdentity )))

		if errorIndication:
				print errorIndication, " : ", host
		elif errorStatus:
				print('%s at %s' % (errorStatus.prettyPrint(),errorIndex and varBinds[int(errorIndex) - 1][0] or '?'))
		else:
				for varBind in varBinds:
						varB=(' = '.join([x.prettyPrint() for x in varBind]))
						resultado= " ".join(varB.split()[2:])
				return resultado
		return -1

def setSNMP(comunidad, host, puerto, oid, valor):
	errorIndication, errorStatus, errorIndex, varBinds = next(
		setCmd(SnmpEngine(),
				CommunityData(comunidad),
				UdpTransportTarget((host, puerto), timeout=1.5),
				ContextData(),
				ObjectType(ObjectIdentity(oid),
					valor))
	)

	if errorIndication:
		print errorIndication, " : ", host
	elif errorStatus:
		print('%s at %s' % (errorStatus.prettyPrint(),
							errorIndex and varBinds[int(errorIndex) - 1][0] or '?'))
	else:
		for varBind in varBinds:
			varB=(' = '.join([x.prettyPrint() for x in varBind]))
			resultado= varB.split()[2]
		return resultado
	return ""