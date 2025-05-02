package Api.service;

import Api.model.CarrinhoDeCompra;
import Api.model.ItemLista;
import Api.model.Usuario;
import Api.repository.CarrinhoDeCompraRepository;
import Api.repository.ItemListaRepository;
import Api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarrinhoDeCompraService {

    private final CarrinhoDeCompraRepository carrinhoRepo;
    private final ItemListaRepository itemRepo;
    private final UsuarioRepository usuarioRepo;

    public CarrinhoDeCompraService(CarrinhoDeCompraRepository carrinhoRepo,
                                   ItemListaRepository itemRepo,
                                   UsuarioRepository usuarioRepo) {
        this.carrinhoRepo = carrinhoRepo;
        this.itemRepo = itemRepo;
        this.usuarioRepo = usuarioRepo;
    }

    public ItemLista adicionarItemPorUsuario(Long usuarioId, ItemLista itemRequest) {
        CarrinhoDeCompra carrinho = pegarOuCriarCarrinhoPeloUsuario(usuarioId);

        ItemLista item = new ItemLista();
        item.setNome(itemRequest.getNome());
        item.setQuantidade(itemRequest.getQuantidade());
        item.setCarrinhoDeCompra(carrinho);

        ItemLista savedItem = itemRepo.save(item);
        carrinho.getItens().add(savedItem);
        carrinhoRepo.save(carrinho);

        return savedItem;
    }

    public void removerItem(Long carrinhoId, Long itemId) {
        CarrinhoDeCompra carrinho = carrinhoRepo.findById(carrinhoId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));
        boolean itemExiste = carrinho.getItens().stream()
                .anyMatch(item -> item.getId().equals(itemId));
        if (!itemExiste) {
            throw new RuntimeException("Item não encontrado no carrinho");
        }
        carrinho.getItens().removeIf(item -> item.getId().equals(itemId));
        carrinhoRepo.save(carrinho);
        itemRepo.deleteById(itemId);
    }
    public List<ItemLista> listarItens(Long carrinhoId) {
        Optional<CarrinhoDeCompra> carrinho = carrinhoRepo.findById(carrinhoId);
        return carrinho.get().getItens();
    }

    public CarrinhoDeCompra getCarrinhoByUsuarioEmail(String email) {
        Usuario usuario = usuarioRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return carrinhoRepo.findByUsuario(usuario)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado para este usuário"));
    }

    public CarrinhoDeCompra pegarOuCriarCarrinhoPeloUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepo.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Optional<CarrinhoDeCompra> carrinhoOpt = carrinhoRepo.findByUsuario(usuario);

        if (carrinhoOpt.isPresent()) {
            return carrinhoOpt.get();
        } else {
            CarrinhoDeCompra novoCarrinho = new CarrinhoDeCompra();
            novoCarrinho.setUsuario(usuario);
            novoCarrinho.setItens(new ArrayList<>());
            return carrinhoRepo.save(novoCarrinho);
        }
    }
}