/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.model;

import cr.ac.una.wsrestuna.dto.OrdenDto;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Farlen
 */
@Entity
@Table(name = "ORDEN", schema = "RESTUNA")
@NamedQueries({
    @NamedQuery(name = "Orden.findAll", query = "SELECT o FROM Orden o"),
    @NamedQuery(name = "Orden.findByIdOrden", query = "SELECT o FROM Orden o WHERE o.idOrden = :idOrden"),
    @NamedQuery(name = "Orden.findByNombreCliente", query = "SELECT o FROM Orden o WHERE o.nombreCliente = :nombreCliente")})
public class Orden implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "ORDEN_ID_GENERATOR", sequenceName = "RESTUNA.SEQ_ID_ORDEN", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDEN_ID_GENERATOR")
    @Basic(optional = false)
//    @NotNull
    @Column(name = "ID_ORDEN")
    private Long idOrden;
    @Size(max = 40)
    @Column(name = "NOMBRE_CLIENTE")
    private String nombreCliente;
    @JoinColumn(name = "ID_ELEMENTO", referencedColumnName = "ID_ELEMENTO")
    @ManyToOne(fetch = FetchType.LAZY)
    private Elementodeseccion idElemento;
    @OneToMany(mappedBy = "idOrden", fetch = FetchType.LAZY)
    private List<Productopororden> productoporordenList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrden", fetch = FetchType.LAZY)
    private List<Factura> facturaList;

    public Orden() {
    }

    public Orden(Long idOrden) {
        this.idOrden = idOrden;
    }

    public Orden(OrdenDto ordenDto) {
        this.idOrden = ordenDto.getIdOrden();
        
    }
    public void actualizarOrden(OrdenDto ordenDto) {
        this.nombreCliente = ordenDto.getNombreCliente();
        this.idElemento = ordenDto.getIdElemento();
        this.productoporordenList = ordenDto.getProductoporordenList();
        this.facturaList = ordenDto.getFacturaList();
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrden != null ? idOrden.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orden)) {
            return false;
        }
        Orden other = (Orden) object;
        if ((this.idOrden == null && other.idOrden != null) || (this.idOrden != null && !this.idOrden.equals(other.idOrden))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wsrestuna.model.Orden[ idOrden=" + idOrden + " ]";
    }
    
}
