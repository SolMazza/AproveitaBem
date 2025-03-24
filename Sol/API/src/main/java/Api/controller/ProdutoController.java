package Api.controller;

import Api.model.Produto;
import Api.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "produtos")
public class ProdutoController {


    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Produto> adicionar(@RequestBody Produto produto) {
        return ResponseEntity.ok(produtoService.adicionar(produto));
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

    @PutMapping("/editar/{id}")
    public ResponseEntity<Produto> editar(
            @PathVariable Long id,
            @RequestBody Produto produto) {
        return ResponseEntity.ok(produtoService.editar(id, produto));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletarPeloId(id);
        return ResponseEntity.noContent().build();
    }

}
