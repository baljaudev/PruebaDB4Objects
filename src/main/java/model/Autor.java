package model;

public class Autor {

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
        return "Autor -> Iniciales: " + iniciales + ". Nombre: " + nombre + ". Nacionalidad: " + nacionalidad;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}