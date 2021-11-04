/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.controller;

import cr.ac.una.wsrestuna.dto.SeccionDto;
import cr.ac.una.wsrestuna.service.SeccionService;
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
@Path("/SeccionController")
public class SeccionController {
    
    @EJB
    SeccionService seccionService;
    
    @GET
    @Path("/seccion/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSeccion(@PathParam("id") Long id)
    {
        try
        {
            Respuesta res = seccionService.getSeccion(id);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((SeccionDto) res.getResultado("Seccion")).build();
        }
        catch(Exception ex)
        {
            Logger.getLogger(SeccionController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener la seccion ").build();
        }
    }
    
    @GET
    @Path("/secciones")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSecciones()
    {
        try
        {
            Respuesta res = seccionService.getSecciones();
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }

          return Response.ok(new GenericEntity<List<SeccionDto>>((List<SeccionDto>) res.getResultado("SeccionesList")) {
            }).build();

        }
        catch(Exception ex)
        {
            Logger.getLogger(SeccionController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el seccion ").build();
        }
    }
    
    //Falta probar desde el cliente si funciona
    @POST
    @Path("/seccion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarSeccion(SeccionDto seccion)
    {
        try
        {
            Respuesta res = seccionService.guardarSeccion(seccion);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((SeccionDto) res.getResultado("Seccion")).build();
        }
        catch(Exception ex)
        {
            Logger.getLogger(SeccionController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener la seccion ").build();
        }
    }

    @DELETE
    @Path("/seccion/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarSeccion(@PathParam("id") Long id)
    {
        try
        {
            Respuesta res = seccionService.eliminarSeccion(id);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();
        }
        catch(Exception ex)
        {
            Logger.getLogger(SeccionController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener la seccion ").build();
        }
    }
}
