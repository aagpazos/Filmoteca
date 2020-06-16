/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.coti.tools.Rutas;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 *
 * @author aagpazos
 */
public class Filmoteca {

    private List<Actor> actores;
    private List<Director> directores;
    private List<Pelicula> peliculas;

    private final String nameOfFolder = "Filmot18";
    private final String peliculasBin = "peliculas.bin";
    private final String actoresBin = "actores.bin";
    private final String directoresBin = "directores.bin";

    private final String peliculasTxt = "peliculas.txt";
    private final String actoresTxt = "actores.txt";
    private final String directoresTxt = "directores.txt";

    private final String peliculasHTML = "peliculas.html";
    private final String directoresCol = "directores.col";
    
    public void arranque() {
        if (loadBinFile(peliculasBin) == true) {
            System.out.println("Se ha importado correctamente " + peliculasBin);
        } else {
            loadPeliculasTxtFile();
            System.out.println("Se ha importado correctamente " + peliculasTxt);
        }

        if (loadBinFile(actoresBin) == true) {
            System.out.println("Se ha importado correctamente " + actoresBin);
        } else {
            loadActoresTxtFile();
            System.out.println("Se ha importado correctamente " + actoresTxt);
        }

        if (loadBinFile(directoresBin) == true) {
            System.out.println("Se ha importado correctamente " + directoresBin);
        } else {
            loadDirectoresTxtFile();
            System.out.println("Se ha importado correctamente " + directoresTxt);
        }
    }

    public boolean loadBinFile(String nameOfFile) {
        Path p = Rutas.pathToFileInFolderOnDesktop(nameOfFolder, nameOfFile);
        FileInputStream fis;
        BufferedInputStream bis;
        ObjectInputStream ois = null;
        boolean importado = true;

        if (!Files.exists(p)) {
            System.err.println("El archivo " + nameOfFile + " no existe");
            return false;
        }

        try {
            fis = new FileInputStream(p.toFile());
            bis = new BufferedInputStream(fis);
            ois = new ObjectInputStream(bis);

            switch (nameOfFile) {
                case "peliculas.bin":
                    peliculas = (ArrayList<Pelicula>) ois.readObject();
                    break;
                case "actores.bin":
                    actores = (ArrayList<Actor>) ois.readObject();
                    break;
                case "directores.bin":
                    directores = (ArrayList<Director>) ois.readObject();
                    break;
                default:
                    System.err.println("No fue posible encontrar nameOfFile");
                    importado = false;
            }
            ois.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("No fue posible leer el archivo " + nameOfFile);
            System.out.println(ex.toString());
            importado = false;
        } finally {
            if (null != ois) {
                try {
                    ois.close();
                } catch (IOException ex) {
                    System.err.println("No fue posible cerrar el archivo");
                }
            }

        }
        return importado;
    }

    public void loadPeliculasTxtFile() {
        Path p = Rutas.pathToFileInFolderOnDesktop(nameOfFolder, peliculasTxt);

        if (!Files.exists(p)) {
            System.err.println("El arhivo " + peliculasTxt + " no existe");
        }
        List<String> tmp;

        try {
            tmp = Files.readAllLines(p);
        } catch (IOException ex) {
            System.out.println("Error al leer el archivo " + peliculasTxt);
            return;
        }

        this.peliculas = new ArrayList<>();
        Pelicula P = new Pelicula();

        if (!tmp.isEmpty()) {
            for (String s : tmp) {
                this.peliculas.add(P.stringToPelicula(s));
            }
        }

    }

    public void loadActoresTxtFile() {
        Path p = Rutas.pathToFileInFolderOnDesktop(nameOfFolder, actoresTxt);

        if (!Files.exists(p)) {
            System.err.println("El arhivo " + actoresTxt + " no existe");
        }
        List<String> tmp;

        try {
            tmp = Files.readAllLines(p);
        } catch (IOException ex) {
            System.out.println("Error al leer el archivo " + actoresTxt);
            return;
        }

        this.actores = new ArrayList<>();
        Actor a = new Actor();

        if (!tmp.isEmpty()) {
            for (String s : tmp) {
                this.actores.add(a.stringToActor(s));
            }
        }

    }

    public void loadDirectoresTxtFile() {
        Path p = Rutas.pathToFileInFolderOnDesktop(nameOfFolder, directoresTxt);

        if (!Files.exists(p)) {
            System.err.println("El arhivo " + directoresTxt + " no existe");
        }
        List<String> tmp;

        try {
            tmp = Files.readAllLines(p);
        } catch (IOException ex) {
            System.out.println("Error al leer el archivo " + directoresTxt);
            return;
        }

        this.directores = new ArrayList<>();
        Director d = new Director();

        if (!tmp.isEmpty()) {
            for (String s : tmp) {
                this.directores.add(d.stringToDirector(s));
            }
        }
    }

    public void savePeliculasHTML() {
        String path = Rutas.pathToFileInFolderOnDesktop(nameOfFolder, peliculasHTML).toString();

        try (PrintWriter pw = new PrintWriter(path)) {
            pw.println("<!DOCTYPE html><HTML>\n<HEAD><meta charset=\"UTF-8\">"
                    + "<H1>PELICULAS</H1></HEAD>\n<BODY>");
            pw.println("<TABLE BORDER = 1>");
            pw.println("<TR>" + "<TD>TITULO</TD>" + "<TD>AÑO</TD>"
                    + "<TD>DURACION</TD>" + "<TD>PAIS</TD>" + "<TD>DIRECTOR</TD>"
                    + "<TD>GUION</TD>" + "<TD>MUSICA</TD>" + "<TD>FOTOGRAFIA</TD>"
                    + "<TD>REPARTO</TD>" + "<TD>PRODUCTORA</TD>" + "<TD>GENERO</TD>"
                    + "<TD>SINOPSIS</TD>" + "</TR>");
            for (Pelicula p : this.peliculas) {
                pw.println(p.exportAsHTMLTableRow());
            }
            pw.close();
            System.out.println("Se ha creado correctamente el archivo HTML");
        } catch (FileNotFoundException ex) {
            System.err.println("No se ha podido crear el archivo HTML");
        }
    }
    
       public void saveDirectoresColumned() {
        String path = Rutas.pathToFileInFolderOnDesktop(nameOfFolder, directoresCol).toString();

        try (PrintWriter pw = new PrintWriter(path)) {
            for (Director d: this.directores) {
                pw.println(d.columnedDescription());
            }
            pw.close();
            System.out.println("Se ha creado correctamente el archivo directores.col");
        } catch (FileNotFoundException ex) {
            System.err.println("No se ha podido crear el archivo directores.col");
        }
    }

    public void salida() {
        saveBinFiles(peliculasBin);
        saveBinFiles(directoresBin);
        saveBinFiles(actoresBin);
    }

    public void saveBinFiles(String nameOfFile) {
        Path p = Rutas.pathToFileInFolderOnDesktop(nameOfFolder, nameOfFile);
        FileOutputStream fos;
        BufferedOutputStream bos;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(p.toFile());
            bos = new BufferedOutputStream(fos);
            oos = new ObjectOutputStream(bos);
            switch (nameOfFile) {
                case "peliculas.bin":
                    oos.writeObject(peliculas);
                    break;
                case "actores.bin":
                    oos.writeObject(actores);
                    break;
                case "directores.bin":
                    oos.writeObject(directores);
                    break;
                default:
                    System.err.println("No fue posible encontrar nameOfFile");
            }
            oos.close();
            System.out.println("Se ha guardado correctamente " + nameOfFile);
        } catch (IOException ex) {
            System.err.println("No fue posible guardar el archivo " + nameOfFile);
            System.out.println(ex.toString());
        } finally {
            if (null != oos) {
                try {
                    oos.close();
                } catch (IOException ex) {
                    System.err.println("No fue posible cerrar el archivo" + nameOfFile);
                    System.out.println(ex.toString());
                }
            }
        }

    }

    public List<Pelicula> getPeliculasAsColums() {
        Comparator<Pelicula> c;
        c = new ComparadorDeTitulo();
        Collections.sort(peliculas, c);
        return peliculas;
    }
    
      public void orderPeliculasByYear() {
        Comparator<Pelicula> c;
        c = new ComparadorDeAño();
        Collections.sort(this.peliculas, c);
    }
    

    public List<Director> getDirectoresAsColumns() {
        Comparator<Director> c;
        c = new ComparadorDirector();
        Collections.sort(directores, c);
        return directores;
    }

    public List<Actor> getActoresAsColumns() {
        Comparator<Actor> c;
        c = new ComparadorActor();
        Collections.sort(actores, c);
        return actores;
    }

    public List<Actor> getActores() {
        return actores;
    }

    public void setActores(List<Actor> actores) {
        this.actores = actores;
    }

    public List<Director> getDirectores() {
        return directores;
    }

    public void setDirectores(List<Director> directores) {
        this.directores = directores;
    }

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

}
