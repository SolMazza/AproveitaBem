package Api.repository;

import Api.model.ItemLista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemListaRepository extends JpaRepository<ItemLista, Long> {
}