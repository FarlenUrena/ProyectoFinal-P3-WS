/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.model;

import cr.ac.una.wsrestuna.dto.SeccionDto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "SECCION", schema = "RESTUNA")
@NamedQueries({
    @NamedQuery(name = "Seccion.findAll", query = "SELECT s FROM Seccion s"),
    @NamedQuery(name = "Seccion.findByIdSeccion", query = "SELECT s FROM Seccion s WHERE s.idSeccion = :idSeccion"),
    @NamedQuery(name = "Seccion.findByNombre", query = "SELECT s FROM Seccion s WHERE s.nombre = :nombre")})
public class Seccion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "SECCION_ID_GENERATOR", sequenceName = "RESTUNA.SEQ_ID_SECCION", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SECCION_ID_GENERATOR")
    @Basic(optional = false)
//    @NotNull
    @Column(name = "ID_SECCION")
    private Long idSeccion;
    @Basic(optional = false)
//    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE")
    private String nombre;
    @Lob
    @Column(name = "FOTO_DISTRIBUCION")
    private byte[] fotoDistribucion;
    @OneToMany(mappedBy = "idSeccion", fetch = FetchType.LAZY)
    private List<Elementodeseccion> elementodeseccionList;

    public Seccion() {
        this.elementodeseccionList = new ArrayList<>();
    }

    public Seccion(Long idSeccion) {
        this.idSeccion = idSeccion;
    }

    public Seccion(Long idSeccion, String nombre) {
        this.idSeccion = idSeccion;
        this.nombre = nombre;
    }

    public Seccion(SeccionDto seccionDto) {
        this.idSeccion = seccionDto.getIdSeccion();
        actualizarSeccion(seccionDto);
    }

    public void actualizarSeccion(SeccionDto seccionDto) {
        this.nombre = seccionDto.getNombre();
        this.fotoDistribucion = seccionDto.getFotoDistribucion();
    }

    public Long getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Long idSeccion) {
        this.idSeccion = idSeccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getFotoDistribucion() {
        return fotoDistribucion;
    }

    public void setFotoDistribucion(byte[] fotoDistribucion) {
        this.fotoDistribucion = fotoDistribucion;
    }

    public List<Elementodeseccion> getElementodeseccionList() {
        return elementodeseccionList;
    }

    public void setElementodeseccionList(List<Elementodeseccion> elementodeseccionList) {
        this.elementodeseccionList = elementodeseccionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSeccion != null ? idSeccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seccion)) {
            return false;
        }
        Seccion other = (Seccion) object;
        if ((this.idSeccion == null && other.idSeccion != null) || (this.idSeccion != null && !this.idSeccion.equals(other.idSeccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wsrestuna.model.Seccion[ idSeccion=" + idSeccion + " ]";
    }

}
