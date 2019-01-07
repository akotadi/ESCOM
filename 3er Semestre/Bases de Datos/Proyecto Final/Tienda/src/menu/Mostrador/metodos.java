/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu.Mostrador;
import menu.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
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
                a=res.getString("Producto_codigo");
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
    
    
    public Object[] getProdVenta(String codigo, String quantity){
        Object[] data = new String[6];
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "codigo, nombre, marca, precio" + " FROM producto" + " WHERE codigo = '" + codigo+"%'");
            try (ResultSet res = pstm.executeQuery()) {
                if (res.next()==true) {
                    String a,b,c,d,e,f;
                    a=res.getString("codigo");
                    b=res.getString("nombre");
                    c=res.getString("marca");
                    d=res.getString("precio");
                    int i = (Integer.valueOf(d))*(Integer.valueOf(quantity));
                    data[0]=a;
                    data[1]=b;
                    data[2]=c;
                    data[3]=d;
                    data[4]=quantity;
                    data[5]=Integer.toString(i);
                }
                res.close();
            }
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
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "codigo, nombre, marca, precio" + " FROM producto" + " WHERE nombre LIKE '%" + nombre + "%'");
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
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM producto p, pp x WHERE p.codigo = x.Producto_codigo");
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
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "p.codigo, p.nombre, p.tamañoCantidad, x.Proveedor_RFC, x.precio" + " FROM producto p, pp x" + " WHERE p.codigo = x.Producto_codigo AND p.nombre LIKE '" + nombre + "%' AND x.Proveedor_RFC = '"+ rfc+"'");
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
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM producto p, pp x WHERE p.codigo = x.Producto_codigo");
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
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "p.codigo, p.nombre, p.tamañoCantidad, x.Proveedor_RFC, x.precio" + " FROM producto p, pp x" + " WHERE p.codigo = x.Producto_codigo AND p.codigo LIKE '" + cod + "%' AND x.Proveedor_RFC = '"+ rfc+"'");
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
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM producto p, lp x WHERE p.codigo = x.Producto_codigo");
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
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "p.codigo, p.nombre, p.precio, p.tamañoCantidad, x.existencia" + " FROM producto p, lp x" + " WHERE p.codigo = x.Producto_codigo AND p.nombre LIKE '" + nombre + "%' AND x.Local_nombre = '"+ local+"'");
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
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM producto p, lp x WHERE p.codigo = x.Producto_codigo");
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
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "p.codigo, p.nombre, p.precio, p.tamañoCantidad, x.existencia" + " FROM producto p, lp x" + " WHERE p.codigo = x.Producto_codigo AND p.codigo = " + codigo + " AND x.Local_nombre = '"+ local+"'");
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
    public void BoMerma(String Code){
        String SQL = "DELETE FROM " + "merma " + "WHERE Producto_codigo = " + Code;
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
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "*" + " FROM producto" + " ORDER BY codigo");
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
    
    public String getnombrecliente(String idCliente){
        String s = null;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "nombre, apPaterno, apMaterno" + " FROM cliente" + " WHERE idCliente like '"+idCliente+"%'");
            ResultSet res = pstm.executeQuery();
            if (res.next() == true) {
                s = res.getString("nombre")+" "+res.getString("apPaterno")+" "+res.getString("apMaterno");
            }
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return s;
    }
    
    public double getcreditoactual(String idCliente){
        double i = 0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "creditoactual, creditomaximo" + " FROM cliente" + " WHERE idCliente like '"+idCliente+"%'");
            ResultSet res = pstm.executeQuery();
            if (res.next() == true) {
                i = Double.valueOf(res.getString("creditomaximo"))-Double.valueOf(res.getString("creditoactual"));
            }
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return i;
    }
    
    public double getdescuento(String idCliente){
        double i = 0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "descuento" + " FROM cliente" + " WHERE idCliente like '"+idCliente+"%'");
            ResultSet res = pstm.executeQuery();
            if (res.next() == true) {
                i = Double.valueOf(res.getString("descuento"));
            }
            res.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return i;
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
    
    public Object[][] getDatosidFactura(String rfc){
        int registros=0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM Pedido");
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
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "idPedido, fechaRealizacion, fechaEntrega, noProductos, montoTotal, Proveedor_RFC" + " FROM Pedido" + " WHERE idPedido LIKE '" + rfc + "%'");
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a,b,c,d,e,f;
            while(res.next()==true){
                a=res.getString("idPedido");
                b=res.getString("FechaRealizacion");
                c=res.getString("FechaEntrega");
                d=res.getString("noProductos");
                e=res.getString("MontoTotal");
                f=res.getString("Proveedor_RFC");
               
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
    
    public Object[][] getDatosPedidoRFC(String nombre){
        int registros=0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM Pedido");
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
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT " + "idPedido, fechaRealizacion, fechaEntrega, noProductos, montoTotal, Proveedor_RFC" + " FROM Pedido" + " WHERE Proveedor_RFC LIKE '" + nombre + "%'");
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a,b,c,d,e,f;
            while(res.next()==true){
                a=res.getString("idPedido");
                b=res.getString("FechaRealizacion");
                c=res.getString("FechaEntrega");
                d=res.getString("noProductos");
                e=res.getString("MontoTotal");
                f=res.getString("Proveedor_RFC");
               
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
    
    public Object[][] getDatosidPedido(String idpedido){
        int registros=0;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(1) as total FROM ppe where idpedido like '"+idpedido+"'");
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
            PreparedStatement pstm=con.getConnection().prepareStatement(
                    "SELECT " + "pe.codigo, p.nombre, pe.cantidad" 
                            + " FROM ppe pe, Producto p" + " WHERE pe.idPedido LIKE '" + idpedido + "%' and pe.codigo = p.codigo order by pe.cantidad desc");
            ResultSet res = pstm.executeQuery();
            int i=0;
            String a,b,c;
            while(res.next()==true){
                a=res.getString("pe.codigo");
                b=res.getString("p.nombre");
                c=res.getString("pe.cantidad");
               
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
    public ResultSet comboLocal(String user){
        ResultSet res = null;
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT nombrelocal FROM Mostrador WHERE Empleado_CURP = '"+user+"'");
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
    
    public void updatePedido(String idPedido, int cantidad, String producto, String local){
        String SQL = "UPDATE ppe SET cantidad=cantidad-"+cantidad+" where idPedido like '"+idPedido+"%' and codigo like'"+producto+"'";
        String SQL2 = "UPDATE lp SET existencia = existencia+"+cantidad+" where Producto_codigo like '"+producto+"%' and Local_nombre like '"+local+"%'";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.execute();
            pstm1 = con.getConnection().prepareStatement(SQL2);
            pstm1.execute();
            JOptionPane.showMessageDialog(null,"Se actualizo el registro con exito...");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al actualizar registro..");
        } 
    }
            
    public boolean updateVentaCliente(Vector numdata, Vector cantprod, String monto, String sfecha, String cliente, String descuento){
        String SQL = "INSERT INTO venta (montototal,noproductos,fechaventa,mostrador_curp,cliente_idcliente,descuento) VALUES "
                + "('"+monto+"',"+numdata.size()+",'"+sfecha+"','"+Sesion.getUser()+"','"+cliente+"','"+descuento+"')";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.execute();
            for (int i = 0; i < numdata.size(); i++) {
                String SQL2 = "INSERT INTO pv VALUES ((SELECT idVenta from venta where montototal = '"+monto+"' and noproductos = "+numdata.size()+" "
                + "and mostrador_curp = '"+Sesion.getUser()+"' and cliente_idcliente = '"+cliente+"'),'"+numdata.get(i)+"','"+cantprod.get(i)+"')";
                pstm1 = con.getConnection().prepareStatement(SQL2);
                pstm1.execute();
            }
            JOptionPane.showMessageDialog(null,"Se actualizo el registro con exito...");
            return true;
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al actualizar registro..");
            return false;
        } 
        }   
    public void updateVenta(Vector numdata, Vector cantprod, String monto, String sfecha){
        String SQL = "INSERT INTO venta (montototal,noproductos,fechaventa,mostrador_curp) VALUES "
                + "('"+monto+"',"+numdata.size()+",'"+sfecha+"','"+Sesion.getUser()+"')";
        try{
            PreparedStatement pstm1 = con.getConnection().prepareStatement(SQL);
            pstm1.execute();
            for (int i = 0; i < numdata.size(); i++) {
                String SQL2 = "INSERT INTO pv VALUES ((SELECT idVenta from venta where montototal = '"+monto+"' and noproductos = "+numdata.size()+" "
                + "and mostrador_curp = '"+Sesion.getUser()+"'),'"+numdata.get(i)+"','"+cantprod.get(i)+"')";
                pstm1 = con.getConnection().prepareStatement(SQL2);
                pstm1.execute();
            }
            JOptionPane.showMessageDialog(null,"Se actualizo el registro con exito...");
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error al actualizar registro..");
        }
    }
        
    public void updateCredito(double credito, String cliente){
    String SQL = "UPDATE Cliente SET creditoactual = creditoactual -"+credito+" WHERE idCliente = '"+cliente+"'";
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
    
}

    
