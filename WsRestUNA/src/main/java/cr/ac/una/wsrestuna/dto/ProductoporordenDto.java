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
    private int cantidad;
    private float precioProducto;
    private Orden idOrden;
    private Producto idProducto;


    
    
    public ProductoporordenDto(Productopororden productopororden) {
        this.idProductoPorOrden = productopororden.getIdProductoPorOrden();
        this.cantidad = productopororden.getCantidad();
        this.precioProducto = productopororden.getPrecioProducto();
        this.idOrden = productopororden.getIdOrden();
        this.idProducto = productopororden.getIdProducto();
    }

    public Long getIdProductoPorOrden() {
        return idProductoPorOrden;
    }

    public void setIdProductoPorOrden(Long idProductoPorOrden) {
        this.idProductoPorOrden = idProductoPorOrden;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(float precioProducto) {
        this.precioProducto = precioProducto;
    }

    public Orden getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Orden idOrden) {
        this.idOrden = idOrden;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

}
