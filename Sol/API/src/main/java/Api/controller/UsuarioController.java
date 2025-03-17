package Api.controller;

import Api.dto.LoginDto;
import Api.dto.UsuarioRequestDto;
import Api.dto.UsuarioResponseDto;
import Api.model.Usuario;
import Api.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "usuarios")
public class
UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioResponseDto usuarioResponseDto;


    public UsuarioController(UsuarioService usuarioService, UsuarioResponseDto usuarioResponseDto) {
        this.usuarioService = usuarioService;
        this.usuarioResponseDto = usuarioResponseDto;
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

    @PutMapping("/editarSenha")
    public ResponseEntity<String> editarSenha(@RequestBody LoginDto loginDto) {
        usuarioService.editarSenha(loginDto.email(), loginDto.senha());
        return ResponseEntity.ok("Senha atualizada com sucesso!");
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioResponseDto> cadastrar(@RequestBody UsuarioRequestDto usuarioRequestDto) {
        return ResponseEntity.ok(usuarioService.cadastrar(usuarioRequestDto));
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        Usuario usuario = usuarioService.autenticar(loginDto.email(), loginDto.senha());
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
