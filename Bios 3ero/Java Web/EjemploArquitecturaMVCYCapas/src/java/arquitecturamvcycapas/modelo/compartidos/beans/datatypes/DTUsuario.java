/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquitecturamvcycapas.modelo.compartidos.beans.datatypes;

import java.io.Serializable;

/**
 *
 * @author Raul
 */
public class DTUsuario implements Serializable {
    
    private String nombreUsuario;
    private String claveAcceso;
    
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    public String getClaveAcceso() {
        return claveAcceso;
    }
    
    public void setClaveAcceso(String claveAcceso) {
        this.claveAcceso = claveAcceso;
    }
    
    
    public DTUsuario() {
        this("invitado", "");
    }
    
    public DTUsuario(String nombreUsuario, String claveAcceso) {
        setNombreUsuario(nombreUsuario);
        setClaveAcceso(claveAcceso);
    }
    
    
    @Override
    public String toString() {
        return getNombreUsuario();
    }
    
}
