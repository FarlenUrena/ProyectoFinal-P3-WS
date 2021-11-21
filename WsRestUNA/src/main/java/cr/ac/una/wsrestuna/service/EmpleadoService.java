/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.service;

import cr.ac.una.wsrestuna.dto.CajaDto;
import cr.ac.una.wsrestuna.dto.EmpleadoDto;
import cr.ac.una.wsrestuna.dto.OrdenDto;
import cr.ac.una.wsrestuna.dto.ReporteDto;
import cr.ac.una.wsrestuna.model.Caja;
import cr.ac.una.wsrestuna.model.Empleado;
import cr.ac.una.wsrestuna.model.Orden;
import cr.ac.una.wsrestuna.util.CodigoRespuesta;
import cr.ac.una.wsrestuna.util.Reporte;
import cr.ac.una.wsrestuna.util.Respuesta;
import cr.ac.una.wsrestuna.wsrestuna;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Farlen
 */
@LocalBean
@Stateless
public class EmpleadoService {

    private static final Logger LOG = Logger.getLogger(EmpleadoService.class.getName());

    @PersistenceContext(unitName = "WsRestUnaPU")
    private EntityManager em;

//    Validar inicio de sesion
    public Respuesta validarUsuario(String usuario, String clave) {
        try {
            Query qryEmpleado = em.createNamedQuery("Empleado.findByUsuClave", Empleado.class);
            qryEmpleado.setParameter("nombreUsuario", usuario);
            qryEmpleado.setParameter("password", clave);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Empleado", new EmpleadoDto((Empleado) qryEmpleado.getSingleResult()));
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un empleado con el código ingresado.", "getEmpleado NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el empleado.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el empleado.", "getEmpleado NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el empleado.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el empleado.", "getEmpleado " + ex.getMessage());
        }
    }

//    Obetener un empleado
    public Respuesta getEmpleado(Long id) {
        try {
            Query qryEmpleado = em.createNamedQuery("Empleado.findByIdEmpleado", Empleado.class);
            qryEmpleado.setParameter("idEmpleado", id);
            Empleado empleado = (Empleado) qryEmpleado.getSingleResult();
            EmpleadoDto empleadoDto = new EmpleadoDto(empleado);
            for (Orden orden : empleado.getOrdenList()) {
                empleadoDto.getOrdenesDto().add(new OrdenDto(orden));
            }
            for (Caja caja : empleado.getCajaList()) {
                empleadoDto.getCajasDto().add(new CajaDto(caja));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Empleado", empleadoDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un empleado con el id ingresado.", "getEmpleado NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el empleado.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el empleado.", "getEmpleado NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el empleado.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el empleado.", "getEmpleado " + ex.getMessage());
        }
    }

//    Guardar o actualizar un empleado
    public Respuesta guardarEmpleado(EmpleadoDto empleadoDto) {
        try {
            Empleado empleado;
            if (empleadoDto.getIdEmpleado() != null && empleadoDto.getIdEmpleado() > 0) {
                empleado = em.find(Empleado.class, empleadoDto.getIdEmpleado());
                if (empleado == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el empleado a modificar.", "guardarEmpleado NoResultException");
                }
                empleado.actualizarEmpleado(empleadoDto);
                //ORDENES
                for (OrdenDto orden : empleadoDto.getOrdenesElimindasDto()) {
                    empleado.getOrdenList().remove(new Orden(orden.getIdOrden()));
                }
                if (!empleadoDto.getOrdenesDto().isEmpty()) {
                    for (OrdenDto ord : empleadoDto.getOrdenesDto()) {
                        if (ord.getModificado()) {
                            Orden orden = em.find(Orden.class, ord.getIdOrden());
                            orden.setIdEmpleado(empleado);
                            empleado.getOrdenList().add(orden);
                        }
                    }
                }
                //CAJAS
                for (CajaDto caj : empleadoDto.getCajasEliminadasDto()) {
                    empleado.getCajaList().remove(new Caja(caj.getIdCaja()));
                }
                if (!empleadoDto.getCajasDto().isEmpty()) {
                    for (CajaDto caj : empleadoDto.getCajasDto()) {
                        if (caj.getModificado()) {
                            Caja caja = em.find(Caja.class, caj.getIdCaja());
                            caja.setIdEmpleado(empleado);
                            empleado.getCajaList().add(caja);
                        }
                    }
                }
                empleado = em.merge(empleado);
            } else {
                empleado = new Empleado(empleadoDto);
                em.persist(empleado);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Empleado", new EmpleadoDto(empleado));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el empleado.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el empleado.", "guardarEmpleado " + ex.getMessage());
        }
    }

//    Eliminar un empleado permanentemente
    public Respuesta eliminarEmpleado(Long id) {
        try {
            Empleado empleado;
            if (id != null && id > 0) {
                empleado = em.find(Empleado.class, id);
                if (empleado == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el empleado a eliminar.", "eliminarEmpleado NoResultException");
                }
                em.remove(empleado);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el empleado a eliminar.", "eliminarEmpleado NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el empleado porque tiene relaciones con otros registros.", "eliminarEmpleado " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el empleado.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el empleado.", "eliminarEmpleado " + ex.getMessage());
        }
    }

//    Obtener un listado de todos los empleados
    public Respuesta getEmpleados() {
        try {
            Query qryEmpleado = em.createNamedQuery("Empleado.findAll", Empleado.class);

            List<Empleado> empleados = (List<Empleado>) qryEmpleado.getResultList();
            List<EmpleadoDto> empleadosDto = new ArrayList<>();
            empleados.forEach(empleado -> {
                EmpleadoDto empleadoDto = new EmpleadoDto(empleado);
                for (Orden orden : empleado.getOrdenList()) {
                    empleadoDto.getOrdenesDto().add(new OrdenDto(orden));
                }
                for (Caja caja : empleado.getCajaList()) {
                    empleadoDto.getCajasDto().add(new CajaDto(caja));
                }
                empleadosDto.add(empleadoDto);
            });

            return new Respuesta(true,
                    CodigoRespuesta.CORRECTO,
                    "Empleados encontrados",
                    "Empleados encontrados correctamente",
                    "EmpleadosList", empleadosDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen empleados en la base de datos.", "getEmpleados NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar los empleados.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar los empleados.", "getEmpleados " + ex.getMessage());
        }
    }

        public Respuesta generarReporteJasper(ReporteDto reporteDto, Map<String,Object> datos){
    try {        
        int tpReporte = 0;
        tpReporte = (int) datos.get("tipo");
        System.out.println(tpReporte);
        switch(tpReporte){// tipos de reportes disponibles.
            case 1:
                datos.put("url", "FacturaFechas");
                break;
            case 2:
                datos.put("url", "CajasByEmpleadoAndDay");
                break;
            case 3:
                datos.put("url", "CantidadVendidaProductos");
                break;
            case 4:
                datos.put("url", "Factura");
                datos.put("subReport","cr/ac/una/wsrestuna/Reports/FacturaSBR.jasper");
//                datos.put("subReport", "C:\\Users\\User\\JaspersoftWorkspace\\RESTUNAPROJECT\\FacturaSBR.jasper");
                
                break;
        }
        Reporte reporte = new Reporte();
        reporteDto = reporte.generarReportes(reporteDto,(HashMap<String, Object>) datos);
        
        return new Respuesta(true,
                    CodigoRespuesta.CORRECTO,
                    "Reporte generado",
                    "Reporte generado correctamente",
                    "Reporte", reporteDto);
        
        } catch 
                (FileNotFoundException | SQLException | JRException | NamingException ex) {
            Logger.getLogger(EmpleadoService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
