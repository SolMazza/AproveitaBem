package Api.controller;

import Api.model.CarrinhoDeCompra;
import Api.model.Produto;
import Api.repository.CarrinhoDeCompraRepository;
import Api.service.CarrinhoDeCompraService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "CarrinhoDeCompras")
public class CarrinhoDeCompraController {

    private CarrinhoDeCompraService carrinhoDeCompraService;

    public CarrinhoDeCompraController(CarrinhoDeCompraService carrinhoDeCompraService){
        this.carrinhoDeCompraService = carrinhoDeCompraService;
    }

    @PostMapping("/adicionar")
    public ResponseEntity<CarrinhoDeCompra> adicionarProduto(@RequestBody Long idCarrinho, Long idProduto){
        carrinhoDeCompraService.adicionarProduto(idCarrinho,idProduto);
        return (ResponseEntity<CarrinhoDeCompra>) ResponseEntity.ok();
    }

    @DeleteMapping("/remover")
    public ResponseEntity<CarrinhoDeCompra> removerProduto(@RequestBody Long idCarrinho, Long idProduto){
        carrinhoDeCompraService.removerProduto(idCarrinho,idProduto);
        return (ResponseEntity<CarrinhoDeCompra>) ResponseEntity.ok();
    }

    @DeleteMapping
    public ResponseEntity<String> apagarLista(@RequestParam Long id){
        carrinhoDeCompraService.excluirCarrinho(id);
        return ResponseEntity.ok("Lista Apagada");
    }


}
