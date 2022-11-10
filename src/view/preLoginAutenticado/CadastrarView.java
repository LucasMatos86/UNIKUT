package view.preLoginAutenticado;

import controller.controlador.PrincipalController;
import java.util.Scanner;

public class CadastrarView {

    public void cadastrar(Scanner in, PrincipalController controllerPrincipal){
        String login, senha, nome;

        in.nextLine();
        System.out.println("Insira os seguintes dados para criar sua conta:");
        System.out.print("Nome de login: ");
        login = in.next();
        System.out.print("Senha do usuário: ");
        senha = in.next();
        System.out.print("Nome de usuário (opcional. Seu nome será visto como " + "\"" + "convidado"
                + "\"" + " caso não preencha o espaço.): ");
        in.nextLine();
        nome = in.nextLine();
        if (nome.equals("")) {
            nome = "Convidado";
        }
        try {
            controllerPrincipal.cadastro(login, senha, nome);
            System.out.println("");
            System.out.println("Cadastro concluído com sucesso.");
            System.out.println("");
        } catch (Exception e) {
            System.out.println("");
            System.out.println("Já existe um usuário com este login. Cadastro não efetuado.");
            System.out.println("");
        }

    }
}
