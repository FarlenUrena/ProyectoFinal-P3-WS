/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.service;

import cr.ac.una.wsrestuna.dto.OrdenDto;
import cr.ac.una.wsrestuna.dto.ProductoporordenDto;
import cr.ac.una.wsrestuna.model.Orden;
import cr.ac.una.wsrestuna.model.Productopororden;
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
public class OrdenService {

    private static final Logger LOG = Logger.getLogger(OrdenService.class.getName());

    @PersistenceContext(unitName = "WsRestUnaPU")
    private EntityManager em;

    //    Obetener una orden
    public Respuesta getOrden(Long id) {
        try {
            Query qryOrden = em.createNamedQuery("Orden.findByIdOrden", Orden.class);
            qryOrden.setParameter("idOrden", id);
            Orden orden = (Orden) qryOrden.getSingleResult();
            
            OrdenDto ordenDto = new OrdenDto(orden);
            
            
            for (Productopororden p : orden.getProductoporordenList()) {
                ordenDto.getProductosporordenDto().add(new ProductoporordenDto(p));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Orden", ordenDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe una orden con el id ingresado.", "getOrden NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la orden.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la orden.", "getOrden NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el orden.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la orden.", "getOrden " + ex.getMessage());
        }
    }

//    Guardar o actualizar una orden
    public Respuesta guardarOrden(OrdenDto ordenDto) {
        try {
            Orden orden;
            if (ordenDto.getIdOrden() != null && ordenDto.getIdOrden() > 0) {
                orden = em.find(Orden.class, ordenDto.getIdOrden());
                if (orden == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la orden a modificar.", "guardarOrden NoResultException");
                }
                orden.actualizarOrden(ordenDto);
                
                orden = em.merge(orden);
            } else {
                orden = new Orden(ordenDto);
                em.persist(orden);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Orden", new OrdenDto(orden));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la orden.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar la orden.", "guardarOrden " + ex.getMessage());
        }
    }

//    Eliminar una orden permanentemente
    public Respuesta eliminarOrden(Long id) {
        try {
            Orden orden;
            if (id != null && id > 0) {
                orden = em.find(Orden.class, id);
                if (orden == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la orden a eliminar.", "eliminarOrden NoResultException");
                }
                em.remove(orden);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar la orden a eliminar.", "eliminarOrden NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar la orden porque tiene relaciones con otros registros.", "eliminarOrden " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la orden.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar la orden.", "eliminarOrden " + ex.getMessage());
        }
    }

//    Obtener un listado de todas las ordenes
    public Respuesta getOrdenes() {
        try {
            Query qryOrden = em.createNamedQuery("Orden.findAll", Orden.class);

            List<Orden> ordenes = (List<Orden>) qryOrden.getResultList();
            List<OrdenDto> ordenesDto = new ArrayList<>();
            ordenes.forEach(orden
                    -> {
                OrdenDto ordenDto = new OrdenDto(orden);
                
                for (Productopororden p : orden.getProductoporordenList()) {
                    ordenDto.getProductosporordenDto().add(new ProductoporordenDto(p));
                }

                ordenesDto.add(ordenDto);
            });

            return new Respuesta(true,
                    CodigoRespuesta.CORRECTO,
                    "Ordenes encontradas",
                    "Ordenes encontradas correctamente",
                    "OrdenesList", ordenesDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen ordenes en la base de datos.", "getOrdenes NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar las ordenes.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar las ordenes.", "getOrdenes " + ex.getMessage());
        }
    }
}
