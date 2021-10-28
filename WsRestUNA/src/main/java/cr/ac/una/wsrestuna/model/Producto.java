/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.model;

import cr.ac.una.wsrestuna.dto.ProductoDto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "PRODUCTO",schema = "RESTUNA")
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findByIdProducto", query = "SELECT p FROM Producto p WHERE p.idProducto = :idProducto"),
    @NamedQuery(name = "Producto.findByNombre", query = "SELECT p FROM Producto p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Producto.findByNombreCorto", query = "SELECT p FROM Producto p WHERE p.nombreCorto = :nombreCorto"),
    @NamedQuery(name = "Producto.findByPrecio", query = "SELECT p FROM Producto p WHERE p.precio = :precio"),
    @NamedQuery(name = "Producto.findByEsAccesoRapido", query = "SELECT p FROM Producto p WHERE p.esAccesoRapido = :esAccesoRapido"),
    @NamedQuery(name = "Producto.findByVentasTotales", query = "SELECT p FROM Producto p WHERE p.ventasTotales = :ventasTotales"),
//    @NamedQuery(name = "Producto.findByGrupo", query = "SELECT p FROM Producto p WHERE p.ventasTotales = :ventasTotales")
})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "PRODUCTO_ID_GENERATOR", sequenceName = "RESTUNA.SEQ_ID_PRODUCTO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCTO_ID_GENERATOR")
    @Basic(optional = false)
//    @NotNull
    @Column(name = "ID_PRODUCTO")
    private Long idProducto;
    @Basic(optional = false)
//    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
//    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "NOMBRE_CORTO")
    private String nombreCorto;
    @Basic(optional = false)
//    @NotNull
    @Column(name = "PRECIO")
    private double precio;
    @Basic(optional = false)
//    @NotNull
    @Column(name = "ES_ACCESO_RAPIDO")
    private Long esAccesoRapido;
    @Basic(optional = false)
//    @NotNull
    @Column(name = "VENTAS_TOTALES")
    private Long ventasTotales;
    @Lob
    @Column(name = "IMAGEN")
    private byte[] imagen;
    @JoinColumn(name = "ID_GRUPO", referencedColumnName = "ID_GRUPO")
    @ManyToOne(fetch = FetchType.LAZY)
    private Grupo idGrupo;
    
    @OneToMany(mappedBy = "idProducto", fetch = FetchType.LAZY)
    private List<Productopororden> productoporordenList;

    public Producto() {
    }

    public Producto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Producto(Long idProducto, String nombre, String nombreCorto, double precio, Long esAccesoRapido, Long ventasTotales) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.nombreCorto = nombreCorto;
        this.precio = precio;
        this.esAccesoRapido = esAccesoRapido;
        this.ventasTotales = ventasTotales;
    }

    public Producto(ProductoDto productoDto) {
        this.idProducto = productoDto.getIdProducto();
        actualizarProducto(productoDto);
    }

        public void actualizarProducto(ProductoDto productoDto) {
    
        this.nombre = productoDto.getNombre();
        this.nombreCorto = productoDto.getNombreCorto();
        this.precio = productoDto.getPrecio();
        this.esAccesoRapido = productoDto.getEsAccesoRapido();
        this.ventasTotales = productoDto.getVentasTotales();
        this.imagen = productoDto.getImagen();
        this.idGrupo = productoDto.getIdGrupo();
    }
    
    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Long getEsAccesoRapido() {
        return esAccesoRapido;
    }

    public void setEsAccesoRapido(Long esAccesoRapido) {
        this.esAccesoRapido = esAccesoRapido;
    }

    public Long getVentasTotales() {
        return ventasTotales;
    }

    public void setVentasTotales(Long ventasTotales) {
        this.ventasTotales = ventasTotales;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public Grupo getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Grupo idGrupo) {
        this.idGrupo = idGrupo;
    }

    public List<Productopororden> getProductoporordenList() {
        return productoporordenList;
    }

    public void setProductoporordenList(List<Productopororden> productoporordenList) {
        this.productoporordenList = productoporordenList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wsrestuna.model.Producto[ idProducto=" + idProducto + " ]";
    }
    
}
