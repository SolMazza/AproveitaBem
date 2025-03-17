package Api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "categorias")
@EqualsAndHashCode(of = "id")
public class Categoria {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @OneToMany
    @JoinColumn(name = "id")
    private Set<Produto> produtos;

    @Column
    private String formaDeArmazenar;

    public Categoria() {
    }

    public Categoria(Long id, String nome, Set<Produto> produtos, String formaDeArmazenar) {
        this.id = id;
        this.nome = nome;
        this.produtos = produtos;
        this.formaDeArmazenar = formaDeArmazenar;
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

    public Set<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
    }

    public String getFormaDeArmazenar() {
        return formaDeArmazenar;
    }

    public void setFormaDeArmazenar(String formaDeArmazenar) {
        this.formaDeArmazenar = formaDeArmazenar;
    }
}