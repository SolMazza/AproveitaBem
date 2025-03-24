package Api.service;

import Api.model.CarrinhoDeCompra;
import Api.model.ItemLista;
import Api.repository.CarrinhoDeCompraRepository;
import Api.repository.ItemListaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemListaService {

    private final ItemListaRepository itemListaRepository;
    private final CarrinhoDeCompraRepository carrinhoDeCompraRepository;

    public ItemListaService(ItemListaRepository itemListaRepository, CarrinhoDeCompraRepository carrinhoDeCompraRepository) {
        this.itemListaRepository = itemListaRepository;
        this.carrinhoDeCompraRepository = carrinhoDeCompraRepository;
    }

    public ItemLista adicionarItem(Long carrinhoId, String nome, int quantidade) {
        CarrinhoDeCompra carrinho = carrinhoDeCompraRepository.findById(carrinhoId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        ItemLista item = new ItemLista();
        item.setNome(nome);
        item.setQuantidade(quantidade);
        item.setCarrinhoDeCompra(carrinho);

        return itemListaRepository.save(item);
    }

    public ItemLista buscarItem(Long itemId ){

        ItemLista itemLista = itemListaRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));
        return itemLista;

    }


    public void removerItem(Long itemId) {
        itemListaRepository.deleteById(itemId);
    }

    public void removerTodosItens(){
        itemListaRepository.deleteAll();
    }
}