public class DiscoVO {

	private int id;
	private String titulo;
	private String genero;
	private short existencia;
	private float precio;
	
	
	DiscoVO(String titulo,String genero,float precio,short existencia){
		this.titulo = titulo;
		this.genero = genero;
		this.existencia = existencia;
		this.precio = precio;
	}
	public short getExistencia() {
		return existencia;
	}
	public void setExistencia(short existencia) {
		this.existencia = existencia;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	
}
