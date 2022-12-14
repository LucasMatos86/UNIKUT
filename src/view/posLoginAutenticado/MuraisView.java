package view.posLoginAutenticado;

import controller.controlador.PrincipalController;


import java.util.Scanner;

public class MuraisView {

    public void murais(Scanner in, String logado, int op, PrincipalController controllerPrincipal) {
        String login;

        String mural;
        switch (op) {
            case 0:// Sair
                break;
            case 1:// Exibir Murais
                System.out.println("Insira aqui o login de quem você deseja ver o mural: ");
                login = in.next();
                Thread t = new Thread(
                    ()->{
                        try {
                            String exibirMural = controllerPrincipal.exibirMural(login);
                            System.out.println("Mural:");
                            System.out.println(exibirMural+ "\n");
                
                      
                        } catch (Exception e) {
                            System.out.println(e.getMessage()+ "\n");
                        }
                    }
                );
               
                //t.run();
                t.start();
                try {
                    t.join();          
                } catch (InterruptedException e) {
                   
                }
                break;

            case 2: // Criar Mural
                System.out.println("Escreva sua mensagem para o mundo: ");
                in.nextLine();
                mural = in.nextLine();
                controllerPrincipal.enviarMural(logado, mural);
                System.out.println("Mural criado com sucesso.\n");
                break;

            case 3:// excluir mural
                Thread t1 = new Thread( ()->{  try {
                            controllerPrincipal.excluirMural(logado);
                            System.out.println("Seu Mural foi excluido com sucesso.\n");
                        } catch (Exception e) {
                            System.out.println(e.getMessage()+ "\n");
                        } } );
                      
               // t1.run();
               t1.start();
               try {
                   t1.join();          
               } catch (InterruptedException e) {
                  
               }
                break;
        }
    }
}
