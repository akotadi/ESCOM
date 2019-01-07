class Factorial
{
    static int factorial(int n)
    {
        if( n < 2 )
            return(1);
        else
            return n * factorial(n-1);
    }
    public static void main (String []arg)
    {
        System.out.println("factorial de " + arg[0] + " = " + factorial(Integer.parseInt(arg[0])) );
    }
}
