package view.preLoginAutenticado;

import controller.controlador.PrincipalController;

import java.util.Scanner;

public class CadastrarAdmView {

    public void cadastrar(Scanner in, PrincipalController controllerPrincipal) {
        String login, senha, nome, codigoDeAdmin;

        in.nextLine();
        System.out.println("Insira os seguintes dados para criar sua conta de administrador:");
        System.out.print("Nome de login: ");
        login = in.next();
        System.out.print("Senha do usuário: ");
        senha = in.next();
        System.out.print("Nome de usuário (opcional. Seu nome será visto como " + "\"" + "Admin"
                + "\"" + " caso não preencha o espaço.): ");
        in.nextLine();
        nome = in.nextLine();
        if (nome.equals("")) {
            nome = "Admin";
        }
        System.out.println("Digite o código de autenticação de criação do administrador: ");
        codigoDeAdmin = in.next();
        try {
            controllerPrincipal.cadastro(login, senha, nome, codigoDeAdmin);
            System.out.println("");
            System.out.println("Cadastro concluído com sucesso.");
            System.out.println("");
        } catch (Exception e) {
            System.out.println("");
            System.out.println(e.getMessage());
            System.out.println("");
        }
    }
}
