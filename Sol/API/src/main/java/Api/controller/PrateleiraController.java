package Api.controller;

import Api.model.*;
import Api.service.PrateleiraService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/prateleiras")
public class PrateleiraController {
    private PrateleiraService prateleiraService;

    public PrateleiraController(PrateleiraService prateleiraService) {
        this.prateleiraService = prateleiraService;
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<Prateleira> cadastrar(
            @AuthenticationPrincipal Usuario usuario,
            @RequestBody Prateleira prateleira) {
        return ResponseEntity.ok(prateleiraService.cadastrar(usuario.getId(), prateleira));
    }



    @PostMapping("/{prateleiraId}/produtos/{produtoId}")
    public ResponseEntity<Prateleira> adicionarProduto(
            @PathVariable Long prateleiraId,
            @PathVariable Long produtoId) {
        Prateleira prateleira = prateleiraService.adicionarProduto(prateleiraId, produtoId);
        return ResponseEntity.ok(prateleira);
    }

    @DeleteMapping("/{prateleiraId}/produtos/{produtoId}")
    public ResponseEntity<Void> removerProduto(
            @PathVariable Long prateleiraId,
            @PathVariable Long produtoId) {
        prateleiraService.removerProduto(prateleiraId, produtoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{prateleiraId}/produtos")
    public ResponseEntity<List<Produto>> listarProdutos(@PathVariable Long prateleiraId) {
        List<Produto> produtos = prateleiraService.buscarTodosProdutos(prateleiraId);
        return ResponseEntity.ok(produtos);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        prateleiraService.excluirPrateleira(id);
        return ResponseEntity.noContent().build();
    }

}
