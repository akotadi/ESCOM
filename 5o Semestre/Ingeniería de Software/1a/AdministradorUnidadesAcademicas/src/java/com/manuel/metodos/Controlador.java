/**
 * Desarrollado por: 
 * Hernandez Salinas Octavio Ivan
 * Sanchez Valencia Sergio Gabriel
 */
package com.manuel.metodos;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manuel.clases.*;

/**
 *
 * @author honte
 */
public class Controlador extends HttpServlet {
    /**
     * Metodo que se ejecuta cuando se hace una solicitud HTTP a /VerificarDatos con método POST
     * @param req
     * @param res 
     * @throws java.io.IOException 
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException
    {
       
        PrintWriter out = res.getWriter();
        Unidad_de_aprendizaje uda = new Unidad_de_aprendizaje();
        Mensajes msg = new Mensajes();
        
        int index;
        
        if (uda.Validar_nombre(req.getParameter("nombre"))) {
            Unidad_tematica ut = new Unidad_tematica();
            ut.setCompetencia(req.getParameter("competencia"));
            ut.setNombreUnidad_tematica(req.getParameter("nombreUnidad_tematica"));
            ut.setNumero(req.getParameter("numero"));

            Tiempos_Asignados ta = new Tiempos_Asignados();
            ta.setHoras_practica_semana(req.getParameter("horas_practica_semana"));
            ta.setHoras_teoria_nivel(req.getParameter("horas_teoria_nivel"));
            ta.setHoras_teoria_semana(req.getParameter("horas_teoria_semana"));
            ta.setHoras_total_nivel(req.getParameter("horas_total_nivel"));

            Contenido cont = new Contenido();
            cont.setHoras(req.getParameter("horas"));
            cont.setNombreContenido(req.getParameter("nombreContenido"));
            cont.setNumeroContenido(req.getParameter("numero"));

            index = uda.Agregar_Contenido(cont);
            cont.setIdContenido(index);

            index = uda.Agregar_Tiempos_Asignados(ta);
            ta.setIdtiempoAsignado(index);

            index = uda.Agregar_Unidad_Tematica(ut);
            ut.setIdUnidad_Tematica(index);

            boolean flag = uda.Agregar(Double.parseDouble(req.getParameter("CATCC")), Double.parseDouble(req.getParameter("CTEPIC")), 
                    req.getParameter("aprobado_por"), req.getParameter("area_de_informacion"), req.getParameter("autorizado_por"), 
                    req.getParameter("modalidad"), Integer.parseInt(req.getParameter("nivel")), req.getParameter("nombre"), 
                    req.getParameter("objetivo"), req.getParameter("orientacion_educativa"), req.getParameter("proposito"), 
                    req.getParameter("revisado_por"), req.getParameter("tipo"), req.getParameter("vigencia"),
                    cont.getIdContenido(), ta.getIdtiempoAsignado(), ut.getIdUnidad_Tematica());

            if(flag)
                out.print(msg.getMS_001());
            else
                out.print("Ha habido un problema, favor de intentar luego.");
        } else{
            out.print("Unidad de Aprendizaje ya existente, por favor ingrese una con nombre diferente.");
        }
    }
}
