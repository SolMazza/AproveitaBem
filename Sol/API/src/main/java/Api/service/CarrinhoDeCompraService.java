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

    private CarrinhoDeCompraRepository carrinhoRepo;
    private ItemListaRepository itemRepo;

    public CarrinhoDeCompraService(CarrinhoDeCompraRepository carrinhoRepo, ItemListaRepository itemRepo){
        this.carrinhoRepo = carrinhoRepo;
        this.itemRepo = itemRepo;
    }

    public CarrinhoDeCompra adicionarItem(Long carrinhoId, ItemLista itemRequest) {
        CarrinhoDeCompra carrinho = carrinhoRepo.findById(carrinhoId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));
        ItemLista item = new ItemLista();
        item.setNome(itemRequest.getNome());
        item.setQuantidade(itemRequest.getQuantidade());
        item.setCarrinhoDeCompra(carrinho);
        carrinho.getItens().add(itemRepo.save(item));
        return carrinhoRepo.save(carrinho);
    }

    public void removerItem(Long carrinhoId, Long itemId) {
        CarrinhoDeCompra carrinho = carrinhoRepo.findById(carrinhoId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));
        carrinho.getItens().removeIf(item -> item.getId().equals(itemId));
        carrinhoRepo.save(carrinho);
    }

    public List<ItemLista> listarItens(Long carrinhoId) {
        return carrinhoRepo.findById(carrinhoId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"))
                .getItens();
    }


}