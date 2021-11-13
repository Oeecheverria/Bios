/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquitecturamvcycapas.presentacion.servlets.controladores;

import arquitecturamvcycapas.modelo.compartidos.beans.datatypes.DTUsuario;
import arquitecturamvcycapas.modelo.logica.FabricaLogica;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Raul
 */
public class ControladorInicio extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) accion = "index";
        
        switch (accion) {
            case "index":
                index_get(request, response);
                
                break;
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        
        switch (accion) {
            case "ingresar":
                ingresar_post(request, response);
                
                break;
            case "salir":
                salir_post(request, response);
                
                break;
        }
    }
    
    private void index_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/vistas/inicio/index.jsp").forward(request, response);
    }
    
    private void ingresar_post(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombreUsuario = request.getParameter("nombreUsuario");
        String claveAcceso = request.getParameter("claveAcceso");
        
        DTUsuario usuario = FabricaLogica.getLogicaUsuario().ingresar(nombreUsuario, claveAcceso);
        
        if (usuario != null) {
            request.getSession().setAttribute("usuarioLogueado", usuario);
            
            response.sendRedirect("inicio");
        } else {
            request.getSession().setAttribute("mensaje", "¡ERROR! Las credenciales no son válidas.");
            
            request.getRequestDispatcher("WEB-INF/vistas/inicio/index.jsp").forward(request, response);
        }
    }
    
    private void salir_post(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().removeAttribute("usuarioLogueado");
        
        response.sendRedirect("inicio");
    }
    
}
