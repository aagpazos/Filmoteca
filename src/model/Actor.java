package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author aagpazos
 */
public class Actor implements Serializable{

    private String nombre;
    private LocalDate fecha_nacimiento;
    private String nacionalidad;
    private int año_debut;
    private List<String> peliculas;

    public Actor(String nombre, LocalDate fecha_nacimiento, String nacionalidad, int año_debut, List<String> peliculas) {
        this.nombre = nombre;
        this.fecha_nacimiento = fecha_nacimiento;
        this.nacionalidad = nacionalidad;
        this.año_debut = año_debut;
        this.peliculas = peliculas;
    }

    public Actor() {
        this.nombre = "Unknown";
        this.fecha_nacimiento = LocalDate.of(9999, 9, 9);
        this.nacionalidad = "Unknown";
        this.año_debut = 9999;
        peliculas = new ArrayList<>();
        peliculas.add("Unknown");
    }

    Actor stringToActor(String s) {
        Actor ac = new Actor();
        String[] a = s.split("#");
        if (a[0].length() > 0) {
            ac.nombre = a[0];
        }
        if (a[1].length() > 0) {
            ac.fecha_nacimiento = LocalDate.parse(a[1]);
        }
        if (a[2].length() > 0) {
            ac.nacionalidad = a[2];
        }
        if (a[3].length() > 0) {
            ac.año_debut = Integer.parseInt(a[3]);
        }
        if (a[4].length() > 0) {
            ac.peliculas = stringTabToStringList(a[4]);
        }

        return ac;
    }

    public List<String> stringTabToStringList(String s) {
        String[] st = s.split("\\t");
        List<String> tmp = new ArrayList<>();

        for (String str : st) {
            tmp.add(str);
        }

        return tmp;
    }

    public String exportAsColumns() {
        String actorFilms = String.join(",", this.peliculas);
        String columns = String.format("| %-20s | %10s | %15s | %9d | %-140s |",
                this.nombre, this.fecha_nacimiento, this.nacionalidad,
                this.año_debut, actorFilms);
        return columns;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getAño_debut() {
        return año_debut;
    }

    public void setAño_debut(int año_debut) {
        this.año_debut = año_debut;
    }

    public List<String> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<String> peliculas) {
        this.peliculas = peliculas;
    }

}
