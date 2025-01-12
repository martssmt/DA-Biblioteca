import java.util.ArrayList;
import java.util.LinkedList;

public class Inventario {

    // Atributos:

    private static LinkedList<Asignatura> asignaturas;

    // Constructor:

    public Inventario() {
        asignaturas=new LinkedList<>();
    }

    // toString:

    @Override
    public String toString() {
        StringBuilder resp= new StringBuilder();
        resp.append("\t\tBilioteca DA-ETSISI\n\n");
        for (Asignatura asignatura:asignaturas) {
            resp.append(asignatura.getNombre());
            resp.append("\n");
            for (Libro libro:asignatura.getLibros()) {
                resp.append(libro.toString());
            }
            resp.append("\n");
        }
        return resp.toString();
    }

    // Métodos:

    public static Asignatura buscarAsignatura() {
        boolean volver=false;
        Asignatura resp=null;
        ArrayList<Asignatura> coincidencias=new ArrayList<>();
        String texto;
        do {
            texto = Utilidades.leerCadena("Introduzca el nombre de la asignatura ('fin' para volver): ");
            if (!texto.equalsIgnoreCase("fin")) {
                for (Asignatura asignatura : asignaturas) {
                    if (asignatura.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                        coincidencias.add(asignatura);
                    }
                }
                if (coincidencias.isEmpty())
                    System.out.println("No se ha encontrado ninguna coincidencia. Vuelva a intentarlo.");
            } else volver=true;
        } while (coincidencias.isEmpty()&&!volver);
        if (!volver) resp=seleccionarAsignatura(coincidencias);
        return resp;
    }

    public static Asignatura seleccionarAsignatura(ArrayList<Asignatura> coincidencias) {
        Asignatura resp=null;
        System.out.println("\tResultados:");
        for (int i=0; i< coincidencias.size(); i++) {
            System.out.println(i+". "+coincidencias.get(i).getNombre());
        }
        System.out.println(coincidencias.size()+". Volver");
        System.out.println();
        int opcion=Utilidades.leerNumero("Seleccione la asignatura: ",0,coincidencias.size());
        if (opcion!=coincidencias.size()) resp= coincidencias.get(opcion);
        return resp;
    }

    public static Libro buscarLibro() {
        boolean volver=false;
        Libro resp=null;
        ArrayList<Libro> coincidencias=new ArrayList<>();
        String texto;
        do {
            texto = Utilidades.leerCadena("Introduzca el nombre del libro ('fin' para volver): ");
            if (!texto.equalsIgnoreCase("fin")) {
                for (Asignatura asignatura : asignaturas) {
                    for (Libro libro:asignatura.getLibros()) {
                        if (libro.getTitulo().toLowerCase().contains(texto.toLowerCase())) {
                            coincidencias.add(libro);
                        }
                    }
                }
                if (coincidencias.isEmpty())
                    System.out.println("No se ha encontrado ninguna coincidencia. Vuelva a intentarlo.");
            } else volver=true;
        } while (coincidencias.isEmpty()&&!volver);
        if (!volver) resp=seleccionarLibro(coincidencias);
        return resp;
    }

    public static Libro seleccionarLibro(ArrayList<Libro> lista) {
        Libro resp=null;
        System.out.println("\tResultados:");
        for (int i=0; i< lista.size(); i++) {
            System.out.println(i+". "+lista.get(i).getTitulo());
        }
        System.out.println(lista.size()+". Volver");
        System.out.println();
        int opcion=Utilidades.leerNumero("Seleccione la asignatura: ",0,lista.size());
        if (opcion!=lista.size()) resp= lista.get(opcion);
        return resp;
    }

}
