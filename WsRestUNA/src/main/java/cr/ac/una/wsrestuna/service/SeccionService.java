/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.service;

import cr.ac.una.wsrestuna.dto.ElementodeseccionDto;
import cr.ac.una.wsrestuna.dto.EmpleadoDto;
import cr.ac.una.wsrestuna.dto.SeccionDto;
import cr.ac.una.wsrestuna.model.Elementodeseccion;
import cr.ac.una.wsrestuna.model.Empleado;
import cr.ac.una.wsrestuna.model.Seccion;
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
public class SeccionService {

    private static final Logger LOG = Logger.getLogger(SeccionService.class.getName());

    @PersistenceContext(unitName = "WsRestUnaPU")
    private EntityManager em;

    //    Obetener una seccion
    public Respuesta getSeccion(Long id) {
        try {
            Query qrySeccion = em.createNamedQuery("Seccion.findByIdSeccion", Seccion.class);
            qrySeccion.setParameter("idSeccion", id);
            Seccion seccion = (Seccion) qrySeccion.getSingleResult();
            SeccionDto seccionDto = new SeccionDto(seccion);

            for (Elementodeseccion ele : seccion.getElementodeseccionList()) {
                seccionDto.getElementosdeseccionDto().add(new ElementodeseccionDto(ele));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Seccion", seccionDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe una seccion con el id ingresado.", "getSeccion NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la seccion.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la seccion.", "getSeccion NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el seccion.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la seccion.", "getSeccion " + ex.getMessage());
        }
    }

//    Guardar o actualizar una seccion
    public Respuesta guardarSeccion(SeccionDto seccionDto) {
        try {
            Seccion seccion;
            if (seccionDto.getIdSeccion() != null && seccionDto.getIdSeccion() > 0) {
                seccion = em.find(Seccion.class, seccionDto.getIdSeccion());
                if (seccion == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la seccion a modificar.", "guardarSeccion NoResultException");
                }
                seccion.actualizarSeccion(seccionDto);
                //elementos de seccion
//                for (ElementodeseccionDto eleDto : seccionDto.getElementosdeseccionEliminadosDto()) {
//                    seccion.getElementodeseccionList().remove(new Elementodeseccion(eleDto.getIdElemento()));
//                }
//                if (!seccionDto.getElementosdeseccionDto().isEmpty()) {
//                    for (ElementodeseccionDto eleDto : seccionDto.getElementosdeseccionDto()) {
//                        if (eleDto.getModificado()) {
//                            Elementodeseccion eleE = em.find(Elementodeseccion.class, eleDto.getIdElemento());
//                            eleE.setIdSeccion(seccion);
//                            seccion.getElementodeseccionList().add(eleE);
//                        }
//                    }
//                }
                seccion = em.merge(seccion);
            } else {
                seccion = new Seccion(seccionDto);
                em.persist(seccion);
            }
            em.flush();

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Seccion", new SeccionDto(seccion));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la seccion.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar la seccion.", "guardarSeccion " + ex.getMessage());
        }
    }

//    Eliminar una seccion permanentemente
    public Respuesta eliminarSeccion(Long id) {
        try {
            Seccion seccion;
            if (id != null && id > 0) {
                seccion = em.find(Seccion.class, id);
                if (seccion == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la seccion a eliminar.", "eliminarSeccion NoResultException");
                }
                em.remove(seccion);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar la seccion a eliminar.", "eliminarSeccion NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar la seccion porque tiene relaciones con otros registros.", "eliminarSeccion " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la seccion.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar la seccion.", "eliminarSeccion " + ex.getMessage());
        }
    }

//    Obtener un listado de todas las secciones
    public Respuesta getSecciones() {
        try {
            Query qrySeccion = em.createNamedQuery("Seccion.findAll", Seccion.class);

            List<Seccion> secciones = (List<Seccion>) qrySeccion.getResultList();
            List<SeccionDto> SeccionesDto = new ArrayList<>();

            secciones.forEach(seccion -> {
                SeccionDto seccionDto = new SeccionDto(seccion);

                for (Elementodeseccion ele : seccion.getElementodeseccionList()) {
                    seccionDto.getElementosdeseccionDto().add(new ElementodeseccionDto(ele));
                }
                SeccionesDto.add(seccionDto);
            });

            return new Respuesta(true,
                    CodigoRespuesta.CORRECTO,
                    "Secciones encontradas",
                    "Secciones encontradas correctamente",
                    "SeccionesList", SeccionesDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen secciones en la base de datos.", "getSecciones NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar las secciones.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar las secciones.", "getSecciones " + ex.getMessage());
        }
    }

}
