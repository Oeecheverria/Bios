/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplojpa;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "Jornaleros")
@PrimaryKeyJoinColumn(name = "CEDULA", referencedColumnName= "CEDULA")

public class Jornalero   extends Empleado  implements  Serializable {
    private double jornal;
    private int diasTrabajados;

    public double getJornal() {
        return jornal;
    }

    public void setJornal(double jornal) {
        this.jornal = jornal;
    }

    public int getDiasTrabajados() {
        return diasTrabajados;
    }

    public void setDiasTrabajados(int diasTrabajados) {
        this.diasTrabajados = diasTrabajados;
    }


public Jornalero(){
    this(1,"N/F",null, null,0,0);
    
}

    public Jornalero(long cedula, String nombre, Integer edad, Boolean casado,double jornal, int diasTrabajados){
        super(cedula,nombre, edad ,casado);
        
        setJornal(jornal);
        setDiasTrabajados(diasTrabajados);
    }
    
}
