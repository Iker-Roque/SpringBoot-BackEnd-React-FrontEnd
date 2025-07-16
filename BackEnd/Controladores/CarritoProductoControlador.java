package isil.eva.pe.controladores.apirest;

import isil.eva.pe.excepciones.RecursoNoEncontradoException;
import isil.eva.pe.jpa.modelo.CarritoProducto;
import isil.eva.pe.jpa.modelo.CarritoProductoId;
import isil.eva.pe.jpa.repositorios.CarritoProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class CarritoProductoControlador {

    @Autowired
    private CarritoProductoRepositorio carritoProductoRepositorio;

    // GET: Obtener todos los productos del carrito
    @GetMapping("/carrito-productos")
    public List<CarritoProducto> obtenerTodos() {
        return carritoProductoRepositorio.findAll();
    }

    // GET: Obtener un producto del carrito por ID compuesto
    @GetMapping("/carrito-productos/{idCarrito}/{idProducto}")
    public ResponseEntity<CarritoProducto> obtenerPorId(
            @PathVariable Integer idCarrito,
            @PathVariable Integer idProducto) throws RecursoNoEncontradoException {

        CarritoProductoId id = new CarritoProductoId(idCarrito, idProducto);
        CarritoProducto cp = carritoProductoRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el producto en el carrito"));

        return ResponseEntity.ok(cp);
    }

    // POST: Agregar un producto al carrito
    @PostMapping("/carrito-productos")
    public CarritoProducto crear(@RequestBody CarritoProducto cp) {
        if (cp.getCarrito() != null && cp.getProducto() != null) {
            CarritoProductoId id = new CarritoProductoId(
                    cp.getCarrito().getId_carrito(),
                    cp.getProducto().getIdProducto()
            );
            cp.setId(id);
        }

        return carritoProductoRepositorio.save(cp);
    }

    // PUT: Actualizar la cantidad de un producto en el carrito
    @PutMapping("/carrito-productos/{idCarrito}/{idProducto}")
    public ResponseEntity<CarritoProducto> actualizar(
            @PathVariable Integer idCarrito,
            @PathVariable Integer idProducto,
            @RequestBody CarritoProducto detalles) throws RecursoNoEncontradoException {

        CarritoProductoId id = new CarritoProductoId(idCarrito, idProducto);
        CarritoProducto existente = carritoProductoRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el producto en el carrito"));

        existente.setCantidad(detalles.getCantidad());
        return ResponseEntity.ok(carritoProductoRepositorio.save(existente));
    }

    // DELETE: Eliminar un producto del carrito
    @DeleteMapping("/carrito-productos/{idCarrito}/{idProducto}")
    public ResponseEntity<Map<String, Boolean>> eliminar(
            @PathVariable Integer idCarrito,
            @PathVariable Integer idProducto) throws RecursoNoEncontradoException {

        CarritoProductoId id = new CarritoProductoId(idCarrito, idProducto);
        CarritoProducto cp = carritoProductoRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No se encontró el producto con ID " + idProducto + " en el carrito " + idCarrito));

        carritoProductoRepositorio.delete(cp);

        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
