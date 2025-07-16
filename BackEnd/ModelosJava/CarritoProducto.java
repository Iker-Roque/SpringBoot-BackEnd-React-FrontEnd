package isil.eva.pe.jpa.modelo;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name = "carrito_productos")
public class CarritoProducto implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private CarritoProductoId id;

    @ManyToOne
    @MapsId("carrito")
    @JoinColumn(name = "id_carrito")
    private Carrito carrito;

    @ManyToOne
    @MapsId("producto")
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    public CarritoProducto() {
    }

    public CarritoProducto(Carrito carrito, Producto producto, Integer cantidad) {
        this.carrito = carrito;
        this.producto = producto;
        this.id = new CarritoProductoId(carrito.getId_carrito(), producto.getIdProducto());
        this.cantidad = cantidad;
    }

    public CarritoProductoId getId() {
        return id;
    }

    public void setId(CarritoProductoId id) {
        this.id = id;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
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

    @Override
    public String toString() {
        return "CarritoProducto{" +
               "carritoId=" + (carrito != null ? carrito.getId_carrito() : "null") +
               ", productoId=" + (producto != null ? producto.getIdProducto() : "null") +
               ", cantidad=" + cantidad +
               '}';
    }
}
