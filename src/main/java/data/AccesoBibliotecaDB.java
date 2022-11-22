package data;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import model.Autor;
import model.Libro;

import java.util.ArrayList;

public class AccesoBibliotecaDB {

    static final String PATH_DB = "src/main/resources/biblioteca.yap";
    ObjectContainer db;


    //Constructor para decirle dónde está ubicada la base de datos
    public AccesoBibliotecaDB() {
        db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), PATH_DB);
    }


    //Método que usaremos para cerrar la bbdd en el main después de realizar todas las operaciones
    public void cerrarDB() {
        db.close();
    }


    //para insertar libros
    public void insertarLibro(Libro libro) {
        db.store(libro);
    }


    //para insertar autores
    public void insertarAutor(Autor autor) {
        db.store(autor);
    }



    public boolean comprobarNomAutor(String nombre) {
        //construcror por defecto:
        Autor autorNomb = new Autor();
        autorNomb.setNombre(nombre);

        //Busca por el filtro que le hemos puesto:
        ObjectSet<Autor> setAutores = db.queryByExample(autorNomb);

        //para contabilizar si ha encontrado el autor:
        boolean autorEncontrado = false;

        //Si encuentra autor para recuperar su 'id interno'. Solo entra si setAutores tiene dato
        if (setAutores.hasNext()) {
            //Y cambiamos a true, porque ha encontrado un dato
            autorEncontrado = true;
        }

        return autorEncontrado;
    }


    public ArrayList<Libro> consultarTodosDatos() {
        ArrayList<Libro> listaLibros = new ArrayList<Libro>();
        ObjectSet<Libro> setLibros = db.queryByExample(Libro.class);
        listaLibros.addAll(setLibros);

        return listaLibros;
    }


    public ArrayList<Libro> consultarNumPag(int numPag) {
        Query consulta = db.query();
        consulta.constrain(Libro.class);

        consulta.descend(Libro.ATR_NUM_PAG).constrain(numPag).greater().equal();

        ObjectSet<Libro> setLibros = consulta.execute();

        return new ArrayList<>(setLibros);
    }


    public int modificarNumPag(String titulo, int nuevoNumPag) {
        Libro libroModPag = new Libro();
        libroModPag.setTitulo(titulo);

        ObjectSet<Libro> setLibros = db.queryByExample(libroModPag);

        int cantLibMod = 0;

        while (setLibros.hasNext()) {
            libroModPag = setLibros.next();
            libroModPag.setNumPag(nuevoNumPag);
            db.store(libroModPag);
            cantLibMod++;
        }
        return cantLibMod;
    }


    public Autor recuperarAutor(String nombre) {
        Autor autorNomb = new Autor();
        autorNomb.setNombre(nombre);

        ObjectSet<Autor> setAutores = db.queryByExample(autorNomb);

        //Si encuentra autor para recuperar su 'id interno'. Solo entra si setAutores tiene dato
        if (setAutores.hasNext()) {
            //Asignamos a la variable esa persona:
            autorNomb = setAutores.next();
        }

        return autorNomb;
    }


}
