package aproveite_bem.exeption;

public class RegistroNaoEncontrado extends RuntimeException{
    public RegistroNaoEncontrado(String mensagem){
        super(mensagem);
    }

}
