/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviciosweb;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sistema.ExcepcionPersonalizada;
import sistema.Producto;
import sistema.RegistroProductos;

/**
 *
 * @author Raul
 */
@Path("/productos")
public class ServicioProductos {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar() {
        try {
            List<Producto> productos = RegistroProductos.getInstancia().listar();
            
            return Response.status(Response.Status.OK).entity(new GenericEntity<List<Producto>>(productos) {}).build();
        } catch (Exception ex) {
            MensajeErrorApiRest mensajeErrorApiRest = new MensajeErrorApiRest("No se pudo listar los productos.", ex.getMessage());
            
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(mensajeErrorApiRest).build());
        }
    }
    
    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscar(@PathParam("codigo") int codigo) {
        try {
            Producto producto = RegistroProductos.getInstancia().buscar(codigo);
            
            Response respuesta;
            
            if (producto != null) {
                respuesta = Response.status(Response.Status.OK).entity(producto).build();
            } else {
                respuesta = Response.status(Response.Status.NOT_FOUND).build();
            }
            
            return respuesta;
        } catch (Exception ex) {
            MensajeErrorApiRest mensajeErrorApiRest = new MensajeErrorApiRest("No se pudo buscar el producto.", ex.getMessage());
            
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(mensajeErrorApiRest).build());
        }
    }
    
    @POST
    //@Consumes(MediaType.APPLICATION_JSON)
    public Response agregar(Producto producto) {
        try {
            Producto productoExistente = RegistroProductos.getInstancia().buscar(producto.getCodigo());
            
            Response respuesta;
            
            if (productoExistente == null) {
                RegistroProductos.getInstancia().agregar(producto);
                
                respuesta = Response.status(Response.Status.CREATED).build();
            } else {
                respuesta = Response.status(Response.Status.CONFLICT).build();
            }
            
            return respuesta;
        } catch (ExcepcionPersonalizada ex) {
            MensajeErrorApiRest mensajeErrorApiRest = new MensajeErrorApiRest(ex.getMessage());
            
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(mensajeErrorApiRest).build());
        } catch (Exception ex) {
            MensajeErrorApiRest mensajeErrorApiRest = new MensajeErrorApiRest("No se pudo agregar el producto.", ex.getMessage());
            
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(mensajeErrorApiRest).build());
        }
    }
    
    @PUT
    //@Consumes(MediaType.APPLICATION_JSON)
    public Response modificar(Producto producto) {
        try {
            Producto productoExistente = RegistroProductos.getInstancia().buscar(producto.getCodigo());
            
            Response respuesta;
            
            if (productoExistente != null) {
                RegistroProductos.getInstancia().modificar(producto);
                
                respuesta = Response.status(Response.Status.OK).build();
            } else {
                respuesta = Response.status(Response.Status.NOT_FOUND).build();
            }
            
            return respuesta;
        } catch (ExcepcionPersonalizada ex) {
            MensajeErrorApiRest mensajeErrorApiRest = new MensajeErrorApiRest(ex.getMessage());
            
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(mensajeErrorApiRest).build());
        } catch (Exception ex) {
            MensajeErrorApiRest mensajeErrorApiRest = new MensajeErrorApiRest("No se pudo modificar el producto.", ex.getMessage());
            
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(mensajeErrorApiRest).build());
        }
    }
    
    @DELETE
    @Path("/{codigo}")
    public Response eliminar(@PathParam("codigo") int codigo) {
        try {
            Producto productoExistente = RegistroProductos.getInstancia().buscar(codigo);
            
            Response respuesta;
            
            if (productoExistente != null) {
                RegistroProductos.getInstancia().eliminar(codigo);
                
                respuesta = Response.status(Response.Status.OK).build();
            } else {
                respuesta = Response.status(Response.Status.NOT_FOUND).build();
            }
            
            return respuesta;
        } catch (ExcepcionPersonalizada ex) {
            MensajeErrorApiRest mensajeErrorApiRest = new MensajeErrorApiRest(ex.getMessage());
            
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(mensajeErrorApiRest).build());
        } catch (Exception ex) {
            MensajeErrorApiRest mensajeErrorApiRest = new MensajeErrorApiRest("No se pudo eliminar el producto.", ex.getMessage());
            
            throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(mensajeErrorApiRest).build());
        }
    }
    
}
