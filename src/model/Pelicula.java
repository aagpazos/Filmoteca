/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author aagpazos
 */
public class Pelicula {

    Director di = new Director();
    private String[] titulo;
    private int año;
    private int duracion;
    private String[] pais;
    private List<Director> direccion;
    private String[] guion;
    private String[] fotografia;
    private List<Actor> reparto;
    private String[] productora;
    private String[] genero;
    private String[] sinopsis;

    public Pelicula(String[] titulo, int año, int duracion, String[] pais, List<Director> direccion, String[] guion, String[] fotografia, List<Actor> reparto, String[] productora, String[] genero, String[] sinopsis) {
        this.titulo = titulo;
        this.año = año;
        this.duracion = duracion;
        this.pais = pais;
        this.direccion = direccion;
        this.guion = guion;
        this.fotografia = fotografia;
        this.reparto = reparto;
        this.productora = productora;
        this.genero = genero;
        this.sinopsis = sinopsis;
    }

    public Director getDi() {
        return di;
    }

    public void setDi(Director di) {
        this.di = di;
    }

    public String[] getTitulo() {
        return titulo;
    }

    public void setTitulo(String[] titulo) {
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

    public String[] getPais() {
        return pais;
    }

    public void setPais(String[] pais) {
        this.pais = pais;
    }

    public List<Director> getDireccion() {
        return direccion;
    }

    public void setDireccion(List<Director> direccion) {
        this.direccion = direccion;
    }

    public String[] getGuion() {
        return guion;
    }

    public void setGuion(String[] guion) {
        this.guion = guion;
    }

    public String[] getFotografia() {
        return fotografia;
    }

    public void setFotografia(String[] fotografia) {
        this.fotografia = fotografia;
    }

    public List<Actor> getReparto() {
        return reparto;
    }

    public void setReparto(List<Actor> reparto) {
        this.reparto = reparto;
    }

    public String[] getProductora() {
        return productora;
    }

    public void setProductora(String[] productora) {
        this.productora = productora;
    }

    public String[] getGenero() {
        return genero;
    }

    public void setGenero(String[] genero) {
        this.genero = genero;
    }

    public String[] getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String[] sinopsis) {
        this.sinopsis = sinopsis;
    }

}
