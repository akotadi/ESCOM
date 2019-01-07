/**
 * Desarrollado por: 
 * Hernandez Salinas Octavio Ivan
 * Sanchez Valencia Sergio Gabriel
 */
package com.manuel.conexion;

import com.manuel.clases.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author honte
 */
public class DatabaseUA{
    
    static String bd="mydb";
    static String login="root";
    static String password="root";
    static String url="jdbc:mysql://localhost/" + bd;
    
    Connection con;
    
    /**
     * Constructor sin argumentos
     */
    public DatabaseUA(){
        
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
           con=DriverManager.getConnection(url,login,password);
           if(con!=null)
           {
               System.out.println("Conexion a base de datos " + bd + " listo.");
           }
        }
        catch(SQLException | ClassNotFoundException e)
        {
            System.out.println(e);
            System.out.println("Error en Conexión a base de datos " + bd);
        }
    }
    
    
    /**
     * Método que realiza la conexión con la base de datos "UnidadAprendizaje
     */
    public void conectar()
    {/*
        try {
            con =  DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "root");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUA.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
    }
    
    public String buscarMensaje(int numero){
        String mensaje = "";
        try{
            PreparedStatement pstm=con.prepareStatement("SELECT MS_00"+numero+" as mensaje FROM Mensajes WHERE id = 1");
            try (ResultSet res = pstm.executeQuery()) {
                if (res.next()==true) {
                    mensaje = res.getString("mensaje");
                }
                res.close();
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return mensaje;
    }
    
    public boolean buscarUnidad(String nombre){
        int exitCode = 0;
        try{
            PreparedStatement pstm=con.prepareStatement("SELECT nombre FROM Unidad_de_aprendizaje WHERE nombre = '"+nombre+"'");
            try (ResultSet res = pstm.executeQuery()) {
                if (res.next()==true) {
                    exitCode++;
                }
                res.close();
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return (exitCode == 0);
    }
    
    public int enviarUnidadTematica(Unidad_tematica ut){
        int index = 0;
        PreparedStatement ps;
        try {
            con.setAutoCommit(false);
            ps = con.prepareStatement("INSERT INTO unidad_tematica (competencia,nombre,numero) VALUES (?,?,?)");
            ps.setString(1, ut.getCompetencia());
            ps.setString(2, ut.getNombreUnidad_tematica());
            ps.setString(3, ut.getNumero());
            ps.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUA.class.getName()).log(Level.SEVERE, null, ex);
            if (con != null) {
                try {
                    System.err.print("Error en la transacción, haciendo rollback.");
                    con.rollback();
                } catch(SQLException excp) {
                    Logger.getLogger(DatabaseUA.class.getName()).log(Level.SEVERE, null, excp);
                }
            }
        }
        try{
            PreparedStatement pstm=con.prepareStatement("SELECT idUnidad_Tematica FROM unidad_tematica WHERE nombre = '"
                    +ut.getNombreUnidad_tematica()+"' and competencia = '"+ut.getCompetencia()+"' and numero = '"+ut.getNumero()+"'");
            try (ResultSet res = pstm.executeQuery()) {
                if (res.next()==true) {
                    String idUnidad_Tematica;
                    idUnidad_Tematica = res.getString("idUnidad_Tematica");
                    index = Integer.valueOf(idUnidad_Tematica);
                }
                res.close();
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return index;
    }
    
    public int enviarTiempoAsignado(Tiempos_Asignados ta){
        int index = 0;
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("INSERT INTO tiempoasignado "
                    + "(horas_practica_semana,horas_teoria_nivel,horas_teoria_semana,horas_total_nivel) VALUES (?,?,?,?)");
            ps.setString(1, ta.getHoras_practica_semana());
            ps.setString(2, ta.getHoras_teoria_nivel());
            ps.setString(3, ta.getHoras_teoria_semana());
            ps.setString(4, ta.getHoras_total_nivel());
            ps.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUA.class.getName()).log(Level.SEVERE, null, ex);
            if (con != null) {
                try {
                    System.err.print("Error en la transacción, haciendo rollback.");
                    con.rollback();
                } catch(SQLException excp) {
                    Logger.getLogger(DatabaseUA.class.getName()).log(Level.SEVERE, null, excp);
                }
            }
        }
        try{
            PreparedStatement pstm=con.prepareStatement("SELECT idtiempoAsignado FROM tiempoasignado WHERE horas_practica_semana = '"
                    +ta.getHoras_practica_semana()+"' and horas_teoria_nivel = '"+ta.getHoras_teoria_nivel()+"' and horas_teoria_semana = '"
                    +ta.getHoras_teoria_semana()+"' and horas_total_nivel = '"+ta.getHoras_total_nivel()+"'");
            try (ResultSet res = pstm.executeQuery()) {
                if (res.next()==true) {
                    String idtiempoasignado;
                    idtiempoasignado = res.getString("idtiempoAsignado");
                    index = Integer.valueOf(idtiempoasignado);
                }
                res.close();
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return index;
    }
    
    public int enviarContenido(Contenido cont){
        int index = 0;
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("INSERT INTO contenido (horas,nombre,numero) VALUES (?,?,?)");
            ps.setString(1, cont.getHoras());
            ps.setString(2, cont.getNombreContenido());
            ps.setString(3, cont.getNumeroContenido());
            ps.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUA.class.getName()).log(Level.SEVERE, null, ex);
            if (con != null) {
                try {
                    System.err.print("Error en la transacción, haciendo rollback.");
                    con.rollback();
                } catch(SQLException excp) {
                    Logger.getLogger(DatabaseUA.class.getName()).log(Level.SEVERE, null, excp);
                }
            }
        }
        try{
            PreparedStatement pstm=con.prepareStatement("SELECT idContenido FROM contenido WHERE nombre = '"
                    +cont.getNombreContenido()+"' and horas = '"+cont.getHoras()+"' and numero = '"+cont.getNumeroContenido()+"'");
            try (ResultSet res = pstm.executeQuery()) {
                if (res.next()==true) {
                    String idContenido;
                    idContenido = res.getString("idContenido");
                    index = Integer.valueOf(idContenido);
                }
                res.close();
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return index;
    }
    
    public int enviarUnidadAprendizaje(Unidad_de_aprendizaje ua)
    {
        int exitCode = 0;
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("INSERT INTO unidad_de_aprendizaje (CATCC, CTEPIC, aprobado_por, area_de_informacion,"
                    + "autorizado_por, modalidad, nivel, nombre, objetivo, orientacion_educativa, proposito, revisado_por, tipo, vigencia, "
                    + "idContenido, idtiempoAsignado, idUnidad_Tematica) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setDouble(1, ua.getCATCC());
            ps.setDouble(2, ua.getCTEPIC());
            ps.setString(3, ua.getAprobado_por());
            ps.setString(4, ua.getArea_de_informacion());
            ps.setString(5, ua.getAutorizado_por());
            ps.setString(6, ua.getModalidad());
            ps.setInt(7, ua.getNivel());
            ps.setString(8, ua.getNombre());
            ps.setString(9, ua.getObjetivo());
            ps.setString(10, ua.getOrientacion_educativa());
            ps.setString(11, ua.getProposito());
            ps.setString(12, ua.getRevisado_por());
            ps.setString(13, ua.getTipo());
            ps.setString(14, ua.getVigencia());
            ps.setInt(15, ua.getIdContenido());
            ps.setInt(16, ua.getIdtiempoAsignado());
            ps.setInt(17, ua.getIdUnidad_Tematica());
            ps.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUA.class.getName()).log(Level.SEVERE, null, ex);
            if (con != null) {
                try {
                    System.err.print("Error en la transacción, haciendo rollback.");
                    con.rollback();
                } catch(SQLException excp) {
                    Logger.getLogger(DatabaseUA.class.getName()).log(Level.SEVERE, null, excp);
                }
            }
        }
        try{
            PreparedStatement pstm=con.prepareStatement("SELECT idUnidad_de_aprendizaje FROM Unidad_de_aprendizaje WHERE nombre = '"
                    +ua.getNombre()+"'");
            try (ResultSet res = pstm.executeQuery()) {
                if (res.next()==true) {
                    String idUnidad_de_aprendizaje;
                    idUnidad_de_aprendizaje = res.getString("idUnidad_de_aprendizaje");
                    exitCode = Integer.valueOf(idUnidad_de_aprendizaje);
                }
                res.close();
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return exitCode;
    }
    
}
