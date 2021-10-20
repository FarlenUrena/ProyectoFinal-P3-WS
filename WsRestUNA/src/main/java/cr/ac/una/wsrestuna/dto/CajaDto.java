/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.dto;

import cr.ac.una.wsrestuna.model.Caja;
import cr.ac.una.wsrestuna.model.Empleado;
import cr.ac.una.wsrestuna.model.Factura;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Farlen
 */
public class CajaDto {

    private Long idCaja;
    private float saldoEfectivo;
    private float saldoTarjeta;
    private float saldoEfectivoCierre;
    private float saldoTarjetaCierre;
    private Date fechaCierre;
    private Empleado idEmpleado;
    private List<Factura> facturaList;
   
    public CajaDto(Caja caja) {
        this.idCaja = caja.getIdCaja();
        this.saldoEfectivo = caja.getSaldoEfectivo();
        this.saldoTarjeta = caja.getSaldoTarjeta();
        this.saldoEfectivoCierre = caja.getSaldoEfectivoCierre();
        this.saldoTarjetaCierre = caja.getSaldoTarjetaCierre();
        this.fechaCierre = caja.getFechaCierre();
        this.idEmpleado = caja.getIdEmpleado();
        this.facturaList = caja.getFacturaList();
    }

    public Long getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(Long idCaja) {
        this.idCaja = idCaja;
    }

    public float getSaldoEfectivo() {
        return saldoEfectivo;
    }

    public void setSaldoEfectivo(float saldoEfectivo) {
        this.saldoEfectivo = saldoEfectivo;
    }

    public float getSaldoTarjeta() {
        return saldoTarjeta;
    }

    public void setSaldoTarjeta(float saldoTarjeta) {
        this.saldoTarjeta = saldoTarjeta;
    }

    public float getSaldoEfectivoCierre() {
        return saldoEfectivoCierre;
    }

    public void setSaldoEfectivoCierre(float saldoEfectivoCierre) {
        this.saldoEfectivoCierre = saldoEfectivoCierre;
    }

    public float getSaldoTarjetaCierre() {
        return saldoTarjetaCierre;
    }

    public void setSaldoTarjetaCierre(float saldoTarjetaCierre) {
        this.saldoTarjetaCierre = saldoTarjetaCierre;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }
    
    
}
