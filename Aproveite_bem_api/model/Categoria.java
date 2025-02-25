package Aproveite_bem_api.model;


import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "categoria")
public class Categoria {


 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private Long id;

 @Column(nullable = false)
 private String nome;

 @OneToMany
 @JoinColumn(name = "id")
 private Set<Produto> produtos;

 @Column
 private String formaDeArmazenar;

    public Categoria(String nome, Set<Produto> produtos, String formaDeArmazenar) {
        this.nome = nome;
        this.produtos = produtos;
        this.formaDeArmazenar = formaDeArmazenar;
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


