package Api.controller;

import Api.model.ItemLista;
import Api.model.Usuario;
import Api.repository.UsuarioRepository;
import Api.service.CarrinhoDeCompraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/carrinho")
public class CarrinhoDeCompraController {

    private final CarrinhoDeCompraService carrinhoService;
    private final UsuarioRepository usuarioRepository;

    public CarrinhoDeCompraController(CarrinhoDeCompraService carrinhoService, UsuarioRepository usuarioRepository) {
        this.carrinhoService = carrinhoService;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/itens")
    public ResponseEntity<List<ItemLista>> listarItens(@RequestHeader("App-Key") String appKey,
                                                       @RequestParam String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return ResponseEntity.ok(carrinhoService.listarItensPorUsuario(usuario.getId()));
    }

    @PostMapping("/itens")
    public ResponseEntity<ItemLista> adicionarItem(@RequestHeader("App-Key") String appKey,
                                                   @RequestParam String email,
                                                   @RequestBody @Valid ItemLista itemRequest) {
        if (itemRequest.getQuantidade() <= 0) {
            return ResponseEntity.badRequest().build();
        }
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return ResponseEntity.ok(carrinhoService.adicionarItemPorUsuario(usuario.getId(), itemRequest));
    }

    @DeleteMapping("/itens/{itemId}")
    public ResponseEntity<Void> removerItem(@RequestHeader("App-Key") String appKey,
                                            @RequestParam String email,
                                            @PathVariable Long itemId) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        carrinhoService.removerItemPorUsuario(usuario.getId(), itemId);
        return ResponseEntity.noContent().build();
    }
}