package model.exceptions;

public class exceptionsModelFactory {
    public static Exception getException(int i, String mensagem){

        switch (i){
            case 1:
            return new JaPossuemMatchException(mensagem);
            
            case 2:
            return new JaSaoAmigosException(mensagem);
            
            case 3: 
            return new ListaVaziaException(mensagem);

            case 4:
            return new MatchJaFeitoException(mensagem);

            case 5:
            return new MensagemNaoSecretaException(mensagem);

            case 6:
            return new PedidoJaExistenteException(mensagem);

        }
        return null;
        
    }
}
