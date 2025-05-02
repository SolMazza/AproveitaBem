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

import java.util.Collections;
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
    @PostMapping("/login")
    public ResponseEntity<UsuarioResponseDto> login(@RequestBody LoginDto loginDto) {
        Usuario usuario = usuarioService.autenticar(loginDto.email());
        return usuario != null
                ? ResponseEntity.ok(new UsuarioResponseDto(usuario))
                : ResponseEntity.status(404).build();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioResponseDto> cadastrar(@RequestBody @Valid UsuarioRequestDto usuarioRequestDto) {
        return ResponseEntity.ok(usuarioService.cadastrar(usuarioRequestDto));
    }


    @GetMapping("/me")
    public ResponseEntity<UsuarioResponseDto> getUsuarioLogado(@RequestParam String email) {
        return ResponseEntity.ok(usuarioService.busca(email));
    }

    @GetMapping("/{email}/prateleiras")
    public ResponseEntity<List<Prateleira>> buscarPrateleiras(@PathVariable String email) {
        try {
            List<Prateleira> prateleiras = usuarioService.buscarTodasPrateleiras(email);

            if (prateleiras == null) {
                prateleiras = Collections.emptyList();
            }

            return ResponseEntity.ok(prateleiras);
        } catch (Exception e) {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }



    @PutMapping
    public ResponseEntity<Usuario> editar(@RequestParam String email,
                                          @RequestBody UsuarioRequestDto usuarioRequestDto) {
        return ResponseEntity.ok(usuarioService.editar(email, usuarioRequestDto));
    }

    @DeleteMapping
    public ResponseEntity<String> deletar(@RequestParam String email) {
        usuarioService.deletar(email);
        return ResponseEntity.ok("Usuário deletado com sucesso");
    }
}