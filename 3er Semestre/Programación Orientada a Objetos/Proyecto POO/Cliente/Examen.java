import java.text.*;
import java.time.*;
import java.util.*;

public class Examen{

	String titulo, fecha, materia;
	int ultimaPregunta, calificacion, idUsuario;
	Vector<Reactivo> Questions;
	Vector vAnswers;

	public Examen(String titulo, int idUsuario, int ultimaPregunta, int calificacion, Vector<Reactivo> Questions, Vector Answers, String materia){

		System.out.println("Examen 1");

		this.titulo = titulo;
		this.idUsuario = idUsuario;
		this.ultimaPregunta = ultimaPregunta;
		this.calificacion = calificacion;
		this.Questions = Questions;
		this.materia = materia;
		vAnswers = Answers;
		System.out.println(1);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		fecha = (String)dateFormat.format(date);

		System.out.println(fecha);

		System.out.println(2);

		/*LocalDate localDate = LocalDate.now();
        fecha = (String)(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate));*/
	}

	public Examen(String titulo, int idUsuario,  Vector<Reactivo> Questions, String materia){
		
		System.out.println("Examen 2");

		this.titulo = titulo;
		this.idUsuario = idUsuario;
		ultimaPregunta = 0;
		calificacion = 0;
		this.materia = materia;
		this.Questions = Questions;
		vAnswers = new Vector(10);

		System.out.println(1);
		System.out.println(Questions.elementAt(1).getPregunta());

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		fecha = (String)dateFormat.format(date);

		System.out.println(fecha);

		System.out.println(2);

		/*LocalDate localDate = LocalDate.now();
        fecha = (String)(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate));*/
	}
}
