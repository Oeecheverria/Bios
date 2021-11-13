/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class RotadorMensajes implements Serializable {
  
    private List<String> mensajes;
    private int indiceActual;
    
    
    public String getMensaje(){
        String mensaje;
        
        if(mensajes.size()>0)
            
        {
            mensaje = mensajes.get(indiceActual);
            indiceActual = indiceActual <mensajes.size() - 1 ? indiceActual + 1 : 0;
        }else{
            mensaje = "error no hay mensajes";
                    }
        return mensaje;
                        
            
            
        }
    
    
    public RotadorMensajes(){
        mensajes = new ArrayList();
        
      /*  mensajes.add("no soy un inutil al menos sirvo de mal ejemplo");
        mensajes.add(" lo importante no es saber sino preguntar el telefono del q sabe");
        mensajes.add("otro mensaje");
        mensajes.add("otro...");
        mensajes.add("...");*/
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("Error no se pudo instanciar el driver mysql");
        }
          try {
              
              
        } catch (Exception e) {
            System.out.println("Error con la BD");
        }
        
        indiceActual = 0;
        
    }
    
    
    
}
