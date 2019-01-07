import java.io.*;

public class ArchivoRandom
{
RandomAccessFile archivo ;
String nombre;

    public ArchivoRandom(String _nombre)
    {
        nombre = _nombre;
        try
        {
            archivo = new RandomAccessFile(_nombre ,"rw");
        }
        catch(FileNotFoundException e)
        {
            System.out.println("No abrí el archivo " + nombre);
        }
    }

    public void cierra()
    {
        try
        {
            archivo.close();
        }
        catch(IOException e)
        {
            System.out.println("Falla al cerrar el archivo " + nombre);
        }
    }

    public void escribe(String _cadena)
    {
        try
        {
            archivo.writeBytes(_cadena);
        }
        catch(IOException e)
        {
            System.out.println("Falla al escribir en "+nombre);
        }
    }

    public void escribe(long _largo)
    {
        try
        {
            archivo.writeLong(_largo);
        }
        catch(IOException e)
        {
            System.out.println("Falla al escribir en "+nombre);
        }
    }

    public void escribe(int _entero)
    {
        try
        {
            archivo.writeInt(_entero);
        }
        catch(IOException e)
        {
            System.out.println("Falla al escribir en "+nombre);
        }
    }

    public long leeLong()
    {
        try
        {
            return archivo.readLong();
        }
        catch(IOException e)
        {
            System.out.println("Falla al leer en "+nombre);
        }
        return -1;
    }

    public int leeInt()
    {
        try
        {
            return archivo.readInt();
        }
        catch(IOException e)
        {
            System.out.println("Falla al leer en "+nombre);
        }
        return -1;
    }

    public String leeString(int _talla)
    {
    byte dato[] = new byte[_talla];

        try
        {
            archivo.read(dato);
            return new String(dato);
        }
        catch(IOException e)
        {
            System.out.println("Falla al leer en "+nombre);
        }
        return null;
    }

    public long longitud()
    {
        try
        {
            return archivo.length();
        }
        catch(IOException e)
        {
            System.out.println("Falla en la longitud de "+nombre);
        }
        return -1;
    }

    public void posicionarse(long _posicion)
    {
        try
        {
            archivo.seek(_posicion);
        }
        catch(IOException e)
        {
            System.out.println("Falla al posicionarse en "+nombre);
        }
   }
}