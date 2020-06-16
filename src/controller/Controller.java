/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.time.LocalDate;
import model.Model;

/**
 *
 * @author aagpazos
 */
public class Controller {

    Model m = new Model();

    public void arranque() {
        m.arranque();
    }

    public void salida() {
        m.salida();
    }

    public void peliculasHTML(){
        m.peliculasHTML();
    }
    
    public void directoresCol(){
        m.directoresCol();
    }
    public String consultaPeliculas(String s) {
        String p = m.consultaPeliculas(s);
        return p;
    }

    public int busquedaPelicula(String s) {
        return m.busquedaPelicula(s);
    }

    public boolean modificarPelicula(int opcion, int pelicula, String modificacion) {
        return m.modificarPelicula(opcion, pelicula, modificacion);
    }

    public void añadirPelicula(String[] atributos, int[] añoDuracion) {
        m.añadirPelicula(atributos, añoDuracion);
    }

    public void borrarPelicula(int i) {
        m.borrarPelicula(i);
    }

    public void añadirDirector(String[] atributos, LocalDate nacimiento) {
        m.añadirDirector(atributos, nacimiento);
    }

    public void borrarDirector(int i) {
        m.borrarDirector(i);
    }

    public int busquedaDirector(String p) {
        return m.busquedaDirector(p);
    }

    public boolean modificarDirector(int opcion, int director, String modificacion) {
        return m.modificarDirector(opcion, director, modificacion);
    }

    public void añadirActor(String[] atributos, LocalDate nacimiento, int año) {
        m.añadirActor(atributos, nacimiento, año);
    }

    public void borrarActor(int i) {
        m.borrarActor(i);
    }

    public int busquedaActor(String p) {
        return m.busquedaActor(p);
    }

    public boolean modificarActor(int opcion, int actor, String modificacion) {
        return m.modificarActor(opcion, actor, modificacion);
    }
    
    public String[] peliculasActor(int i){
        String[] tmp = m.peliculasActor(i);
        return tmp;
    }

    public String[] peliculasColumns() {
        return m.peliculasAsColums();
    }

    public String[] directoresColumns() {
        return m.directoresAsColumns();
    }

    public String[] actoresColumns() {
        return m.actoresAsColumns();
    }

}
