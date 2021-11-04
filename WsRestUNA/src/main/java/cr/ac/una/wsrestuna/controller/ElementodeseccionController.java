/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.controller;

import cr.ac.una.wsrestuna.dto.ElementodeseccionDto;
import cr.ac.una.wsrestuna.service.ElementodeseccionService;
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
@Path("/ElementodeseccionController")
public class ElementodeseccionController {
    
    @EJB
    ElementodeseccionService elementodeseccionService;
    
    
    @GET
    @Path("/elementoDeSeccion/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getElementodeseccion(@PathParam("id") Long id)
    {
        try
        {
            Respuesta res = elementodeseccionService.getElementodeseccion(id);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((ElementodeseccionDto) res.getResultado("Elementodeseccion")).build();
        }
        catch(Exception ex)
        {
            Logger.getLogger(ElementodeseccionController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el elemento de seccion ").build();
        }
    }
    
    @GET
    @Path("/elementosDeSeccion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getElementosdeseccion()
    {
        try
        {
            Respuesta res = elementodeseccionService.getElementodeseccions();
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }

          return Response.ok(new GenericEntity<List<ElementodeseccionDto>>((List<ElementodeseccionDto>) res.getResultado("ElementosdeseccionList")) {
            }).build();

        }
        catch(Exception ex)
        {
            Logger.getLogger(ElementodeseccionController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el elemento de seccion ").build();
        }
    }
    
    //Falta probar desde el cliente si funciona
    @POST
    @Path("/elementoDeSeccion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarElementodeseccion(ElementodeseccionDto elementodeseccion)
    {
        try
        {
            Respuesta res = elementodeseccionService.guardarElementodeseccion(elementodeseccion);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((ElementodeseccionDto) res.getResultado("Elementodeseccion")).build();
        }
        catch(Exception ex)
        {
            Logger.getLogger(ElementodeseccionController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el elemento de seccion ").build();
        }
    }

    @DELETE
    @Path("/elementoDeSeccion/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarElementodeseccion(@PathParam("id") Long id)
    {
        try
        {
            Respuesta res = elementodeseccionService.eliminarElementodeseccion(id);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();
        }
        catch(Exception ex)
        {
            Logger.getLogger(ElementodeseccionController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el elemento de seccion ").build();
        }
    }
    
}
