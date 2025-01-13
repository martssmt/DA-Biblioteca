import java.util.ArrayList;
import java.util.Collections;
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

    public void menu() {
        int opcion;
        String menu="\tMenú:\n";
        menu+="1. Mostrar inventario\n";
        menu+="2. Consultar inventario\n";
        menu+="3. Modificar inventario\n";
        menu+="4. Prestar libro\n";
        menu+="5. Devolver libro\n";
        menu+="6. Alumnos con préstamos\n";
        menu+="7. Modificar datos de un alumno\n";
        menu+="8. Terminar ejecución\n\n";
        do {
            System.out.print(menu);
            opcion=Utilidades.leerNumero("Introduzca la opción deseada: ",1,8);
            switch (opcion) {
                case 1: // Mostrar inventario
                    mostrarInventario();
                    break;
                case 2: // Consultar inventario
                    consultarInventario();
                    break;
                case 3: // Modificar inventario
                    modificarInventario();
                    break;
                case 4: // Prestar libro
                    prestar();
                    break;
                case 5: // Devolver libro
                    devolver();
                    break;
                case 6: // Alumnos con préstamos
                    mostrarAlumnosConPrestamos();
                    break;
                case 7: // Modificar datos de un alumno
                    modificarAlumnos();
                    break;
                case 8: // Terminar ejecución
                    terminar();
                    break;
            }
        } while (opcion!=8);
        System.out.println();
        System.out.println("Fin de la ejecución.");
        System.out.println("\u00A9 2025 Delegación de Alumnos de ETSI Sistemas Informáticos (UPM)");
    }

    // 1. Mostrar inventario

    public void mostrarInventario() {
        if (Utilidades.leerSiONo("¿Desea ver todo?")) {
            System.out.println(inventario.toString());
        } else {
            Asignatura asigAVer=inventario.buscarAsignatura();
            if (asigAVer!=null) {
                System.out.println(asigAVer.toString());
            }
        }
    }

    // 2. Consultar inventario

    public void consultarInventario() {

    }

    // 3. Modificar inventario

    public void modificarInventario() {
        int opcion;
        String menu="\tMenú:\n";
        menu+="1. Añadir libro nuevo\n";
        menu+="2. Eliminar libro\n";
        menu+="3. Añadir ejemplares\n";
        menu+="4. Eliminar ejemplares\n";
        menu+="5. Volver\n";
        do {
            System.out.println(menu);
            opcion=Utilidades.leerNumero("Introduzca la opción deseada: ", 1, 5);
            Libro modificado;
            switch (opcion) {
                case 1: // Añadir libro nuevo
                    Asignatura asignatura;
                    if (Utilidades.leerSiONo("¿Quieres añadir también una asignatura?")) {
                        asignatura=inventario.crearAsignatura();
                    } else {
                        asignatura=inventario.buscarAsignatura();
                    }
                    if (asignatura!=null) {
                        asignatura.crearLibro();
                    }
                    break;
                case 2: // Eliminar libro
                    modificado=busqueda();
                    for (Asignatura asignaturaEl:inventario.getAsignaturas()) {
                        for (Libro libro: asignaturaEl.getLibros()) {
                            if (libro.getTitulo().equals(modificado.getTitulo())) {
                                asignaturaEl.getLibros().remove(modificado);
                                break;
                            }
                        }
                    }
                    break;
                case 3: // Añadir ejemplares
                    modificado=busqueda();
                    System.out.println("Actualmente hay "+modificado.getEjemplares()+" ejemplares de "+modificado.getTitulo()+", de los cuales "+modificado.getPrestados()+" están prestados.");
                    System.out.println();
                    int numAnadir=Utilidades.leerNumPositivo("Introduce el número de ejemplares a añadir: ");
                    modificado.anadirEjemplares(numAnadir);
                    break;
                case 4: // Eliminar ejemplares
                    modificado=busqueda();
                    int numElim=Utilidades.leerNumPositivo("Introduce el número de ejemplares a eliminar: ");
                    modificado.eliminarEjemplares(numElim);
                    break;
            }
        } while (opcion!=5);
    }

    // 4. Prestar libro

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
            for (Alumno alumnoConPrestamo:alumnosConPrestamos) {
                if (alumno.getMatricula().equalsIgnoreCase(alumnoConPrestamo.getMatricula())) {
                    System.out.println("El alumno ya está en el registro. Se le añadirá un libro más.");
                    System.out.println();
                    alumno=alumnoConPrestamo;
                }
            }
        }
        if (!interrumpido) {
            Libro libroAPrestar=busqueda();
            if (libroAPrestar!=null) {
                Libro libroAIntroducirEnAlumno=libroAPrestar.prestarLibro(fecha);
                alumnosConPrestamos.add(alumno);
                Collections.sort(alumnosConPrestamos);
                alumno.prestar(libroAIntroducirEnAlumno);
            }
        }
    }

    // 5. Devolver libro

    public void devolver() {

    }

    // 6. Alumnos con préstamos

    public void mostrarAlumnosConPrestamos() {

    }

    // 7. Modificar datos de un alumno

    public void modificarAlumnos() {

    }

    // 8. Terminar ejecución

    public void terminar() {

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

    public Libro busqueda() {
        Libro resp;
        if (Utilidades.leerSiONo("¿Quiere buscar el libro por asignaturas?: ")) {
            Asignatura asignatura=inventario.buscarAsignatura();
            ArrayList<Libro> todos=new ArrayList<>(asignatura.getLibros());
            resp=asignatura.seleccionarLibro(todos);
        } else {
            resp=inventario.buscarLibro();
        }
        return resp; // Devuelve null si la búsqueda se ha interrumpido
    }

}
