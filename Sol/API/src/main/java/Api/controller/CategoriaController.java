package Api.controller;

import Api.model.Categoria;
import Api.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping("/cadastrar/{usuarioId}")
    public ResponseEntity<Categoria> cadastrar(
            @PathVariable Long usuarioId,
            @RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.cadastrar(usuarioId, categoria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPeloId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.buscarPeloId(id));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Categoria>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(categoriaService.listarPorUsuario(usuarioId));
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