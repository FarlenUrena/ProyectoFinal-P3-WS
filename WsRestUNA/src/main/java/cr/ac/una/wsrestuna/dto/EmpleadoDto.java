/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.dto;

import cr.ac.una.wsrestuna.model.Caja;
import cr.ac.una.wsrestuna.model.Empleado;
import cr.ac.una.wsrestuna.model.Seccion;
import java.util.List;

/**
 *
 * @author Farlen
 */
public class EmpleadoDto {

    private Long idEmpleado;
    private String nombre;
    private String apellido;
    private String cedula;
    private String nombreUsuario;
    private String password;
    private int rol;
    private List<Seccion> seccionList;
    private List<Caja> cajaList;

    public EmpleadoDto(Empleado empleado) {
        this.idEmpleado = empleado.getIdEmpleado();
        this.nombre = empleado.getNombre();
        this.apellido = empleado.getApellido();
        this.cedula = empleado.getCedula();
        this.nombreUsuario = empleado.getNombreUsuario();
        this.password = empleado.getPassword();
        this.rol = empleado.getRol();
        this.seccionList = empleado.getSeccionList();
        this.cajaList = empleado.getCajaList();
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
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
    
    

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
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

}
