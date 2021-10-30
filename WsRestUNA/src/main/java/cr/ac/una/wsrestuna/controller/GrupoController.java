/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.controller;

import cr.ac.una.wsrestuna.dto.GrupoDto;
import cr.ac.una.wsrestuna.dto.ProductoDto;
import cr.ac.una.wsrestuna.model.Grupo;
import cr.ac.una.wsrestuna.service.CajaService;
import cr.ac.una.wsrestuna.service.GrupoService;
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
 * @author jeez
 */
@Secure
@Path("/GrupoController")
public class GrupoController {

    @EJB
    GrupoService grupoService;

    @GET
    @Path("/getGrupoId/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGrupoId(@PathParam("id") Long id) {
        try {
            Respuesta res = grupoService.getGrupo(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((GrupoDto) res.getResultado("Grupo")).build();
        } catch (Exception ex) {
            Logger.getLogger(CajaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el grupo").build();
        }
    }

    @GET
    @Path("/getGrupoNombre/{nombre}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGrupoNombre(@PathParam("nombre") String nombre) {
        try {
            Respuesta res = grupoService.getGrupo(nombre);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((GrupoDto) res.getResultado("Grupo")).build();
        } catch (Exception ex) {
            Logger.getLogger(CajaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el grupo").build();
        }
    }

    @GET
    @Path("/getGrupos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGrupos() {
        try {
            Respuesta res = grupoService.getGrupos();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
              return Response.ok(new GenericEntity<List<GrupoDto>>((List<GrupoDto>) res.getResultado("GruposList")) {
            }).build();
            
          
            
            
        } catch (Exception ex) {
            Logger.getLogger(CajaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener los Grupos").build();
        }
    }

    @POST
    @Path("/grupo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarGrupo(GrupoDto grupoDto) {
        try {
            Respuesta res = grupoService.guardarGrupo(grupoDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((GrupoDto) res.getResultado("Grupo")).build();
        } catch (Exception ex) {
            Logger.getLogger(CajaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al guardar el grupo").build();
        }
    }

    @DELETE
    @Path("/grupo/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarGrupo(@PathParam("id") Long id)
    {
        try
        {
            Respuesta res = grupoService.eliminarGrupo(id);
            if(!res.getEstado())
            {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();
        }
        catch(Exception ex)
        {
            Logger.getLogger(GrupoController.class.getName()).log(Level.SEVERE , null , ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el empleado ").build();
        }
    }    
    
}
