/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.util.Scanner;

/**
 *
 * @author aagpazos
 */
public class View {

    Scanner sc = new Scanner(System.in);
    Controller c = new Controller();

    public void runMenu(String menu) {
        boolean salir = false;
        do {
            System.out.printf("%s: ", menu);
            String opcion = sc.nextLine();
            switch (opcion) {
                case "1":

                    break;
                case "2":

                    break;
                case "3":

                    break;
                case "q":
                    salir = preguntarSiSalimos();
                    break;
                default:
                    System.out.println("%nOpcion Incorrecta%n");
            }
        } while (!salir);
    }

    private boolean preguntarSiSalimos() {
        String opcion;
        do {
            System.out.printf("Deseo realmente salir de la aplicacion:  ");
            opcion = sc.nextLine();
        } while (!"sSnN".contains(opcion));
        System.out.println();
        return (!opcion.isEmpty() && "sS".contains(opcion));

    }
}
