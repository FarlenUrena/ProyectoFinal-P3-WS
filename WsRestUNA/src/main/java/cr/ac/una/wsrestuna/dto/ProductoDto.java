/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.dto;

import cr.ac.una.wsrestuna.model.Producto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Farlen
 */
public class ProductoDto {

    private Long idProducto;
    private String nombre;
    private String nombreCorto;
    private double precio;
    private Long esAccesoRapido;
    private byte[] imagen;
    private Boolean modificado;
    private GrupoDto grupoDto;
//    private List<ProductoporordenDto> productosporordenDto;
//    private List<ProductoporordenDto> productosporordenEliminadosDto;

    public ProductoDto() {
        this.modificado = false;
//        this.productosporordenDto = new ArrayList<>();
//        this.productosporordenEliminadosDto = new ArrayList<>();
    }

    public ProductoDto(Producto producto) {
        this();
        this.idProducto = producto.getIdProducto();
        this.nombre = producto.getNombre();
        this.nombreCorto = producto.getNombreCorto();
        this.precio = producto.getPrecio();
        this.esAccesoRapido = producto.getEsAccesoRapido();
        this.imagen = producto.getImagen();
        this.grupoDto = new GrupoDto(producto.getIdGrupo());
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Long getEsAccesoRapido() {
        return esAccesoRapido;
    }

    public void setEsAccesoRapido(Long esAccesoRapido) {
        this.esAccesoRapido = esAccesoRapido;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public GrupoDto getGrupoDto() {
        return grupoDto;
    }

    public void setGrupoDto(GrupoDto grupoDto) {
        this.grupoDto = grupoDto;
    }

//    public List<ProductoporordenDto> getProductosporordenDto() {
//        return productosporordenDto;
//    }
//
//    public void setProductosporordenDto(List<ProductoporordenDto> productosporordenDto) {
//        this.productosporordenDto = productosporordenDto;
//    }
//
//    public List<ProductoporordenDto> getProductosporordenEliminadosDto() {
//        return productosporordenEliminadosDto;
//    }
//
//    public void setProductosporordenEliminadosDto(List<ProductoporordenDto> productosporordenEliminadosDto) {
//        this.productosporordenEliminadosDto = productosporordenEliminadosDto;
//    }
}
