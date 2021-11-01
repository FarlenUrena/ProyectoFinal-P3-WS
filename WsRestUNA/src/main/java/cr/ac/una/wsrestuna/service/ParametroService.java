/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.service;

import cr.ac.una.wsrestuna.dto.ParametroDto;
import cr.ac.una.wsrestuna.model.Parametro;
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

/**@author Farlen*/
@LocalBean
@Stateless
public class ParametroService {
    
    private static final Logger LOG = Logger.getLogger(ParametroService.class.getName());

    @PersistenceContext(unitName = "WsRestUnaPU")
    private EntityManager em;   
    
//    Obtener un parametro
    public Respuesta getParametro(Long id) {
        try {
            Query qryParametro = em.createNamedQuery("Parametro.findByIdParametro", Parametro.class);
            qryParametro.setParameter("idParametro", id);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Parametro", new ParametroDto((Parametro) qryParametro.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un parametro con el id ingresado.", "getParametro NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el parametro.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el parametro.", "getParametro NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el parametro.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el parametro.", "getParametro " + ex.getMessage());
        }
    }  
    
//    Guardar o actualizar un parametro
    public Respuesta guardarParametro(ParametroDto parametroDto) {
        try {
            Parametro parametro;
            System.out.println(parametroDto.toString());
            if (parametroDto.getIdParametro() != null && parametroDto.getIdParametro() > 0) {
                parametro = em.find(Parametro.class, parametroDto.getIdParametro());
                if (parametro == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el parametro a modificar.", "guardarParametro NoResultException");
                }
                parametro.actualizarParametro(parametroDto);
                parametro = em.merge(parametro);
            } else {
                parametro = new Parametro(parametroDto);
                em.persist(parametro);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Parametro", new ParametroDto(parametro));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el parametro.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el parametro.", "guardarParametro " + ex.getMessage());
        }
    }

//    Eliminar un parametro permanentemente
    public Respuesta eliminarParametro(Long id) {
        try {
            Parametro parametro;
            if (id != null && id > 0) {
                parametro = em.find(Parametro.class, id);
                if (parametro == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el parametro a eliminar.", "eliminarParametro NoResultException");
                }
                em.remove(parametro);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el parametro a eliminar.", "eliminarParametro NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el parametro porque tiene relaciones con otros registros.", "eliminarParametro " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el parametro.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el parametro.", "eliminarParametro " + ex.getMessage());
        }
    }

//    Obtener un listado de todos los parametros
    public Respuesta getParametros() {
        try {
            Query qryParametro = em.createNamedQuery("Parametro.findAll",Parametro.class);

            List<Parametro> parametros = (List<Parametro>) qryParametro.getResultList();
            List<ParametroDto> ParametroDto = new ArrayList<>();
            parametros.forEach(parametro
                    -> {
                ParametroDto.add(new ParametroDto(parametro));
            });

            return new Respuesta(true,
                    CodigoRespuesta.CORRECTO,
                    "Parametros encontrados",
                    "Parametros encontrados correctamente",
                    "ParametrosList", ParametroDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen parametros en la base de datos.", "getParametros NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar los parametros.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar los parametros.", "getParametros " + ex.getMessage());
        }
    }    
}
