package Api.dto;

import Api.model.Usuario;




public record UsuarioResponseDto(Long id, String nomeCompleto, String senha, String email) {
    public UsuarioResponseDto(Usuario usuario) {
        this(usuario.getId(), usuario.getNomeCompleto(), usuario.getSenha(), usuario.getEmail());
        }
}
