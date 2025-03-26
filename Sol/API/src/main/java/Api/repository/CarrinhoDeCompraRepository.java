package Api.repository;

import Api.model.CarrinhoDeCompra;
import Api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CarrinhoDeCompraRepository extends JpaRepository<CarrinhoDeCompra, Long> {
    Optional<CarrinhoDeCompra> findByUsuario(Usuario usuario);
}
