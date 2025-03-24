package Api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "itens_lista")
@EqualsAndHashCode(of = "id")
public class ItemLista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "carrinho_id", nullable = false)
    private CarrinhoDeCompra carrinhoDeCompra;

    public ItemLista() {
    }

    public ItemLista(Long id, String nome, int quantidade, CarrinhoDeCompra carrinhoDeCompra) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.carrinhoDeCompra = carrinhoDeCompra;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public CarrinhoDeCompra getCarrinhoDeCompra() {
        return carrinhoDeCompra;
    }

    public void setCarrinhoDeCompra(CarrinhoDeCompra carrinhoDeCompra) {
        this.carrinhoDeCompra = carrinhoDeCompra;
    }
}