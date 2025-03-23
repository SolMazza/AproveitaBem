package Api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "carrinhos_de_compra")
@EqualsAndHashCode(of = "id")
public class CarrinhoDeCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;

    @OneToMany(mappedBy = "carrinhoDeCompra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<itemLista> itens;

    public CarrinhoDeCompra() {
    }

    public CarrinhoDeCompra(Long id, Usuario usuario, List<itemLista> itens) {
        this.id = id;
        this.usuario = usuario;
        this.itens = itens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<itemLista> getItens() {
        return itens;
    }

    public void setItens(List<itemLista> itens) {
        this.itens = itens;
    }
}