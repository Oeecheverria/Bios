/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemploconsumirwssoap;

import java.util.List;
import servicioproductos.ExcepcionPersonalizada_Exception;
import servicioproductos.Producto;
import servicioproductos.ServicioProductos;
import servicioproductos.ServicioProductos_Service;

/**
 *
 * @author Raul
 */
public class EjemploConsumirWSSOAP {
    
    private static ServicioProductos_Service servicio;
    private static ServicioProductos puerto;
    
    private static Producto producto;
    private static Producto productoBuscado;
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        servicio = new ServicioProductos_Service();
        puerto = servicio.getServicioProductosPort();
        
        System.out.println();
        
        listar();
        
        agregar();
        
        buscar();
        
        listar();
        
        modificar();
        
        listar();
        
        eliminar();
        
        listar();
    }
    
    private static void listar() {
        try {
            List<Producto> productos = puerto.listar();
            
            for (Producto p : productos) {
                System.out.println("Código: " + p.getCodigo() + ", Descripción: " + p.getDescripcion() + ", Precio: " + p.getPrecio());
            }
        } catch (Exception ex) {
            System.out.println("¡ERROR! No se pudo listar los productos.");
        }
        
        System.out.println();
    }
    
    private static void buscar() {
        try {
            productoBuscado = puerto.buscar(4);
            
            System.out.println("Datos del producto 4:");
            System.out.println("Código: " + productoBuscado.getCodigo() + ", Descripción: " + productoBuscado.getDescripcion() + ", Precio: " + productoBuscado.getPrecio());
        } catch (Exception ex) {
            System.out.println("¡ERROR! No se pudo buscar el producto.");
        }
        
        System.out.println();
    }
    
    private static void agregar() {
        producto = new Producto();
        producto.setCodigo(4);
        producto.setDescripcion("Producto 4");
        producto.setPrecio(400);
        
        try {
            puerto.agregar(producto);
            
            System.out.println("Agregado producto 4.");
        } catch (ExcepcionPersonalizada_Exception ex) {
            System.out.println("¡ERROR! " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("¡ERROR! No se pudo agregar el producto.");
        }
        
        System.out.println();
    }
    
    private static void modificar() {
        producto.setDescripcion("Otro Producto 4");
        
        try {
            puerto.modificar(producto);
            
            System.out.println("Modificado producto 4.");
        } catch (ExcepcionPersonalizada_Exception ex) {
            System.out.println("¡ERROR! " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("¡ERROR! No se pudo modificar el producto.");
        }
        
        System.out.println();
    }
    
    private static void eliminar() {
        try {
            puerto.eliminar(4);
            
            System.out.println("Eliminado producto 4.");
        } catch (ExcepcionPersonalizada_Exception ex) {
            System.out.println("¡ERROR! " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("¡ERROR! No se pudo eliminar el producto.");
        }
        
        System.out.println();
    }
    
}
