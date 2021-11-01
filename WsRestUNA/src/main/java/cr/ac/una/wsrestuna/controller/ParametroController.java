/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.controller;

import cr.ac.una.wsrestuna.dto.ParametroDto;
import cr.ac.una.wsrestuna.service.ParametroService;
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

/*
 * @author Farlen
 */
@Secure
@Path("/ParametroController")
public class ParametroController {   
     @EJB
     ParametroService parametroService;
    
    @GET
    @Path("/parametro/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParametro(@PathParam("id") Long id)
    {
        try
        {
            Respuesta res = parametroService.getParametro(id);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
             System.out.println(res.getResultado("Parametro").toString());
            return Response.ok((ParametroDto) res.getResultado("Parametro")).build();
        }
        catch(Exception ex)
        {
            Logger.getLogger(ParametroController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el parametro ").build();
        }
    }
    
    @GET
    @Path("/parametros")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParametros()
    {
        try
        {
            Respuesta res = parametroService.getParametros();
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }

          return Response.ok(new GenericEntity<List<ParametroDto>>((List<ParametroDto>) res.getResultado("ParametrosList")) {
            }).build();

        }
        catch(Exception ex)
        {
            Logger.getLogger(ParametroController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el parametro ").build();
        }
    }
    
    //Falta probar desde el cliente si funciona
    @POST
    @Path("/parametro")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarParametro(ParametroDto parametroDto) {
        try {
            System.out.println(parametroDto.toString());
            Respuesta res = parametroService.guardarParametro(parametroDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
          
            return Response.ok((ParametroDto) res.getResultado("Parametro")).build();
        } catch (Exception ex) {
            Logger.getLogger(ParametroDto.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el parametro ").build();
        }
    }
    @DELETE
    @Path("/parametro/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarParametro(@PathParam("id") Long id)
    {
        try
        {
            Respuesta res = parametroService.eliminarParametro(id);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();
        }
        catch(Exception ex)
        {
            Logger.getLogger(ParametroController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el parametro ").build();
        }
    }
     
     
}
