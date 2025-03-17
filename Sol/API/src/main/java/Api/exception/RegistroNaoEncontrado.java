package Api.exception;

public class RegistroNaoEncontrado extends RuntimeException{
    public RegistroNaoEncontrado(String mensagem){
        super(mensagem);
    }

}