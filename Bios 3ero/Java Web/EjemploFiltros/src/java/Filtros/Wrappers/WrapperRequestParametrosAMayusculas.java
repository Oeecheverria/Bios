/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filtros.Wrappers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 *
 * @author Usuario
 */
public class WrapperRequestParametrosAMayusculas extends HttpServletRequestWrapper{
   public WrapperRequestParametrosAMayusculas(HttpServletRequest request){ 
                super(request);
   
   }
   @Override
   public String getParameter(String name){
       return super.getParameter(name).toUpperCase();
   }
}
           
