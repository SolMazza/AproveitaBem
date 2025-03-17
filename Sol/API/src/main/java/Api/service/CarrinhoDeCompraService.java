package Api.service;

import Api.model.CarrinhoDeCompra;
import Api.model.Produto;
import Api.repository.CarrinhoDeCompraRepository;
import Api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class CarrinhoDeCompraService {

    private CarrinhoDeCompraRepository carrinhoDeCompraRepository;
    private ProdutoRepository produtoRepository;

    public CarrinhoDeCompraService(CarrinhoDeCompraRepository carrinhoDeCompraRepository, ProdutoRepository produtoRepository){
        this.carrinhoDeCompraRepository = carrinhoDeCompraRepository;
        this.produtoRepository = produtoRepository;
    }

    public CarrinhoDeCompra adicionarProduto(Long carrinhoId, Long produtoId) {
        CarrinhoDeCompra carrinho = carrinhoDeCompraRepository.findById(carrinhoId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        carrinho.getProdutos().add(produto);
        return carrinhoDeCompraRepository.save(carrinho);
    }
    public CarrinhoDeCompra removerProduto(Long carrinhoId, Long produtoId) {
        CarrinhoDeCompra carrinho = carrinhoDeCompraRepository.findById(carrinhoId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        carrinho.getProdutos().removeIf(produto -> produto.getId().equals(produtoId));
        return carrinhoDeCompraRepository.save(carrinho);
    }

    public void excluirCarrinho(Long id) {
        carrinhoDeCompraRepository.deleteById(id);
    }
}