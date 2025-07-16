package isil.eva.pe.controladores.apirest;


import isil.eva.pe.jpa.modelo.Usuario;
import isil.eva.pe.jpa.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*") // Permite todas las conexiones CORS
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuarios")
    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll(); // Devuelve todos los usuarios con todos los campos
    }
}