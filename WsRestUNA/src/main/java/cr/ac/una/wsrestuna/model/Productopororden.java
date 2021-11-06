/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.model;

import cr.ac.una.wsrestuna.dto.ProductoporordenDto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Farlen
 */
@Entity
@Table(name = "PRODUCTOPORORDEN", schema = "RESTUNA")
@NamedQueries({
    @NamedQuery(name = "Productopororden.findAll", query = "SELECT p FROM Productopororden p"),
    @NamedQuery(name = "Productopororden.findByIdProductoPorOrden", query = "SELECT p FROM Productopororden p WHERE p.idProductoPorOrden = :idProductoPorOrden"),
    @NamedQuery(name = "Productopororden.findByCantidad", query = "SELECT p FROM Productopororden p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "Productopororden.findByPrecioProducto", query = "SELECT p FROM Productopororden p WHERE p.precioProducto = :precioProducto"),
    @NamedQuery(name = "Productopororden.findBySubtotal", query = "SELECT p FROM Productopororden p WHERE p.subtotal = :subtotal")})
public class Productopororden implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "PRODUCTOPORORDEN_ID_GENERATOR", sequenceName = "RESTUNA.SEQ_ID_PRODUCTOPORORDEN", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCTOPORORDEN_ID_GENERATOR")
    @Basic(optional = false)
//    @NotNull
    @Column(name = "ID_PRODUCTO_POR_ORDEN")
    private Long idProductoPorOrden;
    @Basic(optional = false)
//    @NotNull
    @Column(name = "CANTIDAD")
    private Long cantidad;
    @Basic(optional = false)
//    @NotNull
    @Column(name = "PRECIO_PRODUCTO")
    private double precioProducto;
    @Basic(optional = false)
    @Column(name = "SUBTOTAL")
    private double subtotal;
    @JoinColumn(name = "ID_ORDEN", referencedColumnName = "ID_ORDEN")
    @ManyToOne(fetch = FetchType.LAZY)
    private Orden idOrden;
    @JoinColumn(name = "ID_PRODUCTO", referencedColumnName = "ID_PRODUCTO")
    @OneToOne(fetch = FetchType.LAZY)
    private Producto idProducto;

    public Productopororden() {
    }

    public Productopororden(Long idProductoPorOrden) {
        this.idProductoPorOrden = idProductoPorOrden;
    }

    public Productopororden(Long idProductoPorOrden, Long cantidad, double precioProducto, double subtotal) {
        this.idProductoPorOrden = idProductoPorOrden;
        this.cantidad = cantidad;
        this.precioProducto = precioProducto;
        this.subtotal = subtotal;
    }

    public Productopororden(ProductoporordenDto productoporordenDto) {
        this.idProductoPorOrden = productoporordenDto.getIdProductoPorOrden();
        actualizarProductopororden(productoporordenDto);
    }

    public void actualizarProductopororden(ProductoporordenDto productoporordenDto) {
        this.cantidad = productoporordenDto.getCantidad();
        this.precioProducto = productoporordenDto.getPrecioProducto();
        this.subtotal = productoporordenDto.getSubtotal();
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

}
