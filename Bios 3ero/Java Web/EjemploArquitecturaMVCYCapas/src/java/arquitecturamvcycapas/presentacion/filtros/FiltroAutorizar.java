/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquitecturamvcycapas.presentacion.filtros;

import arquitecturamvcycapas.modelo.compartidos.beans.datatypes.DTUsuario;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Raul
 */
public class FiltroAutorizar implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig)
            throws ServletException {
        
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String urlControlador = ((HttpServletRequest)request).getServletPath();
        
        String accion = request.getParameter("accion");
        if (accion == null) accion = "index";
        
        DTUsuario usuarioLogueado = (DTUsuario)((HttpServletRequest)request).getSession().getAttribute("usuarioLogueado");
        String tipoUsuario = usuarioLogueado != null ? usuarioLogueado.getClass().getSimpleName() : null;
        
        boolean autorizado = false;
        
        switch (urlControlador) {
            case "/inicio":
                switch (accion) {
                    case "index":
                        autorizado = true;
                        
                        break;
                    case "ingresar":
                        autorizado = tipoUsuario == null;
                        
                        break;
                    case "salir":
                        autorizado = tipoUsuario != null;
                        
                        break;
                }
                
                break;
            case "/empleados":
                switch (accion) {
                    case "index":
                    case "ver":
                        autorizado = "DTAdministrador".equals(tipoUsuario) || "DTConsultor".equals(tipoUsuario);
                        
                        break;
                    default:
                        autorizado = "DTAdministrador".equals(tipoUsuario);
                        
                        break;
                }
                
                break;
        }
        
        if (autorizado) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletRequest)request).getSession().setAttribute("mensaje", "¡ERROR! No tiene autorización para realizar esa acción.");
            ((HttpServletResponse)response).sendRedirect("inicio");
        }
    }
    
    @Override
    public void destroy() {
        
    }
    
}
