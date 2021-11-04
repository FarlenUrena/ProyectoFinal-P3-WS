/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.service;

import cr.ac.una.wsrestuna.dto.ElementodeseccionDto;
import cr.ac.una.wsrestuna.model.Elementodeseccion;
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

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Elementodeseccion", new ElementodeseccionDto((Elementodeseccion) qryElementodeseccion.getSingleResult()));

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
            if (elementodeseccionDto.getIdElemento()!= null && elementodeseccionDto.getIdElemento() > 0) {
                elementodeseccion = em.find(Elementodeseccion.class, elementodeseccionDto.getIdElemento());
                if (elementodeseccion == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el elemento de seccion a modificar.", "guardarElementodeseccion NoResultException");
                }
                if(elementodeseccion.getIdSeccion().getIdSeccion() !=elementodeseccionDto.getIdSeccion().getIdSeccion()){
                    elementodeseccion.setIdSeccion(new Seccion(elementodeseccionDto.getIdSeccion()));
                }
                elementodeseccion.actualizarElementodeseccion(elementodeseccionDto);
                elementodeseccion = em.merge(elementodeseccion);
            } else {
                elementodeseccion = new Elementodeseccion(elementodeseccionDto);
                elementodeseccion.setIdSeccion(new Seccion(elementodeseccionDto.getIdSeccion()));
                em.persist(elementodeseccion);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Elementodeseccion", new ElementodeseccionDto(elementodeseccion));
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
            Query qryElementodeseccion = em.createNamedQuery("Elementodeseccion.findAll",Elementodeseccion.class);

            List<Elementodeseccion> elementodeseccions = (List<Elementodeseccion>) qryElementodeseccion.getResultList();
            List<ElementodeseccionDto> ElementodeseccionDto = new ArrayList<>();
            elementodeseccions.forEach(elementodeseccion
                    -> {
                ElementodeseccionDto.add(new ElementodeseccionDto(elementodeseccion));
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
