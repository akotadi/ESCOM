class Racional {
	int den, num;

	public Racional(int num, int den){
		this.den=den; 
		this.num=num;
	}
	Racional mult(Racional r){
		return new Racional(num * r.num, den * r.den);
	}
	Racional division(Racional r){
		return new Racional(num * r.den, den * r.num);
	}
	boolean esIgual(Racional r){
		return num * r.den == den * r.num;
	}
	void imprime(){
		System.out.println(this.num+" / "+ this.den);
	}
}	
