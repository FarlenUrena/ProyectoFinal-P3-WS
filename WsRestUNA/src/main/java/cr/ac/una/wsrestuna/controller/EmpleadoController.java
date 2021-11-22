/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.controller;

import cr.ac.una.wsrestuna.dto.EmpleadoDto;
import cr.ac.una.wsrestuna.dto.ReporteDto;
import cr.ac.una.wsrestuna.service.EmpleadoService;
import cr.ac.una.wsrestuna.util.CodigoRespuesta;
import cr.ac.una.wsrestuna.util.JwTokenHelper;
import cr.ac.una.wsrestuna.util.Respuesta;
import cr.ac.una.wsrestuna.util.Secure;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Farlen
 */
@Secure
@Path("/EmpleadoController")
public class EmpleadoController {

    @EJB
    EmpleadoService empleadoService;

    @Context
    SecurityContext securityContext;

    @GET
    @Path("/usuario/{usuario}/{clave}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuario(@PathParam("usuario") String usuario, @PathParam("clave") String clave) {
        try {
            Respuesta res = empleadoService.validarUsuario(usuario, clave);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            EmpleadoDto empleadoDto = (EmpleadoDto) res.getResultado("Empleado");
            empleadoDto.setToken(JwTokenHelper.getInstance().generatePrivateKey(usuario));

            return Response.ok(empleadoDto).build();
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al validar el usuario ").build();
        }
    }

    @GET
    @Path("/empleado/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmpleado(@PathParam("id") Long id) {
        try {
            Respuesta res = empleadoService.getEmpleado(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((EmpleadoDto) res.getResultado("Empleado")).build();
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el empleado ").build();
        }
    }

    @GET
    @Path("/empleados")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmpleados() {
        try {
            Respuesta res = empleadoService.getEmpleados();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }

            return Response.ok(new GenericEntity<List<EmpleadoDto>>((List<EmpleadoDto>) res.getResultado("EmpleadosList")) {
            }).build();

        } catch (Exception ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el empleado ").build();
        }
    }

    //Falta probar desde el cliente si funciona
    @POST
    @Path("/empleado")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarEmpleado(EmpleadoDto empleado) {
        try {
            Respuesta res = empleadoService.guardarEmpleado(empleado);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((EmpleadoDto) res.getResultado("Empleado")).build();
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el empleado ").build();
        }
    }

    @DELETE
    @Path("/empleado/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarEmpleado(@PathParam("id") Long id) {
        try {
            Respuesta res = empleadoService.eliminarEmpleado(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error al obtener el empleado ").build();
        }
    }

    @GET
    @Path("/renovar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response renovarToken() {
        try {
            String usuarioRequest = securityContext.getUserPrincipal().getName();
            if (usuarioRequest != null && !usuarioRequest.isBlank()) {
                return Response.ok(JwTokenHelper.getInstance().generatePrivateKey(usuarioRequest)).build();
            } else {
                return Response.status(CodigoRespuesta.ERROR_PERMISOS.getValue()).entity("Error renovando el token.").build();
            }

        } catch (Exception ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error renovando el token.").build();

        }
    }

    @POST
    @Path("/reporte")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReporte(ReporteDto reporte) {
        try {
            Map<String,Object> datos = new HashMap<>();
            
        datos.put("tipo",reporte.getTipo());
        datos.put("NombreEmpresa",reporte.getNombreEmpresa());
        datos.put("DateInicio",reporte.getDateInicio());
        datos.put("DateFinal",reporte.getDateFinal());
        datos.put("FechaCierreCaja",reporte.getFechaCierreCaja());
        datos.put("IdEmpleado",reporte.getIdEmpleado());
        datos.put("FacturaId",reporte.getIdFactura());
        datos.put("telefono", reporte.getTelefono());
        datos.put("CajaId", reporte.getIdCaja());
            
            Respuesta res = empleadoService.generarReporteJasper(reporte,datos);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((ReporteDto) res.getResultado("Reporte")).build();

        }catch (Exception ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error generando el reporte.").build();

        }

    }

}
