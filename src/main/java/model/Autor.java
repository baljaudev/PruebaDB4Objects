package model;

public class Autor {

    public static final String ATR_INI = "iniciales";
    public static final String ATR_NOMBRE = "nombre";
    public static final String ATR_NACIONALIDAD = "nacionalidad";

    private String iniciales;
    private String nombre;
    private String nacionalidad;


    public Autor() {
    }

    public Autor(String iniciales, String nombre, String nacionalidad) {
        super();
        this.iniciales = iniciales;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }


    @Override
    public String toString() {
        return "Autor [iniciales=" + iniciales + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad + "]";
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}