package controller.exceptions;

public class exceptionsFactory extends Exception{
    public static Exception getException(int i, String mensagem){

        switch (i){
            case 1:
            return new AutoRequisicaoException(mensagem);
            
            case 2:
            return new CodigoAdmErradoException(mensagem);
            
            case 3: 
            return new LoginInvalidoException(mensagem);

            case 4:
            return new SenhaInvalidaException(mensagem);

            case 5:
            return new UsuarioExistenteException(mensagem);

        }
        return null;
    }
}