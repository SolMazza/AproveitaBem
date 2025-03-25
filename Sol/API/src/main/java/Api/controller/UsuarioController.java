package Api.controller;

import Api.dto.LoginDto;
import Api.dto.UsuarioRequestDto;
import Api.dto.UsuarioResponseDto;
import Api.model.Categoria;
import Api.model.Prateleira;
import Api.model.Produto;
import Api.model.Usuario;
import Api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:5173")
@Controller
@RequestMapping(path = "usuarios")
public class
UsuarioController {

    private final UsuarioService usuarioService;



    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<UsuarioResponseDto> buscarPorEmail(@RequestParam String email) {
        UsuarioResponseDto UsuarioResponseDto = usuarioService.busca(email);
        return ResponseEntity.ok(UsuarioResponseDto);
    }

    @PostMapping("/editar")
    public ResponseEntity<Usuario> editar(@RequestBody String email, UsuarioRequestDto usuarioRequestDto) {
        return ResponseEntity.ok(usuarioService.editar(email, usuarioRequestDto));
    }


    @GetMapping("/{usuarioEmail}/BuscarPrateleiras")
    public List<Prateleira> buscarListaItens(@RequestParam String email){
        List<Prateleira> lista = usuarioService.buscarTodasPrateleiras(email);
        return lista;
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioResponseDto> cadastrar(@Valid @RequestBody UsuarioRequestDto usuarioRequestDto) {
        return ResponseEntity.ok(usuarioService.cadastrar(usuarioRequestDto));
    }



    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDto loginDto) {
        Usuario usuario = usuarioService.autenticar(loginDto.email());
        if (usuario != null) {
            return ResponseEntity.ok("Login bem-sucedido!");
        }
        return ResponseEntity.status(401).body("Credenciais inválidas.");
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<String> deletarPeloEmail(@RequestParam String email) {
        usuarioService.deletar(email);
        return ResponseEntity.ok("Usuário deletado com sucesso");
    }
}
