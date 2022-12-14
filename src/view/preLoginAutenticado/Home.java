package view.preLoginAutenticado;

import controller.controlador.PrincipalController;

import java.util.Scanner;

public class Home {

    // Nota: O sinal "/////" refere-se a trechos que foram alterados (visível no
    // vídeo da atividade)
    public void home() {
        Scanner in = new Scanner(System.in);
        int op;

        PrincipalController controllerPrincipal = new PrincipalController();
        LoginView viewLogin = new LoginView();
        CadastrarView viewCadastrar = new CadastrarView();
        CadastrarAdmView viewCadastrarAdm = new CadastrarAdmView();

        do {
            menuInicial();
            op = in.nextInt();
            while (op > 3 || op < 0) {
                System.out.println("Opção inválida.");
                menuInicial();
                op = in.nextInt();
            }
            switch (op) {
                case 1: // criação de conta
                    viewCadastrar.cadastrar(in, controllerPrincipal);
                    break;

                case 2: // login + interações do usuário
                    viewLogin.login(in, controllerPrincipal);
                    break;

                case 3: // Criação de conta ADM
                    viewCadastrarAdm.cadastrar(in, controllerPrincipal);
                    break;
            }

        } while (op != 0);
        in.close();
    }

    public void menuInicial() {
        System.out.println("Bem Vindo à UNIKUT, A Rede Social mais Antisocial do Mundo");

        System.out.println("1 - Cadastrar");
        System.out.println("2 - Login");
        System.out.println("3 - Cadastrar Admin");
        System.out.println("0 - Sair");
    }

}
