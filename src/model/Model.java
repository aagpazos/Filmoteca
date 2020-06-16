/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author aagpazos
 */
public class Model {

    Filmoteca fm = new Filmoteca();

    public void arranque() {
        fm.arranque();
    }

    public void salida() {
        fm.salida();
    }
    
    public void peliculasHTML(){
         fm.savePeliculasHTML();
    }
    
    public void directoresCol(){
        fm.saveDirectoresColumned();
    }

    public int busquedaPelicula(String s) {
        for (int i = 0; i < fm.getPeliculas().size(); i++) {
            if (0 == s.compareToIgnoreCase(fm.getPeliculas().get(i).getTitulo())) {
                return i;
            }
        }
        return -1;
    }

    public int busquedaDirector(String s) {
        for (int i = 0; i < fm.getDirectores().size(); i++) {
            if (0 == s.compareToIgnoreCase(fm.getDirectores().get(i).getNombre())) {
                return i;
            }
        }
        return -1;
    }

    public int busquedaActor(String s) {
        for (int i = 0; i < fm.getActores().size(); i++) {
            if (0 == s.compareToIgnoreCase(fm.getActores().get(i).getNombre())) {
                return i;
            }
        }
        return -1;
    }

    public String consultaPeliculas(String s) {
        int i = busquedaPelicula(s);
        if (i != -1) {
            return fm.getPeliculas().get(i).exportAllAtributesAsString();
        }

        return null;
    }

    public boolean modificarPelicula(int opcion, int pelicula, String s) {
        switch (opcion) {
            case 1:
                try {
                    int año = Integer.parseInt(s);
                    fm.getPeliculas().get(pelicula).setAño(año);
                    return true;
                } catch (NumberFormatException ex) {
                    System.out.println("El año debe ser un numero valido");
                    return false;
                }

            case 2:
                try {
                    int duracion = Integer.parseInt(s);
                    fm.getPeliculas().get(pelicula).setDuracion(duracion);
                    return true;
                } catch (NumberFormatException ex) {
                    System.out.println("El año debe ser un numero valido");
                    return false;
                }
            case 3:
                fm.getPeliculas().get(pelicula).setPais(s);
                return true;
            case 4:
                fm.getPeliculas().get(pelicula).setGuion(s);
                return true;
            case 5:
                fm.getPeliculas().get(pelicula).setMusica(s);
                return true;
            case 6:
                fm.getPeliculas().get(pelicula).getFotografia();
                return true;
            case 7:
                fm.getPeliculas().get(pelicula).setProductora(s);
                return true;
            case 8:
                fm.getPeliculas().get(pelicula).setGenero(s);
                return true;
            case 9:
                fm.getPeliculas().get(pelicula).setSinopsis(s);
                return true;
            default:
                return false;
        }
    }

    public void borrarPelicula(int i) {
        String pelicula = fm.getPeliculas().get(i).getTitulo();
        String direccionTemp = String.join("#", fm.getPeliculas().get(i).getDireccion());
        String[] direccion = direccionTemp.split("#");
        for (String dir1 : direccion) {
            int dir = busquedaDirector(dir1);
            if (dir != -1) {
                int k = fm.getDirectores().get(dir).getPeliculas().indexOf(pelicula);
                if (-1 != k) {
                    System.out.println("Se ha eliminado la pelicula " + pelicula
                            + " de la coleccion del director " + dir1);
                    System.out.println("");
                    fm.getDirectores().get(dir).getPeliculas().remove(k);
                }
            }
        }

        String actoresTemp = String.join("-", fm.getPeliculas().get(i).getReparto());
        String[] actores = actoresTemp.split("-");
        for (String act1 : actores) {
            int act = busquedaActor(act1);
            if (-1 != act) {
                int k = fm.getActores().get(act).getPeliculas().indexOf(pelicula);
                if (k != -1) {
                    fm.getActores().get(act).getPeliculas().remove(k);
                    System.out.println("Se ha eliminado la pelicula " + pelicula
                            + " de la coleccion del actor " + act1);
                }
            }
        }
        fm.getPeliculas().remove(i);
    }

    public void añadirPelicula(String[] atributos, int[] añoDuracion) {
        String[] directores = atributos[2].split(", ");
        for (String d1 : directores) {
            int dir = busquedaDirector(d1);
            if (-1 != dir) {
                fm.getDirectores().get(dir).getPeliculas().add(atributos[0]);
                System.out.println("Se ha añadido la pelicula a la coleccion "
                        + " del director " + d1);
            } else {
                Director d = new Director();
                d.setNombre(d1);
                List<String> tmp = new ArrayList<>();
                tmp.add(atributos[0]);
                d.setPeliculas(tmp);
                System.out.println("Se ha añadido el director " + d1 + " a la"
                        + " coleccion de directores");
                fm.getDirectores().add(d);

            }
        }

        String[] reparto = atributos[6].split(", ");
        for (String act1 : reparto) {
            int act = busquedaActor(act1);
            if (-1 != act) {
                fm.getActores().get(act).getPeliculas().add(atributos[0]);
                System.out.println("Se ha añadido la pelicula a la coleccion "
                        + " del actor " + act1);
            } else {
                Actor a = new Actor();
                a.setNombre(act1);
                List<String> tmp = new ArrayList<>();
                tmp.add(atributos[0]);
                a.setPeliculas(tmp);
                System.out.println("Se ha añadido el actor " + act1 + " a la"
                        + " coleccion de actores");
                fm.getActores().add(a);
            }
        }

        List<String> direccion = new ArrayList<>();
        Collections.addAll(direccion, directores);
        List<String> actores = new ArrayList<>();
        Collections.addAll(actores, reparto);

        Pelicula p = new Pelicula(atributos[0], añoDuracion[0], añoDuracion[1],
                atributos[1], direccion, atributos[3], atributos[4], atributos[5],
                actores, atributos[7], atributos[8], atributos[9]);
        fm.getPeliculas().add(p);
    }

    public void añadirDirector(String[] atributos, LocalDate nacimiento) {
        String[] peliculas = atributos[3].split(", ");
        List<String> films = new ArrayList<>();
        Collections.addAll(films, peliculas);
        Director d = new Director(atributos[0], nacimiento, atributos[1],
                atributos[2], films);
        fm.getDirectores().add(d);
    }

    public void borrarDirector(int i) {
        String peliculasTemp = String.join("#", fm.getDirectores().get(i).getPeliculas());
        String[] peliculas = peliculasTemp.split("#");
        boolean peliculasEnAlta = false;

        for (String p : peliculas) {
            int pe = busquedaPelicula(p);
            if (pe != -1) {
                System.out.printf("%s, ", p);
                peliculasEnAlta = true;
            }
        }

        if (peliculasEnAlta == true) {
            System.out.printf("se encuentra(n) en la coleccion de peliculas."
                    + " No se puede borrar el director");
        } else {
            fm.getDirectores().remove(i);
            System.out.println("El director se ha eliminado correctamente");
        }

    }

    public boolean modificarDirector(int opcion, int director, String s) {
        switch (opcion) {
            case 1:
                try {
                    LocalDate nacimiento = LocalDate.parse(s);
                    fm.getDirectores().get(director).setFecha_nacimiento(nacimiento);
                    return true;
                } catch (DateTimeParseException ex) {
                    System.out.println("La fecha de nacimiento debe ser valida(AÑO-MES-DIA)");
                    return false;
                }
            case 2:
                fm.getDirectores().get(director).setNacionalidad(s);
                return true;
            case 3:
                fm.getDirectores().get(director).setOcupacion(s);
                return true;
            default:
                return false;
        }
    }

    public void añadirActor(String[] atributos, LocalDate nacimiento, int año) {
        String[] peliculas = atributos[2].split(", ");
        List<String> films = new ArrayList<>();
        Collections.addAll(films, peliculas);
        Actor a = new Actor(atributos[0], nacimiento, atributos[1], año, films);
        fm.getActores().add(a);
    }

    public void borrarActor(int i) {
        String peliculasTemp = String.join("#", fm.getActores().get(i).getPeliculas());
        String[] peliculas = peliculasTemp.split("#");
        boolean peliculasEnAlta = false;
        for (String p : peliculas) {
            int pe = busquedaPelicula(p);
            if (pe != -1) {
                System.out.printf("%s, ", p);
                peliculasEnAlta = true;
            }
        }
        if (peliculasEnAlta == true) {
            System.out.printf("se encuentra(n) en la coleccion de peliculas"
                    + " No se puede borrar el actor");
        } else {
            fm.getActores().remove(i);
            System.out.println("El director se ha eliminado correctamente");
        }

    }

    public boolean modificarActor(int opcion, int actor, String s) {
        switch (opcion) {
            case 1:
                try {
                    LocalDate nacimiento = LocalDate.parse(s);
                    fm.getActores().get(actor).setFecha_nacimiento(nacimiento);
                    return true;
                } catch (DateTimeParseException ex) {
                    System.out.println("La fecha de nacimiento debe ser valida(AÑO-MES-DIA)");
                    return false;
                }
            case 2:
                fm.getActores().get(actor).setNacionalidad(s);
                return true;
            case 3:
                try {
                    int añoDebut = Integer.parseInt(s);
                    fm.getActores().get(actor).setAño_debut(añoDebut);
                    return true;
                } catch (NumberFormatException ex) {
                    System.out.println("El año debe ser un numero valido");
                    return false;
                }
            default:
                return false;
        }
    }

    public String[] peliculasActor(int actor) {
        String peliculasTemp = String.join("#", fm.getActores().get(actor).getPeliculas());
        String[] peliculas = peliculasTemp.split("#");
        String[] tmp = new String[peliculas.length];
        fm.orderPeliculasByYear();
        for (int i = 0; i < peliculas.length; i++) {
            int pe = busquedaPelicula(peliculas[i]);
            if (-1 != pe) {
                tmp[i] = fm.getPeliculas().get(pe).exportAsColumns();
            } else { //Si una pelicula no aparece en la coleccion de peliculas se muestra tambien pero los otros atributos vacios
                tmp[i] = String.format("| %-48s | %4s | %8s | %-30s | %15s |",
                        peliculas[i], "", "", "", "");
            }
        }
        return tmp;
    }

    public String[] peliculasAsColums() {
        if (null == fm.getPeliculasAsColums().get(0)) {
            return null;
        }

        String[] tmp = new String[fm.getPeliculasAsColums().size()];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = fm.getPeliculasAsColums().get(i).exportAsColumns();
        }
        return tmp;
    }

    public String[] directoresAsColumns() {
        if (null == fm.getDirectoresAsColumns().get(0)) {
            return null;
        }

        String[] tmp = new String[fm.getDirectoresAsColumns().size()];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = fm.getDirectoresAsColumns().get(i).exportAsColumns();
        }
        return tmp;
    }

    public String[] actoresAsColumns() {
        if (null == fm.getActoresAsColumns().get(0)) {
            return null;
        }

        String[] tmp = new String[fm.getActoresAsColumns().size()];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = fm.getActoresAsColumns().get(i).exportAsColumns();
        }
        return tmp;
    }

}
