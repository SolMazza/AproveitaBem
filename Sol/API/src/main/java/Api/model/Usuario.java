package Api.model;

import Api.dto.UsuarioRequestDto;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
@EqualsAndHashCode(of = "id")
@Builder
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

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prateleira> prateleiras;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Categoria> categorias;


    public Usuario() {
    }

    public Usuario(UsuarioRequestDto usuarioRequestDto) {
        this.email = usuarioRequestDto.email();
        this.nomeCompleto = usuarioRequestDto.nomeCompleto();
        this.senha = usuarioRequestDto.senha();
        this.categorias = new ArrayList<>();
        this.prateleiras = new ArrayList<>();
    }

    public CarrinhoDeCompra getCarrinhoDeCompra() {
        return carrinhoDeCompra;
    }

    public void setCarrinhoDeCompra(CarrinhoDeCompra carrinhoDeCompra) {
        this.carrinhoDeCompra = carrinhoDeCompra;
    }

    public List<Prateleira> getPrateleiras() {
        return prateleiras;
    }

    public void setPrateleiras(List<Prateleira> prateleiras) {
        this.prateleiras = prateleiras;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
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
}
