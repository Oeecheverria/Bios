/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquitecturamvcycapas.modelo.compartidos.beans.datatypes;

/**
 *
 * @author Raul
 */
public class DTAdministrador extends DTUsuario {
    
    public DTAdministrador() {
        this("invitado", "");
    }
    
    public DTAdministrador(String nombreUsuario, String claveAcceso) {
        super(nombreUsuario, claveAcceso);
    }
    
}
