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

@Controller
@RequestMapping(path = "CarrinhoDeCompras")
public class CarrinhoDeCompraController {

    private CarrinhoDeCompraService carrinhoDeCompraService;

    public CarrinhoDeCompraController(CarrinhoDeCompraService carrinhoDeCompraService) {
        this.carrinhoDeCompraService = carrinhoDeCompraService;
    }

    @PostMapping("/adicionar")
    public ResponseEntity<CarrinhoDeCompra> adicionarProduto(@RequestBody Long idCarrinho, Long idItemLista) {
        carrinhoDeCompraService.adicionarItemLista(idCarrinho, idItemLista);
        return (ResponseEntity<CarrinhoDeCompra>) ResponseEntity.ok();
    }

    @DeleteMapping("/remover")
    public ResponseEntity<CarrinhoDeCompra> removerProduto(@RequestBody Long idCarrinho, Long idItemLista) {
        carrinhoDeCompraService.removerProduto(idCarrinho, idItemLista);
        return (ResponseEntity<CarrinhoDeCompra>) ResponseEntity.ok();
    }

    @GetMapping("/{carrinhoId}/BuscarListaItem")
    public List<ItemLista> buscarListaItens(@RequestParam Long carrinhoId){
        List<ItemLista> lista = carrinhoDeCompraService.buscarItensCarrinho(carrinhoId);
        return lista;
    }

}