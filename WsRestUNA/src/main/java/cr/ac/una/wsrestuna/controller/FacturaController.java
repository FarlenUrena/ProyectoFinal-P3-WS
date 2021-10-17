/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.controller;

import cr.ac.una.wsrestuna.dto.FacturaDto;
import cr.ac.una.wsrestuna.service.FacturaService;
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
@Path("/FacturaController")
public class FacturaController {
    
    @EJB
    FacturaService facturaService;
    
    @GET
    @Path("/factura/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFactura(@PathParam("id") Long id)
    {
        try
        {
            Respuesta res = facturaService.getFactura(id);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((FacturaDto) res.getResultado("Factura")).build();
        }
        catch(Exception ex)
        {
            Logger.getLogger(FacturaController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener la factura ").build();
        }
    }
    
    @GET
    @Path("/facturas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFacturas()
    {
        try
        {
            Respuesta res = facturaService.getFacturas();
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }

          return Response.ok(new GenericEntity<List<FacturaDto>>((List<FacturaDto>) res.getResultado("FacturasList")) {
            }).build();

        }
        catch(Exception ex)
        {
            Logger.getLogger(FacturaController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el factura ").build();
        }
    }
    
    //Falta probar desde el cliente si funciona
    @POST
    @Path("/factura/{factura}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarFactura(FacturaDto factura)
    {
        try
        {
            Respuesta res = facturaService.guardarFactura(factura);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((FacturaDto) res.getResultado("Factura")).build();
        }
        catch(Exception ex)
        {
            Logger.getLogger(FacturaController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener la factura ").build();
        }
    }

    @DELETE
    @Path("/factura/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarFactura(@PathParam("id") Long id)
    {
        try
        {
            Respuesta res = facturaService.eliminarFactura(id);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();
        }
        catch(Exception ex)
        {
            Logger.getLogger(FacturaController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener la factura ").build();
        }
    }
}
