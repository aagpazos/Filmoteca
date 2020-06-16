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
public class ComparadorActor implements Comparator<Actor> {
    @Override
    public int compare(Actor act1, Actor act2) {
        if(0 != Integer.compare(act1.getAño_debut(), act2.getAño_debut())){
            return Integer.compare(act1.getAño_debut(), act2.getAño_debut());
        }else {
            String a1 = act1.getNombre();
            String a2 = act2.getNombre();
            return a1.compareToIgnoreCase(a2);
        }
    }

    

}
