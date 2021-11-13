/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arquitecturamvcycapas.modelo.logica;

/**
 *
 * @author Raúl
 */
public class FabricaLogica {
    
    public static ILogicaUsuario getLogicaUsuario() {
        return LogicaUsuario.getInstancia();
    }
    
    public static ILogicaEmpleado getLogicaEmpleado() {
        return LogicaEmpleado.getInstancia();
    }
    
}
