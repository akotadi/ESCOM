class ExcepcionArbol extends Exception
{
	ExcepcionArbol(String _mensaje)
	{
		System.out.println(_mensaje);
	}
}

class Nodo // BEAN
{
	Nodo ader, aizq;
	String dato;
	
	Nodo(String _dato)
	{
		dato = _dato;
		ader = null;
		aizq = null;
	}
	
	String getDato()
	{
		return dato;
	}
	
	Nodo getAizq()
	{
		return aizq;
	}
	
	Nodo getAder()
	{
		return ader;
	}
	
	void setAizq(Nodo _aizq)
	{
		aizq = _aizq;
	}
	
	void setAder(Nodo _ader)
	{
		ader = _ader;
	}	
}

class ArbolBinario
{
	Nodo raiz;
	
	ArbolBinario()
	{
		raiz = null;
	}
	
	void inserta(String _dato) throws ExcepcionArbol
	{
		if(raiz == null)
		{
			Nodo nuevo = new Nodo(_dato);
			raiz = nuevo;
		}
		else
		{
			insertaNodo(raiz, _dato);
		}
	}

	private void insertaNodo(Nodo _padre, String _dato) throws ExcepcionArbol
	{
		if(_padre.getDato().equals(_dato))
		  throw new ExcepcionArbol("El dato ya existe");
		if(_padre.getDato().compareTo(_dato) < 0)
		{
			if(_padre.getAder() == null)
			{
				Nodo nuevo = new Nodo(_dato);
				_padre.setAder(nuevo);
			}
			else
				insertaNodo(_padre.getAder(), _dato);
		}
		else
		{
			if(_padre.getAizq() == null)
			{
				Nodo nuevo = new Nodo(_dato);
				_padre.setAizq(nuevo);
			}
			else
				insertaNodo(_padre.getAizq(), _dato);
		}
	}
	
	boolean busca(String _dato) throws ExcepcionArbol
	{
		if(raiz == null)
			throw new ExcepcionArbol("La raiz es nula");
		else
			return buscaNodo(raiz, _dato);
	
	}
	
	private boolean buscaNodo(Nodo _padre, String _dato) throws ExcepcionArbol
	{
		if(_padre.getDato().equals(_dato))
		  return true;
		if(_padre.getDato().compareTo(_dato) > 0)
		{ 
			if(_padre.getAizq() == null)
				throw new ExcepcionArbol("El dato no existe");
			else
				return buscaNodo(_padre.getAizq(), _dato);
		}
		else
		{ 
			if(_padre.getAder() == null)
				throw new ExcepcionArbol("El dato no existe");
			else
				return buscaNodo(_padre.getAder(), _dato);
		}
	}
}

class Arbol
{
	public static void main(String arg[]) throws ExcepcionArbol
	{
		ArbolBinario arbol = new ArbolBinario();
		try
		{
		arbol.inserta("H");
		arbol.inserta("A");
		arbol.inserta("B");
        arbol.inserta("H");
	//  arbol.insertaNodo(new Nodo("Z"),"F");
		
		if(arbol.busca("B") == false)
		{
			System.out.println("El dato no se encuentra");
		}
		else
		{
			System.out.println("El dato SI se encuentra");
		}
	}
	catch(ExcepcionArbol e)
	  {
	  }
	}
}