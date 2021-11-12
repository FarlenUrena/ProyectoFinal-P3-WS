/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.service;

import cr.ac.una.wsrestuna.dto.ProductoporordenDto;
import cr.ac.una.wsrestuna.model.Orden;
import cr.ac.una.wsrestuna.model.Producto;
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
 * @author Farlen
 */
@LocalBean
@Stateless
public class ProductoporordenService {

    private static final Logger LOG = Logger.getLogger(ProductoporordenService.class.getName());
    @PersistenceContext(unitName = "WsRestUnaPU")
    private EntityManager em;

    //    Obetener un productopororden
    public Respuesta getProductopororden(Long id) {
        try {
            Query qryProductopororden = em.createNamedQuery("Productopororden.findByIdProductoPorOrden", Productopororden.class);
            qryProductopororden.setParameter("idProductoPorOrden", id);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Productopororden", new ProductoporordenDto((Productopororden) qryProductopororden.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un producto por orden con el id ingresado.", "getProductopororden NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el producto por orden.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el producto por orden.", "getProductopororden NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el producto por orden.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el producto por orden.", "getProductopororden " + ex.getMessage());
        }
    }

    
    public Respuesta guardarProductopororden(ProductoporordenDto productoporordenDto) {
        try {
            Productopororden productopororden;
            if (productoporordenDto.getIdProductoPorOrden() != null && productoporordenDto.getIdProductoPorOrden() > 0) {
                productopororden = em.find(Productopororden.class, productoporordenDto.getIdProductoPorOrden());
                if (productopororden == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO,"No se encrontró el producto por orden a modificar.", "guardarProductopororden NoResultException");
                }
                productopororden.actualizarProductopororden(productoporordenDto);
                productopororden.setIdOrden(new Orden(productoporordenDto.getIdOrdenDto()));
                productopororden.setIdProducto(new Producto(productoporordenDto.getIdProductoDto()));

                productopororden = em.merge(productopororden);
            } else {
                productopororden = new Productopororden(productoporordenDto);
                em.persist(productopororden);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Productopororden", new ProductoporordenDto(productopororden));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el productopororden.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el producto por orden.", "guardarProductopororden " + ex.getMessage());
        }
    }

//    Eliminar un productopororden permanentemente
    public Respuesta eliminarProductopororden(Long id) {
        try {
            Productopororden productopororden;
            if (id != null && id > 0) {
                productopororden = em.find(Productopororden.class, id);
                if (productopororden == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el producto por orden a eliminar.", "eliminarProductopororden NoResultException");
                }
                em.remove(productopororden);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el producto por orden a eliminar.", "eliminarProductopororden NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el producto por orden porque tiene relaciones con otros registros.", "eliminarProductopororden " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el productopororden.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el producto por orden.", "eliminarProductopororden " + ex.getMessage());
        }
    }

//    Obtener un listado de todos los productoporordens
    public Respuesta getProductoporordens() {
        try {
            Query qryProductopororden = em.createNamedQuery("Productopororden.findAll", Productopororden.class);

            List<Productopororden> productoporordens = (List<Productopororden>) qryProductopororden.getResultList();
            List<ProductoporordenDto> ProductoporordenDto = new ArrayList<>();

            productoporordens.forEach(productopororden -> {
                ProductoporordenDto.add(new ProductoporordenDto(productopororden));
            });

            return new Respuesta(true,
                    CodigoRespuesta.CORRECTO,
                    "elementos de seccion encontrados",
                    "elementos de seccion encontrados correctamente",
                    "ProductosporordenList", ProductoporordenDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen elementos de seccion en la base de datos.", "getProductoporordens NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar los elementos de seccion.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar los elementos de seccion.", "getProductoporordens " + ex.getMessage());
        }
    }

}
