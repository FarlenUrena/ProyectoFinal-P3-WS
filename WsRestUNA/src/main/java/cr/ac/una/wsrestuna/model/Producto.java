/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.wsrestuna.model;

import cr.ac.una.wsrestuna.dto.ProductoDto;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
//import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Farlen
 */
@Entity
@Table(name = "PRODUCTO")
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findByIdProducto", query = "SELECT p FROM Producto p WHERE p.idProducto = :idProducto"),
    @NamedQuery(name = "Producto.findByNombre", query = "SELECT p FROM Producto p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Producto.findByNombreCorto", query = "SELECT p FROM Producto p WHERE p.nombreCorto = :nombreCorto"),
    @NamedQuery(name = "Producto.findByPrecio", query = "SELECT p FROM Producto p WHERE p.precio = :precio"),
    @NamedQuery(name = "Producto.findByGrupo", query = "SELECT p FROM Producto p WHERE p.grupo = :grupo"),
    @NamedQuery(name = "Producto.findByEsAccesoRapido", query = "SELECT p FROM Producto p WHERE p.esAccesoRapido = :esAccesoRapido"),
    @NamedQuery(name = "Producto.findByVentasTotales", query = "SELECT p FROM Producto p WHERE p.ventasTotales = :ventasTotales")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
//    @NotNull
    @Column(name = "ID_PRODUCTO")
    private Long idProducto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NOMBRE_CORTO")
    private String nombreCorto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GRUPO")
    private int grupo;
    @Column(name = "ES_ACCESO_RAPIDO")
    private int esAccesoRapido;
    @Column(name = "VENTAS_TOTALES")
    private int ventasTotales;

    
    @Basic(optional = false)
//    @NotNull
    @Column(name = "PRECIO")
    private float precio;
    @OneToMany(mappedBy = "idProducto", fetch = FetchType.LAZY)
    private List<Productopororden> productoporordenList;

    public Producto() {
    }

    public Producto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Producto(Long idProducto, String nombre, String nombreCorto, float precio, int grupo) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.nombreCorto = nombreCorto;
        this.precio = precio;
        this.grupo = grupo;
    }

    public Producto(ProductoDto productoDto) {
        this.idProducto = productoDto.getIdProducto();
        actualizarProducto(productoDto);
    }

        public void actualizarProducto(ProductoDto productoDto) {
    
        this.nombre = productoDto.getNombre();
        this.nombreCorto = productoDto.getNombreCorto();
        this.precio = productoDto.getPrecio();
        this.grupo = productoDto.getGrupo();
        this.esAccesoRapido = productoDto.getEsAccesoRapido();
        this.ventasTotales = productoDto.getVentasTotales();
        this.productoporordenList = productoDto.getProductoporordenList();
    }
    
    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }


    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }


    public int getEsAccesoRapido() {
        return esAccesoRapido;
    }

    public void setEsAccesoRapido(int esAccesoRapido) {
        this.esAccesoRapido = esAccesoRapido;
    }

    public int getVentasTotales() {
        return ventasTotales;
    }

    public void setVentasTotales(int ventasTotales) {
        this.ventasTotales = ventasTotales;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }    

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }



    
}
