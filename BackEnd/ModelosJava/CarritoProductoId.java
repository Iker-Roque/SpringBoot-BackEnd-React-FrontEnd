package isil.eva.pe.jpa.modelo;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class CarritoProductoId implements Serializable {

    private Integer carrito;
    private Integer producto;

    public CarritoProductoId() {
    }

    public CarritoProductoId(Integer carrito, Integer producto) {
        this.carrito = carrito;
        this.producto = producto;
    }

    public Integer getCarrito() {
        return carrito;
    }

    public void setCarrito(Integer carrito) {
        this.carrito = carrito;
    }

    public Integer getProducto() {
        return producto;
    }

    public void setProducto(Integer producto) {
        this.producto = producto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarritoProductoId)) return false;
        CarritoProductoId that = (CarritoProductoId) o;
        return Objects.equals(carrito, that.carrito) &&
               Objects.equals(producto, that.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carrito, producto);
    }
}
