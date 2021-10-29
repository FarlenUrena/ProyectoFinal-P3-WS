/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.dto;

import cr.ac.una.wsrestuna.model.Grupo;
import cr.ac.una.wsrestuna.model.Producto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jeez
 */
public class GrupoDto implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Long idGrupo;
    private String nombreGrupo;
    private List<ProductoDto> productoList;

    public GrupoDto() {
        List<ProductoDto> productoList = new ArrayList<>();
    }

    public GrupoDto(Grupo grupo) {
        this.idGrupo = grupo.getIdGrupo();
        this.nombreGrupo = grupo.getNombreGrupo();
    }

    public Long getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public List<ProductoDto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<ProductoDto> productoList) {
        this.productoList = productoList;
    }

}
