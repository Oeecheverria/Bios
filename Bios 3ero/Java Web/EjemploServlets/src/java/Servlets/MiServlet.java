/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
public class MiServlet extends HttpServlet{
protected void procesarSolicitud(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException{
            response.setContentType("text/html;charset=UTF-8");
            
            PrintWriter salida= response.getWriter();
            
            
       try{
           salida.println("<!DOCTYPE html>");
           salida.println("<html>");
           salida.println("<head>");
           salida.println("<title>MiServlet</title>");
           salida.println("</head>");
           salida.println("<body>");
           salida.println("<p>Mi servlet funca bien</p>");
           salida.println("</body>");
           salida.println("</html>");
       }finally
       {salida.close();
       }
           
       }     
            
            

    
    
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
  }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
    }
    
    
    
    
    
}
