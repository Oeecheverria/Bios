/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arquitecturamvcycapas.modelo.logica;

import arquitecturamvcycapas.modelo.compartidos.beans.datatypes.DTEmpleado;
import arquitecturamvcycapas.modelo.compartidos.beans.excepciones.ExcepcionPersonalizada;
import java.util.List;

/**
 *
 * @author Raúl
 */
public interface ILogicaEmpleado {
    
    void agregar(DTEmpleado empleado) throws ExcepcionPersonalizada;
    void modificar(DTEmpleado empleado) throws ExcepcionPersonalizada;
    void eliminar(int cedula) throws ExcepcionPersonalizada;
    DTEmpleado obtener(int cedula) throws ExcepcionPersonalizada;
    List<DTEmpleado> listar() throws ExcepcionPersonalizada;
    List<DTEmpleado> buscar(String criterio) throws ExcepcionPersonalizada;
    
}
