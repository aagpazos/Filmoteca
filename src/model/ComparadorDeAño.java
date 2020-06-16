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
public class ComparadorDeAño implements Comparator<Pelicula> {

    @Override
    public int compare(Pelicula p1, Pelicula p2) {
        return Integer.compare(p1.getAño(), p2.getAño());
    }

    

}
