package Api.repository;

import Api.model.CarrinhoDeCompra;
import Api.model.Prateleira;
import Api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PrateleiraRepository extends JpaRepository<Prateleira,Long > {
    List<Prateleira> findByUsuario(Usuario usuario);
}
