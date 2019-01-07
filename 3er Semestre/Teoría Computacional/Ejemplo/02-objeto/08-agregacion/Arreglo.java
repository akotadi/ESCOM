/** Arreglo.java 

    ILUSTRA EL USO DE ARREGLOS EN JAVA

2003 08 Jesus Olivares
*/

class Arreglo
{
    public static void main(String args[])
    {
    // VECTORES DIMENSION 1
    int vector[] = new int[10] ; 
    int constante[] = {1,2,3,5,7,11,13,17};

    // MATRICES DIMENSION 3
    int matriz[][] = new int[3][4];
    int dato[][] = {{1,2,3},{2,4,6}};

    // ARREGLOS MULTIDIMENSIONALES
    int multiple[][][] = new int [5][2][4]; //
    int multi[][][] = { {{1,2},{3,4}}, {{5,6},{7,8}} };

    int i ,ren ,col ,x ,y, z;

        for( i = 0; i < 10; i++ )
        {
            vector[i] = i * i;
        }

        System.out.println("Vectores"); // DESPLIEGA

        for( i = 0; i < 10; i++ )
            System.out.print(vector[i] + " ");
        System.out.println();

        for( i = 0; i < constante.length; i++ )
            System.out.print(constante[i] + " ");
        System.out.println();

        System.out.println(dato.length);
        for( ren = 0; ren < 2; ren++ )
        {
            for( col = 0; col < 3; col++ )
                System.out.print(dato[ren][col] + " ");
            System.out.println();
        }
        System.out.println(9 ^ 15);
    }
}
