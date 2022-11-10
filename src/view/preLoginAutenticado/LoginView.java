package view.preLoginAutenticado;

import controller.controlador.PrincipalController;
import view.posLoginAutenticado.UsuarioAdmView;
import view.posLoginAutenticado.UsuarioView;

import java.util.Scanner;

public class LoginView {

    public void login(Scanner in, PrincipalController controllerPrincipal) {

        String login, senha, logado;
        boolean estadoDeLogado = false, administrador = false;
        UsuarioView viewUsuario = new UsuarioView();
        UsuarioAdmView viewUsuarioAdm = new UsuarioAdmView();

        in.nextLine();
        System.out.print("Nome de login: ");
        login = in.next();
        System.out.print("Senha: ");
        senha = in.next();
        System.out.println("");
        try {
            controllerPrincipal.login(login, senha);
            estadoDeLogado = true;
            administrador = controllerPrincipal.tipoUsuario(login);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (estadoDeLogado) {
            System.out.println("Bem-vindo!");
            logado = login;
            if (!administrador) {
                viewUsuario.usuario(logado, controllerPrincipal, in);
            } else {
                logado = login;
                viewUsuarioAdm.usuario(logado, controllerPrincipal, in);
            }
        } else {
            System.out.println("Login ou Senha inválidos. Tente novamente.");
            System.out.println("");
        }
    }
}

