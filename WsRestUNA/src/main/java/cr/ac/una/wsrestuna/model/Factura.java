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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Farlen
 */
@Entity
@Table(name = "FACTURA", schema = "RESTUNA")
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f"),
    @NamedQuery(name = "Factura.findByIdFactura", query = "SELECT f FROM Factura f WHERE f.idFactura = :idFactura"),
    @NamedQuery(name = "Factura.findByFechaFacturacion", query = "SELECT f FROM Factura f WHERE f.fechaFacturacion = :fechaFacturacion"),
    @NamedQuery(name = "Factura.findByMetodoDePago", query = "SELECT f FROM Factura f WHERE f.metodoDePago = :metodoDePago"),
    @NamedQuery(name = "Factura.findByMontoPagado", query = "SELECT f FROM Factura f WHERE f.montoPagado = :montoPagado"),
    @NamedQuery(name = "Factura.findByTotal", query = "SELECT f FROM Factura f WHERE f.total = :total"),
    @NamedQuery(name = "Factura.findByVuelto", query = "SELECT f FROM Factura f WHERE f.vuelto = :vuelto"),
    @NamedQuery(name = "Factura.findByDescuento", query = "SELECT f FROM Factura f WHERE f.descuento = :descuento"),
    @NamedQuery(name = "Factura.findByImpuestoVenta", query = "SELECT f FROM Factura f WHERE f.impuestoVenta = :impuestoVenta"),
    @NamedQuery(name = "Factura.findByImpuestoServicio", query = "SELECT f FROM Factura f WHERE f.impuestoServicio = :impuestoServicio")})
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "FACTURA_ID_GENERATOR", sequenceName = "RESTUNA.SEQ_ID_FACTURA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FACTURA_ID_GENERATOR")
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
    @Column(name = "METODO_DE_PAGO")
    private Long metodoDePago;
    @Basic(optional = false)
//    @NotNull
    @Column(name = "MONTO_PAGADO")
    private double montoPagado;
    @Basic(optional = false)
//    @NotNull
    @Column(name = "TOTAL")
    private double total;    
    @Basic(optional = false)
//    @NotNull
    @Column(name = "VUELTO")
    private double vuelto;
    @Basic(optional = false)
//    @NotNull
    @Size(max = 40)
    @Column(name = "NOMBRE_CLIENTE")
    private String nombreCliente;
    @Column(name = "DESCUENTO")
    private double descuento;
    @Basic(optional = false)
    @Column(name = "IMPUESTO_VENTA")
    private double impuestoVenta;
    @Basic(optional = false)
    @Column(name = "IMPUESTO_SERVICIO")
    private double impuestoServicio;
    @JoinColumn(name = "ID_CAJA", referencedColumnName = "ID_CAJA")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Caja idCaja;
    @JoinColumn(name = "ID_ORDEN", referencedColumnName = "ID_ORDEN")
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Orden idOrden;

    public Factura() {
    }

    public Factura(Long idFactura) {
        this.idFactura = idFactura;
    }

    public Factura(Long idFactura, Date fechaFacturacion, Long metodoDePago, float montoPagado, float total, float vuelto, float descuento,float impuestoVenta,String nombreCliente,float impuestoServicio) {
        this.idFactura = idFactura;
        this.fechaFacturacion = fechaFacturacion;
        this.metodoDePago = metodoDePago;
        this.montoPagado = montoPagado;
        this.total = total;
        this.vuelto = vuelto;
        this.descuento = descuento;
        this.nombreCliente = nombreCliente;
        this.impuestoVenta = impuestoVenta;
        this.impuestoServicio = impuestoServicio;
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
        this.vuelto = facturaDto.getVuelto();
        this.descuento = facturaDto.getDescuento();
        this.impuestoVenta = facturaDto.getImpuestoVenta();
        this.impuestoServicio = facturaDto.getImpuestoServicio();
        this.nombreCliente = facturaDto.getNombreCliente();
        if(facturaDto.getIdCajaDto()!=null){
            this.idCaja = new Caja((facturaDto.getIdCajaDto()));
        }if(facturaDto.getIdOrdenDto()!=null){
            this.idOrden = new Orden((facturaDto.getIdOrdenDto()));
        }
//        this.idCaja = new Caja(facturaDto.getIdCajaDto());
//        this.idOrden = new Orden(facturaDto.getIdOrdenDto());
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

    public Long getMetodoDePago() {
        return metodoDePago;
    }

    public void setMetodoDePago(Long metodoDePago) {
        this.metodoDePago = metodoDePago;
    }

    public double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(double montoPagado) {
        this.montoPagado = montoPagado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getVuelto() {
        return vuelto;
    }

    public void setVuelto(double vuelto) {
        this.vuelto = vuelto;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(Long descuento) {
        this.descuento = descuento;
    }

    public double getImpuestoVenta() {
        return impuestoVenta;
    }

    public void setImpuestoVenta(double impuestoVenta) {
        this.impuestoVenta = impuestoVenta;
    }

    public double getImpuestoServicio() {
        return impuestoServicio;
    }

    public void setImpuestoServicio(double impuestoServicio) {
        this.impuestoServicio = impuestoServicio;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
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
