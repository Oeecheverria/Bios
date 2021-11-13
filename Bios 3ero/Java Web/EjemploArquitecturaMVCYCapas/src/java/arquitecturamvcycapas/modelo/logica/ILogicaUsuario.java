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
public interface ILogicaUsuario {
    
    DTUsuario ingresar(String nombreUsuario, String claveAcceso);
    
}
