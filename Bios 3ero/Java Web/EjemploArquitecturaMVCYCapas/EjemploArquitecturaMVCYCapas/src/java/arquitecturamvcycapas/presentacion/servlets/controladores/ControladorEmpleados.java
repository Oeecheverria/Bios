/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arquitecturamvcycapas.presentacion.servlets.controladores;

import arquitecturamvcycapas.modelo.compartidos.beans.excepciones.ExcepcionPersonalizada;
import arquitecturamvcycapas.modelo.compartidos.beans.datatypes.DTEmpleado;
import arquitecturamvcycapas.modelo.compartidos.beans.excepciones.ExcepcionPresentacion;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import arquitecturamvcycapas.modelo.logica.FabricaLogica;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author Raúl
 */
public class ControladorEmpleados extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) accion = "index";
        
        switch (accion) {
            case "index":
                index_get(request, response);
                
                break;
            case "agregar":
                agregar_get(request, response);
                
                break;
            case "ver":
                ver_get(request, response);
                
                break;
            case "modificar":
                modificar_get(request, response);
                
                break;
            case "eliminar":
                eliminar_get(request, response);
                
                break;
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        
        switch (accion) {
            case "agregar":
                agregar_post(request, response);
                
                break;
            case "modificar":
                modificar_post(request, response);
                
                break;
            case "eliminar":
                eliminar_post(request, response);
                
                break;
        }
    }
    
    public void index_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<DTEmpleado> empleados = FabricaLogica.getLogicaEmpleado().buscar(request.getParameter("buscar"));
            
            request.setAttribute("empleados", empleados);
            request.setAttribute("mensaje", "Cantidad de empleados: " + empleados.size());
            
            request.getRequestDispatcher("WEB-INF/vistas/empleados/index.jsp").forward(request, response);
        } catch (Exception ex) {
            if (ex instanceof ExcepcionPersonalizada) {
                request.setAttribute("mensaje", ex.getMessage());
            } else {
                request.setAttribute("mensaje", "No se pudo listar los empleados.");
            }
            
            request.getRequestDispatcher("WEB-INF/vistas/empleados/index.jsp").forward(request, response);
        }
    }
    
    public void agregar_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/vistas/empleados/agregar.jsp").forward(request, response);
    }
    
    public void agregar_post(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int cedula = 0;
            
            try {
                cedula = Integer.parseInt(request.getParameter("cedula"));
            } catch (NumberFormatException ex) {
                throw new ExcepcionPresentacion("La cédula no es válida.");
            }
            
            String nombre = request.getParameter("nombre");
            
            double sueldo = 0;
            
            try {
                sueldo = Double.parseDouble(request.getParameter("sueldo"));
            } catch (NumberFormatException ex) {
                throw new ExcepcionPresentacion("El sueldo no es válido.");
            }
            
            DTEmpleado empleado = new DTEmpleado(cedula, nombre, sueldo);
            FabricaLogica.getLogicaEmpleado().agregar(empleado);
            
            request.getSession().setAttribute("mensaje", "¡Empleado agregado con éxito!");
            
            response.sendRedirect("empleados");
        } catch (Exception ex) {
            if (ex instanceof ExcepcionPersonalizada) {
                request.setAttribute("mensaje", "¡ERROR! " + ex.getMessage());
            } else {
                request.setAttribute("mensaje", "¡ERROR! Se produjo un error al agregar el empleado.");
            }
            
            request.getRequestDispatcher("WEB-INF/vistas/empleados/agregar.jsp").forward(request, response);
        }
    }
    
    public void ver_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int cedula;
            
            try {
                cedula = Integer.parseInt(request.getParameter("cedula"));
            } catch (NumberFormatException ex) {
                throw new ExcepcionPresentacion("La cédula no es válida.");
            }
            
            DTEmpleado empleado = FabricaLogica.getLogicaEmpleado().obtener(cedula);
            
            if (empleado != null) {
                request.setAttribute("empleado", empleado);
                request.setAttribute("mensaje", "¡Empleado encontrado!");
                
                request.getRequestDispatcher("WEB-INF/vistas/empleados/ver.jsp").forward(request, response);
            } else {
                throw new ExcepcionPresentacion("No se encontró ningún empleado con la cédula " + cedula + ".");
            }
        } catch (Exception ex) {
            if (ex instanceof ExcepcionPersonalizada) {
                request.setAttribute("mensaje", "¡ERROR! " + ex.getMessage());
            } else {
                request.setAttribute("mensaje", "¡ERROR! Se produjo un error al obtener el empleado.");
            }
            
            request.getRequestDispatcher("WEB-INF/vistas/empleados/ver.jsp").forward(request, response);
        }
    }
    
    public void modificar_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int cedula;
            
            try {
                cedula = Integer.parseInt(request.getParameter("cedula"));
            } catch (NumberFormatException ex) {
                request.setAttribute("ocultarFormulario", true);
                
                throw new ExcepcionPresentacion("La cédula no es válida.");
            }
            
            DTEmpleado empleado = FabricaLogica.getLogicaEmpleado().obtener(cedula);
            
            if (empleado != null) {
                request.setAttribute("empleado", empleado);
                request.setAttribute("mensaje", "¡Empleado encontrado!");
                
                request.getRequestDispatcher("WEB-INF/vistas/empleados/modificar.jsp").forward(request, response);
            } else {
                request.setAttribute("ocultarFormulario", true);
                
                throw new ExcepcionPresentacion("No se encontró ningún empleado con la cédula " + cedula + ".");
            }
        } catch (Exception ex) {
            if (ex instanceof ExcepcionPersonalizada) {
                request.setAttribute("mensaje", "¡ERROR! " + ex.getMessage());
            } else {
                request.setAttribute("mensaje", "¡ERROR! Se produjo un error al obtener el empleado.");
            }
            
            request.getRequestDispatcher("WEB-INF/vistas/empleados/modificar.jsp").forward(request, response);
        }
    }
    
    public void modificar_post(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int cedula;
            
            try {
                cedula = Integer.parseInt(request.getParameter("cedula"));
            } catch (NumberFormatException ex) {
                throw new ExcepcionPresentacion("La cédula no es válida.");
            }
            
            String nombre = request.getParameter("nombre");
            
            double sueldo;
            
            try {
                sueldo = Double.parseDouble(request.getParameter("sueldo"));
            } catch (NumberFormatException ex) {
                throw new ExcepcionPresentacion("El sueldo no es válido.");
            }
            
            DTEmpleado empleado = new DTEmpleado(cedula, nombre, sueldo);
            FabricaLogica.getLogicaEmpleado().modificar(empleado);
            
            request.getSession().setAttribute("mensaje", "¡Empleado modificado con éxito!");
            
            response.sendRedirect("empleados");
        } catch (Exception ex) {
            if (ex instanceof ExcepcionPersonalizada) {
                request.setAttribute("mensaje", "¡ERROR! " + ex.getMessage());
            } else {
                request.setAttribute("mensaje", "¡ERROR! Se produjo un error al modificar el empleado.");
            }
            
            request.getRequestDispatcher("WEB-INF/vistas/empleados/modificar.jsp").forward(request, response);
        }
    }
    
    public void eliminar_get(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int cedula;
            
            try {
                cedula = Integer.parseInt(request.getParameter("cedula"));
            } catch (NumberFormatException ex) {
                throw new ExcepcionPresentacion("La cédula no es válida.");
            }
            
            DTEmpleado empleado = FabricaLogica.getLogicaEmpleado().obtener(cedula);
            
            if (empleado != null) {
                request.setAttribute("empleado", empleado);
                request.setAttribute("mensaje", "¡Empleado encontrado!");
                
                request.getRequestDispatcher("WEB-INF/vistas/empleados/eliminar.jsp").forward(request, response);
            } else {
                throw new ExcepcionPresentacion("No se encontró ningún empleado con la cédula " + cedula + ".");
            }
        } catch (Exception ex) {
            if (ex instanceof ExcepcionPersonalizada) {
                request.setAttribute("mensaje", "¡ERROR! " + ex.getMessage());
            } else {
                request.setAttribute("mensaje", "¡ERROR! Se produjo un error al obtener el empleado.");
            }
            
            request.getRequestDispatcher("WEB-INF/vistas/empleados/eliminar.jsp").forward(request, response);
        }
    }
    
    public void eliminar_post(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int cedula;
            
            try {
                cedula = Integer.parseInt(request.getParameter("cedula"));
            } catch (NumberFormatException ex) {
                throw new ExcepcionPresentacion("La cédula no es válida.");
            }
            
            FabricaLogica.getLogicaEmpleado().eliminar(cedula);
            
            request.getSession().setAttribute("mensaje", "¡Empleado eliminado con éxito!");
            
            response.sendRedirect("empleados");
        } catch (Exception ex) {
            if (ex instanceof ExcepcionPersonalizada) {
                request.setAttribute("mensaje", "¡ERROR! " + ex.getMessage());
            } else {
                request.setAttribute("mensaje", "¡ERROR! Se produjo un error al eliminar el empleado.");
            }
            
            request.getRequestDispatcher("WEB-INF/vistas/empleados/eliminar.jsp").forward(request, response);
        }
    }
    
}
