/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arquitecturamvcycapas.modelo.persistencia;

/**
 *
 * @author Raúl
 */
public class FabricaPersistencia {
    
    public static IPersistenciaEmpleado getPersistenciaEmpleado() {
        return PersistenciaEmpleado.getInstancia();
    }
    
}
