
public class PbaRac {
public static void main( String[] args ) {
	Racional r1, r2, r3;
	r1=new Racional(1, 2);
	r2=new Racional(1, 4);
	r3=r1.mult(r2);
	r3.imprime();
}
}
