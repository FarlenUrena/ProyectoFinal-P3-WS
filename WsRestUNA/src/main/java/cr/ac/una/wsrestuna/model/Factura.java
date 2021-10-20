/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.model;

import cr.ac.una.wsrestuna.dto.FacturaDto;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
//import javax.validation.constraints.NotNull;

/**
 *
 * @author Farlen
 */
@Entity
@Table(name = "FACTURA")
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f"),
    @NamedQuery(name = "Factura.findByIdFactura", query = "SELECT f FROM Factura f WHERE f.idFactura = :idFactura"),
    @NamedQuery(name = "Factura.findByFechaFacturacion", query = "SELECT f FROM Factura f WHERE f.fechaFacturacion = :fechaFacturacion"),
    @NamedQuery(name = "Factura.findByMetodoDePago", query = "SELECT f FROM Factura f WHERE f.metodoDePago = :metodoDePago"),
    @NamedQuery(name = "Factura.findByMontoPagado", query = "SELECT f FROM Factura f WHERE f.montoPagado = :montoPagado"),
    @NamedQuery(name = "Factura.findByTotal", query = "SELECT f FROM Factura f WHERE f.total = :total")})
public class Factura implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "METODO_DE_PAGO")
    private int metodoDePago;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
//    @NotNull
    @Column(name = "ID_FACTURA")
    private Long idFactura;
    @Basic(optional = false)
//    @NotNull
    @Column(name = "FECHA_FACTURACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFacturacion;
    @Basic(optional = false)
//    @NotNull
    @Column(name = "MONTO_PAGADO")
    private float montoPagado;
    @Basic(optional = false)
//    @NotNull
    @Column(name = "TOTAL")
    private float total;
    @JoinColumn(name = "ID_CAJA", referencedColumnName = "ID_CAJA")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Caja idCaja;
    @JoinColumn(name = "ID_ORDEN", referencedColumnName = "ID_ORDEN")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Orden idOrden;

    public Factura() {
    }

    public Factura(Long idFactura) {
        this.idFactura = idFactura;
    }

    public Factura(Long idFactura, Date fechaFacturacion, int metodoDePago, float montoPagado, float total) {
        this.idFactura = idFactura;
        this.fechaFacturacion = fechaFacturacion;
        this.metodoDePago = metodoDePago;
        this.montoPagado = montoPagado;
        this.total = total;
    }

    public Factura(FacturaDto facturaDto) {
    this.idFactura = facturaDto.getIdFactura();
        actualizarFactura(facturaDto);
    }

    public void actualizarFactura(FacturaDto facturaDto) {
        this.fechaFacturacion = facturaDto.getFechaFacturacion();
        this.metodoDePago = facturaDto.getMetodoDePago();
        this.montoPagado = facturaDto.getMontoPagado();
        this.total = facturaDto.getTotal();
        this.idCaja = facturaDto.getIdCaja();
        this.idOrden = facturaDto.getIdOrden();
    }
    public Long getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Long idFactura) {
        this.idFactura = idFactura;
    }

    public Date getFechaFacturacion() {
        return fechaFacturacion;
    }

    public void setFechaFacturacion(Date fechaFacturacion) {
        this.fechaFacturacion = fechaFacturacion;
    }

    public int getMetodoDePago() {
        return metodoDePago;
    }

    public void setMetodoDePago(int metodoDePago) {
        this.metodoDePago = metodoDePago;
    }

    public float getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(float montoPagado) {
        this.montoPagado = montoPagado;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Caja getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(Caja idCaja) {
        this.idCaja = idCaja;
    }

    public Orden getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Orden idOrden) {
        this.idOrden = idOrden;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFactura != null ? idFactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.idFactura == null && other.idFactura != null) || (this.idFactura != null && !this.idFactura.equals(other.idFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wsrestuna.model.Factura[ idFactura=" + idFactura + " ]";
    }

    
    
}
