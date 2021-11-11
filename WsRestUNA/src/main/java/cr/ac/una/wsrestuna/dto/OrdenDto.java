/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.dto;

import cr.ac.una.wsrestuna.model.Orden;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Farlen
 */
public class OrdenDto {

    private Long idOrden;
    private Date fechaCreacion;
    private String nombreCliente;
    private Long esEstado;
    private ElementodeseccionDto idElementodeseccionDto;
    private EmpleadoDto idEmpleadoDto;
    private Boolean modificado;
    private List<ProductoporordenDto> productosporordenDto;
    private List<ProductoporordenDto> productosporordenElimindosDto;
    private List<FacturaDto> facturasDto;
    private List<FacturaDto> facturasEliminadasDto;

    public OrdenDto() {
        this.modificado = false;
        this.fechaCreacion = new Date();
        this.productosporordenDto = new ArrayList<>();
        this.productosporordenElimindosDto = new ArrayList<>();
        this.facturasDto = new ArrayList<>();
        this.facturasEliminadasDto = new ArrayList<>();
    }

    public OrdenDto(Orden orden) {
        this();
        this.idOrden = orden.getIdOrden();
        this.fechaCreacion = orden.getFechaCreacion();
        this.nombreCliente = orden.getNombreCliente();
        this.esEstado = orden.getEsEstado();

        if (orden.getIdElemento() != null) {
            this.idElementodeseccionDto = new ElementodeseccionDto(orden.getIdElemento());
        } else {
            this.idElementodeseccionDto = new ElementodeseccionDto();
        }
        if (orden.getIdEmpleado() != null) {
            this.idEmpleadoDto = new EmpleadoDto(orden.getIdEmpleado());
        } else {
            this.idEmpleadoDto = new EmpleadoDto();
        }
    }

    public Long getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Long idOrden) {
        this.idOrden = idOrden;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Long getEsEstado() {
        return esEstado;
    }

    public void setEsEstado(Long esEstado) {
        this.esEstado = esEstado;
    }

    public ElementodeseccionDto getIdElementodeseccionDto() {
        return idElementodeseccionDto;
    }

    public void setIdElementodeseccionDto(ElementodeseccionDto idElementodeseccionDto) {
        this.idElementodeseccionDto = idElementodeseccionDto;
    }

    public EmpleadoDto getIdEmpleadoDto() {
        return idEmpleadoDto;
    }

    public void setIdEmpleadoDto(EmpleadoDto idEmpleaadoDto) {
        this.idEmpleadoDto = idEmpleaadoDto;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public List<ProductoporordenDto> getProductosporordenDto() {
        return productosporordenDto;
    }

    public void setProductosporordenDto(List<ProductoporordenDto> productosporordenDto) {
        this.productosporordenDto = productosporordenDto;
    }

    public List<ProductoporordenDto> getProductosporordenElimindosDto() {
        return productosporordenElimindosDto;
    }

    public void setProductosporordenElimindosDto(List<ProductoporordenDto> productosporordenElimindosDto) {
        this.productosporordenElimindosDto = productosporordenElimindosDto;
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
