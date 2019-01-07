public class Propiedades
{
  public static void main(String args[])
  {
  String[] nombrePropiedad = // PROPIEDADES LEGIBLES PARA APPLETS Y CONSOLA
                           {"file.separator" ,"line.separator" ,"path.separator"
                           ,"java.class.version" ,"java.vendor" ,"java.vendor.url"
                           ,"java.version" ,"os.name" ,"os.arch" ,"os.version"
                           // EXCLUSIVAS PARA CONSOLA
                           ,"java.class.path" ,"java.home" ,"user.dir" ,"user.name"
                           };
  String valor ;

      for( int i = 0; i < nombrePropiedad.length; i++)
      {
          try
          {
              valor = System.getProperty(nombrePropiedad[i]);
              System.out.println(nombrePropiedad[i] + " = " + valor);
          }
          catch(Exception e)
          {
              System.out.println(
              "No lei " + nombrePropiedad[i] + " porque " + e.toString());
          }
      }
    }
}
