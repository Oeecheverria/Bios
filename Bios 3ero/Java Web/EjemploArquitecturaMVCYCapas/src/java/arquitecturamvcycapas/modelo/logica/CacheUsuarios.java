/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquitecturamvcycapas.modelo.logica;

import arquitecturamvcycapas.modelo.compartidos.beans.datatypes.DTAdministrador;
import arquitecturamvcycapas.modelo.compartidos.beans.datatypes.DTConsultor;
import arquitecturamvcycapas.modelo.compartidos.beans.datatypes.DTUsuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Raul
 */
class CacheUsuarios {
    
    private static CacheUsuarios instancia = null;
    
    
    public static CacheUsuarios getInstancia() {
        if (instancia == null) {
            instancia = new CacheUsuarios();
        }
        
        return instancia;
    }
    
    
    private List<DTUsuario> usuarios;
    
    
    private CacheUsuarios() {
        usuarios = new ArrayList();
        
        usuarios.add(new DTAdministrador("admin", "admin123"));
        usuarios.add(new DTConsultor("consul", "consul123"));
    }
    
    
    public DTUsuario obtener(String nombreUsuario) {
        DTUsuario usuario = null;
        
        for (DTUsuario u : usuarios) {
            if (u.getNombreUsuario().equals(nombreUsuario)) {
                if (u instanceof DTAdministrador) {
                    usuario = new DTAdministrador(u.getNombreUsuario(), u.getClaveAcceso());
                } else {
                    usuario = new DTConsultor(u.getNombreUsuario(), u.getClaveAcceso());
                }
                
                break;
            }
        }
        
        return usuario;
    }
    
}
