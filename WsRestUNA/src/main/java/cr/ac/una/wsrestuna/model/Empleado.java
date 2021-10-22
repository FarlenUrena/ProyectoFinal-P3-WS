/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.model;

import cr.ac.una.wsrestuna.dto.EmpleadoDto;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.QueryHint;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Farlen
 */
@Entity
@Table(name = "EMPLEADO", schema = "RESTUNA")
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e"),
    @NamedQuery(name = "Empleado.findByIdEmpleado", query = "SELECT e FROM Empleado e WHERE e.idEmpleado = :idEmpleado"),
    @NamedQuery(name = "Empleado.findByNombre", query = "SELECT e FROM Empleado e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Empleado.findByApellido", query = "SELECT e FROM Empleado e WHERE e.apellido = :apellido"),
    @NamedQuery(name = "Empleado.findByCedula", query = "SELECT e FROM Empleado e WHERE e.cedula = :cedula"),
    @NamedQuery(name = "Empleado.findByNombreUsuario", query = "SELECT e FROM Empleado e WHERE e.nombreUsuario = :nombreUsuario"),
    @NamedQuery(name = "Empleado.findByPassword", query = "SELECT e FROM Empleado e WHERE e.password = :password"),
    @NamedQuery(name = "Empleado.findByRol", query = "SELECT e FROM Empleado e WHERE e.rol = :rol"),
    @NamedQuery(name = "Empleado.findByUsuClave", query = "SELECT e FROM Empleado e WHERE e.nombreUsuario = :nombreUsuario and e.password = :password", hints = @QueryHint(name = "eclipselink.refresh", value = "true"))    
})
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "EMPLEADO_ID_GENERATOR", sequenceName = "RESTUNA.SEQ_ID_EMPLEADO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLEADO_ID_GENERATOR")
    @Basic(optional = false)
//    @NotNull
    @Column(name = "ID_EMPLEADO")
    private Long idEmpleado;
    @Basic(optional = false)
//    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
//    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "APELLIDO")
    private String apellido;
    @Basic(optional = false)
//    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CEDULA")
    private String cedula;
    @Basic(optional = false)
//    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE_USUARIO")
    private String nombreUsuario;
    @Basic(optional = false)
//    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
//    @NotNull
    @Column(name = "ROL")
    private Long rol;
    
    @JoinTable(name = "EMPLEADO_SECCION", joinColumns = {
        @JoinColumn(name = "ID_EMPLEADO", referencedColumnName = "ID_EMPLEADO")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_SECCION", referencedColumnName = "ID_SECCION")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Seccion> seccionList;
    @OneToMany(mappedBy = "idEmpleado", fetch = FetchType.LAZY)
    private List<Caja> cajaList;

    public Empleado() {
    }

    public Empleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Empleado(String nombre, String apellido, String cedula, String nombreUsuario, String password, Long rol, Long idEmpleado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.rol = rol;
        this.idEmpleado = idEmpleado;
    }
    
    
    public Empleado(EmpleadoDto empleado) {
        this.idEmpleado = empleado.getIdEmpleado();
        actualizarEmpleado(empleado);
    }
    
    public void actualizarEmpleado(EmpleadoDto empleadoDto) {
        this.nombre = empleadoDto.getNombre();
        this.apellido = empleadoDto.getApellido();
        this.cedula = empleadoDto.getCedula();
        this.nombreUsuario = empleadoDto.getNombreUsuario();
        this.password = empleadoDto.getPassword();
        this.rol = empleadoDto.getRol();
    
    }
    

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }


    public List<Seccion> getSeccionList() {
        return seccionList;
    }

    public void setSeccionList(List<Seccion> seccionList) {
        this.seccionList = seccionList;
    }

    public List<Caja> getCajaList() {
        return cajaList;
    }

    public void setCajaList(List<Caja> cajaList) {
        this.cajaList = cajaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpleado != null ? idEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.idEmpleado == null && other.idEmpleado != null) || (this.idEmpleado != null && !this.idEmpleado.equals(other.idEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wsrestuna.model.Empleado[ idEmpleado=" + idEmpleado + " ]";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRol() {
        return rol;
    }

    public void setRol(Long rol) {
        this.rol = rol;
    }

    
    
}
