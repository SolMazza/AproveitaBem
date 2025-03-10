package aproveite_bem.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "carrinhoDeCompra")
public class CarrinhoDeCompra {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    @JoinColumn(name = "id")
    private Set<Produto> produtos;

    //adicionar futuramente funcionalidade para baixar a lista da compra
    //adicionar futuramente se possivel funcionalidade para acessar a lista da compra com o QRcode


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
