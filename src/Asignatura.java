import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Asignatura {

    // Atributos:

    private String nombre;
    private LinkedList<Libro> libros;

    // Getters:

    public String getNombre() {
        return nombre;
    }

    public LinkedList<Libro> getLibros() {
        return libros;
    }

    // Metodos:

    public void anadirLibro(Libro libro) {
        libros.add(libro);
        Collections.sort(libros);
    }

    public boolean eliminarLibro(String titulo) { //Devuelve false si no se ha encontrado un libro bajo ese titulo
        boolean resp=false;
        Libro aEliminar=buscarLibroEnAsignatura(titulo);
        if (aEliminar!=null) {
            resp=libros.remove(aEliminar);
        }
        return resp;
    }

    public Libro buscarLibroEnAsignatura(String titulo) {
        Libro resp = null;      // Devuelve null si no se ha encontrado nada
        ArrayList<Libro> coincidencias = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                coincidencias.add(libro);
            }
        }
        if (coincidencias.isEmpty()) {
            System.out.println("No se han encontrado coincidencias.");
        } else resp=seleccionarLibro(coincidencias);
        return resp;
    }

    public Libro seleccionarLibro(ArrayList<Libro> coincidencias) {
        Libro select=null;
        int resp;
        System.out.println("Resultados:");
        for (int i = 0; i < coincidencias.size(); i++) {
            System.out.println(i + ". " + coincidencias.get(i).getTitulo());
        }
        System.out.println(coincidencias.size()+". Repetir búsqueda");
        resp=Utilidades.leerNumero("Seleccione el libro escogido: ",0,coincidencias.size());
        if (resp!=coincidencias.size()) select=coincidencias.get(resp);
        return select;
    }

}
