package Api.controller;

import Api.model.Categoria;
import Api.model.Produto;
import Api.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }
    @PostMapping("/cadastrar")
    public ResponseEntity<Categoria> cadastrar(@RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.cadastrar(categoria));
    }

    @GetMapping()
    public ResponseEntity<Categoria> buscarPeloId(@RequestParam Long Id){
    Categoria categoria = categoriaService.buscarPeloId(Id);
    return ResponseEntity.ok(categoria);

    }
    @GetMapping()
        public List<Categoria> ListarTodos(){
        List<Categoria> categorias = categoriaService.ListarTodos();
        return categorias;
    }

    @GetMapping()
        public List<Categoria> ListarNomes(@RequestParam String nome){
        List<Categoria> categorias = categoriaService.ListarNome(nome);
        return categorias;
    }
    @DeleteMapping()
    public ResponseEntity<String> deletarTodos(){
    categoriaService.deletarTodos();
    return ResponseEntity.ok("Categoria deletada com sucesso");
    }

    @DeleteMapping("/id")
    public ResponseEntity<String> deletar(@RequestParam Long id){
    categoriaService.deletarPeloId((id));
    return ResponseEntity.ok("Categoria deletada com sucesso!");
    }
    @PutMapping("/editar")
    public ResponseEntity<Categoria> editar(@RequestBody Long id, Categoria categoria){
    return ResponseEntity.ok(categoriaService.editar(id, categoria));
    }

}
