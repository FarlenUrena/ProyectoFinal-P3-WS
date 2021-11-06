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
    private Boolean modificado;
    private List<ElementodeseccionDto> elementosdeseccionDto;
    private List<ElementodeseccionDto> elementosdeseccionEliminadosDto;

    public SeccionDto() {
        this.modificado = false;
        this.elementosdeseccionDto = new ArrayList<>();
        this.elementosdeseccionEliminadosDto = new ArrayList<>();
    }

    public SeccionDto(Seccion seccion) {
        this();
        this.idSeccion = seccion.getIdSeccion();
        this.nombre = seccion.getNombre();
        this.fotoDistribucion = seccion.getFotoDistribucion();
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

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public List<ElementodeseccionDto> getElementosdeseccionDto() {
        return elementosdeseccionDto;
    }

    public void setElementosdeseccionDto(List<ElementodeseccionDto> elementosdeseccionDto) {
        this.elementosdeseccionDto = elementosdeseccionDto;
    }

    public List<ElementodeseccionDto> getElementosdeseccionEliminadosDto() {
        return elementosdeseccionEliminadosDto;
    }

    public void setElementosdeseccionEliminadosDto(List<ElementodeseccionDto> elementosdeseccionEliminadosDto) {
        this.elementosdeseccionEliminadosDto = elementosdeseccionEliminadosDto;
    }

}
