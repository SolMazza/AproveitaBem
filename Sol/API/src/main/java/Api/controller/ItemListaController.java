package Api.controller;

import Api.model.ItemLista;
import Api.service.ItemListaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/itens-lista")
public class ItemListaController {

    private final ItemListaService itemListaService;

    public ItemListaController(ItemListaService itemListaService) {
        this.itemListaService = itemListaService;
    }

    @PostMapping("/adicionar")
    public ResponseEntity<ItemLista> adicionarItem(
            @RequestBody Long carrinhoId, String nome, int quantidade) {
        ItemLista item = itemListaService.adicionarItem(carrinhoId, nome, quantidade);
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/remover/{itemId}")
    public ResponseEntity<String> removerItem(@PathVariable Long itemId) {
        itemListaService.removerItem(itemId);
        return ResponseEntity.ok("Item removido com sucesso");
    }

    @DeleteMapping("/deletar-Todos")
    public ResponseEntity<String> deletarTodos(){
        itemListaService.removerTodosItens();
        return ResponseEntity.ok(("Todos os itens foram removidos"));
    }
 }