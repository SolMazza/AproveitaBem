package aproveite_bem.service;

import aproveite_bem.model.Produto;
import aproveite_bem.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listaTodosOsProdutos(){
        List<Produto> produtos = produtoRepository.findAll();
        return produtos;
    }

}
