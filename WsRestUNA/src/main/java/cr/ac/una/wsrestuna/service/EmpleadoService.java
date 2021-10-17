/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.service;

import cr.ac.una.wsrestuna.dto.EmpleadoDto;
import cr.ac.una.wsrestuna.model.Empleado;
import cr.ac.una.wsrestuna.util.CodigoRespuesta;
import cr.ac.una.wsrestuna.util.Respuesta;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Empleado", new EmpleadoDto((Empleado) qryEmpleado.getSingleResult()));

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
            Query qryEmpleado = em.createNamedQuery("Empleado.findAll",Empleado.class);

            List<Empleado> empleados = (List<Empleado>) qryEmpleado.getResultList();
            List<EmpleadoDto> EmpleadoDto = new ArrayList<>();
            empleados.forEach(empleado
                    -> {
                EmpleadoDto.add(new EmpleadoDto(empleado));
            });

            return new Respuesta(true,
                    CodigoRespuesta.CORRECTO,
                    "Empleados encontrados",
                    "Empleados encontrados correctamente",
                    "EmpleadosList", EmpleadoDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen empleados en la base de datos.", "getEmpleados NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar los empleados.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar los empleados.", "getEmpleados " + ex.getMessage());
        }
    }

}
