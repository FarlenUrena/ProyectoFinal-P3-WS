/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.controller;

import cr.ac.una.wsrestuna.dto.GrupoDto;
import cr.ac.una.wsrestuna.dto.ProductoDto;
import cr.ac.una.wsrestuna.model.Grupo;
import cr.ac.una.wsrestuna.service.GrupoService;
import cr.ac.una.wsrestuna.service.ProductoService;
import cr.ac.una.wsrestuna.util.CodigoRespuesta;
import cr.ac.una.wsrestuna.util.Respuesta;
import cr.ac.una.wsrestuna.util.Secure;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Farlen
 */
@Secure
@Path("/ProductoController")
public class ProductoController {

    @EJB
    ProductoService productoService;
    @EJB
    GrupoService grupoService;

    @GET
    @Path("/producto/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducto(@PathParam("id") Long id) {
        try {
            Respuesta res = productoService.getProducto(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((ProductoDto) res.getResultado("Producto")).build();
        } catch (Exception ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el producto ").build();
        }
    }

    @GET
    @Path("/productos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductos() {
        try {
            Respuesta res = productoService.getProductos();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }

            return Response.ok(new GenericEntity<List<ProductoDto>>((List<ProductoDto>) res.getResultado("ProductosList")) {
            }).build();

        } catch (Exception ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el producto ").build();
        }
    }

    @GET
    @Path("/productos/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductosGrupo(@PathParam("id") Long id) {
        try {
            Respuesta res = new Respuesta();
            Respuesta res2 = grupoService.getGrupo(id);
            if (!res2.getEstado()) {
                res = productoService.getProductosPorGrupo((GrupoDto) res2.getResultado("Grupo"));
                if (!res.getEstado()) {
                    return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
                }
            }

            return Response.ok(new GenericEntity<List<ProductoDto>>((List<ProductoDto>) res.getResultado("ProductosList")) {
            }).build();

        } catch (Exception ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el producto ").build();
        }
    }

    //Falta probar desde el cliente si funciona
    @POST
    @Path("/producto/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarProducto(ProductoDto productoDto) {
        try {
            Respuesta res = productoService.guardarProducto(productoDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((ProductoDto) res.getResultado("Producto")).build();
        } catch (Exception ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el producto ").build();
        }
    }
    //Falta probar desde el cliente si funciona
    @DELETE
    @Path("/producto/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarProducto(@PathParam("id") Long id) {
        try {
            Respuesta res = productoService.eliminarProducto(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el producto ").build();
        }
    }

}
