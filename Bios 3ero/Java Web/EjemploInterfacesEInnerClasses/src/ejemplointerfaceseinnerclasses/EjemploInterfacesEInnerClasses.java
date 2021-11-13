/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplointerfaceseinnerclasses;

/**
 *
 * @author Raul
 */
public class EjemploInterfacesEInnerClasses {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Numerable numerable = new Factura();
        
        numerable.numerar(1010);
        
        Factura factura = (Factura)numerable;
        
        System.out.println("\nNúmero de la factura: " + factura.leerNumero());
        
        factura.agregarLinea(10, 50, "Reloj Cucú", 900);
        factura.agregarLinea(2, 22, "Sombrero Bombín", 440);
        factura.agregarLinea(7, 37, "Tocadiscos RCA Victor", 1500);
        
        System.out.println();
        
        for (Factura.LineaFactura lf : factura.getLineas()) {
            System.out.println(lf);
        }
        
        System.out.println("\nTotal: " + factura.getTotal());
    }
    
}
