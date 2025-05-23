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

import java.util.Collections;
import java.util.List;


@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CategoriaService categoriaService;
    private final PrateleiraRepository prateleiraRepository;

    public UsuarioService(UsuarioRepository usuarioRepository,  PrateleiraRepository prateleiraRepository,
                          BCryptPasswordEncoder passwordEncoder, CategoriaService categoriaService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.categoriaService = categoriaService;
        this.prateleiraRepository = prateleiraRepository;
          }



    public UsuarioResponseDto cadastrar(UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = new Usuario(usuarioRequestDto);
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        CarrinhoDeCompra carrinho = new CarrinhoDeCompra(usuario);
        usuario.setCarrinhoDeCompra(carrinho);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        categoriaService.criarCategoriasPadrao(usuarioSalvo.getId());
        return new UsuarioResponseDto(usuarioSalvo);
    }

    public List<Prateleira> buscarTodasPrateleiras(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        List<Prateleira> prateleiras = prateleiraRepository.findByUsuario(usuario);
        return prateleiras != null ? prateleiras : Collections.emptyList();
    }

    public Usuario autenticar(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RegistroNaoEncontrado("Usuário não encontrado"));
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