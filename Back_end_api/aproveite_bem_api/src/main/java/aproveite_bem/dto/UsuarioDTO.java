package aproveite_bem.dto;



import aproveite_bem.model.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    private String nomeCompleto;
    private String email;
    private String senha;

    public Usuario toEntity() {
        Usuario usuario = new Usuario();
        usuario.setNomeCompleto(this.nomeCompleto);
        usuario.setEmail(this.email);
        usuario.setSenha(this.senha);
        return usuario;
    }

    public String getNomeCompletp() {
        return nomeCompleto;
    }

    public void setNomeCompletp(String nomeCompleto) {
        this.nomeCompleto= nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
