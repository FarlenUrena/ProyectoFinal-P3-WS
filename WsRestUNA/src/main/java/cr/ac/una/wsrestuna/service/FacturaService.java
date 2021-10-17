/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.service;

import cr.ac.una.wsrestuna.dto.FacturaDto;
import cr.ac.una.wsrestuna.model.Factura;
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
public class FacturaService {
    private static final Logger LOG = Logger.getLogger(FacturaService.class.getName());

    @PersistenceContext(unitName = "WsRestUnaPU")
    private EntityManager em;
    
    //    Obetener una factura
    public Respuesta getFactura(Long id) {
        try {
            Query qryFactura = em.createNamedQuery("Factura.findByIdFactura", Factura.class);
            qryFactura.setParameter("idFactura", id);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Factura", new FacturaDto((Factura) qryFactura.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe una factura con el id ingresado.", "getFactura NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la factura.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la factura.", "getFactura NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el factura.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la factura.", "getFactura " + ex.getMessage());
        }
    }

//    Guardar o actualizar una factura
    public Respuesta guardarFactura(FacturaDto facturaDto) {
        try {
            Factura factura;
            if (facturaDto.getIdFactura() != null && facturaDto.getIdFactura() > 0) {
                factura = em.find(Factura.class, facturaDto.getIdFactura());
                if (factura == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la factura a modificar.", "guardarFactura NoResultException");
                }
                factura.actualizarFactura(facturaDto);
                factura = em.merge(factura);
            } else {
                factura = new Factura(facturaDto);
                em.persist(factura);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Factura", new FacturaDto(factura));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la factura.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar la factura.", "guardarFactura " + ex.getMessage());
        }
    }

//    Eliminar una factura permanentemente
    public Respuesta eliminarFactura(Long id) {
        try {
            Factura factura;
            if (id != null && id > 0) {
                factura = em.find(Factura.class, id);
                if (factura == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la factura a eliminar.", "eliminarFactura NoResultException");
                }
                em.remove(factura);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar la factura a eliminar.", "eliminarFactura NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar la factura porque tiene relaciones con otros registros.", "eliminarFactura " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la factura.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar la factura.", "eliminarFactura " + ex.getMessage());
        }
    }

//    Obtener un listado de todas las facturas
    public Respuesta getFacturas() {
        try {
            Query qryFactura = em.createNamedQuery("Factura.findAll",Factura.class);

            List<Factura> facturas = (List<Factura>) qryFactura.getResultList();
            List<FacturaDto> FacturaDto = new ArrayList<>();
            facturas.forEach(factura
                    -> {
                FacturaDto.add(new FacturaDto(factura));
            });

            return new Respuesta(true,
                    CodigoRespuesta.CORRECTO,
                    "Facturas encontradas",
                    "Facturas encontradas correctamente",
                    "FacturasList", FacturaDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen facturas en la base de datos.", "getFacturas NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar las facturas.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar las facturas.", "getFacturas " + ex.getMessage());
        }
    }
    
}
