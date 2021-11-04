/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.dto;

import cr.ac.una.wsrestuna.model.Elementodeseccion;
import cr.ac.una.wsrestuna.model.Orden;
import cr.ac.una.wsrestuna.model.Seccion;
import java.util.List;

/**
 *
 * @author Farlen
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
    private SeccionDto idSeccion;
//    private List<Orden> ordenList;

    public ElementodeseccionDto(Elementodeseccion elementodeseccion) {
        this.idElemento = elementodeseccion.getIdElemento();
        this.tipo = elementodeseccion.getTipo();
        this.nombre = elementodeseccion.getNombre();
        this.esOcupada = elementodeseccion.getEsOcupada();
        this.posicionX = elementodeseccion.getPosicionX();
        this.posicionY = elementodeseccion.getPosicionY();
        this.imagenElemento = elementodeseccion.getImagenElemento();
        this.impuestoPorServicio = elementodeseccion.getImpuestoPorServicio();
        this.idSeccion = new SeccionDto(elementodeseccion.getIdSeccion());
//        this.ordenList = elementodeseccion.getOrdenList();
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

    public SeccionDto getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(SeccionDto idSeccion) {
        this.idSeccion = idSeccion;
    }
//    public Seccion getIdSeccion() {
//        return idSeccion;
//    }
//
//    public void setIdSeccion(Seccion idSeccion) {
//        this.idSeccion = idSeccion;
//    }
//
//    public List<Orden> getOrdenList() {
//        return ordenList;
//    }
//
//    public void setOrdenList(List<Orden> ordenList) {
//        this.ordenList = ordenList;
//    }

}
