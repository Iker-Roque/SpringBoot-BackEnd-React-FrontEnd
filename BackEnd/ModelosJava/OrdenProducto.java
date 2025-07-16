package isil.eva.pe.jpa.modelo;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name = "ordenes_productos")
public class OrdenProducto implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private OrdenProductoId id;

    @ManyToOne
    @MapsId("orden")
    @JoinColumn(name = "id_orden")
    private Orden orden;

    @ManyToOne
    @MapsId("producto")
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "precio", nullable = false)
    private Double precio;

    public OrdenProducto() {
    }

    public OrdenProducto(Orden orden, Producto producto, Integer cantidad, Double precio) {
        this.orden = orden;
        this.producto = producto;
        this.id = new OrdenProductoId(orden.getId_orden(), producto.getIdProducto());
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public OrdenProductoId getId() {
        return id;
    }

    public void setId(OrdenProductoId id) {
        this.id = id;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "OrdenProducto{" +
               "ordenId=" + (orden != null ? orden.getId_orden() : "null") +
               ", productoId=" + (producto != null ? producto.getIdProducto() : "null") +
               ", cantidad=" + cantidad +
               ", precio=" + precio +
               '}';
    }
}
