/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquitecturamvcycapas.modelo.logica;

import arquitecturamvcycapas.modelo.compartidos.beans.datatypes.DTUsuario;

/**
 *
 * @author Raul
 */
class LogicaUsuario implements ILogicaUsuario {
    
    private static LogicaUsuario instancia = null;
    
    
    public static LogicaUsuario getInstancia() {
        if (instancia == null) {
            instancia = new LogicaUsuario();
        }
        
        return instancia;
    }
    
    
    private LogicaUsuario() {
        
    }
    
    
    @Override
    public DTUsuario ingresar(String nombreUsuario, String claveAcceso) {
        DTUsuario usuario = CacheUsuarios.getInstancia().obtener(nombreUsuario);
        
        if (usuario != null && usuario.getClaveAcceso().equals(claveAcceso)) {
            return usuario;
        } else {
            return null;
        }
    }
    
}
