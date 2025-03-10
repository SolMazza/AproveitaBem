package aproveite_bem.service;

import aproveite_bem.model.CarrinhoDeCompra;
import aproveite_bem.model.Categoria;
import aproveite_bem.model.Produto;
import aproveite_bem.repository.CarrinhoDeCompraRepository;
import aproveite_bem.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrinhoDeCompraService {

    private CarrinhoDeCompraRepository carrinhoDeCompraRepository;

    public CarrinhoDeCompraService(CarrinhoDeCompraRepository carrinhoDeCompraRepository){
        this.carrinhoDeCompraRepository = carrinhoDeCompraRepository;
    }

    public CarrinhoDeCompra adicionarProduto(Long carrinhoId, Long produtoId) {
        CarrinhoDeCompra carrinho = carrinhoDeCompraRepository.findById(carrinhoId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        carrinho.getProdutos().add(produto);
        return carrinhoDeCompraRepository.save(carrinho);
    }
    }
}


