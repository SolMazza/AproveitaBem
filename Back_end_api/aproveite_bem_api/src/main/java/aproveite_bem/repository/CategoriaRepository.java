package aproveite_bem.repository;

import aproveite_bem.model.Categoria;
import aproveite_bem.service.CategoriaService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {


    List<Categoria> findByNome(String nome);
}
