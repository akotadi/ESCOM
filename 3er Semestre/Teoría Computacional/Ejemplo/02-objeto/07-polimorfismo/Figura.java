class Circulo
{
   void dibuja()
   {
       System.out.println("Soy un circulo");
   }
}

class Rectangulo
{
   void dibuja()
   {
       System.out.println("Soy un Rectangulo");
   }
}

class Triangulo
{
   void dibuja()
   {
       System.out.println("Soy un Triangulo");
   }
}

class Figura
{
   public static void main(String A[])
   {
   Circulo a = new Circulo();
   Rectangulo b = new Rectangulo();
   Triangulo c = new Triangulo();

      System.out.println(A[0]+ A[1]);
      a.dibuja();
      b.dibuja();
      c.dibuja();
   }
}








