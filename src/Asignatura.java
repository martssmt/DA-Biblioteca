import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Asignatura {

    // Atributos:

    private String nombre;
    private LinkedList<Libro> libros;

    // Constructor:

    public Asignatura (String nombre) {
        this.nombre=nombre;
        libros=new LinkedList<>();
    }

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

    public void crearLibro() {
        String tit;
        Libro nuevoLibro;
        boolean repetir;
        boolean esNuevo=true;
        do {
            repetir=false;
            tit = Utilidades.leerCadena("Introduce el nombre del libro ('fin' para volver): ");
            if (!tit.equalsIgnoreCase("fin")) {
                for (Libro libro : libros) {
                    if (libro.getTitulo().equalsIgnoreCase(tit)) {
                        nuevoLibro = libro;
                        esNuevo=false;
                        if (Utilidades.leerSiONo("El libro ya existe, ¿quieres añadir ejemplares?")) {
                            int num = Utilidades.leerNumPositivo("Introduzca el número de ejemplares a añadir: ");
                            nuevoLibro.anadirEjemplares(num);
                        } else repetir = true;
                        break;
                    }
                }
                if (esNuevo) {
                    int num = Utilidades.leerNumPositivo("Introduzca el número de ejemplares a añadir: ");
                    nuevoLibro=new Libro(tit,num);
                    anadirLibro(nuevoLibro);
                }
            }
        } while (repetir || !tit.equalsIgnoreCase("fin"));
    }

    /*
    public boolean eliminarLibro() { //Devuelve false si no se ha encontrado un libro bajo ese titulo
        boolean resp=false;
        Libro aEliminar=buscarLibroEnAsignatura();
        if (aEliminar!=null) {
            resp=libros.remove(aEliminar);
            System.out.println("Libro eliminado con éxito.");
        }
        return resp;
    }

    public Libro buscarLibroEnAsignatura() {
        Libro resp = null;      // Devuelve null si no se ha encontrado nada o si se ha decidido volver
        ArrayList<Libro> coincidencias = new ArrayList<>();
        String texto="";
        boolean volver=false;
        do {
            texto = Utilidades.leerCadena("Introduzca el nombre de la asignatura ('fin' para volver): ");
            if (!texto.equalsIgnoreCase("fin")) {
                for (Libro libro : libros) {
                    if (libro.getTitulo().toLowerCase().contains(texto.toLowerCase())) {
                        coincidencias.add(libro);
                    }
                }
                if (coincidencias.isEmpty())
                    System.out.println("No se ha encontrado ninguna coincidencia. Vuelva a intentarlo.");
            } else volver=true;
        } while (coincidencias.isEmpty()&&!volver);
        if (!volver) resp=seleccionarLibro(coincidencias);
        return resp;
    }

     */

    public Libro seleccionarLibro(ArrayList<Libro> listaLibros) {
        Libro select=null;
        int resp;
        System.out.println("\t"+nombre);
        for (int i=0; i<listaLibros.size(); i++) {
            System.out.println(i+". "+listaLibros.get(i).getTitulo());
        }
        System.out.println(listaLibros.size()+". Volver");
        System.out.println();
        resp=Utilidades.leerNumero("Seleccione el libro escogido: ",0,listaLibros.size());
        if (resp!=listaLibros.size()) select=listaLibros.get(resp);
        return select;
    }

}
