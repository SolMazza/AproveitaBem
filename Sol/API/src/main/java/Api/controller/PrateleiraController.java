package Api.controller;

import Api.model.*;
import Api.repository.UsuarioRepository;
import Api.service.PrateleiraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/prateleiras")
public class PrateleiraController {
    private PrateleiraService prateleiraService;
    private UsuarioRepository usuarioRepository;

    public PrateleiraController(PrateleiraService prateleiraService, UsuarioRepository usuarioRepository) {
        this.prateleiraService = prateleiraService;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Prateleira> cadastrar(
            @RequestParam String email,
            @RequestBody Prateleira prateleira) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return ResponseEntity.ok(prateleiraService.cadastrar(usuario.getId(), prateleira));
    }



    @PostMapping("/{prateleiraId}/produtos/{produtoId}")
    public ResponseEntity<Prateleira> adicionarProduto(
            @PathVariable Long prateleiraId,
            @PathVariable Long produtoId) {

        // Verificação adicional
        if (prateleiraId == null || produtoId == null) {
            throw new IllegalArgumentException("IDs não podem ser nulos");
        }

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
