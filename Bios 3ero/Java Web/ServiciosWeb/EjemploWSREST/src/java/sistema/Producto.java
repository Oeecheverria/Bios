/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Raul
 */
@XmlRootElement
public class Producto implements Serializable {
    
    private int codigo;
    private String descripcion;
    private double precio;
    
    
    public int getCodigo() {
        return codigo;
    }
    
    @XmlElement
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    @XmlElement
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public double getPrecio() {
        return precio;
    }
    
    @XmlElement
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
    public Producto() {
        this(1, "", 0);
    }
    
    public Producto(int codigo, String descripcion, double precio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
    }
    
}
