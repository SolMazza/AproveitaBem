package Api.controller;

import Api.model.CarrinhoDeCompra;
import Api.model.ItemLista;
import Api.model.Produto;
import Api.repository.CarrinhoDeCompraRepository;
import Api.service.CarrinhoDeCompraService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@Controller
@RequestMapping(path = "CarrinhoDeCompras")
public class CarrinhoDeCompraController {

    private CarrinhoDeCompraService carrinhoService;

    public CarrinhoDeCompraController(CarrinhoDeCompraService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @PostMapping("/{carrinhoId}/adicionar-item")
    public ResponseEntity<CarrinhoDeCompra> adicionarItem(
            @PathVariable Long carrinhoId,
            @RequestBody ItemLista itemRequest) {
        return ResponseEntity.ok(carrinhoService.adicionarItem(carrinhoId, itemRequest));
    }

    @DeleteMapping("/{carrinhoId}/remover-item/{itemId}")
    public ResponseEntity<Void> removerItem(
            @PathVariable Long carrinhoId,
            @PathVariable Long itemId) {
        carrinhoService.removerItem(carrinhoId, itemId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{carrinhoId}/itens")
    public ResponseEntity<List<ItemLista>> listarItens(@PathVariable Long carrinhoId) {
        return ResponseEntity.ok(carrinhoService.listarItens(carrinhoId));
    }
}

