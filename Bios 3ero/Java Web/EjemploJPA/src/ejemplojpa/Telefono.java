/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplojpa;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name ="Telefonos")

public class Telefono implements  Serializable {
    
    
    @Id
    @Column(name = "IDETEL")
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    
    @Column (name = "NROTEL",length = 10, unique = true)
       
    private String numero;
    
    @ManyToOne 
    @JoinColumn(name = "EMPLEADO")
    private Empleado empleado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Empleado getEmpleado() {
        return empleado;
    }


    public Telefono() {
        this(1, null , null);
    }
    public Telefono(String numero, Empleado empleado){
        this(1,numero, empleado);
    }
    
    public Telefono(int id, String numero, Empleado empleado)
    {
        setId(id);
        setNumero(numero);
       // setEmpleado(empleado);
        
        
        
    }

    @Override
    public String toString() {
        return getNumero();
                
                
                }

}
