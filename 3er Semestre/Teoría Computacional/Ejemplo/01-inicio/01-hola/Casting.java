/** Casting.java

    prueba de casting y promoción
*/

class Casting
{
    public static void main(String args[])
    {
    	short x;
        System.out.println("area = "+(3.1416 * 3 * 3));
    	x=(short)(3.1416 * 3 * 3);
        System.out.println("area = "+x);
        x=(short)3.1416 * 3 * 3;
        System.out.println("area = "+x);
    }
}