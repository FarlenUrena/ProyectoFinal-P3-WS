/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.dto;

import cr.ac.una.wsrestuna.model.Elementodeseccion;
import cr.ac.una.wsrestuna.model.Orden;
import cr.ac.una.wsrestuna.model.Seccion;
import java.util.ArrayList;
import java.util.List;

/**@author Farlen
 */
public class ElementodeseccionDto {

    private Long idElemento;
    private Long tipo;
    private String nombre;
    private Long esOcupada;
    private double posicionX;
    private double posicionY;
    private double impuestoPorServicio;
    private byte[] imagenElemento;
    private Boolean modificado;
    private SeccionDto idSeccionDto;
    private List<OrdenDto> ordenesDtoList;
    private List<OrdenDto> ordenesEliminadasDtoList;

    public ElementodeseccionDto() {
        this.modificado = false;
        this.ordenesDtoList = new ArrayList<>();
        this.ordenesEliminadasDtoList = new ArrayList<>();
    }

    public ElementodeseccionDto(Elementodeseccion elementodeseccion) {
        this();
        this.idElemento = elementodeseccion.getIdElemento();
        this.tipo = elementodeseccion.getTipo();
        this.nombre = elementodeseccion.getNombre();
        this.esOcupada = elementodeseccion.getEsOcupada();
        this.posicionX = elementodeseccion.getPosicionX();
        this.posicionY = elementodeseccion.getPosicionY();
        this.imagenElemento = elementodeseccion.getImagenElemento();
        this.impuestoPorServicio = elementodeseccion.getImpuestoPorServicio();
        if (elementodeseccion.getIdSeccion() != null) {
            this.idSeccionDto = new SeccionDto(elementodeseccion.getIdSeccion());
        }
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

    public SeccionDto getIdSeccionDto() {
        return idSeccionDto;
    }

    public void setIdSeccionDto(SeccionDto idSeccionDto) {
        this.idSeccionDto = idSeccionDto;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean Modificado) {
        this.modificado = Modificado;
    }

    public List<OrdenDto> getOrdenesDtoList() {
        return ordenesDtoList;
    }

    public void setOrdenesDtoList(List<OrdenDto> ordenesDtoList) {
        this.ordenesDtoList = ordenesDtoList;
    }

    public List<OrdenDto> getOrdenesEliminadasDtoList() {
        return ordenesEliminadasDtoList;
    }

    public void setOrdenesEliminadasDtoList(List<OrdenDto> ordenesEliminadasDtoList) {
        this.ordenesEliminadasDtoList = ordenesEliminadasDtoList;
    }
}
