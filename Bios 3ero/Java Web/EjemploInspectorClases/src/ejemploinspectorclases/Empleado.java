/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemploinspectorclases;

/**
 *
 * @author Raul
 */
public class Empleado {
    
    public String nombre;
    public String apellido;
    public int edad;
    public boolean casado;
    private double sueldo;
    
    
    public Empleado() {
        this("", "", 0, false, 0);
    }
    
    public Empleado(String nombre, String apellido, int edad, boolean casado, double sueldo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.casado = casado;
        this.sueldo = sueldo;
    }
    
    
    public double calcularSueldoAPagar() {
        return sueldo * .85;
    }
    
    private void cumplirAnios() {
        edad++;
    }
    
    
    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Apellido: " + apellido + ", Edad: " + edad + ", Casado: " + (casado ? "Sí" : "No") + ", Sueldo: " + sueldo;
    }
    
}
