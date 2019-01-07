/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manuel.clases;

import com.manuel.conexion.DatabaseUA;

/**
 *
 * @author manue
 */
public class Mensajes {
    private int idMensajes;
    private String MS_000;
    private String MS_001;
    private String MS_002;
    private String MS_003;
    private String MS_004;
    private String MS_005;
    private String MS_006;
    
    private final DatabaseUA dua;

    public Mensajes() {
        dua = new DatabaseUA();
        dua.conectar();
        this.MS_000 = dua.buscarMensaje(0);
        this.MS_001 = dua.buscarMensaje(1);
        this.MS_002 = dua.buscarMensaje(2);
        this.MS_003 = dua.buscarMensaje(3);
        this.MS_004 = dua.buscarMensaje(4);
        this.MS_005 = dua.buscarMensaje(5);
        this.MS_006 = dua.buscarMensaje(6);
    }

    /**
     * @return the idMensajes
     */
    public int getIdMensajes() {
        return idMensajes;
    }

    /**
     * @param idMensajes the idMensajes to set
     */
    public void setIdMensajes(int idMensajes) {
        this.idMensajes = idMensajes;
    }

    /**
     * @return the MS_000
     */
    public String getMS_000() {
        return MS_000;
    }

    /**
     * @param MS_000 the MS_000 to set
     */
    public void setMS_000(String MS_000) {
        this.MS_000 = MS_000;
    }

    /**
     * @return the MS_001
     */
    public String getMS_001() {
        return MS_001;
    }

    /**
     * @param MS_001 the MS_001 to set
     */
    public void setMS_001(String MS_001) {
        this.MS_001 = MS_001;
    }

    /**
     * @return the MS_002
     */
    public String getMS_002() {
        return MS_002;
    }

    /**
     * @param MS_002 the MS_002 to set
     */
    public void setMS_002(String MS_002) {
        this.MS_002 = MS_002;
    }

    /**
     * @return the MS_003
     */
    public String getMS_003() {
        return MS_003;
    }

    /**
     * @param MS_003 the MS_003 to set
     */
    public void setMS_003(String MS_003) {
        this.MS_003 = MS_003;
    }

    /**
     * @return the MS_004
     */
    public String getMS_004() {
        return MS_004;
    }

    /**
     * @param MS_004 the MS_004 to set
     */
    public void setMS_004(String MS_004) {
        this.MS_004 = MS_004;
    }

    /**
     * @return the MS_005
     */
    public String getMS_005() {
        return MS_005;
    }

    /**
     * @param MS_005 the MS_005 to set
     */
    public void setMS_005(String MS_005) {
        this.MS_005 = MS_005;
    }

    /**
     * @return the MS_006
     */
    public String getMS_006() {
        return MS_006;
    }

    /**
     * @param MS_006 the MS_006 to set
     */
    public void setMS_006(String MS_006) {
        this.MS_006 = MS_006;
    }

    @Override
    public String toString() {
        return "Mensajes{" + "idMensajes=" + idMensajes + ", MS_000=" + MS_000 + ", MS_001=" + MS_001 + ", MS_002=" + MS_002 + ", MS_003=" + MS_003 + ", MS_004=" + MS_004 + ", MS_005=" + MS_005 + ", MS_006=" + MS_006 + '}';
    }
    
    
}
