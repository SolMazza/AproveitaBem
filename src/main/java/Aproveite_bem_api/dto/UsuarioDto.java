package Aproveite_bem_api.dto;


import lombok.Data;

@Data
public class UsuarioDto {

    private String nomeCompletp;
    private String email;
    private String senha;

    public UsuarioDto(String nomeCompletp, String email, String senha) {
        this.nomeCompletp = nomeCompletp;
        this.email = email;
        this.senha = senha;
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
