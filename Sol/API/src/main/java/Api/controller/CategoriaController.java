package Api.controller;

import Api.model.Categoria;
import Api.model.Usuario;
import Api.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@Controller
@RequestMapping(path = "categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Categoria> cadastrar(
            @AuthenticationPrincipal Usuario usuario,
            @RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.cadastrar(usuario.getId(), categoria));
    }

    @GetMapping("/minhas-categorias")
    public ResponseEntity<List<Categoria>> listarMinhasCategorias(
            @RequestParam String email) {
        return ResponseEntity.ok(categoriaService.listarPorUsuarioEmail(email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPeloId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.buscarPeloId(id));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Categoria> editar(
            @PathVariable Long id,
            @RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.editar(id, categoria));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        categoriaService.deletarPeloId(id);
        return ResponseEntity.noContent().build();
    }
}