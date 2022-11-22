package model;

public class Libro {

    public static final String ATR_TITULO = "nombre";
    public static final String ATR_AUTOR = "autor";
    public static final String ATR_NUM_PAG = "numPag";

    private String titulo;
    private Autor autor;
    private int numPag;


    public Libro() {
    }


    public Libro(String titulo, Autor autor, int numPag) {
        this.titulo = titulo;
        this.autor = autor;
        this.numPag = numPag;
    }


    @Override
    public String toString() {
        return "Libro [titulo=" + titulo + ", autor=" + autor + ", numPag=" + numPag + "]";
    }


    public void setNumPag(int numPag) {
        this.numPag = numPag;
    }


    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}