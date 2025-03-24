package Api.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "prateleiras")
@EqualsAndHashCode(of = "id")
public class Prateleira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(mappedBy = "prateleira", cascade = CascadeType.ALL)
    private List<Produto> produtos;

    @OneToMany
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;


    public Prateleira(Usuario usuario) {
        this.produtos = new ArrayList<>();
        this.usuario = usuario;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
