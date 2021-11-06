/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.dto;

import cr.ac.una.wsrestuna.model.Caja;
import cr.ac.una.wsrestuna.model.Empleado;
import cr.ac.una.wsrestuna.model.Factura;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Farlen
 */
public class CajaDto {

    private Long idCaja;
    private double saldoEfectivo;
    private double saldoTarjeta;
    private double saldoEfectivoCierre;
    private double saldoTarjetaCierre;
    private Date fechaCierre;
    private Long esActiva;
    private Boolean modificado;
    private EmpleadoDto idEmpleadoDto;
    private List<FacturaDto> facturasDto;
    private List<FacturaDto> facturasEliminadasDto;

    public CajaDto() {
        this.modificado = false;
        this.facturasDto = new ArrayList<>();
        this.facturasEliminadasDto = new ArrayList<>();
    }

    public CajaDto(Caja caja) {
        this();
        this.idCaja = caja.getIdCaja();
        this.saldoEfectivo = caja.getSaldoEfectivo();
        this.saldoTarjeta = caja.getSaldoTarjeta();
        this.saldoEfectivoCierre = caja.getSaldoEfectivoCierre();
        this.saldoTarjetaCierre = caja.getSaldoTarjetaCierre();
        this.fechaCierre = caja.getFechaCierre();
        this.esActiva = caja.getEsActiva();
        this.idEmpleadoDto = new EmpleadoDto(caja.getIdEmpleado());
    }

    public Long getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(Long idCaja) {
        this.idCaja = idCaja;
    }

    public double getSaldoEfectivo() {
        return saldoEfectivo;
    }

    public void setSaldoEfectivo(double saldoEfectivo) {
        this.saldoEfectivo = saldoEfectivo;
    }

    public double getSaldoTarjeta() {
        return saldoTarjeta;
    }

    public void setSaldoTarjeta(double saldoTarjeta) {
        this.saldoTarjeta = saldoTarjeta;
    }

    public double getSaldoEfectivoCierre() {
        return saldoEfectivoCierre;
    }

    public void setSaldoEfectivoCierre(double saldoEfectivoCierre) {
        this.saldoEfectivoCierre = saldoEfectivoCierre;
    }

    public double getSaldoTarjetaCierre() {
        return saldoTarjetaCierre;
    }

    public void setSaldoTarjetaCierre(double saldoTarjetaCierre) {
        this.saldoTarjetaCierre = saldoTarjetaCierre;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Long getEsActiva() {
        return esActiva;
    }

    public void setEsActiva(Long esActiva) {
        this.esActiva = esActiva;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean Modificado) {
        this.modificado = Modificado;
    }

    public EmpleadoDto getIdEmpleadoDto() {
        return idEmpleadoDto;
    }

    public void setIdEmpleadoDto(EmpleadoDto idEmpleadoDto) {
        this.idEmpleadoDto = idEmpleadoDto;
    }

    public List<FacturaDto> getFacturasDto() {
        return facturasDto;
    }

    public void setFacturasDto(List<FacturaDto> facturasDto) {
        this.facturasDto = facturasDto;
    }

    public List<FacturaDto> getFacturasEliminadasDto() {
        return facturasEliminadasDto;
    }

    public void setFacturasEliminadasDto(List<FacturaDto> facturasEliminadasDto) {
        this.facturasEliminadasDto = facturasEliminadasDto;
    }

}
