/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.dto;

import cr.ac.una.wsrestuna.model.Elementodeseccion;
import cr.ac.una.wsrestuna.model.Empleado;
import cr.ac.una.wsrestuna.model.Seccion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Farlen
 */
public class SeccionDto {

    private Long idSeccion;
    private String nombre;
    private byte[] fotoDistribucion;
    private List<Empleado> empleadoList;
    private List<Elementodeseccion> elementodeseccionList;


     public SeccionDto() {
        this.empleadoList = new ArrayList<>();
        this.elementodeseccionList = new ArrayList<>();
    }
    
    public SeccionDto(Seccion seccion) {
        this.idSeccion = seccion.getIdSeccion();
        this.nombre = seccion.getNombre();
        this.fotoDistribucion = seccion.getFotoDistribucion();
//        this.empleadoList = seccion.getEmpleadoList();
//        this.elementodeseccionList = seccion.getElementodeseccionList();
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

    
}
