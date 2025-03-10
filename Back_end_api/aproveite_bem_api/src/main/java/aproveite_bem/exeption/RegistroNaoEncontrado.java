package Aproveite_bem_api.exeption;

public class RegistroNaoEncontrado extends RuntimeException{
    public RegistroNaoEncontrado(String mensagem){
        super(mensagem);
    }

}
