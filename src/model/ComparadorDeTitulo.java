/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Comparator;

/**
 *
 * @author aagpazos
 */
public class ComparadorDeTitulo implements Comparator<Pelicula> {
    @Override
    public int compare(Pelicula pelicula1, Pelicula pelicula2) {
        String p1 = pelicula1.getTitulo();
        String p2 = pelicula2.getTitulo();
        return p1.compareToIgnoreCase(p2);
    }

}
