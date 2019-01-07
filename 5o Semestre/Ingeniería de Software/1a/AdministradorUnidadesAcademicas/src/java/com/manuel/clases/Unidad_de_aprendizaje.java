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
public class Unidad_de_aprendizaje {
    
    private int idUnidad_de_aprendizaje;
    private double CATCC;
    private double CTEPIC;
    private String aprobado_por;
    private String area_de_informacion;
    private String autorizado_por;
    private String modalidad;
    private int nivel;
    private String nombre;
    private String objetivo;
    private String orientacion_educativa;
    private String proposito;
    private String revisado_por;
    private String tipo;
    private String vigencia;
    private int idContenido;
    private int idtiempoAsignado;
    private int idUnidad_Tematica;

    private final DatabaseUA dua;

    public Unidad_de_aprendizaje() {
        dua = new DatabaseUA();
        dua.conectar();
    }

    /**
     * @return the idUnidad_de_aprendizaje
     */
    public int getIdUnidad_de_aprendizaje() {
        return idUnidad_de_aprendizaje;
    }

    /**
     * @param idUnidad_de_aprendizaje the idUnidad_de_aprendizaje to set
     */
    public void setIdUnidad_de_aprendizaje(int idUnidad_de_aprendizaje) {
        this.idUnidad_de_aprendizaje = idUnidad_de_aprendizaje;
    }

    /**
     * @return the CATCC
     */
    public double getCATCC() {
        return CATCC;
    }

    /**
     * @param CATCC the CATCC to set
     */
    public void setCATCC(double CATCC) {
        this.CATCC = CATCC;
    }

    /**
     * @return the CTEPIC
     */
    public double getCTEPIC() {
        return CTEPIC;
    }

    /**
     * @param CTEPIC the CTEPIC to set
     */
    public void setCTEPIC(double CTEPIC) {
        this.CTEPIC = CTEPIC;
    }

    /**
     * @return the aprobado_por
     */
    public String getAprobado_por() {
        return aprobado_por;
    }

    /**
     * @param aprobado_por the aprobado_por to set
     */
    public void setAprobado_por(String aprobado_por) {
        this.aprobado_por = aprobado_por;
    }

    /**
     * @return the area_de_informacion
     */
    public String getArea_de_informacion() {
        return area_de_informacion;
    }

    /**
     * @param area_de_informacion the area_de_informacion to set
     */
    public void setArea_de_informacion(String area_de_informacion) {
        this.area_de_informacion = area_de_informacion;
    }

    /**
     * @return the autorizado_por
     */
    public String getAutorizado_por() {
        return autorizado_por;
    }

    /**
     * @param autorizado_por the autorizado_por to set
     */
    public void setAutorizado_por(String autorizado_por) {
        this.autorizado_por = autorizado_por;
    }

    /**
     * @return the modalidad
     */
    public String getModalidad() {
        return modalidad;
    }

    /**
     * @param modalidad the modalidad to set
     */
    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    /**
     * @return the nivel
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the objetivo
     */
    public String getObjetivo() {
        return objetivo;
    }

    /**
     * @param objetivo the objetivo to set
     */
    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    /**
     * @return the orientacion_educativa
     */
    public String getOrientacion_educativa() {
        return orientacion_educativa;
    }

    /**
     * @param orientacion_educativa the orientacion_educativa to set
     */
    public void setOrientacion_educativa(String orientacion_educativa) {
        this.orientacion_educativa = orientacion_educativa;
    }

    /**
     * @return the proposito
     */
    public String getProposito() {
        return proposito;
    }

    /**
     * @param proposito the proposito to set
     */
    public void setProposito(String proposito) {
        this.proposito = proposito;
    }

    /**
     * @return the revisado_por
     */
    public String getRevisado_por() {
        return revisado_por;
    }

    /**
     * @param revisado_por the revisado_por to set
     */
    public void setRevisado_por(String revisado_por) {
        this.revisado_por = revisado_por;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the vigencia
     */
    public String getVigencia() {
        return vigencia;
    }

    /**
     * @param vigencia the vigencia to set
     */
    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

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

    @Override
    public String toString() {
        return "Unidad_de_aprendizaje{" + "idUnidad_de_aprendizaje=" + idUnidad_de_aprendizaje + ", CATCC=" + CATCC + ", CTEPIC=" + CTEPIC + ", aprobado_por=" + aprobado_por + ", area_de_informacion=" + area_de_informacion + ", autorizado_por=" + autorizado_por + ", modalidad=" + modalidad + ", nivel=" + nivel + ", nombre=" + nombre + ", objetivo=" + objetivo + ", orientacion_educativa=" + orientacion_educativa + ", proposito=" + proposito + ", revisado_por=" + revisado_por + ", tipo=" + tipo + ", vigencia=" + vigencia + ", idContenido=" + idContenido + ", idtiempoAsignado=" + idtiempoAsignado + ", idUnidad_Tematica=" + idUnidad_Tematica + '}';
    }
    
    public boolean Validar_nombre(String nombre){
        return dua.buscarUnidad(nombre);
    }
    
    public boolean Agregar(double CATCC, double CTEPIC, String aprobado_por, String area_de_informacion, 
            String autorizado_por, String modalidad, int nivel, String nombre, String objetivo, 
            String orientacion_educativa, String proposito, String revisado_por, String tipo, 
            String vigencia, int idCont, int idTA, int idUT){
        boolean flag = false;
    //    dua = new DatabaseUA();
        this.CATCC = CATCC;
        this.CTEPIC = CTEPIC;
        this.aprobado_por = aprobado_por;
        this.area_de_informacion = area_de_informacion;
        this.autorizado_por = autorizado_por;
        this.modalidad = modalidad;
        this.nivel = nivel;
        this.nombre = nombre;
        this.objetivo = objetivo;
        this.orientacion_educativa = orientacion_educativa;
        this.proposito = proposito;
        this.revisado_por = revisado_por;
        this.tipo = tipo;
        this.idContenido = idCont;
        this.idtiempoAsignado = idTA;
        this.idUnidad_Tematica = idUT;
        this.vigencia = vigencia;
        int aux = dua.enviarUnidadAprendizaje(this);
        if (aux != 0) {
            flag = true;
        }
        return flag;
    }
    
    public int Agregar_Tiempos_Asignados(Tiempos_Asignados ta){
        int index = dua.enviarTiempoAsignado(ta);
        if (index == 0) {
            System.out.println("Error en la búsqueda ta");
            System.exit(index);
        }
        return index;
    }
    
    public int Agregar_Contenido(Contenido co){
        int index = dua.enviarContenido(co);
        if (index == 0) {
            System.out.println("Error en la búsqueda co");
            System.exit(index);
        }
        return index;
    }
    
    public int Agregar_Unidad_Tematica(Unidad_tematica ut){
        int index = dua.enviarUnidadTematica(ut);
        if (index == 0) {
            System.out.println("Error en la búsqueda ut");
            System.exit(index);
        }
        return index;
    }
}
