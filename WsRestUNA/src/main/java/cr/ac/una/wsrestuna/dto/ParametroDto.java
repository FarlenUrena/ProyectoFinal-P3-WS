/*To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.dto;

import cr.ac.una.wsrestuna.model.Parametro;
import java.util.Arrays;

/**
 * @author Farlen
 */
public class ParametroDto {

    private Long idParametro;
    private String nombre;
    private double valorNumerico;
    private String valorTexto;
    private String descripcion;
    private byte[] imagen;

    public ParametroDto(Parametro parametro) {
        this.idParametro = parametro.getIdParametro();
        this.nombre = parametro.getNombre();
        this.valorNumerico = parametro.getValorNumerico();
        this.valorTexto = parametro.getValorTexto();
        this.descripcion = parametro.getDescripcion();
        this.imagen = (byte[]) parametro.getImagen();
    }

    public ParametroDto() {
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public Long getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(Long idParametro) {
        this.idParametro = idParametro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getValorNumerico() {
        return valorNumerico;
    }

    public void setValorNumerico(double valorNumerico) {
        this.valorNumerico = valorNumerico;
    }

    public String getValorTexto() {
        return valorTexto;
    }

    public void setValorTexto(String valorTexto) {
        this.valorTexto = valorTexto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "ParametroDto{" + "idParametro=" + idParametro + ", nombre=" + nombre + ", valorNumerico=" + valorNumerico + ", valorTexto=" + valorTexto + ", descripcion=" + descripcion + ", imagen=" + Arrays.toString(imagen) + '}';
    }

}
