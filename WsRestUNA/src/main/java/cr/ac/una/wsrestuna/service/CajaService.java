/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.service;

import cr.ac.una.wsrestuna.dto.CajaDto;
import cr.ac.una.wsrestuna.dto.EmpleadoDto;
import cr.ac.una.wsrestuna.dto.FacturaDto;
import cr.ac.una.wsrestuna.model.Caja;
import cr.ac.una.wsrestuna.model.Empleado;
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
public class CajaService {

    private static final Logger LOG = Logger.getLogger(CajaService.class.getName());

    @PersistenceContext(unitName = "WsRestUnaPU")
    private EntityManager em;

//    Obetener una caja
    public Respuesta getCaja(Long id) {
        try {
            Query qryCaja = em.createNamedQuery("Caja.findByIdCaja", Caja.class);
            qryCaja.setParameter("idCaja", id);
            Caja caja = (Caja) qryCaja.getSingleResult();
            CajaDto cajaDto = new CajaDto(caja);

            for (Factura f : caja.getFacturaList()) {
                cajaDto.getFacturasDto().add(new FacturaDto(f));
            }
            cajaDto.setIdEmpleadoDto(new EmpleadoDto(caja.getIdEmpleado()));

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Caja", cajaDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe una caja con el id ingresado.", "getCaja NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la caja.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la caja.", "getCaja NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el caja.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la caja.", "getCaja " + ex.getMessage());
        }
    }

//    Guardar o actualizar una caja
    public Respuesta guardarCaja(CajaDto cajaDto) {
        try {
            Caja caja;
            if (cajaDto.getIdCaja() != null && cajaDto.getIdCaja() > 0) {
                caja = em.find(Caja.class, cajaDto.getIdCaja());
                if (caja == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la caja a modificar.", "guardarCaja NoResultException");
                }
                caja.actualizarCaja(cajaDto);

                for (FacturaDto f : cajaDto.getFacturasEliminadasDto()) {
                    caja.getFacturaList().remove(new Factura(f.getIdFactura()));
                }

                if (!cajaDto.getFacturasDto().isEmpty()) {
                    for (FacturaDto f : cajaDto.getFacturasDto()) {
                        if (f.getModificado()) {//si es nueva
                            Factura factura = em.find(Factura.class, f.getIdFactura());
                            factura.setIdCaja(caja);
                            caja.getFacturaList().add(factura);
                        }
                    }
                }
                caja = em.merge(caja);
            } else {
                caja = new Caja(cajaDto);
                em.persist(caja);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Caja", new CajaDto(caja));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la caja.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar la caja.", "guardarCaja " + ex.getMessage());
        }
    }

//    Eliminar una caja permanentemente
    public Respuesta eliminarCaja(Long id) {
        try {
            Caja caja;
            if (id != null && id > 0) {
                caja = em.find(Caja.class, id);
                if (caja == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la caja a eliminar.", "eliminarCaja NoResultException");
                }
                em.remove(caja);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar la caja a eliminar.", "eliminarCaja NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar la caja porque tiene relaciones con otros registros.", "eliminarCaja " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la caja.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar la caja.", "eliminarCaja " + ex.getMessage());
        }
    }

//    Obtener un listado de todas las cajas
    public Respuesta getCajas() {
        try {
            Query qryCaja = em.createNamedQuery("Caja.findAll", Caja.class);

            List<Caja> cajas = (List<Caja>) qryCaja.getResultList();
            List<CajaDto> cajasDto = new ArrayList<>();

            cajas.forEach(caja -> {
                CajaDto cajaDto = new CajaDto(caja);
                List<Factura> facturas = new ArrayList<>();

                for (Factura factura : caja.getFacturaList()) {
                    cajaDto.getFacturasDto().add(new FacturaDto(factura));
                }
                cajaDto.setIdEmpleadoDto(new EmpleadoDto(caja.getIdEmpleado()));

                cajasDto.add(cajaDto);
            });

            return new Respuesta(true,
                    CodigoRespuesta.CORRECTO,
                    "Cajas encontradas",
                    "Cajas encontradas correctamente",
                    "CajasList", cajasDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen cajas en la base de datos.", "getCajas NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar las cajas.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar las cajas.", "getCajas " + ex.getMessage());
        }
    }

}
