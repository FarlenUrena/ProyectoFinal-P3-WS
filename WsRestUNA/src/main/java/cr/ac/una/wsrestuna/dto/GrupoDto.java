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
public class GrupoDto {

    private Long idGrupo;
    private String nombreGrupo;
    private Boolean modificado;
    private List<ProductoDto> productoList;
    private List<ProductoDto> productosEliminadosList;

    public GrupoDto() {
        this.modificado = false;
        this.productoList = new ArrayList<>();
        this.productosEliminadosList = new ArrayList<>();
    }

    public GrupoDto(Grupo grupo) {
        this();
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

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public List<ProductoDto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<ProductoDto> productoList) {
        this.productoList = productoList;
    }

    public List<ProductoDto> getProductosEliminadosList() {
        return productosEliminadosList;
    }

    public void setProductosEliminadosList(List<ProductoDto> productosEliminadosList) {
        this.productosEliminadosList = productosEliminadosList;
    }

}
