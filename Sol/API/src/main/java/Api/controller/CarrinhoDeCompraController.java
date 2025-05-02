package Api.controller;

import Api.model.CarrinhoDeCompra;
import Api.model.ItemLista;
import Api.model.Usuario;
import Api.repository.UsuarioRepository;
import Api.service.CarrinhoDeCompraService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<List<ItemLista>> listarItens(@RequestParam Long carrinhoId) {
        return ResponseEntity.ok(carrinhoService.listarItens(carrinhoId));
    }
    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> carrinhoCompra(@RequestParam String email) {
        CarrinhoDeCompra carrinho = carrinhoService.getCarrinhoByUsuarioEmail(email);

        return ResponseEntity.ok(Map.of(
                "id", carrinho.getId(),
                "itens", carrinho.getItens()
        ));
    }



    @PostMapping("/itens")
    public ResponseEntity<Map<String, Object>> adicionarItem(@RequestParam String email,
                                                             @RequestBody @Valid ItemLista itemRequest) {
        if (itemRequest.getQuantidade() <= 0) {
            return ResponseEntity.badRequest().build();
        }

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        ItemLista itemSalvo = carrinhoService.adicionarItemPorUsuario(usuario.getId(), itemRequest);

        return ResponseEntity.ok(Map.of(
                "id", itemSalvo.getId(),
                "nome", itemSalvo.getNome(),
                "quantidade", itemSalvo.getQuantidade()
        ));
    }

    @PostMapping("/criar")
    public ResponseEntity<CarrinhoDeCompra> criarCarrinho(@RequestParam String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return ResponseEntity.ok(carrinhoService.pegarOuCriarCarrinhoPeloUsuario(usuario.getId()));
    }


    @DeleteMapping("/itens/{itemId}")
    public ResponseEntity<Void> removerItem(@RequestParam Long carrinhoId,
                                            @PathVariable Long itemId) {
        try {
            carrinhoService.removerItem(carrinhoId, itemId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}