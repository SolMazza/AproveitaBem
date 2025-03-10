package aproveite_bem.service;

import aproveite_bem.exeption.RegistroNaoEncontrado;
import aproveite_bem.model.Categoria;
import aproveite_bem.model.Produto;
import aproveite_bem.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> ListarTodos(){
    List<Categoria> categorias = categoriaRepository.findAll();
    return categorias;
    }

    public List<Categoria> ListarNome(String nome){
        List<Categoria> categorias = categoriaRepository.findByNome(nome);
        return categorias;
    }

    public Categoria buscarPeloId(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        return categoria.orElseThrow(() -> new RegistroNaoEncontrado("id " + id + " não encontrado"));

    }

    public void deletarTodos() {
        categoriaRepository.deleteAll();
    }

    public void deletarPeloId(Long id) {
        categoriaRepository.deleteById(id);
    }

    public Categoria cadastrar(Categoria novaCategoria){
        return categoriaRepository.save(novaCategoria);
    }

    public Categoria editar(Long id, Categoria novaCategoria){
        Categoria categoriaEditada = this.buscarPeloId(id);
        categoriaEditada.setNome(novaCategoria.getNome());
        categoriaEditada.setProdutos(novaCategoria.getProdutos());
        categoriaEditada.setFormaDeArmazenar(novaCategoria.getFormaDeArmazenar());

        return categoriaRepository.save(categoriaEditada);

    }


}
