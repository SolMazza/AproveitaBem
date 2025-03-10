package aproveite_bem.controller;

import aproveite_bem.dto.UsuarioDTO;
import aproveite_bem.model.Usuario;
import aproveite_bem.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "usuarios")
public class UsuarioController {

private final UsuarioService usuarioService;


    public UsuarioController(UsuarioService usuarioService, UsuarioDTO usuarioDTO) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<Usuario> buscarPorEmail(@RequestParam String email) {
        Usuario usuario = usuarioService.busca(email);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/editar")
    public ResponseEntity<Usuario> editar(@RequestBody String email, Usuario usuario) {
        return ResponseEntity.ok(usuarioService.editar(email, usuario));
    }

    @PutMapping("/editarSenha")
    public ResponseEntity<String> editarSenha(@RequestParam String email, @RequestParam String senha) {
        usuarioService.editarSenha(email, senha);
        return ResponseEntity.ok("Senha atualizada com sucesso!");
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.cadastrar(usuario));
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String senha) {
        Optional<Usuario> usuario = usuarioService.autenticar(email, senha);
        if (usuario.isPresent()) {
            return ResponseEntity.ok("Login bem-sucedido!");
        }
        return ResponseEntity.status(401).body("Credenciais inválidas.");
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<String> deletarPeloEmail(@RequestBody String email) {
        usuarioService.deletar(email);
        return ResponseEntity.ok("Usuário deletado com sucesso");
    }
}


