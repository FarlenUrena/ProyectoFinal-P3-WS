/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.controller;

import cr.ac.una.wsrestuna.dto.CajaDto;
import cr.ac.una.wsrestuna.service.CajaService;
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
@Path("/CajaController")
public class CajaController {
    
    @EJB
    CajaService cajaService;
    
    @GET
    @Path("/caja/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCaja(@PathParam("id") Long id)
    {
        try
        {
            Respuesta res = cajaService.getCaja(id);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((CajaDto) res.getResultado("Caja")).build();
        }
        catch(Exception ex)
        {
            Logger.getLogger(CajaController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener la caja ").build();
        }
    }
    
    @GET
    @Path("/cajas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCajas()
    {
        try
        {
            Respuesta res = cajaService.getCajas();
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }

          return Response.ok(new GenericEntity<List<CajaDto>>((List<CajaDto>) res.getResultado("CajasList")) {
            }).build();

        }
        catch(Exception ex)
        {
            Logger.getLogger(CajaController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el caja ").build();
        }
    }
    
    //Falta probar desde el cliente si funciona
    @POST
    @Path("/caja/{caja}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarCaja(CajaDto caja)
    {
        try
        {
            Respuesta res = cajaService.guardarCaja(caja);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((CajaDto) res.getResultado("Caja")).build();
        }
        catch(Exception ex)
        {
            Logger.getLogger(CajaController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener la caja ").build();
        }
    }

    @DELETE
    @Path("/caja/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarCaja(@PathParam("id") Long id)
    {
        try
        {
            Respuesta res = cajaService.eliminarCaja(id);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();
        }
        catch(Exception ex)
        {
            Logger.getLogger(CajaController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener la caja ").build();
        }
    }
}
