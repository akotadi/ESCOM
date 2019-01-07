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
public class Tiempos_Asignados {
    private int idtiempoAsignado;
    private String horas_practica_semana;
    private String horas_teoria_nivel;
    private String horas_teoria_semana;
    private String horas_total_nivel;

    /**
     * @return the idtiempoAsignado
     */
    public int getIdtiempoAsignado() {
        return idtiempoAsignado;
    }

    /**
     * @param idtiempoAsignado the idtiempoAsignado to set
     */
    public void setIdtiempoAsignado(int idtiempoAsignado) {
        this.idtiempoAsignado = idtiempoAsignado;
    }

    /**
     * @return the horas_practica_semana
     */
    public String getHoras_practica_semana() {
        return horas_practica_semana;
    }

    /**
     * @param horas_practica_semana the horas_practica_semana to set
     */
    public void setHoras_practica_semana(String horas_practica_semana) {
        this.horas_practica_semana = horas_practica_semana;
    }

    /**
     * @return the horas_teoria_nivel
     */
    public String getHoras_teoria_nivel() {
        return horas_teoria_nivel;
    }

    /**
     * @param horas_teoria_nivel the horas_teoria_nivel to set
     */
    public void setHoras_teoria_nivel(String horas_teoria_nivel) {
        this.horas_teoria_nivel = horas_teoria_nivel;
    }

    /**
     * @return the horas_teoria_semana
     */
    public String getHoras_teoria_semana() {
        return horas_teoria_semana;
    }

    /**
     * @param horas_teoria_semana the horas_teoria_semana to set
     */
    public void setHoras_teoria_semana(String horas_teoria_semana) {
        this.horas_teoria_semana = horas_teoria_semana;
    }

    /**
     * @return the horas_total_nivel
     */
    public String getHoras_total_nivel() {
        return horas_total_nivel;
    }

    /**
     * @param horas_total_nivel the horas_total_nivel to set
     */
    public void setHoras_total_nivel(String horas_total_nivel) {
        this.horas_total_nivel = horas_total_nivel;
    }

    @Override
    public String toString() {
        return "Tiempos_Asignados{" + "idtiempoAsignado=" + idtiempoAsignado + ", horas_practica_semana=" + horas_practica_semana + ", horas_teoria_nivel=" + horas_teoria_nivel + ", horas_teoria_semana=" + horas_teoria_semana + ", horas_total_nivel=" + horas_total_nivel + '}';
    }
    
    
}
