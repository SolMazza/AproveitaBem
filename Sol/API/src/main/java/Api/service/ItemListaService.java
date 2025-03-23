package Api.service;

import Api.model.CarrinhoDeCompra;
import Api.model.itemLista;
import Api.repository.CarrinhoDeCompraRepository;
import Api.repository.itemListaRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemListaService {

    private final itemListaRepository itemListaRepository;
    private final CarrinhoDeCompraRepository carrinhoDeCompraRepository;

    public ItemListaService(itemListaRepository itemListaRepository, CarrinhoDeCompraRepository carrinhoDeCompraRepository) {
        this.itemListaRepository = itemListaRepository;
        this.carrinhoDeCompraRepository = carrinhoDeCompraRepository;
    }

    public itemLista adicionarItem(Long carrinhoId, String nome, int quantidade) {
        CarrinhoDeCompra carrinho = carrinhoDeCompraRepository.findById(carrinhoId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        itemLista item = new itemLista();
        item.setNome(nome);
        item.setQuantidade(quantidade);
        item.setCarrinhoDeCompra(carrinho);

        return itemListaRepository.save(item);
    }

    public void removerItem(Long itemId) {
        itemListaRepository.deleteById(itemId);
    }
}