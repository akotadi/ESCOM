class Nodo():
	def __init__(self, nombre_ = "", oid_ = "", desc_="", tipo_=""):
		self.nombre = nombre_
		self.oid = oid_
		self.hijos = []
		self.Descripcion = desc_
		self.tipo = tipo_
		self.activo = False

	def addChildren(self, node):
		self.hijos.append(node)

	def InOrden(self):
		print self.nombre
		for hijo in self.hijos:
			sys.stdout.write('	')
			hijo.InOrden()

	def BuscarInOrden( self, busca, nivel = 1 ):
		if self.oid == busca: 
			return self, nivel
		for hijo in self.hijos:
			ret = hijo.BuscarInOrden(busca, nivel+1)
			if( ret is not None ):
				return ret
		return None