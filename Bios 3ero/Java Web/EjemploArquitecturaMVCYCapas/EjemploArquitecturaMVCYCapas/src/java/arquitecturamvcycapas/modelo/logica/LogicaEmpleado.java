/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arquitecturamvcycapas.modelo.logica;

import arquitecturamvcycapas.modelo.compartidos.beans.datatypes.DTEmpleado;
import arquitecturamvcycapas.modelo.compartidos.beans.excepciones.ExcepcionPersonalizada;
import arquitecturamvcycapas.modelo.compartidos.beans.excepciones.ExcepcionLogica;
import arquitecturamvcycapas.modelo.persistencia.FabricaPersistencia;
import java.util.List;

/**
 *
 * @author Raúl
 */
public class LogicaEmpleado implements ILogicaEmpleado {
    
    private static LogicaEmpleado instancia = null;
    
    
    public static LogicaEmpleado getInstancia() {
        if (instancia == null) {
            instancia = new LogicaEmpleado();
        }
        
        return instancia;
    }
    
    
    private LogicaEmpleado() {
        
    }
    
    
    public void validar(DTEmpleado empleado)
            throws ExcepcionPersonalizada {
        if (empleado == null) {
            throw new ExcepcionLogica("El empleado es nulo.");
        }
        
        if (empleado.getCedula() < 1) {
            throw new ExcepcionLogica("La cédula debe ser mayor o igual a 1.");
        }
        
        if ("".equals(empleado.getNombre().trim())) {
            throw new ExcepcionLogica("El nombre no puede quedar vacío.");
        }
        
        if (empleado.getNombre().length() > 50) {
            throw new ExcepcionLogica("El nombre no puede exceder los 50 caracteres de longitud.");
        }
        
        if (empleado.getSueldo() < 0) {
            throw new ExcepcionLogica("El sueldo debe ser mayor o igual a 0.");
        }
    }
    
    @Override
    public void agregar(DTEmpleado empleado)
            throws ExcepcionPersonalizada {
        validar(empleado);
        
        FabricaPersistencia.getPersistenciaEmpleado().agregar(empleado);
    }
    
    @Override
    public void modificar(DTEmpleado empleado)
            throws ExcepcionPersonalizada {
        validar(empleado);
        
        FabricaPersistencia.getPersistenciaEmpleado().modificar(empleado);
    }
    
    @Override
    public void eliminar(int cedula)
            throws ExcepcionPersonalizada {
        FabricaPersistencia.getPersistenciaEmpleado().eliminar(cedula);
    }
    
    @Override
    public DTEmpleado obtener(int cedula)
            throws ExcepcionPersonalizada {
        return FabricaPersistencia.getPersistenciaEmpleado().obtener(cedula);
    }
    
    @Override
    public List<DTEmpleado> listar()
            throws ExcepcionPersonalizada {
        return FabricaPersistencia.getPersistenciaEmpleado().listar();
    }
    
    @Override
    public List<DTEmpleado> buscar(String criterio)
            throws ExcepcionPersonalizada {
        if (criterio == null || criterio.trim().length() == 0) {
            return listar();
        }
        
        return FabricaPersistencia.getPersistenciaEmpleado().buscar(criterio);
    }
    
}
