
import ejemplojpa.Asalariado;
import ejemplojpa.Empleado;
import ejemplojpa.Jornalero;
import ejemplojpa.Telefono;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EjemploJPA {

    private static EntityManagerFactory fabricaGestoresEntidades; //va acá porque lo vamos a usar en todos lados
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       //Atención a las bibliotecas que agregué, Driver MySQL y EclipseLink
       
       fabricaGestoresEntidades=Persistence.createEntityManagerFactory("UnidadPersistencia");
       
    //   agregarEmpleado();
      // modificarEmpleado();
      
    //  EliminarEmpleado();
    
    }
    
    private static void agregarEmpleado() {
        
        EntityTransaction transaccion=null;
        
        try {
            EntityManager gestorEntidades=fabricaGestoresEntidades.createEntityManager();
            
            transaccion=gestorEntidades.getTransaction();
            
            Empleado empleado = new Asalariado(2,"María Jose", 25, false, 2700.0);
            
            transaccion.begin();
            
            gestorEntidades.persist(empleado);
            
            transaccion.commit();
            
            System.out.println("Empleado agregado con éxito");
            
        } catch (Exception e) {
            try {
                if(transaccion!=null&&transaccion.isActive()){
                transaccion.rollback();
                }
                
            } catch (Exception eR) {
                System.out.println("ERROR! No se pudo deshacer los cambios");
            }
            
            System.out.println("ERROR! No se pudo agregar el empleado");
        }
    }
    
    
    
    
      
    private static void modificarEmpleado() {
        
        EntityTransaction transaccion=null;
        
        try {
            EntityManager gestorEntidades=fabricaGestoresEntidades.createEntityManager();
            
            transaccion=gestorEntidades.getTransaction();
            
            Empleado empleado =gestorEntidades.find(Empleado.class, 4L);
            transaccion.begin();
            empleado.setNombre("Maria Pia");
            gestorEntidades.merge(empleado);
            transaccion.commit();
            
            System.out.println("Empleado modificado con éxito");
            
        } catch (Exception e) {
            try {
                if(transaccion!=null&&transaccion.isActive()){
                transaccion.rollback();
                }
                
            } catch (Exception eR) {
                System.out.println("ERROR! No se pudo deshacer los cambios");
            }
            
            System.out.println("ERROR! No se pudo agregar el empleado");
        }
    }
        
         private static  void EliminarEmpleado() {
        
        EntityTransaction transaccion=null;
        
        try {
            EntityManager gestorEntidades=fabricaGestoresEntidades.createEntityManager();
            
            transaccion=gestorEntidades.getTransaction();
            
            Empleado empleado =gestorEntidades.find(Empleado.class, 4L);
            transaccion.begin();
         
            gestorEntidades.remove(empleado);
            transaccion.commit();
            
            System.out.println("Empleado borrado con éxito");
            
        } catch (Exception e) {
            try {
                if(transaccion!=null&&transaccion.isActive()){
                transaccion.rollback();
                }
                
            } catch (Exception eR) {
                System.out.println("ERROR! No se pudo deshacer los cambios");
            }
            
            System.out.println("ERROR! No se pudo agregar el empleado");
        }
    
    
}
 private static void agregarEmpleadoContelefono() {
        
        EntityTransaction transaccion=null;
        
        try {
            EntityManager gestorEntidades=fabricaGestoresEntidades.createEntityManager();
            
            transaccion=gestorEntidades.getTransaction();
            
            Empleado empleado = new Jornalero(5,"Arturo", 25, true, 2700.0,10);
            empleado.getTelefonos().add(new Telefono("555555",empleado));
            transaccion.begin();
            
            gestorEntidades.persist(empleado);
            
            transaccion.commit();
            
            System.out.println("Empleado agregado con éxito");
            
        } catch (Exception e) {
            try {
                if(transaccion!=null&&transaccion.isActive()){
                transaccion.rollback();
                }
                
            } catch (Exception eR) {
                System.out.println("ERROR! No se pudo deshacer los cambios");
            }
            
            System.out.println("ERROR! No se pudo agregar el empleado");
        }
    }


}