package Api.controller;

import Api.model.CarrinhoDeCompra;
import Api.model.ItemLista;
import Api.model.Prateleira;
import Api.model.Produto;
import Api.repository.PrateleiraRepository;
import Api.service.CarrinhoDeCompraService;
import Api.service.PrateleiraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class PrateleiraController {
    private PrateleiraService prateleiraService;

    public PrateleiraController(PrateleiraService prateleiraService) {
        this.prateleiraService = prateleiraService;
    }

    @PostMapping("/adicionar")
    public ResponseEntity<Prateleira> adicionarProduto(@RequestBody Long idPrateleira, Long idProduto) {
        prateleiraService.adicionarProduto(idPrateleira, idProduto);
        return (ResponseEntity<Prateleira>) ResponseEntity.ok();
    }

    @DeleteMapping("/remover")
    public ResponseEntity<Prateleira> removerProduto(@RequestBody Long idPrateleira, Long idProduto) {
        prateleiraService.removerProduto(idPrateleira, idProduto);
        return (ResponseEntity<Prateleira>) ResponseEntity.ok();
    }

    @GetMapping("/{prateleiraId}/BuscarTodos")
    public List<Produto> buscarListaItens(@RequestParam Long prateleiraId){
        List<Produto> lista = prateleiraService.buscarTodosProdutos(prateleiraId);
        return lista;
    }




}
