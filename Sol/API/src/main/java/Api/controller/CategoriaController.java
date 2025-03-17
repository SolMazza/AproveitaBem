package Api.controller;

import Api.model.Categoria;
import Api.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
}
/*
public List<Categoria> ListarTodos(){
public List<Categoria> ListarNome(String nome){
public Categoria buscarPeloId(Long id) {
public void deletarTodos()
public void deletarPeloId(Long id) {


*/