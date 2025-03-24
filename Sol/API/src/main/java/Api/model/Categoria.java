package Api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
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

    @OneToMany(mappedBy = "categoria")
    private List<Produto> produtos;

    @Column
    private String formaDeArmazenar;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;


    public Categoria() {
    }

    public Categoria(Long id, String nome, List<Produto> produtos, String formaDeArmazenar) {
        this.id = id;
        this.nome = nome;
        this.produtos = produtos;
        this.formaDeArmazenar = formaDeArmazenar;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public String getFormaDeArmazenar() {
        return formaDeArmazenar;
    }

    public void setFormaDeArmazenar(String formaDeArmazenar) {
        this.formaDeArmazenar = formaDeArmazenar;
    }
}