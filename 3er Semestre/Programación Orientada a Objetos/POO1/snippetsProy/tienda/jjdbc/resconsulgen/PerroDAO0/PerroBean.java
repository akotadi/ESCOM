

public class PerroBean {
	private String nombre;
	private String raza;
	private int edad;
        private String genero;
	public PerroBean(String nombre, String raza,
	int edad, String genero){
		this.nombre = nombre;
		this.genero = genero;
		this.edad = edad;
		this.raza = raza;
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
        String getGenero(){
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
        void setGenero(String genero){
		this.genero = genero;
	}
}
