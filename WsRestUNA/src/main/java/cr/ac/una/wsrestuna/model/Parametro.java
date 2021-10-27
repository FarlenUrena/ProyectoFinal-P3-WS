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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Farlen
 */
@Entity
@Table(name = "PARAMETRO",schema = "RESTUNA")
@NamedQueries({
    @NamedQuery(name = "Parametro.findAll", query = "SELECT p FROM Parametro p"),
    @NamedQuery(name = "Parametro.findByIdParametro", query = "SELECT p FROM Parametro p WHERE p.idParametro = :idParametro"),
    @NamedQuery(name = "Parametro.findByNombre", query = "SELECT p FROM Parametro p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Parametro.findByValorNumerico", query = "SELECT p FROM Parametro p WHERE p.valorNumerico = :valorNumerico"),
    @NamedQuery(name = "Parametro.findByValorTexto", query = "SELECT p FROM Parametro p WHERE p.valorTexto = :valorTexto"),
    @NamedQuery(name = "Parametro.findByDescripcion", query = "SELECT p FROM Parametro p WHERE p.descripcion = :descripcion")})
public class Parametro implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "PARAMETRO_ID_GENERATOR", sequenceName = "RESTUNA.SEQ_ID_PARAMETRO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARAMETRO_ID_GENERATOR")
    @Basic(optional = false)
//    @NotNull
    @Column(name = "ID_PARAMETRO")
    private Long idParametro;
    @Basic(optional = false)
//    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "VALOR_NUMERICO")
    private double valorNumerico;
    @Size(max = 30)
    @Column(name = "VALOR_TEXTO")
    private String valorTexto;
    @Size(max = 60)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Lob
    @Column(name = "IMAGEN")
    private Serializable imagen;

    public Parametro() {
    }

    public Parametro(Long idParametro) {
        this.idParametro = idParametro;
    }

    public Parametro(Long idParametro, String nombre) {
        this.idParametro = idParametro;
        this.nombre = nombre;
    }

    public Parametro(ParametroDto parametroDto) {
        this.idParametro = parametroDto.getIdParametro();
        actualizarParametro(parametroDto);
    }
    
    public void actualizarParametro(ParametroDto parametroDto) {
    
        this.nombre = parametroDto.getNombre();
        this.valorNumerico = parametroDto.getValorNumerico();
        this.valorTexto = parametroDto.getValorTexto();
        this.descripcion = parametroDto.getDescripcion();
    }

    public Long getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(Long idParametro) {
        this.idParametro = idParametro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getValorNumerico() {
        return valorNumerico;
    }

    public void setValorNumerico(double valorNumerico) {
        this.valorNumerico = valorNumerico;
    }

    public String getValorTexto() {
        return valorTexto;
    }

    public void setValorTexto(String valorTexto) {
        this.valorTexto = valorTexto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Serializable getImagen() {
        return imagen;
    }

    public void setImagen(Serializable imagen) {
        this.imagen = imagen;
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
