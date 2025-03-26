package Api.controller;

import Api.model.ItemLista;
import Api.service.CarrinhoDeCompraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/usuarios/{usuarioId}/carrinho")
public class CarrinhoDeCompraController {

    private final CarrinhoDeCompraService carrinhoService;

    public CarrinhoDeCompraController(CarrinhoDeCompraService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @GetMapping("/produtos")
    public ResponseEntity<List<ItemLista>> listarItensPorUsuario(
            @PathVariable Long usuarioId) {
        return ResponseEntity.ok(carrinhoService.listarItensPorUsuario(usuarioId));
    }

    @PostMapping("/produtos")
    public ResponseEntity<ItemLista> adicionarItemPorUsuario(
            @PathVariable Long usuarioId,
            @RequestBody ItemLista itemRequest) {
        return ResponseEntity.ok(carrinhoService.adicionarItemPorUsuario(usuarioId, itemRequest));
    }

    @DeleteMapping("/produtos/{itemId}")
    public ResponseEntity<Void> removerItemPorUsuario(
            @PathVariable Long usuarioId,
            @PathVariable Long itemId) {
        carrinhoService.removerItemPorUsuario(usuarioId, itemId);
        return ResponseEntity.noContent().build();
    }
}