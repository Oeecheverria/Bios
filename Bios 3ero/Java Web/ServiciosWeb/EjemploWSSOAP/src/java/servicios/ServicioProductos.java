/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import sistema.ExcepcionPersonalizada;
import sistema.Producto;
import sistema.RegistroProductos;

/**
 *
 * @author Raul
 */
@WebService(serviceName = "ServicioProductos")
public class ServicioProductos {
    
    @WebMethod(operationName = "listar")
    public List<Producto> listar() {
        return RegistroProductos.getInstancia().listar();
    }
    
    @WebMethod(operationName = "buscar")
    public Producto buscar(@WebParam(name = "codigo") int codigo) {
        return RegistroProductos.getInstancia().buscar(codigo);
    }
    
    // Cambiar la referencia a METRO por JAX-WS
    // (la versión de METRO que incorpora NetBeans da problemas con las excepciones)
    
    @WebMethod(operationName = "agregar")
    public void agregar(@WebParam(name = "producto") Producto producto) throws ExcepcionPersonalizada {
        RegistroProductos.getInstancia().agregar(producto);
    }
    
    @WebMethod(operationName = "modificar")
    public void modificar(@WebParam(name = "producto") Producto producto) throws ExcepcionPersonalizada {
        RegistroProductos.getInstancia().modificar(producto);
    }
    
    @WebMethod(operationName = "eliminar")
    public void eliminar(@WebParam(name = "codigo") int codigo) throws ExcepcionPersonalizada {
        RegistroProductos.getInstancia().eliminar(codigo);
    }
    
}
