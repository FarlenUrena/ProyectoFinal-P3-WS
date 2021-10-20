/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.model;

import cr.ac.una.wsrestuna.dto.CajaDto;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
//import javax.validation.constraints.NotNull;

/**
 *
 * @author Farlen
 */
@Entity
@Table(name = "CAJA")
@NamedQueries({
    @NamedQuery(name = "Caja.findAll", query = "SELECT c FROM Caja c"),
    @NamedQuery(name = "Caja.findByIdCaja", query = "SELECT c FROM Caja c WHERE c.idCaja = :idCaja"),
    @NamedQuery(name = "Caja.findBySaldoEfectivo", query = "SELECT c FROM Caja c WHERE c.saldoEfectivo = :saldoEfectivo"),
    @NamedQuery(name = "Caja.findBySaldoTarjeta", query = "SELECT c FROM Caja c WHERE c.saldoTarjeta = :saldoTarjeta"),
    @NamedQuery(name = "Caja.findBySaldoEfectivoCierre", query = "SELECT c FROM Caja c WHERE c.saldoEfectivoCierre = :saldoEfectivoCierre"),
    @NamedQuery(name = "Caja.findBySaldoTarjetaCierre", query = "SELECT c FROM Caja c WHERE c.saldoTarjetaCierre = :saldoTarjetaCierre"),
    @NamedQuery(name = "Caja.findByFechaCierre", query = "SELECT c FROM Caja c WHERE c.fechaCierre = :fechaCierre")})
public class Caja implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
//    @NotNull
    @Column(name = "ID_CAJA")
    private Long idCaja;
    @Basic(optional = false)
//    @NotNull
    @Column(name = "SALDO_EFECTIVO")
    private float saldoEfectivo;
    @Basic(optional = false)
//    @NotNull
    @Column(name = "SALDO_TARJETA")
    private float saldoTarjeta;
    @Basic(optional = false)
//    @NotNull
    @Column(name = "SALDO_EFECTIVO_CIERRE")
    private float saldoEfectivoCierre;
    @Basic(optional = false)
//    @NotNull
    @Column(name = "SALDO_TARJETA_CIERRE")
    private float saldoTarjetaCierre;
    @Basic(optional = false)
//    @NotNull
    @Column(name = "FECHA_CIERRE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCierre;
    @JoinColumn(name = "ID_EMPLEADO", referencedColumnName = "ID_EMPLEADO")
    @ManyToOne(fetch = FetchType.LAZY)
    private Empleado idEmpleado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCaja", fetch = FetchType.LAZY)
    private List<Factura> facturaList;

    public Caja() {
    }

    public Caja(Long idCaja) {
        this.idCaja = idCaja;
    }

    public Caja(Long idCaja, float saldoEfectivo, float saldoTarjeta, float saldoEfectivoCierre, float saldoTarjetaCierre, Date fechaCierre) {
        this.idCaja = idCaja;
        this.saldoEfectivo = saldoEfectivo;
        this.saldoTarjeta = saldoTarjeta;
        this.saldoEfectivoCierre = saldoEfectivoCierre;
        this.saldoTarjetaCierre = saldoTarjetaCierre;
        this.fechaCierre = fechaCierre;
    }

    public Caja(CajaDto caja) {
        this.idCaja = caja.getIdCaja();
        actualizarCaja(caja);
    }

      public void actualizarCaja(CajaDto caja) {
        this.saldoEfectivo = caja.getSaldoEfectivo();
        this.saldoTarjeta = caja.getSaldoTarjeta();
        this.saldoEfectivoCierre = caja.getSaldoEfectivoCierre();
        this.saldoTarjetaCierre = caja.getSaldoTarjetaCierre();
        this.fechaCierre = caja.getFechaCierre();
    }
      
    public Long getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(Long idCaja) {
        this.idCaja = idCaja;
    }

    public float getSaldoEfectivo() {
        return saldoEfectivo;
    }

    public void setSaldoEfectivo(float saldoEfectivo) {
        this.saldoEfectivo = saldoEfectivo;
    }

    public float getSaldoTarjeta() {
        return saldoTarjeta;
    }

    public void setSaldoTarjeta(float saldoTarjeta) {
        this.saldoTarjeta = saldoTarjeta;
    }

    public float getSaldoEfectivoCierre() {
        return saldoEfectivoCierre;
    }

    public void setSaldoEfectivoCierre(float saldoEfectivoCierre) {
        this.saldoEfectivoCierre = saldoEfectivoCierre;
    }

    public float getSaldoTarjetaCierre() {
        return saldoTarjetaCierre;
    }

    public void setSaldoTarjetaCierre(float saldoTarjetaCierre) {
        this.saldoTarjetaCierre = saldoTarjetaCierre;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCaja != null ? idCaja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caja)) {
            return false;
        }
        Caja other = (Caja) object;
        if ((this.idCaja == null && other.idCaja != null) || (this.idCaja != null && !this.idCaja.equals(other.idCaja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wsrestuna.model.Caja[ idCaja=" + idCaja + " ]";
    }

  
    
}
