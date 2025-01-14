import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Asignatura implements Comparable<Asignatura>{

    // Atributos:

    private final String nombre;
    private final LinkedList<Libro> libros;

    // Constructor:

    public Asignatura(String nombre) {
        this.nombre = nombre;
        libros = new LinkedList<>();
    }

    // Getters:

    public String getNombre() {
        return nombre;
    }

    public LinkedList<Libro> getLibros() {
        return libros;
    }

    // toString

    @Override
    public String toString() {
        StringBuilder resp = new StringBuilder("\t" + nombre + ":\n");
        for (Libro libro : libros) {
            resp.append(libro.toString());
        }
        resp.append("\n");
        return resp.toString();
    }

    // compareTo

    @Override
    public int compareTo(Asignatura otraAsignatura) {
        return this.nombre.compareToIgnoreCase(otraAsignatura.nombre);
    }

    // Metodos:

    public void anadirLibro(Libro libro) {
        libros.add(libro);
        Collections.sort(libros);
    }

    public void crearLibro() {
        String tit;
        Libro nuevoLibro;
        boolean repetir;
        boolean esNuevo = true;
        do {
            repetir = false;
            tit = Utilidades.leerCadena("Introduce el nombre del libro ('fin' para volver): ");
            if (!tit.equalsIgnoreCase("fin")) {
                for (Libro libro : libros) {
                    if (libro.getTitulo().equalsIgnoreCase(tit)) {
                        nuevoLibro = libro;
                        esNuevo = false;
                        if (Utilidades.leerSiONo("El libro ya existe, ¿quieres añadir ejemplares?")) {
                            int num = Utilidades.leerNumPositivo("Introduzca el número de ejemplares a añadir: ");
                            nuevoLibro.anadirEjemplares(num);
                        } else repetir = true;
                        break;
                    }
                }
                if (esNuevo) {
                    int num = Utilidades.leerNumPositivo("Introduzca el número de ejemplares que tiene el libro: ");
                    nuevoLibro = new Libro(tit, num);
                    anadirLibro(nuevoLibro);
                }
            }
        } while (repetir || !tit.equalsIgnoreCase("fin"));
    }

    public Libro seleccionarLibro(ArrayList<Libro> listaLibros) {
        Libro select = null;
        int resp;
        System.out.println("\t" + nombre);
        for (int i = 0; i < listaLibros.size(); i++) {
            System.out.println(i + ". " + listaLibros.get(i).getTitulo());
        }
        System.out.println(listaLibros.size() + ". Volver");
        System.out.println();
        resp = Utilidades.leerNumero("Seleccione el libro escogido: ", 0, listaLibros.size());
        if (resp != listaLibros.size()) select = listaLibros.get(resp);
        return select;
    }

}
