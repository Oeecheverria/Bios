/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplojpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "Empleados")
@Inheritance(strategy = InheritanceType.JOINED)

public class Empleado implements Serializable {
    
    @Id
    private long cedula;
    
    @Column(length = 50, nullable = false)
    private String nombre;
    
    private Integer edad;
    private Boolean casado;
    
    @OneToMany(mappedBy = "Empleado", cascade = {CascadeType.PERSIST, CascadeType.REMOVE} )
    List<Telefono> telefonos;

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Boolean getCasado() {
        return casado;
    }

    public void setCasado(Boolean casado) {
        this.casado = casado;
    }

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    
    public Empleado () {
    this (1, "N/D", null, null);
    }
    
    public Empleado (long cedula, String nombre, Integer edad, Boolean casado){
        setCedula(cedula);
        setNombre(nombre);
        setEdad(edad);
        setCasado(casado);
        
        telefonos = new ArrayList();
    
    }
        
    @Override
    public String toString() {
        return getNombre();
    }
        
        
    
}