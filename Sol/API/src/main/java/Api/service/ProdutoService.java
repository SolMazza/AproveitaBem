package Api.service;

import Api.exception.RegistroNaoEncontrado;
import Api.model.Produto;
import Api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarTodos(){
        List<Produto> produtos = produtoRepository.findAll();
        return produtos;
    }
    public List<Produto> listarPorNome(String nome){
        List<Produto> produtos = produtoRepository.findByNome(nome);
        return produtos;
    }

    public Produto buscarPeloId(Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);

        return produto.orElseThrow(() -> new RegistroNaoEncontrado("id " + id + " não encontrado"));

    }

    public void deletarTodos() {
        produtoRepository.deleteAll();
    }

    public void deletarPeloId(Long id) {
        produtoRepository.deleteById(id);
    }

    public Produto cadastrar(Produto novoProduto){
       return produtoRepository.save(novoProduto);
    }

    public Produto editar(Long id, Produto novoProduto){
        Produto produtoEditado = this.buscarPeloId(id);
        produtoEditado.setCategoria(novoProduto.getCategoria());
        produtoEditado.setDataFabricacao(novoProduto.getDataFabricacao());
        produtoEditado.setDataVencimento(novoProduto.getDataVencimento());
        produtoEditado.setNome(novoProduto.getNome());
        produtoEditado.setQuantidade(novoProduto.getQuantidade());

        return produtoRepository.save(produtoEditado);

    }


}
