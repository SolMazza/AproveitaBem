package Aproveite_bem_api.dto;


import Aproveite_bem_api.model.Usuario;
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
        return nomeCompletp;
    }

    public void setNomeCompletp(String nomeCompletp) {
        this.nomeCompletp = nomeCompletp;
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
