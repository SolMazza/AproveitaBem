package Api.service;

import Api.exception.RegistroNaoEncontrado;
import Api.model.Produto;
import Api.repository.CategoriaRepository;
import Api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(
            ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto adicionar(Produto produto) {
        if (produto.getQuantidade() < 0) {
            throw new IllegalArgumentException("Quantidade não pode ser negativa");
        }
        return produtoRepository.save(produto);
    }

    public Produto buscarPeloId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontrado("Produto não encontrado"));
    }

    public List<Produto> listarPorCategoria(Long categoriaId) {
        return produtoRepository.findByCategoriaId(categoriaId);
    }

    public List<Produto> listarPorNome(String nome) {
        return produtoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto editar(Long id, Produto produtoAtualizado) {
        Produto produto = buscarPeloId(id);
        produto.setNome(produtoAtualizado.getNome());
        produto.setQuantidade(produtoAtualizado.getQuantidade());
        produto.setDataFabricacao(produtoAtualizado.getDataFabricacao());
        produto.setDataVencimento(produtoAtualizado.getDataVencimento());
        produto.setCategoria(produtoAtualizado.getCategoria());
        produto.setPrateleira(produtoAtualizado.getPrateleira());
        return produtoRepository.save(produto);
    }

    public void deletarPeloId(Long id) {
        produtoRepository.deleteById(id);
    }


}
