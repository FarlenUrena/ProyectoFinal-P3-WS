/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.service;

import cr.ac.una.wsrestuna.dto.GrupoDto;
import cr.ac.una.wsrestuna.dto.ProductoDto;
import cr.ac.una.wsrestuna.model.Producto;
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
public class ProductoService {
    
    private static final Logger LOG = Logger.getLogger(ProductoService.class.getName());

    @PersistenceContext(unitName = "WsRestUnaPU")
    private EntityManager em;
    
    //    Obetener un producto
    public Respuesta getProducto(Long id) {
        try {
            Query qryProducto = em.createNamedQuery("Producto.findByIdProducto", Producto.class);
            qryProducto.setParameter("idProducto", id);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Producto", new ProductoDto((Producto) qryProducto.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un producto con el id ingresado.", "getProducto NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el producto.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el producto.", "getProducto NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el producto.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el producto.", "getProducto " + ex.getMessage());
        }
    }

//    Guardar o actualizar un producto
    public Respuesta guardarProducto(ProductoDto productoDto) {
        try {
            Producto producto;
            if (productoDto.getIdProducto() != null && productoDto.getIdProducto() > 0) {
                producto = em.find(Producto.class, productoDto.getIdProducto());
                if (producto == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el producto a modificar.", "guardarProducto NoResultException");
                }
                producto.actualizarProducto(productoDto);
                producto = em.merge(producto);
            } else {
                producto = new Producto(productoDto);
                em.persist(producto);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Producto", new ProductoDto(producto));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el producto.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el producto.", "guardarProducto " + ex.getMessage());
        }
    }

//    Eliminar un producto permanentemente
    public Respuesta eliminarProducto(Long id) {
        try {
            Producto producto;
            if (id != null && id > 0) {
                producto = em.find(Producto.class, id);
                if (producto == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el producto a eliminar.", "eliminarProducto NoResultException");
                }
                em.remove(producto);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el producto a eliminar.", "eliminarProducto NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el producto porque tiene relaciones con otros registros.", "eliminarProducto " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el producto.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el producto.", "eliminarProducto " + ex.getMessage());
        }
    }

//    Obtener un listado de todos los productos
    public Respuesta getProductos() {
        try {
            Query qryProducto = em.createNamedQuery("Producto.findAll",Producto.class);

            List<Producto> productos = (List<Producto>) qryProducto.getResultList();
            List<ProductoDto> ProductoDto = new ArrayList<>();
            productos.forEach(producto
                    -> {
                ProductoDto.add(new ProductoDto(producto));
            });

            return new Respuesta(true,
                    CodigoRespuesta.CORRECTO,
                    "Productos encontrados",
                    "Productos encontrados correctamente",
                    "ProductosList", ProductoDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen productos en la base de datos.", "getProductos NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar los productos.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar los productos.", "getProductos " + ex.getMessage());
        }
    }
        public Respuesta getProductosPorGrupo(GrupoDto grupoDto) {
        try {
//            Query qryProducto = em.createNamedQuery("Producto.findAll",Producto.class);

            List<Producto> productos = (List<Producto>) grupoDto.getProductoList();
           
            List<ProductoDto> productosDto = new ArrayList<>();
            productos.forEach(producto
                    -> {
                productosDto.add(new ProductoDto(producto));
            });

            return new Respuesta(true,
                    CodigoRespuesta.CORRECTO,
                    "Productos encontrados",
                    "Productos encontrados correctamente",
                    "ProductosList", productosDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen productos en la base de datos.", "getProductos NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar los productos.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar los productos.", "getProductos " + ex.getMessage());
        }
    }
}
