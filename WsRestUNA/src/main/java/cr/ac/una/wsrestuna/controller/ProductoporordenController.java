/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.controller;

import cr.ac.una.wsrestuna.dto.OrdenDto;
import cr.ac.una.wsrestuna.dto.ProductoporordenDto;
import cr.ac.una.wsrestuna.service.OrdenService;
import cr.ac.una.wsrestuna.service.ProductoporordenService;
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
@Path("/ProductoporordenController")
public class ProductoporordenController {

    @EJB
    ProductoporordenService productoporordenService;
    @EJB
    OrdenService ordenService;

    @GET
    @Path("/productopororden/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductopororden(@PathParam("id") Long id) {
        try {
            Respuesta res = productoporordenService.getProductopororden(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((ProductoporordenDto) res.getResultado("Productopororden")).build();
        } catch (Exception ex) {
            Logger.getLogger(ProductoporordenController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el producto por orden ").build();
        }
    }

    @GET
    @Path("/productospororden")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductospororden() {
        try {
            Respuesta res = productoporordenService.getProductoporordens();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }

            return Response.ok(new GenericEntity<List<ProductoporordenDto>>((List<ProductoporordenDto>) res.getResultado("ProductosporordenList")) {
            }).build();

        } catch (Exception ex) {
            Logger.getLogger(ProductoporordenController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el producto por orden ").build();
        }
    }
    @GET
    @Path("/productosporordenIdOrden/{idOrden}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductosporordenByOrden(@PathParam("idOrden") Long idOrden) {
        try {
            Respuesta res1 = ordenService.getOrden(idOrden);
            
            Respuesta res = productoporordenService.getProductoporordensByOrden((OrdenDto)res1.getResultado("Orden"));
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }

            return Response.ok(new GenericEntity<List<ProductoporordenDto>>((List<ProductoporordenDto>) res.getResultado("ProductosporordenFiltered")) {
            }).build();

        } catch (Exception ex) {
            Logger.getLogger(ProductoporordenController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el producto por orden ").build();
        }
    }

    //Falta probar desde el cliente si funciona
    @POST
    @Path("/productopororden")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarProductopororden(ProductoporordenDto productopororden) {
        try {
            Respuesta res = productoporordenService.guardarProductopororden(productopororden);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((ProductoporordenDto) res.getResultado("Productopororden")).build();
        } catch (Exception ex) {
            Logger.getLogger(ProductoporordenController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el producto por orden ").build();
        }
    }

    @DELETE
    @Path("/productopororden/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarProductopororden(@PathParam("id") Long id) {
        try {
            Respuesta res = productoporordenService.eliminarProductopororden(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(ProductoporordenController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el producto por orden ").build();
        }
    }

}
