import java.io.Serializable;
public class InfoPlanet implements Serializable {
	String nombre;
	public InfoPlanet(String nombre)
	{
		this.nombre=nombre;	
	}
        public String getNombre(){
               return nombre;
	}
	public void setNombre(String nombre){
		this.nombre=nombre;
	}
}


