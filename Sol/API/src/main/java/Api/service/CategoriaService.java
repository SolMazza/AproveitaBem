package Api.service;

import Api.exception.RegistroNaoEncontrado;
import Api.model.Categoria;
import Api.model.Usuario;
import Api.repository.CategoriaRepository;
import Api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final UsuarioRepository usuarioRepository;

    public CategoriaService(
            CategoriaRepository categoriaRepository,
            UsuarioRepository usuarioRepository) {
        this.categoriaRepository = categoriaRepository;
        this.usuarioRepository = usuarioRepository;
    }


    public void criarCategoriasPadrao(Long usuarioId) {
        List<Categoria> categoriasPadrao = Arrays.asList(
                new Categoria("Laticínios"),
                new Categoria("Carnes"),
                new Categoria("Bebidas"),
                new Categoria("Frutas"),
                new Categoria("Vegetais")
        );

        categoriasPadrao.forEach(categoria -> this.cadastrar(usuarioId, categoria));
    }

    public Categoria cadastrar(Long usuarioId, Categoria categoria) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RegistroNaoEncontrado("Usuário não encontrado"));
        categoria.setUsuario(usuario);
        return categoriaRepository.save(categoria);
    }

    public Categoria buscarPeloId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontrado("Categoria não encontrada"));
    }

    public List<Categoria> listarPorUsuario(Long usuarioId) {
        return categoriaRepository.findByUsuarioId(usuarioId);
    }

    public List<Categoria> listarTodos() {
        return categoriaRepository.findAll();
    }

    public List<Categoria> listarPorNome(String nome) {
        return categoriaRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Categoria editar(Long id, Categoria categoriaAtualizada) {
        Categoria categoria = buscarPeloId(id);
        categoria.setNome(categoriaAtualizada.getNome());
        return categoriaRepository.save(categoria);
    }

    public void deletarPeloId(Long id) {
        categoriaRepository.deleteById(id);
    }

}
