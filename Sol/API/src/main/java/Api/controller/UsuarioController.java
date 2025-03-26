package Api.controller;

import Api.dto.LoginDto;
import Api.dto.UsuarioRequestDto;
import Api.dto.UsuarioResponseDto;
import Api.model.Categoria;
import Api.model.Prateleira;
import Api.model.Produto;
import Api.model.Usuario;
import Api.service.CategoriaService;
import Api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final CategoriaService categoriaService;




    public UsuarioController(UsuarioService usuarioService, CategoriaService categoriaService) {
        this.usuarioService = usuarioService;
        this.categoriaService = categoriaService;
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


    @GetMapping("/{email}/BuscarPrateleiras")
    public List<Prateleira> buscarPrateleira(@PathVariable String email){
        List<Prateleira> lista = usuarioService.buscarTodasPrateleiras(email);
        return lista;
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioResponseDto> cadastrar(@Valid @RequestBody UsuarioRequestDto usuarioRequestDto) {
        return ResponseEntity.ok(usuarioService.cadastrar(usuarioRequestDto));
    }



    @PostMapping("/login")
    public ResponseEntity<UsuarioResponseDto> login(@Valid @RequestBody LoginDto loginDto) {
        Usuario usuario = usuarioService.autenticar(loginDto.email());
        if (usuario != null) {
            return ResponseEntity.ok(new UsuarioResponseDto(
                    usuario.getId(),
                    usuario.getEmail(),
                    usuario.getSenha(),
                    usuario.getNomeCompleto()
            ));
        }
        return ResponseEntity.status(401).build();
    }

    @GetMapping("/usuario/me")
    public ResponseEntity<Long> getUsuarioId(@AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok(usuario.getId());
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<String> deletarPeloEmail(@RequestParam String email) {
        usuarioService.deletar(email);
        return ResponseEntity.ok("Usuário deletado com sucesso");
    }
}
