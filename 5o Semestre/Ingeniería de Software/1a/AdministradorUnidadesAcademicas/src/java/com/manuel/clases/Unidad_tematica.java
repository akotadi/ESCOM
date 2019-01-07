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
public class Unidad_tematica {
    private int idUnidad_Tematica;
    private String competencia;
    private String nombreUnidad_tematica;
    private String numero;

    /**
     * @return the idUnidad_Tematica
     */
    public int getIdUnidad_Tematica() {
        return idUnidad_Tematica;
    }

    /**
     * @param idUnidad_Tematica the idUnidad_Tematica to set
     */
    public void setIdUnidad_Tematica(int idUnidad_Tematica) {
        this.idUnidad_Tematica = idUnidad_Tematica;
    }

    /**
     * @return the competencia
     */
    public String getCompetencia() {
        return competencia;
    }

    /**
     * @param competencia the competencia to set
     */
    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }

    /**
     * @return the nombreUnidad_tematica
     */
    public String getNombreUnidad_tematica() {
        return nombreUnidad_tematica;
    }

    /**
     * @param nombreUnidad_tematica the nombreUnidad_tematica to set
     */
    public void setNombreUnidad_tematica(String nombreUnidad_tematica) {
        this.nombreUnidad_tematica = nombreUnidad_tematica;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Unidad_tematica{" + "idUnidad_Tematica=" + idUnidad_Tematica + ", competencia=" + competencia + ", nombre=" + nombreUnidad_tematica + ", numero=" + numero + '}';
    }
    
    
}
