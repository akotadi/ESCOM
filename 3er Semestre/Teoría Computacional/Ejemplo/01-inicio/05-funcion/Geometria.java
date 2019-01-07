import java.text.*;
import java.io.*;

class Cuadrado
{
	double lado,area;
	
	Cuadrado(double _lado)
	{
		lado = _lado;
	}
	
	double area()
	{
		area = lado * lado;
		return area;
	}
}

class Circulo
{
	double r;
	
	Circulo(double _r)
	{
		r = _r;
	}

	double area()
	{
		double a;
		a = Math.PI*r*r;
		return a;
	}
	// METODO DE CLASE NO necesita de un objeto para invocarse
	static double area(float _r) 
	{
		return Math.PI*Math.pow(_r ,2);		
	}
}

class Geometria
{
	public static void main(String arg[]) throws IOException
	{
 	BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
 	NumberFormat precision4 = NumberFormat.getInstance();
        precision4.setMaximumFractionDigits(4);
        precision4.setMinimumFractionDigits(2);  


		System.out.print("Radio =  ");
	    System.out.println("Area circulo = " + precision4.format(
	    	 Circulo.area(Float.parseFloat(consola.readLine()) )));
		


		System.out.print("Lado del Cuadrado =  ");
		Cuadrado q = new Cuadrado(Double.parseDouble(consola.readLine()));
		
    	
		System.out.print("Radio: ");
		Circulo c = new Circulo(Double.parseDouble(consola.readLine()));
			
		System.out.println("Area circulo = " + precision4.format( c.area()) + 
		" " + c.area());
	
		System.out.println("Area cuadrado = " +precision4.format( q.area())
		+ " " + q.area());
	}
}