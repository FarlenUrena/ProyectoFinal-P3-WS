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

    private Long idGrupoDto;
    private String nombreGrupo;
    private Boolean modificado;
    private List<ProductoDto> productosDto;
//    private List<ProductoDto> productosEliminadosDto;

    public GrupoDto() {
        this.modificado = false;
        this.productosDto = new ArrayList<>();
//        this.productosEliminadosDto = new ArrayList<>();
    }

    public GrupoDto(Grupo grupo) {
        this();
        this.idGrupoDto = grupo.getIdGrupo();
        this.nombreGrupo = grupo.getNombreGrupo();
    }

    public Long getIdGrupoDto() {
        return idGrupoDto;
    }

    public void setIdGrupoDto(Long idGrupo) {
        this.idGrupoDto = idGrupo;
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

    public List<ProductoDto> getProductosDto() {
        return productosDto;
    }

    public void setProductosDto(List<ProductoDto> productosDto) {
        this.productosDto = productosDto;
    }

//    public List<ProductoDto> getProductosEliminadosDto() {
//        return productosEliminadosDto;
//    }
//
//    public void setProductosEliminadosDto(List<ProductoDto> productosEliminadosDto) {
//        this.productosEliminadosDto = productosEliminadosDto;
//    }

}
