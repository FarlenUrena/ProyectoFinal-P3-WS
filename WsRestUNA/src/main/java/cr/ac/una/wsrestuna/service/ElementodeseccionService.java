/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.service;

import cr.ac.una.wsrestuna.dto.ElementodeseccionDto;
import cr.ac.una.wsrestuna.dto.OrdenDto;
import cr.ac.una.wsrestuna.dto.SeccionDto;
import cr.ac.una.wsrestuna.model.Elementodeseccion;
import cr.ac.una.wsrestuna.model.Orden;
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
public class ElementodeseccionService {

    private static final Logger LOG = Logger.getLogger(ElementodeseccionService.class.getName());
    @PersistenceContext(unitName = "WsRestUnaPU")
    private EntityManager em;

    //    Obetener un elementodeseccion
    public Respuesta getElementodeseccion(Long id) {
        try {
            Query qryElementodeseccion = em.createNamedQuery("Elementodeseccion.findByIdElemento", Elementodeseccion.class);
            qryElementodeseccion.setParameter("idElemento", id);
            Elementodeseccion elementodeseccion = (Elementodeseccion) qryElementodeseccion.getSingleResult();
            ElementodeseccionDto elementodeseccionDto = new ElementodeseccionDto(elementodeseccion);

            elementodeseccionDto.setIdSeccionDto(new SeccionDto(elementodeseccion.getIdSeccion()));
            for (Orden ordenE : elementodeseccion.getOrdenList()) {
                elementodeseccionDto.getOrdenesDtoList().add(new OrdenDto(ordenE));
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Elementodeseccion", elementodeseccionDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un elemento de seccion con el id ingresado.", "getElementodeseccion NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el elemento de seccion.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el elemento de seccion.", "getElementodeseccion NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el elemento de seccion.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el elemento de seccion.", "getElementodeseccion " + ex.getMessage());
        }
    }

//    Guardar o actualizar un elementodeseccion
    public Respuesta guardarElementodeseccion(ElementodeseccionDto elementodeseccionDto) {
        try {
            Elementodeseccion elementodeseccion;
            if (elementodeseccionDto.getIdElemento() != null && elementodeseccionDto.getIdElemento() > 0) {
                elementodeseccion = em.find(Elementodeseccion.class, elementodeseccionDto.getIdElemento());
                if (elementodeseccion == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el elemento de seccion a modificar.", "guardarElementodeseccion NoResultException");
                }
                elementodeseccion.actualizarElementodeseccion(elementodeseccionDto);

//                for (OrdenDto ordenDto : elementodeseccionDto.getOrdenesEliminadasDtoList()) {
//                    elementodeseccion.getOrdenList().remove(new Orden(ordenDto.getIdOrden()));
//                }
//
//                if (!elementodeseccion.getOrdenList().isEmpty()) {
//                    for (OrdenDto ordenDto : elementodeseccionDto.getOrdenDtoList()) {
//                        if (ordenDto.getModificado()) {
//                            Orden ordenE = em.find(Orden.class, ordenDto.getIdOrden());
//                            ordenE.setIdElemento(elementodeseccion);
//                            elementodeseccion.getOrdenList().add(ordenE);
//                        }
//                    }
//                }
                elementodeseccion = em.merge(elementodeseccion);
            } else {
                elementodeseccion = new Elementodeseccion(elementodeseccionDto);
                em.persist(elementodeseccion);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Elementodeseccion", new ElementodeseccionDto(elementodeseccion));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el elementodeseccion.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el elemento de seccion.", "guardarElementodeseccion " + ex.getMessage());
        }
    }

    public Respuesta guardarElementosdeseccion(List<ElementodeseccionDto> elementosdeseccionDto) {
        List<ElementodeseccionDto> elementosSend = new ArrayList<>();
        try {
           
            for (ElementodeseccionDto elementodeseccionDto : elementosdeseccionDto) {
               Elementodeseccion elementodeseccion;
                if (elementodeseccionDto.getIdElemento() != null && elementodeseccionDto.getIdElemento() > 0) {
                    elementodeseccion = em.find(Elementodeseccion.class, elementodeseccionDto.getIdElemento());
                    if (elementodeseccion == null) {
                        return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el elemento de seccion a modificar.", "guardarElementodeseccion NoResultException");
                    }
                    elementodeseccion.actualizarElementodeseccion(elementodeseccionDto);

//                for (OrdenDto ordenDto : elementodeseccionDto.getOrdenesEliminadasDtoList()) {
//                    elementodeseccion.getOrdenList().remove(new Orden(ordenDto.getIdOrden()));
//                }
//
//                if (!elementodeseccion.getOrdenList().isEmpty()) {
//                    for (OrdenDto ordenDto : elementodeseccionDto.getOrdenDtoList()) {
//                        if (ordenDto.getModificado()) {
//                            Orden ordenE = em.find(Orden.class, ordenDto.getIdOrden());
//                            ordenE.setIdElemento(elementodeseccion);
//                            elementodeseccion.getOrdenList().add(ordenE);
//                        }
//                    }
//                }
                    elementodeseccion = em.merge(elementodeseccion);
                } else {
                    elementodeseccion = new Elementodeseccion(elementodeseccionDto);
//                    elementodeseccion.setIdSeccion(new Seccion(elementodeseccionDto.getIdSeccionDto()));
                    em.persist(elementodeseccion);
                }
                em.flush();
                elementosSend.add(new ElementodeseccionDto(elementodeseccion));
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "ElementosSend", elementosSend);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el elementodeseccion.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el elemento de seccion.", "guardarElementodeseccion " + ex.getMessage());
        }

    }

//    Eliminar un elementodeseccion permanentemente
    public Respuesta eliminarElementodeseccion(Long id) {
        try {
            Elementodeseccion elementodeseccion;
            if (id != null && id > 0) {
                elementodeseccion = em.find(Elementodeseccion.class, id);
                if (elementodeseccion == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el elemento de seccion a eliminar.", "eliminarElementodeseccion NoResultException");
                }
                em.remove(elementodeseccion);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el elemento de seccion a eliminar.", "eliminarElementodeseccion NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el elemento de seccion porque tiene relaciones con otros registros.", "eliminarElementodeseccion " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el elementodeseccion.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el elemento de seccion.", "eliminarElementodeseccion " + ex.getMessage());
        }
    }

//    Obtener un listado de todos los elementodeseccions
    public Respuesta getElementodeseccions() {
        try {
            Query qryElementodeseccion = em.createNamedQuery("Elementodeseccion.findAll", Elementodeseccion.class);
            List<Elementodeseccion> elementosdeseccion = (List<Elementodeseccion>) qryElementodeseccion.getResultList();
            List<ElementodeseccionDto> ElementodeseccionDto = new ArrayList<>();

            elementosdeseccion.forEach(elementodeseccion -> {
                ElementodeseccionDto elementodeseccionDto = new ElementodeseccionDto(elementodeseccion);
                for (Orden ordenE : elementodeseccion.getOrdenList()) {
                    elementodeseccionDto.getOrdenesDtoList().add(new OrdenDto(ordenE));
                }
                ElementodeseccionDto.add(elementodeseccionDto);
            });

            return new Respuesta(true,
                    CodigoRespuesta.CORRECTO,
                    "elementos de seccion encontrados",
                    "elementos de seccion encontrados correctamente",
                    "ElementosdeseccionList", ElementodeseccionDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen elementos de seccion en la base de datos.", "getElementodeseccions NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar los elementos de seccion.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar los elementos de seccion.", "getElementodeseccions " + ex.getMessage());
        }
    }
}
