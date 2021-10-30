/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.service;

import cr.ac.una.wsrestuna.dto.GrupoDto;
import cr.ac.una.wsrestuna.dto.ProductoDto;
import cr.ac.una.wsrestuna.model.Grupo;
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
 * @author jeez
 */
@LocalBean
@Stateless
public class GrupoService {

    private static final Logger LOG = Logger.getLogger(ElementodeseccionService.class.getName());
    @PersistenceContext(unitName = "WsRestUnaPU")
    private EntityManager em;

    public Respuesta getGrupo(Long id) {
        try {
            Query qryGrupo = em.createNamedQuery("Grupo.findByIdGrupo", Grupo.class);
            qryGrupo.setParameter("idGrupo", id);
            Grupo grupo = (Grupo) qryGrupo.getSingleResult();
            GrupoDto grupoDto =  new GrupoDto(grupo);
            for(Producto prd : grupo.getProductoList())
            {
                grupoDto.getProductoList().add(prd);
            }
            
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Grupo", grupoDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un grupo de seccion con el id ingresado.", "getGrupo NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el elemento de seccion.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el grupo.", "getGrupo NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el elemento de seccion.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el grupo.", "getGrupo " + ex.getMessage());
        }
    }

    public Respuesta getGrupo(String nombre) {
        try {
            Query qryGrupo = em.createNamedQuery("Grupo.findByNombreGrupo", Grupo.class);
            qryGrupo.setParameter("nombreGrupo", nombre);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Grupo", new GrupoDto((Grupo) qryGrupo.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un grupo de seccion con el nombre ingresado.", "getGrupo NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el elemento de seccion.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el grupo.", "getGrupo NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el elemento de seccion.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el grupo.", "getGrupo " + ex.getMessage());
        }
    }

    public Respuesta getGrupos() {
        try {
            Query qryGrupo = em.createNamedQuery("Grupo.findAll", Grupo.class);
//            qryGrupo.setParameter("nombreGrupo", nombre);
            List<Grupo> grupos = (List<Grupo>) qryGrupo.getResultList();

            List<GrupoDto> gruposDto = new ArrayList<>();
            grupos.forEach(grupo -> {
                gruposDto.add(new GrupoDto(grupo));
            });

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "GruposList", gruposDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un grupo de seccion con el nombre ingresado.", "getGrupo NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el elemento de seccion.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el grupo.", "getGrupo NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el elemento de seccion.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el grupo.", "getGrupo " + ex.getMessage());
        }
    }

    public Respuesta guardarGrupo(GrupoDto grupoDto) {
        try {
            Grupo grupo;
            if (grupoDto.getIdGrupo() != null && grupoDto.getIdGrupo() > 0) {
                grupo = em.find(Grupo.class, grupoDto.getIdGrupo());
                if (grupo == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el grupo a modificar.", "guardarGrupo NoResultException");
                }
                //Todo: eliminar poroductos y setear los que quedan
                
                grupo.atualizarGrupo(grupoDto);
                grupo = em.merge(grupo);
            } else {
                
                grupo = new Grupo(grupoDto);
                em.persist(grupo);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Grupo", new GrupoDto(grupo));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el grupo.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el grupo.", "guardarGrupo " + ex.getMessage());
        }
    }
    
    //TODO: ELIMINAR

    //    Eliminar un grupo permanentemente
    public Respuesta eliminarGrupo(Long id) {
        try {
            Grupo grupo;
            if (id != null && id > 0) {
                grupo = em.find(Grupo.class, id);
                if (grupo == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encontró el grupo a eliminar.", "eliminarGrupo NoResultException");
                }
                em.remove(grupo);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el grupo a eliminar.", "eliminarGrupo NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el grupo porque tiene relaciones con otros registros.", "eliminarGrupo " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrió un error al guardar el grupo.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el grupo.", "eliminarGrupo " + ex.getMessage());
        }
    }

}
