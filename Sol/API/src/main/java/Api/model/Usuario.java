package Api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String nomeCompleto;

    @Column(nullable = false)
    private String senha;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private CarrinhoDeCompra carrinhoDeCompra;

    public Usuario(Long id, String email, String nomeCompleto, String senha) {
        this.id = id;
        this.email = email;
        this.nomeCompleto = nomeCompleto;
        this.senha = senha;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public CarrinhoDeCompra getCarrinhoDeCompra() {
        return carrinhoDeCompra;
    }

    public void setCarrinhoDeCompra(CarrinhoDeCompra carrinhoDeCompra) {
        this.carrinhoDeCompra = carrinhoDeCompra;
    }
}