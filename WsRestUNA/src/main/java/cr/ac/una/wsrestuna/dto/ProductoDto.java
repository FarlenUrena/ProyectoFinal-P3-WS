/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.dto;

import cr.ac.una.wsrestuna.model.Producto;
import cr.ac.una.wsrestuna.model.Productopororden;
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
    private float grupo;
    private float esAccesoRapido;
    private float ventasTotales;
    private List<Productopororden> productoporordenList;
    
    public ProductoDto(Producto producto) {
        this.idProducto = producto.getIdProducto();
        this.nombre = producto.getNombre();
        this.nombreCorto = producto.getNombreCorto();
        this.precio = producto.getPrecio();
        this.grupo = producto.getGrupo();
        this.esAccesoRapido = producto.getEsAccesoRapido();
        this.ventasTotales = producto.getVentasTotales();
        this.productoporordenList = producto.getProductoporordenList();
   }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
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

    public float getGrupo() {
        return grupo;
    }

    public void setGrupo(float grupo) {
        this.grupo = grupo;
    }

    public float getEsAccesoRapido() {
        return esAccesoRapido;
    }

    public void setEsAccesoRapido(float esAccesoRapido) {
        this.esAccesoRapido = esAccesoRapido;
    }

    public float getVentasTotales() {
        return ventasTotales;
    }

    public void setVentasTotales(float ventasTotales) {
        this.ventasTotales = ventasTotales;
    }

    public List<Productopororden> getProductoporordenList() {
        return productoporordenList;
    }

    public void setProductoporordenList(List<Productopororden> productoporordenList) {
        this.productoporordenList = productoporordenList;
    }

}
