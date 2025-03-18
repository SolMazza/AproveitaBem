package Api.repository;

import Api.model.CarrinhoDeCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarrinhoDeCompraRepository extends JpaRepository<CarrinhoDeCompra, Long> {

}
