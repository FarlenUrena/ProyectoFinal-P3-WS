/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.model;

import cr.ac.una.wsrestuna.dto.ElementodeseccionDto;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Farlen
 */
@Entity
@Table(name = "ELEMENTODESECCION", schema = "RESTUNA")
@NamedQueries({
    @NamedQuery(name = "Elementodeseccion.findAll", query = "SELECT e FROM Elementodeseccion e"),
    @NamedQuery(name = "Elementodeseccion.findByIdElemento", query = "SELECT e FROM Elementodeseccion e WHERE e.idElemento = :idElemento"),
    @NamedQuery(name = "Elementodeseccion.findByTipo", query = "SELECT e FROM Elementodeseccion e WHERE e.tipo = :tipo"),
    @NamedQuery(name = "Elementodeseccion.findByNombre", query = "SELECT e FROM Elementodeseccion e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Elementodeseccion.findByEsOcupada", query = "SELECT e FROM Elementodeseccion e WHERE e.esOcupada = :esOcupada"),
    @NamedQuery(name = "Elementodeseccion.findByPosicionX", query = "SELECT e FROM Elementodeseccion e WHERE e.posicionX = :posicionX"),
    @NamedQuery(name = "Elementodeseccion.findByPosicionY", query = "SELECT e FROM Elementodeseccion e WHERE e.posicionY = :posicionY"),
    @NamedQuery(name = "Elementodeseccion.findByImpuestoPorServicio", query = "SELECT e FROM Elementodeseccion e WHERE e.impuestoPorServicio = :impuestoPorServicio")})
public class Elementodeseccion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "ELEMENTODESECCION_ID_GENERATOR", sequenceName = "RESTUNA.SEQ_ID_ELEMENTODESECCION", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ELEMENTODESECCION_ID_GENERATOR")
    @Basic(optional = false)
//    @NotNull
    @Column(name = "ID_ELEMENTO")
    private Long idElemento;
    @Basic(optional = false)
//    @NotNull
    @Column(name = "TIPO")
    private Long tipo;
    @Basic(optional = false)
//    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
//    @NotNull
    @Column(name = "ES_OCUPADA")
    private Long esOcupada;
    @Basic(optional = false)
//    @NotNull
    @Column(name = "POSICION_X")
    private double posicionX;
    @Basic(optional = false)
//    @NotNull
    @Column(name = "POSICION_Y")
    private double posicionY;
    @Basic(optional = false)
//    @NotNull
    @Lob
    @Column(name = "IMAGEN_ELEMENTO")
    private byte[] imagenElemento;
    @Basic(optional = false)
//    @NotNull
    @Column(name = "IMPUESTO_POR_SERVICIO")
    private double impuestoPorServicio;
    @JoinColumn(name = "ID_SECCION", referencedColumnName = "ID_SECCION")
    @ManyToOne(fetch = FetchType.LAZY)
    private Seccion idSeccion;

    @OneToMany(mappedBy = "idElemento", fetch = FetchType.LAZY)
    private List<Orden> ordenList;

    public Elementodeseccion() {
    }

    public Elementodeseccion(Long idElemento) {
        this.idElemento = idElemento;
    }

    public Elementodeseccion(Long idElemento, Long tipo, String nombre, Long esOcupada, double posicionX, double posicionY, byte[] imagenElemento, double impuestoPorServicio) {
        this.idElemento = idElemento;
        this.tipo = tipo;
        this.nombre = nombre;
        this.esOcupada = esOcupada;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.imagenElemento = imagenElemento;
        this.impuestoPorServicio = impuestoPorServicio;
    }

    public Elementodeseccion(ElementodeseccionDto elementodeseccionDto) {
        this.idElemento = elementodeseccionDto.getIdElemento();
        actualizarElementodeseccion(elementodeseccionDto);
    }

    public void actualizarElementodeseccion(ElementodeseccionDto elementodeseccionDto) {
        this.tipo = elementodeseccionDto.getTipo();
        this.nombre = elementodeseccionDto.getNombre();
        this.esOcupada = elementodeseccionDto.getEsOcupada();
        this.posicionX = elementodeseccionDto.getPosicionX();
        this.posicionY = elementodeseccionDto.getPosicionY();
        this.imagenElemento = elementodeseccionDto.getImagenElemento();
        this.impuestoPorServicio = elementodeseccionDto.getImpuestoPorServicio();
    }

    public Long getIdElemento() {
        return idElemento;
    }

    public void setIdElemento(Long idElemento) {
        this.idElemento = idElemento;
    }

    public Long getTipo() {
        return tipo;
    }

    public void setTipo(Long tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getEsOcupada() {
        return esOcupada;
    }

    public void setEsOcupada(Long esOcupada) {
        this.esOcupada = esOcupada;
    }

    public double getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(double posicionX) {
        this.posicionX = posicionX;
    }

    public double getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(double posicionY) {
        this.posicionY = posicionY;
    }

    public byte[] getImagenElemento() {
        return imagenElemento;
    }

    public void setImagenElemento(byte[] imagenElemento) {
        this.imagenElemento = imagenElemento;
    }

    public double getImpuestoPorServicio() {
        return impuestoPorServicio;
    }

    public void setImpuestoPorServicio(double impuestoPorServicio) {
        this.impuestoPorServicio = impuestoPorServicio;
    }

    public Seccion getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Seccion idSeccion) {
        this.idSeccion = idSeccion;
    }

    public List<Orden> getOrdenList() {
        return ordenList;
    }

    public void setOrdenList(List<Orden> ordenList) {
        this.ordenList = ordenList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idElemento != null ? idElemento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Elementodeseccion)) {
            return false;
        }
        Elementodeseccion other = (Elementodeseccion) object;
        if ((this.idElemento == null && other.idElemento != null) || (this.idElemento != null && !this.idElemento.equals(other.idElemento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wsrestuna.model.Elementodeseccion[ idElemento=" + idElemento + " ]";
    }

}
