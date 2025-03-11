package aproveite_bem.controller;

import aproveite_bem.model.Produto;
import aproveite_bem.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


}
