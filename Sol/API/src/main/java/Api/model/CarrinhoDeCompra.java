
package Api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "carrinhoDeCompra")
@EqualsAndHashCode(of = "id")
public class CarrinhoDeCompra {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "id")
    private Set<Produto> produtos;

    public CarrinhoDeCompra() {
    }

    public CarrinhoDeCompra(Long id, Set<Produto> produtos) {
        this.id = id;
        this.produtos = produtos;
    }

    public Long getId() {
        return id;
    }

    public Set<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
    }
}

