package Api.controller;

import Api.model.itemLista;
import Api.service.ItemListaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/itens-lista")
public class ItemListaController {

    private final ItemListaService itemListaService;

    public ItemListaController(ItemListaService itemListaService) {
        this.itemListaService = itemListaService;
    }

    @PostMapping("/adicionar")
    public ResponseEntity<itemLista> adicionarItem(
            @RequestParam Long carrinhoId,
            @RequestParam String nome,
            @RequestParam int quantidade) {
        itemLista item = itemListaService.adicionarItem(carrinhoId, nome, quantidade);
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/remover/{itemId}")
    public ResponseEntity<String> removerItem(@PathVariable Long itemId) {
        itemListaService.removerItem(itemId);
        return ResponseEntity.ok("Item removido com sucesso");
    }
}