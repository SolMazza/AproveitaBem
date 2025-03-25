package Api.controller;

import Api.model.ItemLista;
import Api.service.ItemListaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(path = "/itens-lista")
public class ItemListaController {

    private final ItemListaService itemService;

    public ItemListaController(ItemListaService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<ItemLista> criarItem(@RequestBody ItemLista itemRequest) {
        return ResponseEntity.ok(itemService.criarItem(itemRequest));
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deletarItem(@PathVariable Long itemId) {
        itemService.deletarItem(itemId);
        return ResponseEntity.noContent().build();
    }
 }