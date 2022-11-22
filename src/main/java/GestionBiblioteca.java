import data.AccesoBibliotecaDB;
import model.Autor;
import model.Libro;

import java.util.ArrayList;
import java.util.Scanner;

public class GestionBiblioteca {

    static String[] OPCIONES_MENU = {"IL", "ML", "CT", "CL", "S"};
    static AccesoBibliotecaDB accesoDB;
    static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        accesoDB = new AccesoBibliotecaDB();

        String opcionMenu;
        boolean seguirMenu = true;

        while (seguirMenu) {
            opcionMenu = validarOpcion("***Indica la opción que deseas hacer***"
                    + "\nIL - Añadir un libro y autor"
                    + "\nML - Modificar el número de páginas de un libro"
                    + "\nCT - Mostrar todos los libros"
                    + "\nCL - Mostrar libros con más de 300 páginas"
                    + "\nS  - Salir del programa");

            if (opcionMenu.equalsIgnoreCase(OPCIONES_MENU[0])) addLibroAutor();
            else if (opcionMenu.equalsIgnoreCase(OPCIONES_MENU[1])) modNumPag();
            else if (opcionMenu.equalsIgnoreCase(OPCIONES_MENU[2])) consutarTodosLibros();
            else if (opcionMenu.equalsIgnoreCase(OPCIONES_MENU[3])) consultarLibLargos();
            else if (opcionMenu.equalsIgnoreCase(OPCIONES_MENU[4])) seguirMenu = false;
        }

        sc.close();
        accesoDB.cerrarDB();
    }


    private static void consultarLibLargos() {
        int numPag = 300;
        ArrayList<Libro> listaLibLarg = accesoDB.consultarNumPag(numPag);

        if (listaLibLarg.isEmpty()) {
            System.out.println("\nNo se ha encontrado libros con más de " + numPag + " páginas");
        } else {
            System.out.println("\nListado de libros con más de " + numPag + " páginas");
            for (Libro libro : listaLibLarg) {
                System.out.println(libro);
            }
        }
    }


    private static void consutarTodosLibros() {
        ArrayList<Libro> listaLibros = accesoDB.consultarTodosDatos();

        if (listaLibros.isEmpty()) {
            System.out.println("No se han encontrado datos");
        } else {
            System.out.println("Se han encontrado " + listaLibros.size() + " libros:");
            for (Libro libro : listaLibros) {
                System.out.println(libro);
            }
        }
    }


    private static void modNumPag() {
        String titulo = validarString("Introduce el título del libro que deseas modificar:");
        int nuevoNumPag = validadInt("Introduce el nuevo número de páginas:");

        int cantLibMod = accesoDB.modificarNumPag(titulo,nuevoNumPag);
        System.out.println("Se han modificado las páginas de " + cantLibMod + " libro");
    }


    private static void addLibroAutor() {
        Autor autor = comprobarAutor();

        Libro libro = validarLibro(autor);
        accesoDB.insertarLibro(libro);
    }


    private static Libro validarLibro(Autor autor) {
        String titulo = validarString("Introduce el título del libro:");
        int numPag = validadInt("Introduce el número de páginas");

        return new Libro(titulo, autor, numPag);
    }


    private static Autor comprobarAutor() {
        Autor autor;
        String nombre;
        String iniciales;
        String nacionalidad;
        boolean autorEncontrado;

        nombre = validarString("Introduce el nombre del autor:");
        autorEncontrado = accesoDB.comprobarNomAutor(nombre);

        if (!autorEncontrado) {
            iniciales = validarString("Introduce las iniciales del autor:");
            nacionalidad = validarString("Introduce la nacionalidad del autor:");

            autor = new Autor(iniciales, nombre, nacionalidad);
            accesoDB.insertarAutor(autor);
        } else {
            System.out.println("Ese autor ya estaba registrado.");
            autor = accesoDB.recuperarAutor(nombre);
        }

        return autor;
    }


    private static int validadInt(String mensaje) {
        int num = 0;
        boolean valNoK = true;

        while (valNoK) {
            try {
                System.out.println(mensaje);
                num = Integer.parseInt(sc.nextLine());
                if (num >= 100 && num <= 1000) valNoK = false;
                else System.out.println("Valor no válido");

            } catch (NumberFormatException e) {
                System.out.println("Debe introducirse un número");
            }
        }
        return num;
    }


    private static String validarString(String mensaje) {
        String cadena = null;
        boolean cadenaOk = false;

        while (!cadenaOk) {
            System.out.println(mensaje);
            cadena = sc.nextLine().trim();
            if (cadena.isEmpty()) System.out.println("Valor no válido. Prueba de nuevo:");
            else cadenaOk = true;
        }

        return cadena;
    }


    private static String validarOpcion(String mensaje) {
        String opcion = "";
        boolean continuar = true;

        while (continuar) {
            System.out.println(mensaje);
            opcion = sc.nextLine().toUpperCase();

            if (opcion.equalsIgnoreCase(OPCIONES_MENU[0]) || opcion.equalsIgnoreCase(OPCIONES_MENU[1]) || opcion.equalsIgnoreCase(OPCIONES_MENU[2])
                    || opcion.equalsIgnoreCase(OPCIONES_MENU[3]) || opcion.equalsIgnoreCase(OPCIONES_MENU[4])) {
                continuar = false;
            } else {
                System.out.println("Opción de menú no válida");

            }
        }

        return opcion;
    }


}