import java.util.ArrayList;
import java.util.LinkedList;

public class InterfazUsuario {

    // Atributos:

    private LinkedList<Alumno> alumnosConPrestamos;
    private Inventario inventario;

    // Constructor:

    public InterfazUsuario() {
        alumnosConPrestamos=new LinkedList<>();
        inventario=new Inventario();
    }

    // Falta el constructor a partir de archivo

    // Menú

    ////////

    // 2. Prestar libro

    public void prestar() {
        System.out.print("Introduzca la fecha del día del préstamo (dd/mm/aaaa): ");
        String fecha=Utilidades.leerFecha("Introduzca la fecha del día del préstamo (dd/mm/aaaa): ");
        Alumno alumno;
        boolean interrumpido=false;
        if (Utilidades.leerSiONo("¿El alumno tiene algún libro en su posesión?")) {
            alumno=buscarAlumno();
            if (alumno==null) {
                System.out.println("Se ha interrumpido el préstamo.");
                interrumpido=true;
            }
        } else {
            alumno=Alumno.crearAlumno();
        }
        if (!interrumpido) {
            Libro libroAPrestar=busqueda(fecha);
            if (libroAPrestar==null) interrumpido=true;
            else {
                libroAPrestar.prestarLibro(fecha);
                alumnosConPrestamos.add(alumno);
                alumno.prestar(libroAPrestar);
            }
        }
    }

    // Comunes y útiles

    public Alumno buscarAlumno() {
        Alumno resp=null;
        ArrayList<Alumno> coincidencias = new ArrayList<>();
        String nom="";
        boolean volver=false;
        do {
            nom=Utilidades.leerCadena("Introduzca el nombre del alumno ('fin' para volver): ");
            if (!nom.equalsIgnoreCase("fin")) {
                for (Alumno alumno: alumnosConPrestamos) {
                    if (alumno.getNombre().toLowerCase().contains(nom.toLowerCase())) {
                        coincidencias.add(alumno);
                    }
                }
                if (coincidencias.isEmpty())
                    System.out.println("No se ha encontrado ninguna coincidencia. Vuelva a intentarlo.");
            } else volver=true;
        } while (coincidencias.isEmpty()&&!volver);
        if (!volver) resp=seleccionarAlumno(coincidencias);
        return resp;
    }

    public Alumno seleccionarAlumno(ArrayList<Alumno> lista) {
        Alumno select=null;
        int resp;
        System.out.println("\tResultados:");
        for (int i=0; i<lista.size(); i++) {
            System.out.println(i+". "+lista.get(i).getNombre());
        }
        System.out.println(lista.size()+". Volver");
        System.out.println();
        resp=Utilidades.leerNumero("Seleccione el alumno: ",0,lista.size());
        if (resp!=lista.size()) select=lista.get(resp);
        return select;
    }

    public Libro busqueda(String fecha) {
        Libro resp;
        if (Utilidades.leerSiONo("¿Quiere buscar el libro por asignaturas?: ")) {
            Asignatura asignatura=Inventario.buscarAsignatura();
            ArrayList<Libro> todos=new ArrayList<>(asignatura.getLibros());
            resp=asignatura.seleccionarLibro(todos);
        } else {
            resp=Inventario.buscarLibro();
        }
        return resp; // Devuelve null si la búsqueda se ha interrumpido
    }

}
