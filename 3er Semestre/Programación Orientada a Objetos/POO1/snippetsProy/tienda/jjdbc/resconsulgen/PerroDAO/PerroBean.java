
public class PerroBean {
	private String nombre;
	private String raza;
	private int edad;
        private String genero;
        public PerroBean(){
	}
	public PerroBean(String nombre, String raza,
	int edad, String genero){
		this.nombre = nombre;
		this.genero = genero;
		this.edad = edad;
		this.raza = raza;
	}
	public String getNombre(){
		return nombre;
	}
	public String getRaza(){
		return raza;
	}
	public int getEdad(){
		return edad;
	}
        public String getGenero(){
		return genero;
	}		
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	public void setRaza(String raza){
		this.raza = raza;
	}
	public void setEdad(int edad){
		this.edad = edad;
	}
        public void setGenero(String genero){
		this.genero = genero;
	}
}
