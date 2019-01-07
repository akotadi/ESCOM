import java.awt.*;
public class PerroBean {
	private String nombre;
	private String raza;
	private int edad;
        private String genero;
        //private Color color;
	
        public PerroBean(){
	}
        public PerroBean(String nombre){
		this.nombre = nombre;
	}
	public PerroBean(String nombre, String raza,
	int edad, String genero/*, Color color*/){
		this.nombre = nombre;
		this.genero = genero;
		this.edad = edad;
		this.raza = raza;
                //this.color = color;
	}
/*
        public Color getColor(){
		return color;
        }
	public void setColor(Color color){
		this.color = color;
        }*/
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
	public String toString(){
	   return  nombre+genero+edad+raza/*+color*/;
	}
}
