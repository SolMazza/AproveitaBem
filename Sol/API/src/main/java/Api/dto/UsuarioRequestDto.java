package Api.dto;

import Api.model.CarrinhoDeCompra;
import Api.model.Prateleira;

import java.util.List;

public record UsuarioRequestDto(String nomeCompleto, String senha, String email) {

}
