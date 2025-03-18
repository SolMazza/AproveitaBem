package Api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "produtos")
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column
    private LocalDate dataFabricacao;
    @Column
    private LocalDate dataVencimento;
    @Column(nullable = false)
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "carrinho_id")
    private CarrinhoDeCompra carrinhoDeCompra;

    public Produto() {
    }

    public Produto(Long id, String nome, LocalDate dataFabricacao, LocalDate dataVencimento, int quantidade, Categoria categoria, CarrinhoDeCompra carrinhoDeCompra) {
        this.id = id;
        this.nome = nome;
        this.dataFabricacao = dataFabricacao;
        this.dataVencimento = dataVencimento;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.carrinhoDeCompra = carrinhoDeCompra;
    }

    public Long getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(LocalDate dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public CarrinhoDeCompra getCarrinhoDeCompra() {
        return carrinhoDeCompra;
    }

    public void setCarrinhoDeCompra(CarrinhoDeCompra carrinhoDeCompra) {
        this.carrinhoDeCompra = carrinhoDeCompra;
    }
}
