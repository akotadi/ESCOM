
class Nodo
{
	Nodo ader, aizq;
	String dato;
	
	Nodo(String _dato)
	{
		dato = _dato;
		ader = null;
		aizq = null;
	}
}

class ArbolBinario
{
	Nodo raiz;
	
	ArbolBinario()
	{
		raiz = null;
	}
	
	void inserta(String _dato)
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

	private void insertaNodo(Nodo _padre, String _dato)
	{
		if(_padre.dato.equals(_dato))
		  return;
		if(_padre.dato.compareTo(_dato) < 0)
		{
			if(_padre.ader == null)
			{
				Nodo nuevo = new Nodo(_dato);
				_padre.ader = nuevo;
			}
			else
				insertaNodo(_padre.ader, _dato);
		}
		else
		{
			if(_padre.aizq == null)
			{
				Nodo nuevo = new Nodo(_dato);
				_padre.aizq = nuevo;
			}
			else
				insertaNodo(_padre.aizq, _dato);
		}
	}
	
	boolean busca(String _dato)
	{
		if(raiz == null)
			return false;
		else
			return buscaNodo(raiz, _dato);
	
	}
	
	private boolean buscaNodo(Nodo _padre, String _dato)
	{
		if(_padre.dato.equals(_dato))
		  return true;
		if(_padre.dato.compareTo(_dato) > 0)
		{ 
			if(_padre.aizq == null)
				return false;
			else
				return buscaNodo(_padre.aizq, _dato);
		}
		else
		{ 
			if(_padre.ader == null)
				return false;
			else
				return buscaNodo(_padre.ader, _dato);
		}
	}
}

class Arbol
{
	public static void main(String arg[])
	{
		ArbolBinario arbol = new ArbolBinario();
		
		arbol.inserta("H");
		arbol.inserta("A");
		arbol.inserta("B");
	//  arbol.insertaNodo(new Nodo("Z"),"F");
		
		if(arbol.busca("F") == false)
		{
			System.out.println("El dato no se encuentra");
		}
		else
		{
			System.out.println("El dato SI se encuentra");
		}
	}
}