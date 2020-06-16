/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aagpazos
 */
public class Director implements Serializable {

    private String nombre;
    private LocalDate fecha_nacimiento;
    private String nacionalidad;
    private String ocupacion;
    private List<String> peliculas;

    public Director(String nombre, LocalDate fecha_nacimiento, String nacionalidad, String ocupacion, List<String> peliculas) {
        this.nombre = nombre;
        this.fecha_nacimiento = fecha_nacimiento;
        this.nacionalidad = nacionalidad;
        this.ocupacion = ocupacion;
        this.peliculas = peliculas;
    }

    public Director() {
        this.nombre = "Unknown";
        this.fecha_nacimiento = LocalDate.of(9999, 9, 9);
        this.nacionalidad = "Unknown";
        this.ocupacion = "Unknown";
        peliculas = new ArrayList<>();
        peliculas.add("Unknown");
    }

    Director stringToDirector(String s) {
        Director di = new Director();
        String[] d = s.split("#");

        if (d[0].length() > 0) {
            di.nombre = d[0];
        }
        if (d[1].length() > 0) {
            di.fecha_nacimiento = LocalDate.parse(d[1]);
        }
        if (d[2].length() > 0) {
            di.nacionalidad = d[2];
        }
        if (d[3].length() > 0) {
            di.ocupacion = d[3];
        }
        if (d[4].length() > 0) {
            di.peliculas = stringTabToStringList(d[4]);
        }

        return di;
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
        String directorFilms = String.join(",", this.peliculas);
        String Columns = String.format("| %-20s | %10s | %15s | %-64s | %-65s |",
                this.nombre, this.fecha_nacimiento, this.nacionalidad,
                this.ocupacion, directorFilms);

        return Columns;
    }

    public String columnedDescription() {
        String directorFilms = String.join(",", this.peliculas);
        String Columns = String.format("%25s%12s%15s%65s%65s",
                this.nombre, this.fecha_nacimiento, this.nacionalidad,
                this.ocupacion, directorFilms);
        return Columns;
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

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public List<String> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<String> peliculas) {
        this.peliculas = peliculas;
    }

}
