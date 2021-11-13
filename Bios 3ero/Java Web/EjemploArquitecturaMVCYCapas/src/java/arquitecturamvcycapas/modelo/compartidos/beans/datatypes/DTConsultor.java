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
public class DTConsultor extends DTUsuario {
    
    public DTConsultor() {
        this("invitado", "");
    }
    
    public DTConsultor(String nombreUsuario, String claveAcceso) {
        super(nombreUsuario, claveAcceso);
    }
    
}
