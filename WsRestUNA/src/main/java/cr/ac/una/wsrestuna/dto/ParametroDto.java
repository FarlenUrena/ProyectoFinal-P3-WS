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
    private String nombreRestaurante;
    private String correoRestaurante;
    private double impuestoServicio;
    private double impuestoVenta;
    private double descuentoMaximo;
    private byte[] logoRestaurante;

    public ParametroDto(Parametro parametro) {
        //TODO:
        //ARREGLAR ENTIDAD
//        this.idParametro = parametro.getIdParametro();
//        this.nombreRestaurante = parametro.getNombre();
//        this.impuestoServicio = parametro.getValorNumerico();
//        this.logoRestaurante = (byte[]) parametro.getImagen();
    }

    public ParametroDto() {
    }

    public Long getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(Long idParametro) {
        this.idParametro = idParametro;
    }

    public String getNombreRestaurante() {
        return nombreRestaurante;
    }

    public void setNombreRestaurante(String nombreRestaurante) {
        this.nombreRestaurante = nombreRestaurante;
    }

    public String getCorreoRestaurante() {
        return correoRestaurante;
    }

    public void setCorreoRestaurante(String correoRestaurante) {
        this.correoRestaurante = correoRestaurante;
    }

    public double getImpuestoServicio() {
        return impuestoServicio;
    }

    public void setImpuestoServicio(double impuestoServicio) {
        this.impuestoServicio = impuestoServicio;
    }

    public double getImpuestoVenta() {
        return impuestoVenta;
    }

    public void setImpuestoVenta(double impuestoVenta) {
        this.impuestoVenta = impuestoVenta;
    }

    public double getDescuentoMaximo() {
        return descuentoMaximo;
    }

    public void setDescuentoMaximo(double descuentoMaximo) {
        this.descuentoMaximo = descuentoMaximo;
    }

    public byte[] getLogoRestaurante() {
        return logoRestaurante;
    }

    public void setLogoRestaurante(byte[] logoRestaurante) {
        this.logoRestaurante = logoRestaurante;
    }  

    @Override
    public String toString() {
        return "ParametroDto{" + "idParametro=" + idParametro + ", nombreRestaurante=" + nombreRestaurante + ", correoRestaurante=" + correoRestaurante + ", impuestoServicio=" + impuestoServicio + ", impuestoVenta=" + impuestoVenta + ", descuentoMaximo=" + descuentoMaximo + ", logoRestaurante=" + logoRestaurante + '}';
    }

}
