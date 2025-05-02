package Api.controller;

import Api.model.Produto;
import Api.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPeloId(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarPeloId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Produto>> listarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(produtoService.listarPorNome(nome));
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Produto>> listarPorCategoria(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(produtoService.listarPorCategoria(categoriaId));
    }

    @PostMapping
    public ResponseEntity<Produto> adicionar(@RequestBody Produto produto) {
        return ResponseEntity.ok(produtoService.adicionar(produto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> editar(@PathVariable Long id,
                                          @RequestBody Produto produto) {
        return ResponseEntity.ok(produtoService.editar(id, produto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletarPeloId(id);
        return ResponseEntity.noContent().build();
    }
}


