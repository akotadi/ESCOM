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
    
    public int ConectarUsuario(String user, String pass){
        try{
            PreparedStatement pstm=con.getConnection().prepareStatement("SELECT count(*) as total FROM dueño where idDueño = '"+user+"' and contraseña = '"+pass+"'");
            ResultSet res=pstm.executeQuery();
            res.next();
            int registros=res.getInt("total");
            if(registros != 0)
                return 1;
            else{
                pstm=con.getConnection().prepareStatement("SELECT count(*) as total FROM empleado where curp = '"+user+"' and contraseña = '"+pass+"'");
                res=pstm.executeQuery();
                res.next();
                registros = res.getInt("total");
                if(registros != 0){
                    pstm=con.getConnection().prepareStatement("SELECT tipo FROM empleado where curp = '"+user+"' and contraseña = '"+pass+"'");
                    res=pstm.executeQuery();
                    res.next();
                    String s = res.getString("tipo");
                    if(s.equalsIgnoreCase("G"))
                        return 2;
                    else
                        return 3;
                }
                else
                    return -1;
            }
        }
        catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Usuario no encontrado.");
            return -1;
        } 
    }     
    
}

    
