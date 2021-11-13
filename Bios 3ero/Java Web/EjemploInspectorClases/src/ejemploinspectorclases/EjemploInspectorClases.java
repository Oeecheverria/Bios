/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemploinspectorclases;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 *
 * @author Raul
 */
public class EjemploInspectorClases {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Class clase = Class.forName("ejemploinspectorclases.Empleado");
            
            System.out.println("Clase: " + clase.getName());
            System.out.println("Clase: " + clase.getSimpleName());
            
            System.out.println("\n\nAtributos:\n");
            
            Field[] atributos = clase.getFields();
            
            for (Field a : atributos) {
                System.out.println((Modifier.isPublic(a.getModifiers())) ? "+ ":  "- "+ a.getName() + " : " + a.getType().getSimpleName());
            }
            System.out.println();
            
                   Field[] atributosDeclardos = clase.getDeclaredFields();
            
            for (Field a : atributosDeclardos) {
                System.out.println("+ " + a.getName() + " : " + a.getType().getSimpleName());
            }
            
            
            System.out.println("\n\n Constructores: \n");
            
            Constructor[] constructores = clase.getConstructors();
            String parametros;
            for (Constructor c : constructores){
                parametros = "";
                
                
                for(Class p : c.getParameterTypes())
                {
                    if(parametros.length () > 0)
                        
                        parametros += ", ";
                    {
                    
                    parametros += p.getSimpleName();
                    }
                    System.out.println("+ " + clase.getSimpleName() +  "(" + parametros+ ")");
                }
                    
                    }
            System.out.println();
                
                
                
                  System.out.println("\n\n Metodos: \n");
            
            Method[] metodos = clase.getMethods();
            
//            String parametros; ya la estamos usando arriba 
            for (Method m: metodos){
                parametros = "";
                
                
                for(Class p : m.getParameterTypes())
                {
                    if(parametros.length () > 0)
                        
                        parametros += ", ";
                    {
                    
                    parametros += p.getSimpleName();
                    }
                    System.out.println("+ " + m.getName()+  "(" + parametros + ") : " +m.getReturnType().getSimpleName());
                }
                   
                    
                    
                
            }
        
             System.out.println();
             
              Method[] metodosDeclarados = clase.getDeclaredMethods();
            
//            String parametros; ya la estamos usando arriba 
            for (Method m: metodosDeclarados){
                parametros = "";
                
                
                for(Class p : m.getParameterTypes())
                {
                    if(parametros.length () > 0)
                        
                        parametros += ", ";
                    {
                    
                    parametros += p.getSimpleName();
                    }
                              System.out.println((Modifier.isPublic(m.getModifiers())) ? "+ ":  "- "+ m.getName() + " : " + m.getReturnType().getSimpleName());
            }
            }
                    
                 System.out.println();
                 
                  System.out.println("\n\n Pruebas de instancia: \n");
                  
                  Object objeto = clase.newInstance();
            
                    Field atributoEdad = clase.getField("edad");
                  System.out.println("Edad antes de cumplir años: " + atributoEdad.getInt((objeto)));
                  
                  Method metodoCumplirAnios = clase.getDeclaredMethod("cumplirAnios");
                  metodoCumplirAnios.setAccessible(true);
                  
                  metodoCumplirAnios.invoke(objeto);
                   System.out.println("Edad antes de cumplir años: " + atributoEdad.getInt((objeto)));
                  
                  
        } catch (ClassNotFoundException ex) {
            System.out.println("¡ERROR! No se encontró la clase.");
        } catch (Exception ex) {
            System.out.println("¡ERROR! No se pudo realizar las pruebas con la instancia.");
        }
    }
    
}
