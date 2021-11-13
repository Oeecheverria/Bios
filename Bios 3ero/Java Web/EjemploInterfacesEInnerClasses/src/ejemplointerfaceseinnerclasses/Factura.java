/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplointerfaceseinnerclasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Raul
 */
public class Factura extends Documento implements Numerable, Serializable {

    private int numero;
    private List<LineaFactura> lineas;

    public List<LineaFactura> getLineas() {
        return new ArrayList(lineas);
    }

    public double getTotal() {
        double total = 0;

        for (LineaFactura lf : lineas) {
            total += lf.getImporte();
        }

        return total;
    }

    public Factura() {
        numero = 0;
        lineas = new ArrayList();
    }

    public void agregarLinea(int cantidad, int codigoProducto, String descripcion, double precioUnitario) {
        LineaFactura linea = new LineaFactura(cantidad, codigoProducto, descripcion, precioUnitario);
        lineas.add(linea);

        //TODO Ordenar las líneas
        //lineas.sort(new ComparadorLineasFacturaXPrecioUnitario());
        lineas.sort(new Comparator<LineaFactura>() {

            @Override
            public int compare(LineaFactura l1, LineaFactura l2) {
                return ((Integer) l1.getCodigoProducto()).compareTo(l2.getCodigoProducto());

            }

        });
    }

    @Override
    public void numerar(int numero) {
        this.numero = numero;
    }

    @Override
    public int leerNumero() {
        return numero;
    }

    public class LineaFactura {

        private int cantidad;
        private int codigoProducto;
        private String descripcion;
        private double precioUnitario;

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public int getCodigoProducto() {
            return codigoProducto;
        }

        public void setCodigoProducto(int codigoProducto) {
            this.codigoProducto = codigoProducto;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public double getPrecioUnitario() {
            return precioUnitario;
        }

        public void setPrecioUnitario(double precioUnitario) {
            this.precioUnitario = precioUnitario;
        }

        public double getImporte() {
            return getCantidad() * getPrecioUnitario();
        }

        public LineaFactura(int cantidad, int codigoProducto, String descripcion, double precioUnitario) {
            setCantidad(cantidad);
            setCodigoProducto(codigoProducto);
            setDescripcion(descripcion);
            setPrecioUnitario(precioUnitario);
        }

        @Override
        public String toString() {
            return "Cantidad: " + getCantidad() + ", Código de producto: " + getCodigoProducto() + ", Descripción: " + getDescripcion() + ", Precio unitario: " + getPrecioUnitario() + ", Importe: " + getImporte();
        }

    }

}
