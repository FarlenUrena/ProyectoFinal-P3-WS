/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.dto;

import cr.ac.una.wsrestuna.model.Factura;
import java.util.Date;

/**
 *
 * @author Farlen
 */
public class FacturaDto {

    private Long idFactura;
    private Date fechaFacturacion;
    private Long metodoDePago;
    private double montoPagado;
    private double total;
    private double vuelto;
    private double descuento;
    private double impuestoVenta;
    private double impuestoServicio;
    private String nombreCliente;
    private Boolean modificado;
    private CajaDto idCajaDto;
    private OrdenDto idOrdenDto;

    public FacturaDto() {
        this.modificado = false;
    }

    public FacturaDto(Factura factura) {
        this();
        this.idFactura = factura.getIdFactura();
        this.fechaFacturacion = factura.getFechaFacturacion();
        this.metodoDePago = factura.getMetodoDePago();
        this.montoPagado = factura.getMontoPagado();
        this.total = factura.getTotal();
        this.vuelto = factura.getVuelto();
        this.descuento = factura.getDescuento();
        this.impuestoVenta = factura.getImpuestoVenta();
        this.impuestoServicio = factura.getImpuestoServicio();
        this.nombreCliente = factura.getNombreCliente();
        
        if (factura.getIdCaja()!= null) {
        this.idCajaDto = new CajaDto(factura.getIdCaja());
        } 
        if (factura.getIdOrden()!= null) {
        this.idOrdenDto = new OrdenDto(factura.getIdOrden());
        }
    }

    public Long getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Long idFactura) {
        this.idFactura = idFactura;
    }

    public Date getFechaFacturacion() {
        return fechaFacturacion;
    }

    public void setFechaFacturacion(Date fechaFacturacion) {
        this.fechaFacturacion = fechaFacturacion;
    }

    public Long getMetodoDePago() {
        return metodoDePago;
    }

    public void setMetodoDePago(Long metodoDePago) {
        this.metodoDePago = metodoDePago;
    }

    public double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(double montoPagado) {
        this.montoPagado = montoPagado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getVuelto() {
        return vuelto;
    }

    public void setVuelto(double vuelto) {
        this.vuelto = vuelto;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getImpuestoVenta() {
        return impuestoVenta;
    }

    public void setImpuestoVenta(double impuestoVenta) {
        this.impuestoVenta = impuestoVenta;
    }

    public double getImpuestoServicio() {
        return impuestoServicio;
    }

    public void setImpuestoServicio(double impuestoServicio) {
        this.impuestoServicio = impuestoServicio;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    
    
    
    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public CajaDto getIdCajaDto() {
        return idCajaDto;
    }

    public void setIdCajaDto(CajaDto idCajaDto) {
        this.idCajaDto = idCajaDto;
    }

    public OrdenDto getIdOrdenDto() {
        return idOrdenDto;
    }

    public void setIdOrdenDto(OrdenDto idOrdenDto) {
        this.idOrdenDto = idOrdenDto;
    }

}
