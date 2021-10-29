/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.dto;

import cr.ac.una.wsrestuna.model.Grupo;
import cr.ac.una.wsrestuna.model.Producto;
import java.io.Serializable;

/**
 *
 * @author Farlen
 */
public class ProductoDto implements Serializable{

    private Long idProducto;
    private String nombre;
    private String nombreCorto;
    private double precio;
    private Long esAccesoRapido;
    private Long ventasTotales;
    private byte[] imagen;
    private GrupoDto grupoDto;

    

    private static final long serialVersionUID = 1L;
    
    public ProductoDto() {
         GrupoDto grupoDto = null;
    }
    
    public ProductoDto(Producto producto) {
        this.idProducto = producto.getIdProducto();
        this.nombre = producto.getNombre();
        this.nombreCorto = producto.getNombreCorto();
        this.precio = producto.getPrecio();
        this.esAccesoRapido = producto.getEsAccesoRapido();
        this.ventasTotales = producto.getVentasTotales();
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

    public Long getVentasTotales() {
        return ventasTotales;
    }

    public void setVentasTotales(Long ventasTotales) {
        this.ventasTotales = ventasTotales;
    }
    public GrupoDto getIdGrupo() {
        return grupoDto;
    }

    public void setIdGrupo(GrupoDto idGrupo) {
        this.grupoDto = idGrupo;
    }
    

}
