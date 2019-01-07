

public class PerroVO {
	private String nombre;
	private String raza;
	private int edad;
        private char genero;
	public PerroVO(String nombre, String raza,
	int edad, char genero){
		this.nombre = nombre;
		this.genero = genero;
		this.edad = edad;
		this.raza = raza;
	}
        public PerroVO(String nombre){
		this.nombre = nombre;
	}
	String getNombre(){
		return nombre;
	}
	String getRaza(){
		return raza;
	}
	int getEdad(){
		return edad;
	}
        char getGenero(){
		return genero;
	}		
	void setNombre(String nombre){
		this.nombre = nombre;
	}
	void setRaza(String raza){
		this.raza = raza;
	}
	void setEdad(int edad){
		this.edad = edad;
	}
        void setGenero(char genero){
		this.genero = genero;
	}
}
