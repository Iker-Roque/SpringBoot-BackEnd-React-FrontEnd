package isil.eva.pe.controladores.apirest;

import isil.eva.pe.excepciones.RecursoNoEncontradoException;
import isil.eva.pe.jpa.modelo.Administrador;
import isil.eva.pe.jpa.repositorios.AdministradorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class AdministradorControlador {

    @Autowired
    private AdministradorRepositorio administradorRepositorio;

    @GetMapping("/administradores")
    public List<Administrador> obtenerTodos() {
        return administradorRepositorio.findAll();
    }

    @GetMapping("/administradores/{id}")
    public ResponseEntity<Administrador> obtenerPorId(@PathVariable Integer id)
            throws RecursoNoEncontradoException {
        Administrador admin = administradorRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el administrador con ID: " + id));
        return ResponseEntity.ok(admin);
    }

    @PostMapping("/administradores")
    public Administrador crear(@RequestBody Administrador administrador) {
        return administradorRepositorio.save(administrador);
    }

    @PutMapping("/administradores/{id}")
    public ResponseEntity<Administrador> actualizar(@PathVariable Integer id,
                                                    @RequestBody Administrador detalles)
            throws RecursoNoEncontradoException {
        Administrador admin = administradorRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el administrador con ID: " + id));

        admin.setFechaContratacion(detalles.getFechaContratacion());
        admin.setUsuario(detalles.getUsuario());

        return ResponseEntity.ok(administradorRepositorio.save(admin));
    }

    @DeleteMapping("/administradores/{id}")
    public Map<String, Boolean> eliminar(@PathVariable Integer id)
            throws RecursoNoEncontradoException {
        Administrador admin = administradorRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el administrador con ID: " + id));

        administradorRepositorio.delete(admin);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return response;
    }
}
