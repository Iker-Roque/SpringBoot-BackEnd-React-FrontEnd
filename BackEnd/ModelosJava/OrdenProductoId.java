package isil.eva.pe.jpa.modelo;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class OrdenProductoId implements Serializable {

    private Integer orden;
    private Integer producto;

    public OrdenProductoId() {
    }

    public OrdenProductoId(Integer orden, Integer producto) {
        this.orden = orden;
        this.producto = producto;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
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
        if (!(o instanceof OrdenProductoId)) return false;
        OrdenProductoId that = (OrdenProductoId) o;
        return Objects.equals(orden, that.orden) &&
               Objects.equals(producto, that.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orden, producto);
    }
}
