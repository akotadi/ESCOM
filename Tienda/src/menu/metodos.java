/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;
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
    public void NuevoCliente(String Code, String Nom, String AP, String AM, String Direccion){
        String SQL = "INSERT INTO " + "cliente(Cgd_cliente, Nombre, A_pat, A_mat, Direccion) " + "values(?,?,?,?,?)";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.setString(1, Code);
            pstm1.setString(2, Nom);
            pstm1.setString(3, AP);
            pstm1.setString(4, AM);
            pstm1.setString(5, Direccion);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se agrego el registro con exito...");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al agregar registro..");
        }   
    }
    public void NuevoSurtidor(String Code, String Tip, String Des){
        String SQL = "INSERT INTO " + "surtidores(Clave_surtidor, Tipo_producto, Descripcion) " + "values(?,?,?)";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.setString(1, Code);
            pstm1.setString(2, Tip);
            pstm1.setString(3, Des);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se agrego el registro con exito...");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al agregar registro..");
        }   
    }
    public void NuevoProducto(String Code, String Des, String Prec, String Sur){
        String SQL = "INSERT INTO " + "productos(Cgd_producto, Descripcion, Precio, Clave_surtidor) " + "values(?,?,?,?)";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.setString(1, Code);
            pstm1.setString(2, Des);
            pstm1.setString(3, Prec);
            pstm1.setString(4, Sur);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se agrego el registro con exito...");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al agregar registro..");
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
        Object[][] data = new String[registros][5];
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "*" + " FROM cliente" + " ORDER BY Cgd_cliente");
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a,b,c,d,e;
            while(res.next()==true){
                a=res.getString("Cgd_cliente");
                b=res.getString("Nombre");
                c=res.getString("A_pat");
                d=res.getString("A_mat");
                e=res.getString("Direccion");
               
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
    public Object[][] getDatosSurtidores(){
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
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "*" + " FROM surtidores" + " ORDER BY Clave_surtidor");
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
    public Object[][] getDatosProductos(){
        int registros=0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM productos");
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
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "*" + " FROM productos" + " ORDER BY Cgd_producto");
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
    public Object[][] getDatosNom(String nombre){
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
        Object[][] data = new String[registros][9];
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "*" + " FROM Cliente" + " WHERE Nombre LIKE '" + nombre + "%'");
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a,b,c,d,e;
            while(res.next()==true){
                a=res.getString("Cgd_cliente");
                b=res.getString("Nombre");
                c=res.getString("A_pat");
                d=res.getString("A_mat");
                e=res.getString("Direccion");
               
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
        Object[][] data = new String[registros][9];
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "*" + " FROM Cliente" + " WHERE Cgd_cliente Nombre LIKE '" + rfc + "%'");
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a,b,c,d,e;
            while(res.next()==true){
                a=res.getString("Cgd_cliente");
                b=res.getString("Nombre");
                c=res.getString("A_pat");
                d=res.getString("A_mat");
                e=res.getString("Direccion");
               
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

    
