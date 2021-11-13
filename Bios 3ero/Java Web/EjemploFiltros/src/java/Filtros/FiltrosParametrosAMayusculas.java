/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filtros;

import Filtros.Wrappers.WrapperRequestParametrosAMayusculas;
import Filtros.Wrappers.WrapperResponseParametrosAMayusculas;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Usuario
 */
public class FiltrosParametrosAMayusculas implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
     
        WrapperRequestParametrosAMayusculas pedidoFalso = new WrapperRequestParametrosAMayusculas((HttpServletRequest)request);
   
        WrapperResponseParametrosAMayusculas respuestaFalsa = new WrapperResponseParametrosAMayusculas((HttpServletResponse)response);
    
        
        chain.doFilter(pedidoFalso, respuestaFalsa);
    
        
        PrintWriter salida = response.getWriter();
        
        try{
            String advertencia = "<p style =\"backgraund-color:red, color: white; font-weight:bold;\"> Advertencia servelt filtrado</p>";
            String htmlRespuesta = respuestaFalsa.toString().replace("</body>", advertencia + "\n </body>");
        
            salida.print(htmlRespuesta);
        
        }finally
        {
            salida.close();
        }
        
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
