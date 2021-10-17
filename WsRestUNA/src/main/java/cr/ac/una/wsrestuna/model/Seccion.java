/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.model;

import cr.ac.una.wsrestuna.dto.SeccionDto;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
//import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Farlen
 */
@Entity
@Table(name = "SECCION")
@NamedQueries({
    @NamedQuery(name = "Seccion.findAll", query = "SELECT s FROM Seccion s"),
    @NamedQuery(name = "Seccion.findByIdSeccion", query = "SELECT s FROM Seccion s WHERE s.idSeccion = :idSeccion"),
    @NamedQuery(name = "Seccion.findByNombre", query = "SELECT s FROM Seccion s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "Seccion.findByImpuestoPorServicio", query = "SELECT s FROM Seccion s WHERE s.impuestoPorServicio = :impuestoPorServicio"),
    @NamedQuery(name = "Seccion.findByTipo", query = "SELECT s FROM Seccion s WHERE s.tipo = :tipo")})
public class Seccion implements Serializable {

    
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
//    @NotNull
    @Column(name = "ID_SECCION")
    private Long idSeccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE")
    private String nombre;
    @Lob
    @Column(name = "FOTO_DISTRIBUCION")
    private  byte[] fotoDistribucion;
    @Column(name = "TIPO")
    private int tipo;

    
    @Column(name = "IMPUESTO_POR_SERVICIO")
    private Double impuestoPorServicio;
    @ManyToMany(mappedBy = "seccionList", fetch = FetchType.LAZY)
    private List<Empleado> empleadoList;
    @OneToMany(mappedBy = "idSeccion", fetch = FetchType.LAZY)
    private List<Elementodeseccion> elementodeseccionList;

    public Seccion() {
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
        this.impuestoPorServicio = seccionDto.getImpuestoPorServicio();
        this.tipo = seccionDto.getTipo();
        this.empleadoList = seccionDto.getEmpleadoList();
        this.elementodeseccionList = seccionDto.getElementodeseccionList();
    }
    public Long getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Long idSeccion) {
        this.idSeccion = idSeccion;
    }


    public byte[] getFotoDistribucion() {
        return fotoDistribucion;
    }

    public void setFotoDistribucion(byte[] fotoDistribucion) {
        this.fotoDistribucion = fotoDistribucion;
    }

    public Double getImpuestoPorServicio() {
        return impuestoPorServicio;
    }

    public void setImpuestoPorServicio(Double impuestoPorServicio) {
        this.impuestoPorServicio = impuestoPorServicio;
    }


    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

   
    
}
