public class Reactivo{
	
	// VARIABLES
	private int idReactivo;
	private String pregunta, opA, opB, opC, opD;
	private char respuesta;

	// CONSTRUCTOR
	public Reactivo(int n, String s, String A, String B, String C, String D, String c){

		idReactivo = n; // Establecemos el ID del reactivo a partir del valor dado
		pregunta = s.toUpperCase(); // Para facilidad de trabajo, unificamos todo el texto en mayúsculas
		opA = A.toUpperCase();
		opB = B.toUpperCase();
		opC = C.toUpperCase();
		opD = D.toUpperCase();
		c = c.toUpperCase();
		respuesta = c.charAt(0); // Dado que recibimos un String, hacemos el cambio al valor requerido

	}

	public Reactivo(String s, String A, String B, String C, String D, String c){
		pregunta = s.toUpperCase();
		opA = A.toUpperCase();
		opB = B.toUpperCase();
		opC = C.toUpperCase();
		opD = D.toUpperCase();
		c = c.toUpperCase();
		respuesta = c.charAt(0);
	}

	// El método devolverá el ID del reactivo.
	public int getID(){ 
		return idReactivo;
	}

	public String getPregunta(){
		return pregunta;
	}

	public String getOpcionA(){
		return opA;
	}

	public String getOpcionB(){
		return opB;
	}

	public String getOpcionC(){
		return opC;
	}

	public String getOpcionD(){
		return opD;
	}

	public char getRespuesta(){
		return respuesta;
	}

	// A partir de la respuesta predeterminada, regresamos el texto de la misma
	public String getTextoRespuesta(){ 
		if (getRespuesta() == 'A') {
			return opA;
		}
		else if (getRespuesta() == 'B') {
			return opB;
		}
		else if (getRespuesta() == 'C') {
			return opC;
		}
		else if (getRespuesta() == 'D') {
			return opD;
		}
		return null;
	}

	/* A partir del valor de una opción recibida, 
	 	devolvemos el texto asociado a ella. */
	public String getTexto(char select){ 
		if (select == 'A') {
			return opA;
		}
		else if (select == 'B') {
			return opB;
		}
		else if (select == 'C') {
			return opC;
		}
		else if (select == 'D') {
			return opD;
		}
		return null;
	}
}