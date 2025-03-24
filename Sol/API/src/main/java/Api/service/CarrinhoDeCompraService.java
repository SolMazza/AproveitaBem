package Api.service;

import Api.model.CarrinhoDeCompra;
import Api.model.Produto;
import Api.model.ItemLista;
import Api.repository.CarrinhoDeCompraRepository;
import Api.repository.ItemListaRepository;
import Api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrinhoDeCompraService {

    private CarrinhoDeCompraRepository carrinhoDeCompraRepository;
    private ItemListaRepository itemListaRepository;

    public CarrinhoDeCompraService(CarrinhoDeCompraRepository carrinhoDeCompraRepository, ItemListaRepository itemListaRepository){
        this.carrinhoDeCompraRepository = carrinhoDeCompraRepository;
        this.itemListaRepository = itemListaRepository;
    }

    public CarrinhoDeCompra adicionarItemLista(Long carrinhoId, Long itemListaId) {
        CarrinhoDeCompra carrinho = carrinhoDeCompraRepository.findById(carrinhoId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        ItemLista itemLista = itemListaRepository.findById(itemListaId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        carrinho.getItens().add(itemLista);
        return carrinhoDeCompraRepository.save(carrinho);
    }
    public CarrinhoDeCompra removerProduto(Long carrinhoId, Long itemListaId) {
        CarrinhoDeCompra carrinho = carrinhoDeCompraRepository.findById(carrinhoId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        carrinho.getItens().removeIf(itemLista -> itemLista.getId().equals(itemListaId));
        return carrinhoDeCompraRepository.save(carrinho);
    }

    public List<ItemLista> buscarItensCarrinho(Long carrinhoId){
        CarrinhoDeCompra carrinho = carrinhoDeCompraRepository.findById(carrinhoId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        List<ItemLista> listItemLista = carrinho.getItens();

        return listItemLista;
    }


}