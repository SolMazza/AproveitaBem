package Api.controller;

import Api.dto.UsuarioRequestDto;
import Api.model.CarrinhoDeCompra;
import Api.model.ItemLista;
import Api.model.Usuario;
import Api.repository.UsuarioRepository;
import Api.service.CarrinhoDeCompraService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@SpringBootTest
@AutoConfigureMockMvc
class CarrinhoDeCompraControllerTest {

    @Mock
    private CarrinhoDeCompraService carrinhoService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private CarrinhoDeCompraController controller;

    private Usuario usuario;
    private CarrinhoDeCompra carrinho;
    private ItemLista item;

    @BeforeEach
    void setUp() {
        UsuarioRequestDto dto = new UsuarioRequestDto(
                "Nome Completo",
                "senha123",
                "test@example.com"
        );
        usuario = new Usuario(dto);
        usuario.setId(1L);

        carrinho = new CarrinhoDeCompra(usuario);
        carrinho.setId(1L);

        item = new ItemLista("Banana", 2, carrinho);
        item.setId(1L);
    }


}