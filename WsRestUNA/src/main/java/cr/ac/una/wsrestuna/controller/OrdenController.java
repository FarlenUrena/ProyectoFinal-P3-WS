/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.controller;

import cr.ac.una.wsrestuna.dto.OrdenDto;
import cr.ac.una.wsrestuna.service.OrdenService;
import cr.ac.una.wsrestuna.util.CodigoRespuesta;
import cr.ac.una.wsrestuna.util.Respuesta;
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
@Path("/OrdenController")
public class OrdenController {
    
    @EJB
    OrdenService ordenService;
    
    @GET
    @Path("/orden/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrden(@PathParam("id") Long id)
    {
        try
        {
            Respuesta res = ordenService.getOrden(id);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((OrdenDto) res.getResultado("Orden")).build();
        }
        catch(Exception ex)
        {
            Logger.getLogger(OrdenController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener la orden ").build();
        }
    }
    
    @GET
    @Path("/ordenes")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrdenes()
    {
        try
        {
            Respuesta res = ordenService.getOrdenes();
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }

          return Response.ok(new GenericEntity<List<OrdenDto>>((List<OrdenDto>) res.getResultado("OrdenesList")) {
            }).build();

        }
        catch(Exception ex)
        {
            Logger.getLogger(OrdenController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el orden ").build();
        }
    }
    
    //Falta probar desde el cliente si funciona
    @POST
    @Path("/orden/{orden}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarOrden(OrdenDto orden)
    {
        try
        {
            Respuesta res = ordenService.guardarOrden(orden);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((OrdenDto) res.getResultado("Orden")).build();
        }
        catch(Exception ex)
        {
            Logger.getLogger(OrdenController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener la orden ").build();
        }
    }

    @DELETE
    @Path("/orden/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarOrden(@PathParam("id") Long id)
    {
        try
        {
            Respuesta res = ordenService.eliminarOrden(id);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();
        }
        catch(Exception ex)
        {
            Logger.getLogger(OrdenController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener la orden ").build();
        }
    }
    
}
