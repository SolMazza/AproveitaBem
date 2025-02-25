package Aproveite_bem_api.controller;

import Aproveite_bem_api.repository.UsuarioRepository;
import Aproveite_bem_api.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "usuarios")
public class UsuarioController {

private UsuarioService usuarioService;

}
