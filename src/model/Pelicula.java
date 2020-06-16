/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aagpazos
 */
public class Pelicula implements Serializable {

    private String titulo;
    private int año;
    private int duracion;
    private String pais;
    private List<String> direccion;
    private String guion;
    private String musica;
    private String fotografia;
    private List<String> reparto;
    private String productora;
    private String genero;
    private String sinopsis;

    public Pelicula(String titulo, int año, int duracion, String pais, List<String> direccion, String guion, String musica, String fotografia, List<String> reparto, String productora, String genero, String sinopsis) {
        this.titulo = titulo;
        this.año = año;
        this.duracion = duracion;
        this.pais = pais;
        this.direccion = direccion;
        this.guion = guion;
        this.musica = musica;
        this.fotografia = fotografia;
        this.reparto = reparto;
        this.productora = productora;
        this.genero = genero;
        this.sinopsis = sinopsis;
    }

    public Pelicula() {
        this.titulo = "Unknown";
        this.año = 9999;
        this.duracion = 999;
        this.pais = "Unknown";
        direccion = new ArrayList<>();
        direccion.add("Unknown");
        this.guion = "Unknown";
        this.musica = "Unknown";
        this.fotografia = "Unknown";
        reparto = new ArrayList<>();
        reparto.add("Unknown");
        this.productora = "Unknown";
        this.genero = "Unknown";
        this.sinopsis = "Unknown";
    }

    public Pelicula stringToPelicula(String s) {
        Pelicula pc = new Pelicula();
        String[] p = s.split("#");

        if (p[0].length() > 0) {
            pc.titulo = p[0];
        }
        if (p[1].length() > 0) {
            pc.año = Integer.parseInt(p[1]);
        }
        if (p[2].length() > 0) {
            pc.duracion = Integer.parseInt(p[2]);
        }
        if (p[3].length() > 0) {
            pc.pais = p[3];
        }
        if (p[4].length() > 0) {
            pc.direccion = stringTabToStringList(p[4]);
        }
        if (p[5].length() > 0) {
            pc.guion = p[5];
        }
        if (p[6].length() > 0) {
            pc.musica = p[6];
        }
        if (p[7].length() > 0) {
            pc.fotografia = p[7];
        }
        if (p[8].length() > 0) {
            pc.reparto = stringTabToStringList(p[8]);
        }
        if (p[9].length() > 0) {
            pc.productora = p[9];
        }
        if (p[10].length() > 0) {
            pc.genero = p[10];
        }
        if (p[11].length() > 0) {
            pc.sinopsis = p[11];
        }

        return pc;

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
        String Columns = String.format("| %-48s | %4d | %8d | %-30s | %15s |",
                this.titulo, this.año, this.duracion, this.pais, this.genero);
        return Columns;
    }

    public String exportAllAtributesAsString() {
        String director = String.join(",", this.direccion);
        String actores = String.join(",", this.reparto);
        return String.format("TITULO: %s\n"
                + "AÑO: %d\n" + "DURACION: %d\n" + "PAIS: %s\n" + "DIRECCION: %s\n"
                + "GUION: %s\n" + "BANDA SONORA: %s\n" + "FOTOGRAFIA: %s\n"
                + "REPARTO: %s\n" + "PRODUCTORA: %s\n" + "GENERO: %s\n"
                + "SINOPSIS: %s\n", this.titulo, this.año, this.duracion, this.pais,
                director, this.guion, this.musica, this.fotografia,
                actores, this.productora, this.genero, this.sinopsis);

    }

    public String exportAsHTMLTableRow() {
        String director = String.join(",", this.direccion);
        String actores = String.join(",", this.reparto);
        return String.format("<TR>" + "<TD>%s</TD>" + "<TD>%d</TD>"
                + "<TD>%d</TD>" + "<TD>%s</TD>" + "<TD>%s</TD>" + "<TD>%s</TD>"
                + "<TD>%s</TD>" + "<TD>%s</TD>" + "<TD>%s</TD>" + "<TD>%s</TD>"
                + "<TD>%s</TD>" + "<TD>%s</TD>" + "</TR>", this.titulo, this.año, 
                this.duracion, this.pais, director, this.guion, this.musica, 
                this.fotografia,actores, this.productora, this.genero, this.sinopsis);
    }

    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<String> getDireccion() {
        return direccion;
    }

    public void setDireccion(List<String> direccion) {
        this.direccion = direccion;
    }

    public String getGuion() {
        return guion;
    }

    public void setGuion(String guion) {
        this.guion = guion;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public List<String> getReparto() {
        return reparto;
    }

    public void setReparto(List<String> reparto) {
        this.reparto = reparto;
    }

    public String getProductora() {
        return productora;
    }

    public void setProductora(String productora) {
        this.productora = productora;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getMusica() {
        return musica;
    }

    public void setMusica(String musica) {
        this.musica = musica;
    }

}
