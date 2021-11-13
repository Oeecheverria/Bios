/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplojpa;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Usuario
 */

@Entity
@Table(name = "Asalariados")
@PrimaryKeyJoinColumn(name = "CEDULA", referencedColumnName= "CEDULA")

public class Asalariado extends Empleado implements  Serializable{
    
    private double salario;

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    
    public Asalariado(){
        this(1,"N/D",null,null,0);
    }
       public Asalariado(long cedula, String nombre, Integer edad, Boolean casado,double salario)
       {
           super (cedula, nombre, edad , casado);
           setSalario(salario);
       }
     
}
