/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.model;

import cr.ac.una.wsrestuna.dto.ProductoporordenDto;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
//import javax.validation.constraints.NotNull;

/**
 *
 * @author Farlen
 */
@Entity
@Table(name = "PRODUCTOPORORDEN")
@NamedQueries({
    @NamedQuery(name = "Productopororden.findAll", query = "SELECT p FROM Productopororden p"),
    @NamedQuery(name = "Productopororden.findByIdProductoPorOrden", query = "SELECT p FROM Productopororden p WHERE p.idProductoPorOrden = :idProductoPorOrden"),
    @NamedQuery(name = "Productopororden.findByCantidad", query = "SELECT p FROM Productopororden p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "Productopororden.findByPrecioProducto", query = "SELECT p FROM Productopororden p WHERE p.precioProducto = :precioProducto")})
public class Productopororden implements Serializable {

    @Column(name = "CANTIDAD")
    private int cantidad;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
//    @NotNull
    @Column(name = "ID_PRODUCTO_POR_ORDEN")
    private Long idProductoPorOrden;
    @Column(name = "PRECIO_PRODUCTO")
    private Double precioProducto;
    @JoinColumn(name = "ID_ORDEN", referencedColumnName = "ID_ORDEN")
    @ManyToOne(fetch = FetchType.LAZY)
    private Orden idOrden;
    @JoinColumn(name = "ID_PRODUCTO", referencedColumnName = "ID_PRODUCTO")
    @ManyToOne(fetch = FetchType.LAZY)
    private Producto idProducto;

    public Productopororden() {
    }

    public Productopororden(Long idProductoPorOrden) {
        this.idProductoPorOrden = idProductoPorOrden;
    }

    public Productopororden(ProductoporordenDto productoporordenDto) {
        this.idProductoPorOrden = productoporordenDto.getIdProductoPorOrden();
        actualizarProductopororden(productoporordenDto);
    }
    
    public void actualizarProductopororden(ProductoporordenDto productoporordenDto) {
        this.cantidad = productoporordenDto.getCantidad();
        this.precioProducto = productoporordenDto.getPrecioProducto();
        this.idOrden = productoporordenDto.getIdOrden();
        this.idProducto = productoporordenDto.getIdProducto();
    }

    public Long getIdProductoPorOrden() {
        return idProductoPorOrden;
    }

    public void setIdProductoPorOrden(Long idProductoPorOrden) {
        this.idProductoPorOrden = idProductoPorOrden;
    }


    public Double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(Double precioProducto) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProductoPorOrden != null ? idProductoPorOrden.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productopororden)) {
            return false;
        }
        Productopororden other = (Productopororden) object;
        if ((this.idProductoPorOrden == null && other.idProductoPorOrden != null) || (this.idProductoPorOrden != null && !this.idProductoPorOrden.equals(other.idProductoPorOrden))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wsrestuna.model.Productopororden[ idProductoPorOrden=" + idProductoPorOrden + " ]";
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    
    
}
