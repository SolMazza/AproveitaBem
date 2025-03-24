package Api.service;

import Api.model.CarrinhoDeCompra;
import Api.model.Prateleira;
import Api.model.Produto;
import Api.model.ItemLista;
import Api.repository.CarrinhoDeCompraRepository;
import Api.repository.PrateleiraRepository;
import Api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrateleiraService {

    private PrateleiraRepository prateleiraRepository;
    private ProdutoRepository produtoRepository;

    public PrateleiraService(PrateleiraRepository prateleiraRepository, ProdutoRepository produtoRepository){
        this.prateleiraRepository = prateleiraRepository;
        this.produtoRepository = produtoRepository;
    }

    public Prateleira adicionarProduto(Long prateleiraId, Long produtoId) {
        Prateleira prateleira = prateleiraRepository.findById(prateleiraId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        prateleira.getProdutos().add(produto);
        return prateleiraRepository.save(prateleira);
    }
    public List<Produto> buscarTodosProdutos(Long prateleiraId){
        Prateleira prateleira = prateleiraRepository.findById(prateleiraId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        List<Produto> listaProdutos = prateleira.getProdutos();

        return listaProdutos;
    }

    public Prateleira removerProduto(Long prateleiraId, Long produtoId) {
        Prateleira prateleira = prateleiraRepository.findById(prateleiraId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        prateleira.getProdutos().removeIf(produto -> produto.getId().equals(produtoId));
        return prateleiraRepository.save(prateleira);
    }

    public void excluirPrateleira(Long id) {
        prateleiraRepository.deleteById(id);
    }
}