package aproveite_bem.service;

import Aproveite_bem_api.repository.UsuarioRepository;
import aproveite_bem.exeption.RegistroNaoEncontrado;
import aproveite_bem.model.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario cadastrar(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public Usuario busca(String email) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        return usuario.orElseThrow(() -> new RegistroNaoEncontrado("Email " + email + " não encontrado"));
    }

    public Usuario editar(String email, Usuario novoUsuario ){
        Usuario usuarioEditado = this.busca(email);

        usuarioEditado.setEmail(novoUsuario.getEmail());
        usuarioEditado.setEmail(novoUsuario.getNomeCompleto());
        usuarioRepository.save(usuarioEditado);
        return usuarioEditado;
    }

    public Usuario editarSenha(String email, String senha){
        Usuario usuarioEditado = this.busca(email);
        usuarioEditado.setSenha(passwordEncoder.encode(senha));
        return usuarioEditado;

    }
    public void deletar(String email) {
      Usuario deletarEmail = this.busca(email);
      usuarioRepository.delete(deletarEmail);
    }

}
