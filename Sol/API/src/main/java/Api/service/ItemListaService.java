package Api.service;

import Api.model.CarrinhoDeCompra;
import Api.model.ItemLista;
import Api.repository.CarrinhoDeCompraRepository;
import Api.repository.ItemListaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemListaService {

    private final ItemListaRepository itemRepo;

    public ItemListaService(ItemListaRepository itemRepo) {
        this.itemRepo = itemRepo;
    }

    public ItemLista criarItem(ItemLista itemRequest) {
        return itemRepo.save(itemRequest);
    }

    public void deletarItem(Long itemId) {
        itemRepo.deleteById(itemId);
    }
}