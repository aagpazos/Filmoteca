/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import view.View;

/**
 *
 * @author aagpazos
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        View v = new View();
        v.runMenu("1.- Archivos \n"
                + "2.- Peliculas \n"
                + "3.- Directores \n"
                + "4.- Actores \n"
                + "5.- Listados \n"
                + "q.- Salir ");

    }
}
