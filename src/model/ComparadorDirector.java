/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.time.LocalDate;
import java.util.Comparator;

/**
 *
 * @author aagpazos
 */
public class ComparadorDirector implements Comparator<Director> {
    @Override
    public int compare(Director dir1, Director dir2) {
        String di1 = dir1.getNacionalidad();
        String di2 = dir2.getNacionalidad();
        if(0 != (di1.compareToIgnoreCase(di2))){
            return di1.compareToIgnoreCase(di2);
        }else{
            LocalDate d1 = dir1.getFecha_nacimiento();
            LocalDate d2 = dir2.getFecha_nacimiento();
            return d1.compareTo(d2);
        }
    }

}
