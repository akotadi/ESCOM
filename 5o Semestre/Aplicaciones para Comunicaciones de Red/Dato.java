class Dato implements Serializable{
	int n;
	int np;
	byte[] b;
	int tam;

	public Dato(int n, int np, byte[] b, int tam){
		this.n = n;
		this.np = np;
		this.b = b;
		this.tam = tam;
	}

	int getN(){
		return this.n;
	}

	void setN(int n){
		this.n = n;
	}

	int getNP(){
		return this.np;
	}

	void setNP(int np){
		this.np = np;
	}

	byte[] getB(){
		return this.b;
	}

	void setB(byte[] b){
		this.b = b;
	}

	int getTam(){
		return this.tam;
	}

	void setTam(int tam){
		this.tam = tam;
	}
}