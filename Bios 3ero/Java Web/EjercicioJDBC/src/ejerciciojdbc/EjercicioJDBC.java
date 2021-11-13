/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciojdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 *
 * @author Raúl
 */
public class EjercicioJDBC {
    
    private static Connection conexion;
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Ejercicio de JDBC");
        
        if (abrirConexion()) {
            int opcion;
            
            do {
                opcion = mostrarMenu();
                System.out.println();
                
                switch(opcion) {
                    case 1:
                        opcionAgregarPersona();
                        
                        break;
                    case 2:
                        opcionBuscarPersona();
                        
                        break;
                    case 3:
                        opcionActualizarDatosPersona();
                        
                        break;
                    case 4:
                        opcionEliminarPersona();
                        
                        break;
                    case 5:
                        opcionListarPersonas();
                        
                        break;
                    case 0:
                        opcionTerminar();
                        
                        break;
                    default:
                        System.out.println("¡ERROR! Opción no válida.");
                        
                        break;
                }
            } while (opcion != 0);
        } else {
            System.out.println("\n¡Error! No se pudo abrir la conexión con la base de datos.");
        }
    }
    
    public static boolean abrirConexion() {
        try {
            // todo: Abrir conexión
           
            
             conexion =  DriverManager.getConnection("JDBC:mysql://localhost:3306/EjercicioJDBC","root","root");
            
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    public static int pedirNumeroEntero(String mensaje, String mensajeError) {
        boolean error;
        Scanner teclado = new Scanner(System.in);
        int numero = 0;
        
        do {
            error = false;
            
            try {
                System.out.print(mensaje);
                numero = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println(mensajeError);
                error = true;
            }
        } while (error);
        
        return numero;
    }
    
    public static double pedirNumeroDecimal(String mensaje, String mensajeError) {
        boolean error;
        Scanner teclado = new Scanner(System.in);
        double numero = 0;
        
        do {
            error = false;
            
            try {
                System.out.print(mensaje);
                numero = Double.parseDouble(teclado.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println(mensajeError);
                error = true;
            }
        } while (error);
        
        return numero;
    }
    
    public static int mostrarMenu() {
        System.out.println("\n\nMenú:\n");
        System.out.println("1 - Agregar una persona");
        System.out.println("2 - Buscar una persona (por CI)");
        System.out.println("3 - Actualizar datos de una persona (por CI)");
        System.out.println("4 - Eliminar una persona (por CI)");
        System.out.println("5 - Listar todas las personas");
        System.out.println("0 - Terminar");
        
        return pedirNumeroEntero("\nIngrese una opcion: ", "\n¡ERROR! La opción no es un número entero válido.");
    }
    
    public static void opcionAgregarPersona() {
        Scanner teclado = new Scanner(System.in);
        
        long cedula = pedirNumeroEntero("Ingrese la cédula: ", "\n¡Error! La cédula no es un número entero válido.\n");
        
        System.out.print("Ingrese el nombre: ");
        String nombre = teclado.nextLine();
        
        int edad = pedirNumeroEntero("Ingrese la edad: ", "\n¡Error! La edad no es un número entero válido.\n");
        
        System.out.print("¿Está casado/a? (s/n): ");
        boolean casado = teclado.nextLine().trim().toLowerCase().equals("s");
        
        double sueldo = pedirNumeroDecimal("Ingrese el sueldo: ", "\n¡Error! El sueldo no es un número decimal válido.\n");
        
        // todo: Agregar persona
    }
    
    public static void opcionBuscarPersona() {
        long cedula = pedirNumeroEntero("Ingrese la cédula: ", "\n¡Error! La cédula no es un número entero válido.\n");
        PreparedStatement consulta = null;
         ResultSet resultado = null;
        try {
             Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/EjemploJDBC", "root", "root");
          consulta = conexion.prepareStatement("SELECT*  FROM Personas  WHERE Cedula = ?;");
           resultado = consulta.executeQuery();
            System.out.println();
            while (resultado.next()) {
                cedula = resultado.getLong(1);
                String nombre = resultado.getString("Nombre");
                Integer edad = (Integer) resultado.getObject("Edad");
                Double sueldo = (Double) resultado.getObject("Sueldo");
                Boolean casado = resultado.getBoolean("Casado");
                if (casado == false) {
                    casado = resultado.wasNull() ? null : casado;
                }
                System.out.println("Cédula: " + cedula + ", Nombre: " + nombre + ", Edad: " + (edad != null ? edad : "N/D") + ", Sueldo: " + (sueldo != null ? sueldo : "N/D") + ", Casado: " + (casado != null ? (casado ? "Sí" : "No") : "N/D"));
            }
        } catch (Exception ex) {
            System.out.println("¡ERROR! No se pudo listar los empleados.");
        } finally {
            try {
                if (resultado != null) {
                    resultado.close();
                }
                if (consulta != null) {
                    consulta.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (Exception ex) {
                System.out.println("¡ERROR! No se pudo cerrar los recursos.");
            }
        // todo: Buscar persona
    }
    }
    
    public static void opcionActualizarDatosPersona() {
        Scanner teclado = new Scanner(System.in);
        
        long cedula = pedirNumeroEntero("Ingrese la cédula: ", "\n¡Error! La cédula no es un número entero válido.\n");
        
        // todo: Actualizar datos de persona
    }
    
    public static void opcionEliminarPersona() {
        
        
        long cedula = pedirNumeroEntero("Ingrese la cédula: ", "\n¡Error! La cédula no es un número entero válido.\n");
           try {
                CallableStatement consulta = conexion.prepareCall("{CALL EliminarPersona(?)}");
                
                consulta.setLong(1,cedula);
               int filasAfectadas =  consulta.executeUpdate();
                
               if(filasAfectadas == 1){
                System.out.println("Persona eliminada correctamente");
                
               }
               else 
               {
                   System.out.println("No se pudo eliminar la persona");
                 }
             
             
             
        // todo: Eliminar persona (procedimiento almacenado)
        } catch (Exception e) 
        {
            
            System.out.println("No se pudo eliminar la persona");
        }
       
    }
    
    public static void opcionListarPersonas() {
        
             //   Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        try {
       //     conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/EjemploJDBC", "root", "root");
            consulta = conexion.prepareCall("SELECT*  FROM Personas;");
            resultado = consulta.executeQuery();
            System.out.println();
            while (resultado.next()) {
                long cedula = resultado.getLong(1);
                String nombre = resultado.getString("Nombre");
                Integer edad = (Integer) resultado.getObject("Edad");
                Double sueldo = (Double) resultado.getObject("Sueldo");
                Boolean casado = resultado.getBoolean("Casado");
                if (casado == false) {
                    casado = resultado.wasNull() ? null : casado;
                }
                System.out.println("Cédula: " + cedula + ", Nombre: " + nombre + ", Edad: " + (edad != null ? edad : "N/D") + ", Sueldo: " + (sueldo != null ? sueldo : "N/D") + ", Casado: " + (casado != null ? (casado ? "Sí" : "No") : "N/D"));
            }
        } catch (Exception ex) {
            System.out.println("¡ERROR! No se pudo listar los empleados.");
        } finally {
            try {
                if (resultado != null) {
                    resultado.close();
                }
                if (consulta != null) {
                    consulta.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (Exception ex) {
                System.out.println("¡ERROR! No se pudo cerrar los recursos.");
            }
        }
        
        
        
        // todo: Listar personas
    }
    
    public static void opcionTerminar() {
        // todo: Cerrar conexión
    }
    
}
