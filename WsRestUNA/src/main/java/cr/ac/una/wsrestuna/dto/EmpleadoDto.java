/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.dto;

import cr.ac.una.wsrestuna.model.Empleado;
import java.util.ArrayList;
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
    private Long rol;
    private String token;
    private Boolean modificado;
    private List<OrdenDto> ordenesDto;
    private List<OrdenDto> ordenesElimindasDto;
    private List<CajaDto> cajasDto;
    private List<CajaDto> cajasEliminadasDto;

    public EmpleadoDto() {
        this.modificado = false;
        this.ordenesDto = new ArrayList<>();
        this.ordenesElimindasDto = new ArrayList<>();
        this.cajasDto = new ArrayList<>();
        this.cajasEliminadasDto = new ArrayList<>();
    }

    public EmpleadoDto(Empleado empleado) {
        this();
        this.idEmpleado = empleado.getIdEmpleado();
        this.nombre = empleado.getNombre();
        this.apellido = empleado.getApellido();
        this.cedula = empleado.getCedula();
        this.nombreUsuario = empleado.getNombreUsuario();
        this.password = empleado.getPassword();
        this.rol = empleado.getRol();
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

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public Long getRol() {
        return rol;
    }

    public void setRol(Long rol) {
        this.rol = rol;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<OrdenDto> getOrdenesDto() {
        return ordenesDto;
    }

    public void setOrdenesDto(List<OrdenDto> ordenesDto) {
        this.ordenesDto = ordenesDto;
    }

    public List<OrdenDto> getOrdenesElimindasDto() {
        return ordenesElimindasDto;
    }

    public void setOrdenesElimindasDto(List<OrdenDto> ordenesElimindasDto) {
        this.ordenesElimindasDto = ordenesElimindasDto;
    }

    public List<CajaDto> getCajasDto() {
        return cajasDto;
    }

    public void setCajasDto(List<CajaDto> cajasDto) {
        this.cajasDto = cajasDto;
    }

    public List<CajaDto> getCajasEliminadasDto() {
        return cajasEliminadasDto;
    }

    public void setCajasEliminadasDto(List<CajaDto> cajasEliminadasDto) {
        this.cajasEliminadasDto = cajasEliminadasDto;
    }

}
