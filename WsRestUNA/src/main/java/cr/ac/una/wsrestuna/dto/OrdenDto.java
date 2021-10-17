/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.dto;

import cr.ac.una.wsrestuna.model.Elementodeseccion;
import cr.ac.una.wsrestuna.model.Factura;
import cr.ac.una.wsrestuna.model.Orden;
import cr.ac.una.wsrestuna.model.Productopororden;
import java.util.List;

/**
 *
 * @author Farlen
 */
public class OrdenDto {

    private Long idOrden;
    private String nombreCliente;
    private Elementodeseccion idElemento;
    private List<Productopororden> productoporordenList;
    private List<Factura> facturaList;

    public OrdenDto(Orden orden) {
        this.idOrden = orden.getIdOrden();
        this.nombreCliente = orden.getNombreCliente();
        this.idElemento = orden.getIdElemento();
        this.productoporordenList = orden.getProductoporordenList();
        this.facturaList = orden.getFacturaList();
    }

    public Long getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Long idOrden) {
        this.idOrden = idOrden;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Elementodeseccion getIdElemento() {
        return idElemento;
    }

    public void setIdElemento(Elementodeseccion idElemento) {
        this.idElemento = idElemento;
    }

    public List<Productopororden> getProductoporordenList() {
        return productoporordenList;
    }

    public void setProductoporordenList(List<Productopororden> productoporordenList) {
        this.productoporordenList = productoporordenList;
    }

    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

}
