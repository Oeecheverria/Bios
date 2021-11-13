/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Raul
 */
public class RegistroProductos {
    
    private static RegistroProductos instancia = null;
    
    
    public static RegistroProductos getInstancia() {
        if (instancia == null) {
            instancia = new RegistroProductos();
        }
        
        return instancia;
    }
    
    
    private List<Producto> productos;
    
    
    private RegistroProductos() {
        productos = new ArrayList();
        
        productos.add(new Producto(1, "Producto 1", 100));
        productos.add(new Producto(2, "Producto 2", 200));
        productos.add(new Producto(3, "Producto 3", 300));
    }
    
    
    public List<Producto> listar() {
        return new ArrayList(productos);
    }
    
    public Producto buscar(int codigo) {
        Producto productoEncontrado = null;
        
        for (Producto p : productos) {
            if (p.getCodigo() == codigo) {
                productoEncontrado = p;
                
                break;
            }
        }
        
        return productoEncontrado;
    }
    
    private int buscarPosicion(int codigo) {
        int posicion = -1;
        
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo() == codigo) {
                posicion = i;
                
                break;
            }
        }
        
        return posicion;
    }
    
    public void agregar(Producto producto) throws ExcepcionPersonalizada {
        if (buscar(producto.getCodigo()) == null) {
            productos.add(producto);
        } else {
            throw new ExcepcionPersonalizada("El producto ya existe.");
        }
    }
    
    public void modificar(Producto producto) throws ExcepcionPersonalizada {
        int posicion = buscarPosicion(producto.getCodigo());
        
        if (posicion != -1) {
            productos.set(posicion, producto);
        } else {
            throw new ExcepcionPersonalizada("El producto no existe.");
        }
    }
    
    public void eliminar(int codigo) throws ExcepcionPersonalizada {
        int posicion = buscarPosicion(codigo);
        
        if (posicion != -1) {
            productos.remove(posicion);
        } else {
            throw new ExcepcionPersonalizada("El producto no existe.");
        }
    }
    
}
