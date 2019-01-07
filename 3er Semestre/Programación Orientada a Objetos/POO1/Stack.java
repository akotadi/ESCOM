

class Stack {
        int elementos[];
        int tope= 0;
	public Stack(int n)
	{
		elementos=new int[n];
		tope=0;
	}
	int pop(){
                tope--;
		return elementos[tope];
	}
	void push(int dato){
		elementos[tope]=dato;
		tope++;
	}
        boolean estaVacia(){
		return tope==0;
	}
	int top(){
		return elementos[tope-1];
	}
}
