/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manuel.clases;

/**
 *
 * @author manue
 */
public class Contenido {
    private int idContenido;
    private String horas;
    private String nombreContenido;
    private String numeroContenido;

    /**
     * @return the idContenido
     */
    public int getIdContenido() {
        return idContenido;
    }

    /**
     * @param idContenido the idContenido to set
     */
    public void setIdContenido(int idContenido) {
        this.idContenido = idContenido;
    }

    /**
     * @return the horas
     */
    public String getHoras() {
        return horas;
    }

    /**
     * @param horas the horas to set
     */
    public void setHoras(String horas) {
        this.horas = horas;
    }

    /**
     * @return the nombreContenido
     */
    public String getNombreContenido() {
        return nombreContenido;
    }

    /**
     * @param nombreContenido the nombreContenido to set
     */
    public void setNombreContenido(String nombreContenido) {
        this.nombreContenido = nombreContenido;
    }

    /**
     * @return the numeroContenido
     */
    public String getNumeroContenido() {
        return numeroContenido;
    }

    /**
     * @param numeroContenido the numeroContenido to set
     */
    public void setNumeroContenido(String numeroContenido) {
        this.numeroContenido = numeroContenido;
    }

    @Override
    public String toString() {
        return "Contenido{" + "idContenido=" + idContenido + ", horas=" + horas + ", nombre=" + nombreContenido + ", numero=" + numeroContenido + '}';
    }
    
    
}
