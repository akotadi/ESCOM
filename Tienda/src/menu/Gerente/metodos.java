/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu.Gerente;
import menu.Dueño.*;
import menu.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author luisc
 */
public class metodos {
    conexion con;
    public metodos(){
        con=new conexion();
    }
    public void NuevoCliente(String Nom, String AP, String AM, String telefono, String email, String calle, String colonia, String cp, String estado, String ca, String des){
        String SQL = "INSERT INTO " + "cliente(nombre, apPaterno, apMaterno, telefono, email, calle, colonia, cp, estado, creditoMaximo, descuento) " + "values(?,?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.setString(1, Nom);
            pstm1.setString(2, AP);
            pstm1.setString(3, AM);
            pstm1.setString(4, telefono);
            pstm1.setString(5, email);
            pstm1.setString(6, calle);
            pstm1.setString(7, colonia);
            pstm1.setString(8, cp);
            pstm1.setString(9, estado);
            pstm1.setString(10, ca);
            pstm1.setString(11, des);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se agrego el cliente con exito");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al agregar registro..");
        }   
    }
    public void NuevoEmpleado(String curp,String Nom, String AP, String AM, String calle, String colonia, String cp, String estado, String fechacont, String turno, String sueldo, String tipo, String contraseña){
        String SQL = "INSERT INTO " + "empleado(CURP, nombre, apPaterno, apMaterno, calle, colonia, cp, estado, fechaContratacion, turno, sueldo, tipo, contraseña) " + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.setString(1, curp);
            pstm1.setString(2, Nom);
            pstm1.setString(3, AP);
            pstm1.setString(4, AM);
            pstm1.setString(5, calle);
            pstm1.setString(6, colonia);
            pstm1.setString(7, cp);
            pstm1.setString(8, estado);
            pstm1.setString(9, fechacont);
            pstm1.setString(10, turno);
            pstm1.setString(11, sueldo);
            pstm1.setString(12, tipo);
            pstm1.setString(13, contraseña);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se agrego el cliente con exito");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al agregar registro..");
        }   
    }
    public void NuevoGerente(String cod, String fecha){
        String SQL = "INSERT INTO " + "gerente " + "values(?,?)";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.setString(1, cod);
            pstm1.setString(2, fecha);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se agrego el cliente con exito");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al agregar registro..");
        }   
    }
    public void NuevoMostrador(String cod, String num, String nom, String monto){
        String SQL = "INSERT INTO " + "mostrador " + "values(?,?,?,?)";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.setString(1, cod);
            pstm1.setString(2, monto);
            pstm1.setString(3, num);
            pstm1.setString(4, nom);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se agrego el cliente con exito");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al agregar registro..");
        }   
    }
    public void NuevoTelefono(String cod, String tel){
        String SQL = "INSERT INTO " + "telefonoempleado(telefono, Empleado_CURP) " + "values(?,?)";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.setString(1, tel);
            pstm1.setString(2, cod);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se agrego el cliente con exito");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al agregar registro..");
        }   
    }
    public void NuevoEmail(String cod, String email){
        String SQL = "INSERT INTO " + "emailempleado(email, Empleado_CURP) " + "values(?,?)";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.setString(1, email);
            pstm1.setString(2, cod);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se agrego el cliente con exito");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al agregar registro..");
        }   
    }
    
    public void aMerma(String cod, String cantidad, String local){
        String SQL = "INSERT INTO " + "merma " + "values(?,?,?)";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.setString(1, cod);
            pstm1.setString(2, cantidad);
            pstm1.setString(3, local);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se agrego la merma con exito");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al agregar merma..");
        }   
    }
    public void NuevoRefri(String num, String nombre, String modelo, String compa, String fecha){
        String SQL = "INSERT INTO " + "refrigerador " + "values(?,?,?,?,?)";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.setString(1, num);
            pstm1.setString(2, nombre);
            pstm1.setString(3, modelo);
            pstm1.setString(4, compa);
            pstm1.setString(5, fecha);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se agrego el registro con exito");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al agregar el registro..");
        }   
    }
    public void NuevaBascula(String num, String nombre, String modelo,String fecha){
        String SQL = "INSERT INTO " + "bascula " + "values(?,?,?,?)";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.setString(1, num);
            pstm1.setString(2, nombre);
            pstm1.setString(3, modelo);
            pstm1.setString(4, fecha);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se agrego el registro con exito");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al agregar el registro..");
        }   
    }
    public void NuevoProveedor(String Code, String nombre, String apa, String ama, String compañia, String email, String calle, String colonia, String cp, String estado){
        String SQL = "INSERT INTO " + "proveedor " + "values(?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.setString(1, Code);
            pstm1.setString(2, nombre);
            pstm1.setString(3, apa);
            pstm1.setString(4, ama);
            pstm1.setString(5, compañia);
            pstm1.setString(6, email);
            pstm1.setString(7, calle);
            pstm1.setString(8, colonia);
            pstm1.setString(9, cp);
            pstm1.setString(10, estado);
            
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se agrego el registro con exito...");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al agregar registro..");
        }   
    }
    public void NuevoLocal(String num, String nombre, String giro, String curp){
        String SQL = "INSERT INTO " + "local " + "values(?,?,?,?)";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.setString(1, num);
            pstm1.setString(2, nombre);
            pstm1.setString(3, giro);
            pstm1.setString(4, curp);
            
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se agrego el registro con exito...");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al agregar registro..");
        }   
    }
    public void NuevoProducto(String Code, String nombre, String marca, String tipo, String precio, String lmin, String lmax, String cantidad, String unidad, String des){
        String SQL = "INSERT INTO " + "producto " + "values(?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.setString(1, Code);
            pstm1.setString(2, nombre);
            pstm1.setString(3, marca);
            pstm1.setString(4, tipo);
            pstm1.setString(5, precio);
            pstm1.setString(6, lmin);
            pstm1.setString(7, lmax);
            pstm1.setString(8, cantidad);
            pstm1.setString(9, unidad);
            pstm1.setString(10, des);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se agrego el producto con exito...");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al agregar producto..");
        }   
    }
    public Object[][] getProcod(String codigo){
        int registros=0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM producto");
            ResultSet res=pstm.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        Object[][] data = new String[registros][4];
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "codigo, nombre, marca, precio" + " FROM producto" + " WHERE codigo = " + codigo + "");
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a,b,c,d;
            while(res.next()==true){
                a=res.getString("codigo");
                b=res.getString("nombre");
                c=res.getString("marca");
                d=res.getString("precio");
                
               
                data[i][0]=a;
                data[i][1]=b;
                data[i][2]=c;
                data[i][3]=d;
                
             
                i++;
            
            }
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    public Object[][] getMerma(){
        int registros=0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM merma");
            ResultSet res=pstm.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        Object[][] data = new String[registros][3];
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "*" + " FROM merma");
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a,b,c;
            while(res.next()==true){
                a=res.getString("Producto_idProducto");
                b=res.getString("cantidad");
                c=res.getString("proveedor");
                
               
                data[i][0]=a;
                data[i][1]=b;
                data[i][2]=c;
                
             
                i++;
            
            }
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    public Object[][] getBascula(){
        int registros=0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM bascula");
            ResultSet res=pstm.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        Object[][] data = new String[registros][4];
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "*" + " FROM bascula");
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a,b,c,d;
            while(res.next()==true){
                a=res.getString("Local_numero");
                b=res.getString("Local_nombre");
                c=res.getString("modelo");
                d=res.getString("fechaLlegada");
                
               
                data[i][0]=a;
                data[i][1]=b;
                data[i][2]=c;
                data[i][3]=d;
                
             
                i++;
            
            }
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    public Object[][] getRefri(){
        int registros=0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM refrigerador");
            ResultSet res=pstm.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        Object[][] data = new String[registros][5];
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "*" + " FROM refrigerador");
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a,b,c,d,e;
            while(res.next()==true){
                a=res.getString("Local_numero");
                b=res.getString("Local_nombre");
                c=res.getString("modelo");
                d=res.getString("compañia");
                e=res.getString("fechaLlegada");
                
               
                data[i][0]=a;
                data[i][1]=b;
                data[i][2]=c;
                data[i][3]=d;
                data[i][4]=e;
                
             
                i++;
            
            }
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    public Object[][] getLocal(){
        int registros=0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM local");
            ResultSet res=pstm.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        Object[][] data = new String[registros][4];
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "*" + " FROM local");
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a,b,c,d;
            while(res.next()==true){
                a=res.getString("numero");
                b=res.getString("nombre");
                c=res.getString("giroLocal");
                d=res.getString("gerente_CURP");
                
               
                data[i][0]=a;
                data[i][1]=b;
                data[i][2]=c;
                data[i][3]=d;
                
             
                i++;
            
            }
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    public Object[][] getGerente(){
        int registros=0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM empleado");
            ResultSet res=pstm.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        Object[][] data = new String[registros][7];
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "CURP, nombre, apPaterno, apMaterno,fechaContratacion,turno,sueldo" + " FROM empleado WHERE tipo = 'G'");
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a,b,c,d,e,f,g;
            while(res.next()==true){
                a=res.getString("CURP");
                b=res.getString("nombre");
                c=res.getString("apPaterno");
                d=res.getString("apMaterno");
                e=res.getString("fechaContratacion");
                f=res.getString("turno");
                g=res.getString("sueldo");
                
               
                data[i][0]=a;
                data[i][1]=b;
                data[i][2]=c;
                data[i][3]=d;
                
             
                i++;
            
            }
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    public Object[][] getMostrador(){
        int registros=0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM empleado e, mostrador m WHERE e.CURP = m.Empleado_CURP");
            ResultSet res=pstm.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        Object[][] data = new String[registros][8];
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "e.CURP, e.nombre, e.apPaterno, e.apMaterno, e.turno, e.sueldo, m.noLocalAsignado, m.nombreLocal" + " FROM empleado e, mostrador m WHERE e.CURP = m.Empleado_CURP AND e.tipo = 'M'");
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a,b,c,d,e,f,g,h;
            while(res.next()==true){
                a=res.getString("e.CURP");
                b=res.getString("e.nombre");
                c=res.getString("e.apPaterno");
                d=res.getString("e.apMaterno");
                e=res.getString("e.turno");
                f=res.getString("e.sueldo");
                g=res.getString("m.noLocalAsignado");
                h=res.getString("m.nombreLocal");
                
               
                data[i][0]=a;
                data[i][1]=b;
                data[i][2]=c;
                data[i][3]=d;
                data[i][4]=e;
                data[i][5]=f;
                data[i][6]=g;
                data[i][7]=h;
                
                
             
                i++;
            
            }
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    public Object[][] getPronom(String nombre){
        int registros=0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM producto");
            ResultSet res=pstm.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        Object[][] data = new String[registros][4];
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "codigo, nombre, marca, precio" + " FROM producto" + " WHERE nombre LIKE '" + nombre + "%'");
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a,b,c,d;
            while(res.next()==true){
                a=res.getString("codigo");
                b=res.getString("nombre");
                c=res.getString("marca");
                d=res.getString("precio");
                
               
                data[i][0]=a;
                data[i][1]=b;
                data[i][2]=c;
                data[i][3]=d;
                
             
                i++;
            
            }
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    public Object[][] getPronomv(String nombre, String rfc){
        int registros=0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM producto p, pp x WHERE p.codigo = x.Producto_idProducto");
            ResultSet res=pstm.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        Object[][] data = new String[registros][5];
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "p.codigo, p.nombre, p.tamañoCantidad, x.Proveedor_RFC, x.precio" + " FROM producto p, pp x" + " WHERE p.codigo = x.Producto_idProducto AND p.nombre LIKE '" + nombre + "%' AND x.Proveedor_RFC = '"+ rfc+"'");
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a,b,c,d,e;
            while(res.next()==true){
                a=res.getString("codigo");
                b=res.getString("nombre");
                c=res.getString("tamañoCantidad");
                d=res.getString("Proveedor_RFC");
                e=res.getString("precio");
               
                data[i][0]=a;
                data[i][1]=b;
                data[i][2]=c;
                data[i][3]=d;
                data[i][4]=e;
                
             
                i++;
            
            }
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    public Object[][] getProcodv(String cod, String rfc){
        int registros=0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM producto p, pp x WHERE p.codigo = x.Producto_idProducto");
            ResultSet res=pstm.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        Object[][] data = new String[registros][5];
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "p.codigo, p.nombre, p.tamañoCantidad, x.Proveedor_RFC, x.precio" + " FROM producto p, pp x" + " WHERE p.codigo = x.Producto_idProducto AND p.codigo LIKE '" + cod + "%' AND x.Proveedor_RFC = '"+ rfc+"'");
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a,b,c,d,e;
            while(res.next()==true){
                a=res.getString("codigo");
                b=res.getString("nombre");
                c=res.getString("tamañoCantidad");
                d=res.getString("Proveedor_RFC");
                e=res.getString("precio");
               
                data[i][0]=a;
                data[i][1]=b;
                data[i][2]=c;
                data[i][3]=d;
                data[i][4]=e;
                
             
                i++;
            
            }
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    public Object[][] getProsimp(String cod){
        int registros=0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM producto");
            ResultSet res=pstm.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        Object[][] data = new String[registros][3];
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "codigo, nombre, tamañoCantidad" + " FROM producto" + " WHERE nombre LIKE '" + cod + "%'");
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a,b,c,d,e;
            while(res.next()==true){
                a=res.getString("codigo");
                b=res.getString("nombre");
                c=res.getString("tamañoCantidad");
                
               
                data[i][0]=a;
                data[i][1]=b;
                data[i][2]=c;
                
                
             
                i++;
            
            }
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    public Object[][] getPronome(String nombre, String local){
        int registros=0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM producto p, lp x WHERE p.codigo = x.Producto_idProducto");
            ResultSet res=pstm.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        Object[][] data = new String[registros][5];
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "p.codigo, p.nombre, p.precio, p.tamañoCantidad, x.existencia" + " FROM producto p, lp x" + " WHERE p.codigo = x.Producto_idProducto AND p.nombre LIKE '" + nombre + "%' AND x.Local_nombre = '"+ local+"'");
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a,b,c,d,e;
            while(res.next()==true){
                a=res.getString("codigo");
                b=res.getString("nombre");
                c=res.getString("precio");
                d=res.getString("tamañoCantidad");
                e=res.getString("existencia");
               
                data[i][0]=a;
                data[i][1]=b;
                data[i][2]=c;
                data[i][3]=d;
                data[i][4]=e;
                
             
                i++;
            
            }
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    public Object[][] getProcode(String codigo, String local){
        int registros=0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM producto p, lp x WHERE p.codigo = x.Producto_idProducto");
            ResultSet res=pstm.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        Object[][] data = new String[registros][5];
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "p.codigo, p.nombre, p.precio, p.tamañoCantidad, x.existencia" + " FROM producto p, lp x" + " WHERE p.codigo = x.Producto_idProducto AND p.codigo = " + codigo + " AND x.Local_nombre = '"+ local+"'");
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a,b,c,d,e;
            while(res.next()==true){
                a=res.getString("codigo");
                b=res.getString("nombre");
                c=res.getString("precio");
                d=res.getString("tamañoCantidad");
                e=res.getString("existencia");
               
                data[i][0]=a;
                data[i][1]=b;
                data[i][2]=c;
                data[i][3]=d;
                data[i][4]=e;
                
             
                i++;
            
            }
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    public Object[][] getPropre(String r1, String r2){
        int registros=0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM producto");
            ResultSet res=pstm.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        Object[][] data = new String[registros][4];
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "codigo, nombre, marca, precio" + " FROM producto" + " WHERE precio BETWEEN " + r1 + " AND " + r2);
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a,b,c,d;
            while(res.next()==true){
                a=res.getString("codigo");
                b=res.getString("nombre");
                c=res.getString("marca");
                d=res.getString("precio");
                
               
                data[i][0]=a;
                data[i][1]=b;
                data[i][2]=c;
                data[i][3]=d;
                
             
                i++;
            
            }
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    public Object[][] getPedido(){
        int registros=0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM pedido");
            ResultSet res=pstm.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        Object[][] data = new String[registros][9];
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "*" + " FROM pedido");
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a,b,c,d,e,f,g,h,j;
            while(res.next()==true){
                a=res.getString("idPedido");
                b=res.getString("montoTotal");
                c=res.getString("noProductos");
                d=res.getString("fechaRealizacion");
                e=res.getString("fechaEntrega");
                f=res.getString("cantidadProdcuto");
                g=res.getString("Proveedor_RFC");
                h=res.getString("Gerente_CURP");
                j=res.getString("Producto_idProducto");
                
               
                data[i][0]=a;
                data[i][1]=b;
                data[i][2]=c;
                data[i][3]=d;
                
             
                i++;
            
            }
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
     public Object[][] getEmpleado(){
        int registros=0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM empleado");
            ResultSet res=pstm.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        Object[][] data = new String[registros][13];
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "*" + " FROM empleado");
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a,b,c,d,e,f,g,h,j,k,l,m,n;
            while(res.next()==true){
                a=res.getString("CURP");
                b=res.getString("nombre");
                c=res.getString("apPaterno");
                d=res.getString("apMaterno");
                e=res.getString("calle");
                f=res.getString("colonia");
                g=res.getString("cp");
                h=res.getString("estado");
                j=res.getString("fechaContratacion");
                k=res.getString("turno");
                l=res.getString("sueldo");
                m=res.getString("tipo");
                n=res.getString("contraseña");
                
               
                data[i][0]=a;
                data[i][1]=b;
                data[i][2]=c;
                data[i][3]=d;
                data[i][4]=e;
                data[i][5]=f;
                data[i][6]=g;
                data[i][7]=h;
                data[i][8]=j;
                data[i][9]=k;
                data[i][10]=l;
                data[i][11]=m;
                data[i][12]=n;
              
                
             
                i++;
            
            }
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    public void BoCliente(String Code){
        String SQL = "DELETE FROM " + "cliente " + "WHERE idCliente = " + Code;
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se borro el registro con exito...");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al borrar registro..");
        }   
    }
    public void BoPedido(String Code){
        String SQL = "DELETE FROM " + "pedido " + "WHERE idPedido = " + Code;
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se borro el registro con exito...");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al borrar registro..");
        }   
    }
    public void BoVenta(String Code){
        String SQL = "DELETE FROM " + "venta " + "WHERE idVenta = " + Code;
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se borro el registro con exito...");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al borrar registro..");
        }   
    }
    public void BoMostrador(String Code){
        String SQL = "DELETE FROM " + "mostrador " + "WHERE Empleado_CURP = '"+ Code+"'";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se borro el registro con exito...");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al borrar registro..");
        }   
    }
    
    public void BoEmpleado(String Code){
        String SQL = "DELETE FROM " + "empleado " + "WHERE CURP = '" + Code+"'";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se borro el registro con exito...");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al borrar registro..");
        }   
    }
    public void BoGerente(String Code){
        String SQL = "DELETE FROM " + "gerente " + "WHERE Empleado_CURP = '" + Code+"'";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se borro el registro con exito...");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al borrar registro..");
        }   
    }
    public void BoMerma(String Code){
        String SQL = "DELETE FROM " + "merma " + "WHERE Producto_idProducto = " + Code;
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se borro el registro con exito...");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al borrar registro..");
        }   
    }
    public void BoBascula(String num, String nom, String modelo){
        String SQL = "DELETE FROM " + "bascula " + "WHERE Local_numero = " + num+" AND Local_nombre = '"+nom+"' AND modelo = '"+modelo+"'";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se borro el registro con exito...");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al borrar registro..");
        }   
    }
    public void BoLocal(String num, String nom){
        String SQL = "DELETE FROM " + "local " + "WHERE Local_numero = " + num+" AND Local_nombre = '"+nom+"'";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se borro el registro con exito...");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al borrar registro..");
        }   
    }
    public void BoRefri(String num, String nom, String modelo){
        String SQL = "DELETE FROM " + "refrigerador " + "WHERE Local_numero = " + num+" AND Local_nombre = '"+nom+"' AND modelo = '"+modelo+"'";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se borro el registro con exito...");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al borrar registro..");
        }   
    }
    public void BoProducto(String Code){
        String SQL = "DELETE FROM " + "producto " + "WHERE codigo = " + Code;
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se borro el registro con exito...");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al borrar registro..");
        }   
    }
    public void BoProveedor(String Code){
        String SQL = "DELETE FROM " + "proveedor " + "WHERE RFC = " + Code;
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se borro el registro con exito...");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al borrar registro..");
        }   
    }
    public Object[][] getDatosCliente(){
        int registros=0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM cliente");
            ResultSet res=pstm.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        Object[][] data = new String[registros][13];
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "*" + " FROM cliente" + " ORDER BY idCliente");
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a,b,c,d,e,f,g,h,j,k,l,m,n;
            while(res.next()==true){
                a=res.getString("idCliente");
                b=res.getString("nombre");
                c=res.getString("apPaterno");
                d=res.getString("apMaterno");
                e=res.getString("telefono");
                f=res.getString("email");
                g=res.getString("calle");
                h=res.getString("colonia");
                j=res.getString("cp");
                k=res.getString("estado");
                l=res.getString("creditoActual");
                m=res.getString("creditoMaximo");
                n=res.getString("descuento");
                data[i][0]=a;
                data[i][1]=b;
                data[i][2]=c;
                data[i][3]=d;
                data[i][4]=e;
                data[i][5]=f;
                data[i][6]=g;
                data[i][7]=h;
                data[i][8]=j;
                data[i][9]=k;
                data[i][10]=l;
                data[i][11]=m;
                data[i][12]=n;
                
             
                i++;
            }
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    public Object[][] getDatosProveedor(){
        int registros=0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM proveedor");
            ResultSet res=pstm.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        Object[][] data = new String[registros][10];
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "*" + " FROM proveedor" + " ORDER BY RFC");
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a,b,c,d,e,f,g,h,j,k;
            while(res.next()==true){
                a=res.getString("RFC");
                b=res.getString("nombre");
                c=res.getString("apPaterno");
                d=res.getString("apMaterno");
                e=res.getString("compañia");
                f=res.getString("email");
                g=res.getString("calle");
                h=res.getString("colonia");
                j=res.getString("cp");
                k=res.getString("estado");
                data[i][0]=a;
                data[i][1]=b;
                data[i][2]=c;
                data[i][3]=d;
                data[i][4]=e;
                data[i][5]=f;
                data[i][6]=g;
                data[i][7]=h;
                data[i][8]=j;
                data[i][9]=k;
                i++;
            }
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    public Object[][] getDatosProductos(){
        int registros=0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM producto");
            ResultSet res=pstm.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        Object[][] data = new String[registros][10];
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "*" + " FROM producto" + " ORDER BY Cgd_producto");
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a,b,c,d,e,f,g,h,j,k;
            while(res.next()==true){
                a=res.getString("codigo");
                b=res.getString("nombre");
                c=res.getString("marca");
                d=res.getString("tipoVenta");
                e=res.getString("precio");
                f=res.getString("limiteMin");
                g=res.getString("limiteMax");
                h=res.getString("tamañoCantidad");
                j=res.getString("tamañoUnidad");
                k=res.getString("descuento");
                
               
                data[i][0]=a;
                data[i][1]=b;
                data[i][2]=c;
                data[i][3]=d;
                data[i][4]=e;
                data[i][5]=f;
                data[i][6]=g;
                data[i][7]=h;
                data[i][8]=j;
                data[i][9]=k;
                
             
                i++;
            }
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    public Object[][] getEmpt(String cod){
        int registros=0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM telefonoempleado WHERE Empleado_CURP = '"+cod+"'");
            ResultSet res=pstm.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        Object[][] data = new String[registros][1];
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "telefono" + " FROM telefonoempleado" + " WHERE Empleado_CURP = '"+cod+"'");
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a;
            while(res.next()==true){
                a=res.getString("telefono");
                
               
                data[i][0]=a;
                
             
                i++;
            }
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    public Object[][] getEmpe(String cod){
        int registros=0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM emailempleado WHERE Empleado_CURP = '"+cod+"'");
            ResultSet res=pstm.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        Object[][] data = new String[registros][1];
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "email" + " FROM emailempleado" + " WHERE Empleado_CURP = '"+cod+"'");
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a;
            while(res.next()==true){
                a=res.getString("email");
                
               
                data[i][0]=a;
                
             
                i++;
            }
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    public Object[][] getDatosNom(String nombre){
        int registros=0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM cliente");
            ResultSet res=pstm.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        Object[][] data = new String[registros][6];
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "idCliente, nombre, apPaterno, apMaterno, telefono, email" + " FROM cliente" + " WHERE apPaterno LIKE '" + nombre + "%'");
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a,b,c,d,e,f;
            while(res.next()==true){
                a=res.getString("idCliente");
                b=res.getString("nombre");
                c=res.getString("apPaterno");
                d=res.getString("apMaterno");
                e=res.getString("telefono");
                f=res.getString("email");
               
                data[i][0]=a;
                data[i][1]=b;
                data[i][2]=c;
                data[i][3]=d;
                data[i][4]=e;
                data[i][5]=f;
             
                i++;
            
            }
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    public Object[][] getEmpAp(String nombre){
        int registros=0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM empleado m, emailempleado e, telefonoempleado t WHERE e.Empleado_CURP = m.CURP AND m.CURP = t.Empleado_CURP");
            ResultSet res=pstm.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        Object[][] data = new String[registros][6];
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "m.CURP, m.nombre, m.apPaterno, m.apMaterno, t.telefono, e.email" + " FROM empleado m, emailempleado e, telefonoempleado t" + " WHERE e.Empleado_CURP = m.CURP AND m.CURP = t.Empleado_CURP AND apPaterno LIKE '" + nombre + "%'");
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a,b,c,d,e,f;
            while(res.next()==true){
                a=res.getString("m.CURP");
                b=res.getString("m.nombre");
                c=res.getString("m.apPaterno");
                d=res.getString("m.apMaterno");
                e=res.getString("t.telefono");
                f=res.getString("e.email");
               
                data[i][0]=a;
                data[i][1]=b;
                data[i][2]=c;
                data[i][3]=d;
                data[i][4]=e;
                data[i][5]=f;
             
                i++;
            
            }
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    public Object[][] getEmpCURP(String nombre){
        int registros=0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM empleado m, emailempleado e, telefonoempleado t WHERE e.Empleado_CURP = m.CURP AND m.CURP = t.Empleado_CURP");
            ResultSet res=pstm.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        Object[][] data = new String[registros][6];
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "m.CURP, m.nombre, m.apPaterno, m.apMaterno, t.telefono, e.email" + " FROM empleado m, emailempleado e, telefonoempleado t" + " WHERE e.Empleado_CURP = m.CURP AND m.CURP = t.Empleado_CURP AND m.CURP = '" + nombre + "'");
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a,b,c,d,e,f;
            while(res.next()==true){
                a=res.getString("m.CURP");
                b=res.getString("m.nombre");
                c=res.getString("m.apPaterno");
                d=res.getString("m.apMaterno");
                e=res.getString("t.telefono");
                f=res.getString("e.email");
               
                data[i][0]=a;
                data[i][1]=b;
                data[i][2]=c;
                data[i][3]=d;
                data[i][4]=e;
                data[i][5]=f;
             
                i++;
            
            }
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    public Object[][] getDatosRFC(String rfc){
        int registros=0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM Cliente");
            ResultSet res=pstm.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        Object[][] data = new String[registros][6];
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "idCliente, nombre, apPaterno, apMaterno, telefono, email" + " FROM cliente" + " WHERE idCliente LIKE '" + rfc + "%'");
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a,b,c,d,e,f;
            while(res.next()==true){
                a=res.getString("idCliente");
                b=res.getString("nombre");
                c=res.getString("apPaterno");
                d=res.getString("apMaterno");
                e=res.getString("telefono");
                f=res.getString("email");
               
                data[i][0]=a;
                data[i][1]=b;
                data[i][2]=c;
                data[i][3]=d;
                data[i][4]=e;
                data[i][5]=f;
             
                i++;
            
            }
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    public ResultSet bCliente(String cod){
        ResultSet res = null;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT nombre, apPaterno, apMaterno, telefono, email, calle, colonia, cp, estado, creditoMaximo, descuento FROM cliente WHERE idCliente = "+cod);
            res=pstm.executeQuery();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        
        return res;
    }
    public ResultSet bProveedor(String cod){
        ResultSet res = null;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT * FROM proveedor WHERE RFC = "+cod);
            res=pstm.executeQuery();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        
        return res;
    }
    public ResultSet bBascula(String lnum,String lnom){
        ResultSet res = null;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT * FROM bascula WHERE Local_numero = "+lnum+" AND Local_nombre LIKE '"+lnom+"'");
            res=pstm.executeQuery();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        
        return res;
    }
    public ResultSet bProducto(String cod){
        ResultSet res = null;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT * FROM producto WHERE codigo = "+cod);
            res=pstm.executeQuery();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        
        return res;
    }
    public ResultSet bEmpleado(String cod){
        ResultSet res = null;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT * FROM empleado WHERE CURP = '"+cod+"'");
            res=pstm.executeQuery();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        
        return res;
    }
    public ResultSet bEmpG(String cod){
        ResultSet res = null;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT fechaAsignacion FROM gerente WHERE Empleado_CURP = '"+cod+"'");
            res=pstm.executeQuery();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        
        return res;
    }
    public ResultSet bEmpMos(String cod){
        ResultSet res = null;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT * FROM mostrador WHERE Empleado_CURP = '"+cod+"'");
            res=pstm.executeQuery();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return res;
    }
    public ResultSet bEmpt(String cod){
        ResultSet res = null;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT * FROM  telefonoempleado WHERE Empleado_CURP = '"+cod+"'");
            res=pstm.executeQuery();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        
        return res;
    }
    public ResultSet bEmpe(String cod){
        ResultSet res = null;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT * FROM  emailempleado WHERE Empleado_CURP = '"+cod+"'");
            res=pstm.executeQuery();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        
        return res;
    }
    public ResultSet bRefri(String num, String nom){
        ResultSet res = null;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT * FROM refrigerador WHERE Local_numero = "+num+" AND Local_nombre LIKE '"+nom+"'");
            res=pstm.executeQuery();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        
        return res;
    }
    public ResultSet b1Local(String cod){
        ResultSet res = null;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT * FROM local WHERE Local_numero = "+cod);
            res=pstm.executeQuery();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        
        return res;
    }
    public ResultSet b2Local(String cod){
        ResultSet res = null;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT * FROM local WHERE Local_nombre LIKE '"+cod+"'");
            res=pstm.executeQuery();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        
        return res;
    }
    public ResultSet comboLocal(){
        ResultSet res = null;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT l.Local_Nombre FROM lp l, producto p WHERE l.Producto_idProducto = p.codigo");
            res=pstm.executeQuery();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        
        return res;
    }
    public ResultSet comboProveedor(){
        ResultSet res = null;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT RFC FROM proveedor");
            res=pstm.executeQuery();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        
        return res;
    }
    public void acCLiente(String Code, String telefono, String email, String calle, String colonia, String cp, String estado, String ca, String des){
        String SQL = "UPDATE " + "cliente " + " SET telefono = " + telefono +", email  = '" + email +"', calle  = '" + calle +"', colonia  = '" + colonia +"', cp  = " + cp +", estado  = '" + estado +"', creditoMaximo  = " + ca +", descuento  = " + des +" WHERE idCliente = " + Code;
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se actualizo el registro con exito...");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al actualizar registro..");
        }   
    }
    public void acEmpleado(String Code, String calle, String colonia, String cp, String estado, String fecha, String turno, String sueldo,String tipo, String contraseña){
        String SQL = "UPDATE " + "empleado " + " SET calle  = '" + calle +"', colonia  = '" + colonia +"', cp  = " + cp +", estado  = '" + estado +"', fechaContratacion  = '" + fecha +"' , turno  = '" + turno +"', sueldo = "+sueldo+", tipo = '"+tipo+"', contraseña = '" + contraseña+"' WHERE CURP = '" + Code+"'";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se actualizo el registro con exito...");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al actualizar registro..");
        }   
    }
    public void acEmpl(String Code, String calle, String colonia, String cp, String estado, String fecha, String turno, String sueldo,String contraseña){
        String SQL = "UPDATE " + "empleado " + " SET calle  = '" + calle +"', colonia  = '" + colonia +"', cp  = " + cp +", estado  = '" + estado +"', fechaContratacion  = '" + fecha +"' , turno  = '" + turno +"', sueldo = "+sueldo+", contraseña = '" + contraseña+"' WHERE CURP = '" + Code+"'";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se actualizo el registro con exito...");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al actualizar registro..");
        }   
    }
    public void acEmpe(String Code, String tel1, String tel2){
        String SQL = "UPDATE " + "emailempleado " + " SET email  = '" + tel1 +"' WHERE email = '"+tel2+"'";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se actualizo el registro con exito...");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al actualizar registro..");
        }   
    }
    public void acEmpt(String Code, String tel1, String tel2){
        String SQL = "UPDATE " + "telefonoempleado " + " SET telefono  = '" + tel1 +"' WHERE telefono = '"+tel2+"'";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se actualizo el registro con exito...");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al actualizar registro..");
        }   
    }
    
    public void acMostrador(String Code, String montoCorte, String lnum, String lnom){
        String SQL = "UPDATE " + "mostrador " + " SET montoCorte  = " + montoCorte +", noLocalAsignado  = " + lnum +", nombreLocal  = '" + lnom +"' WHERE Empleado_CURP = '" + Code+"'";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se actualizo el registro con exito...");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al actualizar registro..");
        }   
    }
    public void acMost(String Code, String lnum, String lnom){
        String SQL = "UPDATE " + "mostrador " + " SET noLocalAsignado  = " + lnum +", nombreLocal  = '" + lnom +"' WHERE Empleado_CURP = '" + Code+"'";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se actualizo el registro con exito...");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al actualizar registro..");
        }   
    }
    public void acGerente(String Code, String fecha){
        String SQL = "UPDATE " + "gerente " + " SET fechaAsignacion  = '" +fecha+"' WHERE Empleado_CURP = '" + Code+"'";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se actualizo el registro con exito...");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al actualizar registro..");
        }   
    }
    public void acBascula(String lnum, String modelo, String fecha, String lnom){
        String SQL = "UPDATE " + "bascula " + " SET modelo = " + modelo +", fechaLlegada  = '" + fecha +"' WHERE Local_numero = " + lnum + " AND Local_nombre LIKE '"+lnom+"'";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se actualizo el registro con exito...");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al actualizar registro..");
        }   
    }
    public void acLocal(String lnum, String lnom, String giro, String gerente){
        String SQL = "UPDATE " + "local " + " SET giroLocal = " + giro +", gerente_CURP  = '" + gerente +"' WHERE Local_numero = " + lnum + " AND Local_nombre LIKE '"+lnom+"'";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se actualizo el registro con exito...");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al actualizar registro..");
        }   
    }
    public void acRefri(String lnum, String lnom, String modelo, String compañia, String fecha){
        String SQL = "UPDATE " + "refrigerador " + " SET modelo = '" + modelo +"', compañia  = '" + compañia +"', fechaLlegada = '"+fecha+"' WHERE Local_numero = " + lnum + " AND Local_nombre LIKE '"+lnom+"'";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se actualizo el registro con exito...");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al actualizar registro..");
        }   
    }
    public void acProveedor(String Code, String compa, String email, String calle, String colonia, String cp, String estado){
        String SQL = "UPDATE " + "proveedor " + " SET compañia = '" + compa +"', email  = '" + email +"', calle  = '" + calle +"', colonia  = '" + colonia +"', cp  = " + cp +", estado  = '" + estado +"' WHERE RFC = " + Code;
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se actualizo el registro con exito...");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al actualizar registro..");
        }   
    }
    public void acProducto(String Code, String marca, String venta, String precio, String lmin, String lmax, String can, String unidad, String des){
        String SQL = "UPDATE " + "cliente " + " SET marca = '" + marca +"', tipoVenta  = '" + venta +"', precio  = " + precio +", limiteMin  = " + lmin +", limiteMax  = " + lmax +", tamañoCantidad  = " + can +", tamañoUnidad  = " + unidad +", descuento  = " + des +" WHERE codigo = " + Code;
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se actualizo el registro con exito...");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al actualizar registro..");
        }   
    }
    public Object[][] getDatosCodP(String codigo){
        int registros=0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM Productos");
            ResultSet res=pstm.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        Object[][] data = new String[registros][9];
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "*" + " FROM Cliente" + " WHERE Cgd_producto LIKE '" + codigo + "%'");
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a,b,c,d;
            while(res.next()==true){
                a=res.getString("Cgd_producto");
                b=res.getString("Descripcion");
                c=res.getString("Precio");
                d=res.getString("Clave_surtidor");
                
               
                data[i][0]=a;
                data[i][1]=b;
                data[i][2]=c;
                data[i][3]=d;
                
             
                i++;
            }
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    public Object[][] getDatosNomP(String nombre){
        int registros=0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM Productos");
            ResultSet res=pstm.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        Object[][] data = new String[registros][9];
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "*" + " FROM Cliente" + " WHERE Cgd_producto LIKE '" + nombre + "%'");
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a,b,c,d;
            while(res.next()==true){
                a=res.getString("Cgd_producto");
                b=res.getString("Descripcion");
                c=res.getString("Precio");
                d=res.getString("Clave_surtidor");
                
               
                data[i][0]=a;
                data[i][1]=b;
                data[i][2]=c;
                data[i][3]=d;
                
             
                i++;
            }
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    public Object[][] getDatosSur(String codigo){
        int registros=0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM surtidores");
            ResultSet res=pstm.executeQuery();
            res.next();
            registros=res.getInt("total");
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        Object[][] data = new String[registros][9];
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "*" + " FROM surtidores" + " WHERE Clave_surtidor LIKE '" + codigo + "%'");
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a,b,c;
            while(res.next()==true){
                a=res.getString("Clave_surtidor");
                b=res.getString("Tipo_producto");
                c=res.getString("Descripcion");
                
                
                data[i][0]=a;
                data[i][1]=b;
                data[i][2]=c;
                
                i++;
            }
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    
}

    
