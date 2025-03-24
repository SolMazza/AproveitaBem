package Api.service;

import Api.dto.UsuarioRequestDto;
import Api.dto.UsuarioResponseDto;
import Api.exception.RegistroNaoEncontrado;
import Api.model.*;
import Api.repository.CategoriaRepository;
import Api.repository.PrateleiraRepository;
import Api.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
          }


    public UsuarioResponseDto cadastrar(UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = new Usuario(usuarioRequestDto);
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuario.setCarrinhoDeCompra(new CarrinhoDeCompra(usuario));
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioResponseDto(usuarioSalvo);
    }

    public List<Prateleira> buscarTodasPrateleiras(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        List<Prateleira> listaPrateleiras = usuario.getPrateleiras();

        return listaPrateleiras;
    }

    public Usuario autenticar(String email) {
        return usuarioRepository.findByEmail(email)
                .orElse(null);
    }

    public UsuarioResponseDto busca(String email) {
        return usuarioRepository.findByEmail(email)
                .map(UsuarioResponseDto::new)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Usuario editar(String email, UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setEmail(usuarioRequestDto.email());
        usuario.setNomeCompleto(usuarioRequestDto.nomeCompleto());

        return usuarioRepository.save(usuario);
    }


    public void deletar(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RegistroNaoEncontrado("Usuário não encontrado"));

        usuarioRepository.delete(usuario);
    }
}