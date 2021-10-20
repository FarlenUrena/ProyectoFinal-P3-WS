/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.dto;

import cr.ac.una.wsrestuna.model.Elementodeseccion;
import cr.ac.una.wsrestuna.model.Empleado;
import cr.ac.una.wsrestuna.model.Seccion;
import java.util.List;

/**
 *
 * @author Farlen
 */
public class SeccionDto {

    private Long idSeccion;
    private String nombre;
    private byte[] fotoDistribucion;
    private float impuestoPorServicio;
    private int tipo;
    private List<Empleado> empleadoList;
    private List<Elementodeseccion> elementodeseccionList;


    
    public SeccionDto(Seccion seccion) {
        this.idSeccion = seccion.getIdSeccion();
        this.nombre = seccion.getNombre();
        this.fotoDistribucion = seccion.getFotoDistribucion();
        this.impuestoPorServicio = seccion.getImpuestoPorServicio();
        this.tipo = seccion.getTipo();
        this.empleadoList = seccion.getEmpleadoList();
        this.elementodeseccionList = seccion.getElementodeseccionList();
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

    public float getImpuestoPorServicio() {
        return impuestoPorServicio;
    }

    public void setImpuestoPorServicio(float impuestoPorServicio) {
        this.impuestoPorServicio = impuestoPorServicio;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
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
