/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.dto;

import cr.ac.una.wsrestuna.model.Caja;
import cr.ac.una.wsrestuna.model.Factura;
import cr.ac.una.wsrestuna.model.Orden;
import java.util.Date;

/**
 *
 * @author Farlen
 */
public class FacturaDto {

    private Long idFactura;
    private Date fechaFacturacion;
    private int metodoDePago;
    private float montoPagado;
    private float total;
    private Caja idCaja;
    private Orden idOrden;
   
    public FacturaDto(Factura factura) {
        this.idFactura = factura.getIdFactura();
        this.fechaFacturacion = factura.getFechaFacturacion();
        this.metodoDePago = factura.getMetodoDePago();
        this.montoPagado = factura.getMontoPagado();
        this.total = factura.getTotal();
        this.idCaja = factura.getIdCaja();
        this.idOrden = factura.getIdOrden();
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

    public int getMetodoDePago() {
        return metodoDePago;
    }

    public void setMetodoDePago(int metodoDePago) {
        this.metodoDePago = metodoDePago;
    }

    public float getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(float montoPagado) {
        this.montoPagado = montoPagado;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Caja getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(Caja idCaja) {
        this.idCaja = idCaja;
    }

    public Orden getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Orden idOrden) {
        this.idOrden = idOrden;
    }

}
