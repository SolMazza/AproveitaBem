package Api.controller;

import Api.model.Produto;
import Api.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "produtos")
public class ProdutoController {


    private final ProdutoService produtoService;


   public ProdutoController(ProdutoService produtoService){
       this.produtoService = produtoService;
   }

   @GetMapping()
    public ResponseEntity<Produto> buscarPeloId(@RequestParam Long id){
       Produto produto = produtoService.buscarPeloId(id);
       return ResponseEntity.ok(produto);
   }

   @GetMapping()
    public List<Produto> ListarPeloNome(@RequestParam String nome){
       List<Produto> produtos = produtoService.listarPorNome(nome);
       return produtos;
   }
    @GetMapping()
    public List<Produto> ListarTodos(){
        List<Produto> produtos = produtoService.listarTodos();
        return produtos;
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<Produto> cadastrar(@RequestBody Produto produto) {
        return ResponseEntity.ok(produtoService.cadastrar(produto));
    }

    @PutMapping("/editar")
    public ResponseEntity<Produto> editar(@RequestBody Long id, Produto produto) {
       return ResponseEntity.ok(produtoService.editar(id, produto));
    }

   @DeleteMapping()
     public ResponseEntity<String> deletarTodos(){
        produtoService.deletarTodos();
       return ResponseEntity.ok("Produtos deletados com sucesso");
   }

    @DeleteMapping("/id")
    public ResponseEntity<String> deletar(@RequestParam Long id){
        produtoService.deletarPeloId(id);
        return ResponseEntity.ok("Produtos deletados com sucesso");
    }


}
