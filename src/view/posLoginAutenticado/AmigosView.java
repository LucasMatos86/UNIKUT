package view.posLoginAutenticado;

import controller.controlador.PrincipalController;
import java.util.Scanner;

public class AmigosView {

    public void amigos(Scanner in, String logado, int op, PrincipalController controllerPrincipal) {
        String login;

        switch (op) {
            case 1:// Adicionar amigo
                in.nextLine();
                System.out.print("Informe o login do usuário a ser adicionado: ");
                login = in.next();
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        try {
                            boolean pedidoPendente = controllerPrincipal.adicionarAmigo(login, logado);
                            if (pedidoPendente) {
                                System.out.println("\nPedido de amizade enviado. Aguardando aceitação.\n");
                             
                            } else {
                              System.out.println("\nPedido de amizade aceito! Vocês agora são amigos.\n");
                                
                            }
                            
                        } catch (Exception e) {
                          
                            System.out.println("\n"+e.getMessage()+ "\n");
                           
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
                menuListar();// Listar amigos e pedidos de amizade
                op = in.nextInt();
                while (op < 0 || op > 2) {
                    System.out.println("Opção inválida. Tente:");
                    menuListar();
                    op = in.nextInt();
                }
                switch (op) {
                    case 0:// Sair
                        break;
                    case 1:// Exibir lista de amigos
                        Thread t1 = new Thread(new Runnable() {
                            public void run() {
                                try {
                                    String exibirLista = controllerPrincipal.exibirAmigos(logado);
                                    System.out.println("Lista de amigos: ");
                                    System.out.println(exibirLista);
                                } catch (Exception e) {
                                    System.out.println(e.getMessage()+ "\n");
                                }
                            }
                        });
                        //t1.run();
                        t1.start();
                        try {
                            t1.join();          
                        } catch (InterruptedException e) {
                           
                        }
                        
                        break;
                    case 2: // Exibir pendentes
                        Thread t2 = new Thread(new Runnable() {
                            public void run() {
                                try {
                                    String exibirLista1 = controllerPrincipal.exibirPendentes(logado);
                                    System.out.println(
                                            "Envie convites para remover a pendência e que vocês sejam amigos!");
                                    System.out.println("Há convites de amizade pendentes de:");
                                    System.out.println(exibirLista1+ "\n");
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
                break;
        }
    }

    public void menuListar() {
        System.out.println("1 - Listar amigos");
        System.out.println("2 - Pedidos pendentes");
        System.out.println("0 - Sair");
    }
}
