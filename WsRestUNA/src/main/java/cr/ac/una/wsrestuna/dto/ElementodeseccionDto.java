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
    private int tipo;
    private String nombre;
    private int esOcupada;
    private float posicionX;
    private float posicionY;
    private byte[] imagenElemento;
    private Seccion idSeccion;
    private List<Orden> ordenList;

    public ElementodeseccionDto(Elementodeseccion elementodeseccion) {
        this.idElemento = elementodeseccion.getIdElemento();
        this.tipo = elementodeseccion.getTipo();
        this.nombre = elementodeseccion.getNombre();
        this.esOcupada = elementodeseccion.getEsOcupada();
        this.posicionX = elementodeseccion.getPosicionX();
        this.posicionY = elementodeseccion.getPosicionY();
        this.imagenElemento = elementodeseccion.getImagenElemento();
        this.idSeccion = elementodeseccion.getIdSeccion();
        this.ordenList = elementodeseccion.getOrdenList();
    }

    public Long getIdElemento() {
        return idElemento;
    }

    public void setIdElemento(Long idElemento) {
        this.idElemento = idElemento;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEsOcupada() {
        return esOcupada;
    }

    public void setEsOcupada(int esOcupada) {
        this.esOcupada = esOcupada;
    }

    public float getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(float posicionX) {
        this.posicionX = posicionX;
    }

    public float getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(float posicionY) {
        this.posicionY = posicionY;
    }

    public byte[] getImagenElemento() {
        return imagenElemento;
    }

    public void setImagenElemento(byte[] imagenElemento) {
        this.imagenElemento = imagenElemento;
    }

    public Seccion getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Seccion idSeccion) {
        this.idSeccion = idSeccion;
    }

    public List<Orden> getOrdenList() {
        return ordenList;
    }

    public void setOrdenList(List<Orden> ordenList) {
        this.ordenList = ordenList;
    }

}
