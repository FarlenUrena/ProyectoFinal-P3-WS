/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.dto;

import cr.ac.una.wsrestuna.model.Orden;
import cr.ac.una.wsrestuna.model.Producto;
import cr.ac.una.wsrestuna.model.Productopororden;

/**
 *
 * @author Farlen
 */
public class ProductoporordenDto {

    private Long idProductoPorOrden;
    private Long cantidad;
    private double precioProducto;
    private double subtotal;
    private Boolean modificado;
    private OrdenDto idOrdenDto;
    private ProductoDto idProductoDto;

    public ProductoporordenDto() {
        this.modificado = false;
    }

    public ProductoporordenDto(Productopororden productopororden) {
        this();
        this.idProductoPorOrden = productopororden.getIdProductoPorOrden();
        this.cantidad = productopororden.getCantidad();
        this.precioProducto = productopororden.getPrecioProducto();
        this.subtotal = productopororden.getSubtotal();
        this.idOrdenDto = new OrdenDto(productopororden.getIdOrden());
        this.idProductoDto = new ProductoDto(productopororden.getIdProducto());
    }

    public Long getIdProductoPorOrden() {
        return idProductoPorOrden;
    }

    public void setIdProductoPorOrden(Long idProductoPorOrden) {
        this.idProductoPorOrden = idProductoPorOrden;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public OrdenDto getIdOrdenDto() {
        return idOrdenDto;
    }

    public void setIdOrdenDto(OrdenDto idOrdenDto) {
        this.idOrdenDto = idOrdenDto;
    }

    public ProductoDto getIdProductoDto() {
        return idProductoDto;
    }

    public void setIdProductoDto(ProductoDto idProductoDto) {
        this.idProductoDto = idProductoDto;
    }

}
