package isil.eva.pe.controladores.apirest;

import isil.eva.pe.excepciones.RecursoNoEncontradoException;
import isil.eva.pe.jpa.modelo.Cliente;
import isil.eva.pe.jpa.repositorios.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class ClienteControlador {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @GetMapping("/clientes")
    public List<Cliente> obtenerTodos() {
        return clienteRepositorio.findAll();
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> obtenerPorId(@PathVariable Integer id)
            throws RecursoNoEncontradoException {
        Cliente cliente = clienteRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado con ID: " + id));
        return ResponseEntity.ok(cliente);
    }

    @PostMapping("/clientes")
    public Cliente crear(@RequestBody Cliente cliente) {
        return clienteRepositorio.save(cliente);
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente> actualizar(@PathVariable Integer id,
                                              @RequestBody Cliente detalles)
            throws RecursoNoEncontradoException {
        Cliente cliente = clienteRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado con ID: " + id));

        cliente.setDireccion(detalles.getDireccion());
        cliente.setFechaRegistro(detalles.getFechaRegistro());
        cliente.setUsuario(detalles.getUsuario());

        return ResponseEntity.ok(clienteRepositorio.save(cliente));
    }

    @DeleteMapping("/clientes/{id}")
    public Map<String, Boolean> eliminar(@PathVariable Integer id)
            throws RecursoNoEncontradoException {
        Cliente cliente = clienteRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado con ID: " + id));

        clienteRepositorio.delete(cliente);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return response;
    }
}
