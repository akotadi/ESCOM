class Agregado
{
Object  a[] = new Object[3];

    public static void main(String args[])
    {


        a[0] = new Float(3.1416);
        a[1] = new String("hola");
        a[2] = new Integer(1998);
        for( int i = 0; i < 3; i++ )
            System.out.println(a[i].toString());

  }
}

