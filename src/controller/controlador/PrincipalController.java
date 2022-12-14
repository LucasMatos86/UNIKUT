package controller.controlador;

import controller.exceptions.*;
import model.exceptions.*;
import model.mensagens.Mensagem;
import model.mensagens.MensagemSecreta;
import model.usuarios.*;

import java.util.ArrayList;
import java.util.List;

public class PrincipalController {
    public List<Usuario> usuarios;

    public PrincipalController() {
        usuarios = new ArrayList<>();
    }

    public Usuario busca(Usuario u) {
        for (int i = 0; i < usuarios.size(); i++) {// Buscar a posição do usuário
            if (usuarios.get(i).getLogin().equals(u.getLogin())) {
                u = usuarios.get(i);
                return u;
            }
        }
        return null;
    }

    public boolean contem(Usuario u) {
        return usuarios.contains(u);
    }

    public void cadastro(String login, String senha, String nome) throws Exception{// Método de
                                                                  // inserção de um
                                                                  // usuário
        Usuario u = new Usuario(login, senha, nome);
        if (!contem(u)) {
            u.adicionarConta(u, usuarios);
        } else {
            throw exceptionsFactory.getException(5, "Login já utilizado.");
        }
    }

    public void cadastro(String login, String senha, String nome, String codigo) throws Exception {// Método de inserção de um usuário
        String codigoCorreto = "Un1ku7@dm1n";
        if (!codigo.equals(codigoCorreto)) {
            throw exceptionsFactory.getException(2,"Código de administrador incorreto.");
           
        } else {
            UsuarioAdmin u = new UsuarioAdmin(login, senha, nome);
            if (!contem(u)) {
                u.adicionarConta(u, usuarios);
            } else {
                throw exceptionsFactory.getException(5,"Login já utilizado.");
            
            }
        }
    }

//--------------RICARDO----------------

    public void login(String login, String senha) throws Exception { // Login do
                                                                                                          // usuário
        Usuario u = new Usuario(login, "", "");
        u = busca(u);
        if (u == null) {
            throw exceptionsFactory.getException(3,"Login não encontrado.");
            
        } else {
            if (!u.verificaSenha(senha)) {
                throw exceptionsFactory.getException(4,"Senha incorreta.");
                
            }
        }
    }

    public boolean tipoUsuario(String login) throws Exception {
        Usuario u = new Usuario(login, "", "");
        u = busca(u);
        if (u == null) {
             throw exceptionsFactory.getException(3,"Login inválido.");
            
        } else {
            return u.isAdm();
        }
        
    }

    public void editarCadastro(String login, String senha, String nome) {// Edição de perfil
        Usuario u = new Usuario(login, "", "");
        u = busca(u);
        if (senha == null && nome != null) {
            u.setNomeDeUsuario(nome);
        } else if (senha != null && nome == null) {
            u.setSenha(senha);
        } else {
            u.setSenha(senha);
            u.setNomeDeUsuario(nome);
        }
    }

    public boolean adicionarAmigo(String login, String logado)
            throws Exception {
        Usuario usReceptor = new Usuario(login, "", "");
        Usuario usEmissor = new Usuario(logado, "", "");
        if (usEmissor.equals(usReceptor)) {// Se ambos são iguais
            throw exceptionsFactory.getException(1,"Pedido enviado ao mesmo usuário.");
            
        } else if (!contem(usReceptor)) {// Se o receptor não está na lista
            throw exceptionsFactory.getException(4,"Login não encontrado.");

        } else {
            try {
                usReceptor = busca(usReceptor);
                usEmissor = busca(usEmissor);
                return usEmissor.adicionar(usReceptor);
            } catch (JaSaoAmigosException | PedidoJaExistenteException e) {
                throw e;
            }
        }
    }

    public String exibirAmigos(String login) throws Exception{
        Usuario u = new Usuario(login, "", "");
        String info;
        u = busca(u);
        info = u.listaDeAmigos();
        return info;
    }

    public String exibirPendentes(String login) throws Exception {
        Usuario u = new Usuario(login, "", "");
        String info;
        u = busca(u);
        info = u.listaDePendentes();
        return info;
    }

    public String exibirRecados(String logado) throws Exception {
        Usuario u = new Usuario(logado, "", "");
        String info;
        try {
            u = busca(u);
            info = u.listaRecados();
            return info;
        } catch (ListaVaziaException e) {
            throw e;
        }

    }

    public void enviarRecado(String logado, String login, String recado)
            throws Exception {
        Usuario usEmissor = new Usuario(logado, "", "");
        Usuario usReceptor = new Usuario(login, "", "");
        if (usEmissor.equals(usReceptor)) {
            throw exceptionsFactory.getException(1,"Mensagem para si mesmo.");
        } else if (!contem(usReceptor)) {
            throw exceptionsFactory.getException(3,"Login não encontrado.");
        } else {
            usReceptor = busca(usReceptor);
            Mensagem mensagem = new Mensagem(usEmissor.getLogin(), recado);
            usReceptor.adicionarRecado(mensagem);
        }
    }

    public void enviarRecado(String logado, String login, String recado, String palavraChave)
            throws Exception {
        Usuario usEmissor = new Usuario(logado, "", "");
        Usuario usReceptor = new Usuario(login, "", "");
        if (usEmissor.equals(usReceptor)) {
            throw exceptionsFactory.getException(1,"Mensagem para si mesmo.");
        } else if (!contem(usReceptor)) {
            throw exceptionsFactory.getException(3,"Login não encontrado.");
        } else {
            usReceptor = busca(usReceptor);
            MensagemSecreta mensagem = new MensagemSecreta(usEmissor.getLogin(), recado, palavraChave);
            usReceptor.adicionarRecado(mensagem);
        }
    }

    public void excluirRecados(String logado) throws Exception {
        Usuario u = new Usuario(logado, "", "");
        u = busca(u);
        try {
            u.limparRecados();
        } catch (Exception e) {
            throw e;
        }
    }

    public String decodificarRecado(String logado, int indice, String palavraChave) throws Exception {/////
        Usuario u = new Usuario(logado, "", "");
        u = busca(u);
        try {
            return u.decodificar(indice, palavraChave);
        } catch (Exception e) {
            throw e;
        }
    }

    public void removerLogin(String logado, String login) throws Exception {
        UsuarioAdmin u = new UsuarioAdmin(logado, "", "");
        Usuario usuarioDeletado = new Usuario(login, "", "");

        if (!contem(usuarioDeletado)) {
            throw exceptionsFactory.getException(3,"Usuário não encontrado.");
        } else {
            if (usuarioDeletado.equals(u)) {
                throw exceptionsFactory.getException(1,"Auto-Exclusão.");
            } else {
                u.removerUsuario(usuarios, usuarioDeletado);
            }
        }
    }

    public String exibirMural(String login) throws Exception {
        Usuario u = new Usuario(login, "", "");
        String info;
        u = busca(u);
        if (u == null) {
            throw exceptionsFactory.getException(3,"Login inválido.");
        } else {
            try {
                info = u.listaMurais();
            } catch (ListaVaziaException e) {
                throw e;
            }
            return info;
        }
    }

    public void enviarMural(String logado, String mensagem) {
        Usuario u = new Usuario(logado, "", "");
        u = busca(u);
        String recado = u.getLogin() + ":" + mensagem;
        u.adicionarMural(recado);

    }

    public void excluirMural(String logado) throws Exception {
        Usuario u = new Usuario(logado, "", "");
        u = busca(u);
        try {
            u.limparMural();
        } catch (Exception e) {
            throw e;
        }
    }

    public String exibirMatches(String logado) throws Exception {
        Usuario u = new Usuario(logado, "", "");
        String info;
        u = busca(u);
        try {
            info = u.listaDeMatches();
            return info;
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean adicionarMatch(String login, String logado)
            throws Exception {
        Usuario usReceptor = new Usuario(login, "", "");
        Usuario usEmissor = new Usuario(logado, "", "");
        if (usEmissor.equals(usReceptor)) {// Se ambos são iguais
            throw exceptionsFactory.getException(1,"Você não pode fazer um match com você mesmo.");
        } else if (!contem(usReceptor)) {// Se o receptor nao está na lista
            throw exceptionsFactory.getException(3,"Login não encontrado.");
        } else {
            try {
                usReceptor = busca(usReceptor);
                usEmissor = busca(usEmissor);
                boolean statusMatch = usEmissor.adicionarMatch(usReceptor);
                return statusMatch;
            } catch (JaPossuemMatchException | MatchJaFeitoException e) {
                throw e;
            }

        }
    }

    public String exibirMeusMatches(String logado) throws Exception {
        Usuario u = new Usuario(logado, "", "");
        String info;

        u = busca(u);
        try {
            info = u.listaDeMeusMatches();
            return info;
        } catch (Exception e) {
            throw e;
        }
    }

}
