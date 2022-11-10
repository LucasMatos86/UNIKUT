package view.posLoginAutenticado;

import controller.controlador.PrincipalController;
import java.util.Scanner;

public class MatchView {

    public void match(int op, String logado, Scanner in, PrincipalController controllerPrincipal) {
        String login;

        switch (op) {
            case 0:
                break;
            case 1:// matches mútuos
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        try {
                            String exibirListas1 = controllerPrincipal.exibirMatches(logado);
                            System.out.println("Lista de matches:");
                            System.out.println(exibirListas1+ "\n");
                        } catch (Exception e) {
                            System.out.println(e.getMessage()+ "\n");
                        }
                    }
                });
   
                //t.run();
                t.start();
                try {
                    t.join();          
                } catch (InterruptedException e) {
                   
                }
                break;

            case 2:
                in.nextLine();// adicionar
                System.out.println("Informe o login do usuário a ser solicitado o match:");
                login = in.next();
                Thread t1 = new Thread(new Runnable() {
                    public void run() {
                        try {
                            boolean matchMutuo = controllerPrincipal.adicionarMatch(login, logado);
                            if (matchMutuo) {
                                System.out.println("Você deu um match, mas o outro ainda não deu um match em você.");
                            } else {
                                System.out.println("MATCH! Agora vocês dois tem um match.");
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage()+ "\n");
                        }
                    }
                });
               
                //t.run();
                t1.start();
                try {
                    t1.join();          
                } catch (InterruptedException e) {
                   
                }
                break;

            case 3:// matches não-mútuos
                Thread t2 = new Thread(new Runnable() {
                    public void run() {
                        try {
                            String ExibirMatchs = controllerPrincipal.exibirMeusMatches(logado);
                            System.out.println("Aguardando Match Mutuo: ");
                            System.out.println(ExibirMatchs+ "\n");
                        } catch (Exception e) {
                            System.out.println(e.getMessage()+ "\n");
                        }
                    }
                });
               //t2.run();
                t2.start();
                try {
                    t2.join();          
                } catch (InterruptedException e) {
                   
                }
                break;

        }
    }
}
