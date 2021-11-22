/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.model;

import cr.ac.una.wsrestuna.dto.ParametroDto;
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
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PARAMETRO")
    private Long idParametro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE_RESTAURANTE")
    private String nombreRestaurante;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 30)
    @Column(name = "CORREO_RESTURANTE")
    private String correoResturante;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "IMPUESTO_SERVICIO")
    private Double impuestoServicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IMPUESTO_VENTA")
    private Double impuestoVenta;//ARREGLAR EN EL SCRIPT Y MODEL NOMBRE MAL ESCRITO
    @Basic(optional = false)
    @NotNull
    @Column(name = "DESCUENTO_MAXIMO")
    private Double descuentoMaximo;
    @Lob()
    @Column(name = "LOGO_RESTAURANTE")
    private byte[] logoRestaurante;
    @Size(max = 10)
    @Column(name = "TELEFONO_RESTAURANTE")
    private String telefonoRestaurante;
    @Size(max = 11)
    @Column(name = "PSSWRD_CORREO")
    private String psswrdCorreo;
    @Column(name = "EFECTIVO_INICIAL")
    private Double efectivoInicial;
    private static final long serialVersionUID = 1L;
    

    public Parametro() {
    }

    public Parametro(Long idParametro) {
        this.idParametro = idParametro;
    }

    public Parametro(ParametroDto parametroDto) {
        this.idParametro = parametroDto.getIdParametro();
        actualizarParametro(parametroDto);
    }

    public void actualizarParametro(ParametroDto parametroDto) {
        this.nombreRestaurante = parametroDto.getNombreRestaurante();
        this.correoResturante = parametroDto.getCorreoRestaurante();//ARREGLAR EN EL SCRIPT Y MODEL NOMBRE MAL ESCRITO
        this.impuestoServicio = parametroDto.getImpuestoServicio();
        this.impuestoVenta = parametroDto.getImpuestoVenta();
        this.descuentoMaximo = parametroDto.getDescuentoMaximo();
        this.logoRestaurante = parametroDto.getLogoRestaurante();
        this.efectivoInicial = parametroDto.getEfectivoInicial();
        this.psswrdCorreo = parametroDto.getPsswrdCorreo();
        this.telefonoRestaurante = parametroDto.getTelefonoRestaurante();
    }

    public Parametro(Long idParametro, String nombreRestaurante, String correoResturante, Double impuestoServicio, Double impuestoVenta, Double descuentoMaximo) {
        this.idParametro = idParametro;
        this.nombreRestaurante = nombreRestaurante;
        this.correoResturante = correoResturante;//ARREGLAR EN EL SCRIPT Y MODEL NOMBRE MAL ESCRITO
        this.impuestoServicio = impuestoServicio;
        this.impuestoVenta = impuestoVenta;
        this.descuentoMaximo = descuentoMaximo;
//        this.logoRestaurante = logoRestaurante;
    }

    public Long getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(Long idParametro) {
        this.idParametro = idParametro;
    }

    public String getNombreRestaurante() {
        return nombreRestaurante;
    }

    public void setNombreRestaurante(String nombreRestaurante) {
        this.nombreRestaurante = nombreRestaurante;
    }

    public String getCorreoResturante() {
        return correoResturante;//ARREGLAR EN EL SCRIPT Y MODEL NOMBRE MAL ESCRITO
    }

    public void setCorreoResturante(String correoResturante) {
        this.correoResturante = correoResturante;//ARREGLAR EN EL SCRIPT Y MODEL NOMBRE MAL ESCRITO
    }

    public Double getImpuestoServicio() {
        return impuestoServicio;
    }

    public void setImpuestoServicio(Double impuestoServicio) {
        this.impuestoServicio = impuestoServicio;
    }

    public Double getImpuestoVenta() {
        return impuestoVenta;
    }

    public void setImpuestoVenta(Double impuestoVenta) {
        this.impuestoVenta = impuestoVenta;
    }

    public Double getDescuentoMaximo() {
        return descuentoMaximo;
    }

    public void setDescuentoMaximo(Double descuentoMaximo) {
        this.descuentoMaximo = descuentoMaximo;
    }

    public byte[] getLogoRestaurante() {
        return (byte[]) logoRestaurante;
    }

    public void setLogoRestaurante(byte[] logoRestaurante) {
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

   
    public String getTelefonoRestaurante() {
        return telefonoRestaurante;
    }

    public void setTelefonoRestaurante(String telefonoRestaurante) {
        this.telefonoRestaurante = telefonoRestaurante;
    }

    public String getPsswrdCorreo() {
        return psswrdCorreo;
    }

    public void setPsswrdCorreo(String psswrdCorreo) {
        this.psswrdCorreo = psswrdCorreo;
    }

    public Double getEfectivoInicial() {
        return efectivoInicial;
    }

    public void setEfectivoInicial(Double efectivoInicial) {
        this.efectivoInicial = efectivoInicial;
    }

}
