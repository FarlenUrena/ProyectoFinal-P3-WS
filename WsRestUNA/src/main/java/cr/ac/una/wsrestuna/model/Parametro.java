/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
 * @author Kendall
 */
@Entity
@Table(name = "PARAMETRO")
@NamedQueries({
    @NamedQuery(name = "Parametro.findAll", query = "SELECT p FROM Parametro p"),
    @NamedQuery(name = "Parametro.findByIdParametro", query = "SELECT p FROM Parametro p WHERE p.idParametro = :idParametro"),
    @NamedQuery(name = "Parametro.findByNombreRestaurante", query = "SELECT p FROM Parametro p WHERE p.nombreRestaurante = :nombreRestaurante"),
    @NamedQuery(name = "Parametro.findByCorreoResturante", query = "SELECT p FROM Parametro p WHERE p.correoResturante = :correoResturante"),
    @NamedQuery(name = "Parametro.findByImpuestoServicio", query = "SELECT p FROM Parametro p WHERE p.impuestoServicio = :impuestoServicio"),
    @NamedQuery(name = "Parametro.findByImpuestoVenta", query = "SELECT p FROM Parametro p WHERE p.impuestoVenta = :impuestoVenta"),
    @NamedQuery(name = "Parametro.findByDescuentoMaximo", query = "SELECT p FROM Parametro p WHERE p.descuentoMaximo = :descuentoMaximo")})
public class Parametro implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PARAMETRO")
    private BigDecimal idParametro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE_RESTAURANTE")
    private String nombreRestaurante;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CORREO_RESTURANTE")
    private String correoResturante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IMPUESTO_SERVICIO")
    private BigDecimal impuestoServicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IMPUESTO_VENTA")
    private BigDecimal impuestoVenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DESCUENTO_MAXIMO")
    private BigDecimal descuentoMaximo;
    @Lob
    @Column(name = "LOGO_RESTAURANTE")
    private Serializable logoRestaurante;

    public Parametro() {
    }

    public Parametro(BigDecimal idParametro) {
        this.idParametro = idParametro;
    }

    public Parametro(BigDecimal idParametro, String nombreRestaurante, String correoResturante, BigDecimal impuestoServicio, BigDecimal impuestoVenta, BigDecimal descuentoMaximo) {
        this.idParametro = idParametro;
        this.nombreRestaurante = nombreRestaurante;
        this.correoResturante = correoResturante;
        this.impuestoServicio = impuestoServicio;
        this.impuestoVenta = impuestoVenta;
        this.descuentoMaximo = descuentoMaximo;
    }

    public BigDecimal getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(BigDecimal idParametro) {
        this.idParametro = idParametro;
    }

    public String getNombreRestaurante() {
        return nombreRestaurante;
    }

    public void setNombreRestaurante(String nombreRestaurante) {
        this.nombreRestaurante = nombreRestaurante;
    }

    public String getCorreoResturante() {
        return correoResturante;
    }

    public void setCorreoResturante(String correoResturante) {
        this.correoResturante = correoResturante;
    }

    public BigDecimal getImpuestoServicio() {
        return impuestoServicio;
    }

    public void setImpuestoServicio(BigDecimal impuestoServicio) {
        this.impuestoServicio = impuestoServicio;
    }

    public BigDecimal getImpuestoVenta() {
        return impuestoVenta;
    }

    public void setImpuestoVenta(BigDecimal impuestoVenta) {
        this.impuestoVenta = impuestoVenta;
    }

    public BigDecimal getDescuentoMaximo() {
        return descuentoMaximo;
    }

    public void setDescuentoMaximo(BigDecimal descuentoMaximo) {
        this.descuentoMaximo = descuentoMaximo;
    }

    public Serializable getLogoRestaurante() {
        return logoRestaurante;
    }

    public void setLogoRestaurante(Serializable logoRestaurante) {
        this.logoRestaurante = logoRestaurante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParametro != null ? idParametro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parametro)) {
            return false;
        }
        Parametro other = (Parametro) object;
        if ((this.idParametro == null && other.idParametro != null) || (this.idParametro != null && !this.idParametro.equals(other.idParametro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wsrestuna.model.Parametro[ idParametro=" + idParametro + " ]";
    }
    
}
