import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class InterfazUsuario {

    // Atributos:

    private LinkedList<Alumno> alumnosConPrestamos;
    private Inventario inventario;

    // Constructor:

    public InterfazUsuario() {
        alumnosConPrestamos=new LinkedList<>();
        inventario=new Inventario();
    }

    // Menú

    public void menu() {
        int opcion;
        String menu="\tMenú:\n";
        menu+="1. Mostrar inventario\n";
        menu+="2. Modificar inventario\n";
        menu+="3. Consultar libros del inventario\n";
        menu+="4. Prestar libro\n";
        menu+="5. Devolver libro\n";
        menu+="6. Alumnos con préstamos\n";
        menu+="7. Modificar datos de un alumno\n";
        menu+="8. Terminar ejecución\n\n";
        do {
            System.out.print(menu);
            opcion=Utilidades.leerNumero("Introduzca la opción deseada: ",1,8);
            System.out.println();
            switch (opcion) {
                case 1: // Mostrar inventario
                    mostrarInventario();
                    break;
                case 2: // Modificar inventario
                    modificarInventario();
                    break;
                case 3: // Consultar libros del inventario
                    consultarInventario();
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

    // 2. Modificar inventario

    public void modificarInventario() {
        int opcion;
        String menu="\tMenú:\n";
        menu+="1. Añadir libro nuevo\n";
        menu+="2. Eliminar libro\n";
        menu+="3. Cambiar nombre a un libro\n";
        menu+="4. Añadir ejemplares\n";
        menu+="5. Eliminar ejemplares\n";
        menu+="6. Volver\n";
        do {
            System.out.println(menu);
            opcion=Utilidades.leerNumero("Introduzca la opción deseada: ", 1, 6);
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
                    boolean encontrado = false;
                    if (modificado!=null) {
                        for (Asignatura asignaturaEl : inventario.getAsignaturas()) {
                            for (Libro libro : asignaturaEl.getLibros()) {
                                if (libro.getTitulo().equals(modificado.getTitulo())) {
                                    asignaturaEl.getLibros().remove(modificado);
                                    encontrado = true;
                                    break;
                                }
                            }
                            if (encontrado) break;
                        }
                    }
                    break;
                case 3: // Cambiar nombre a un libro
                    modificado=busqueda();
                    String nuevoNom = Utilidades.leerCadena("Introduzca el título correcto: ");
                    modificado.setTitulo(nuevoNom);
                    break;
                case 4: // Añadir ejemplares
                    modificado=busqueda();
                    System.out.println("Actualmente hay "+modificado.getEjemplares()+" ejemplares de "+modificado.getTitulo()+", de los cuales "+modificado.getPrestados()+" están prestados.");
                    System.out.println();
                    int numAnadir=Utilidades.leerNumPositivo("Introduce el número de ejemplares a añadir: ");
                    modificado.anadirEjemplares(numAnadir);
                    break;
                case 5: // Eliminar ejemplares
                    modificado=busqueda();
                    System.out.println("Actualmente hay "+modificado.getEjemplares()+" ejemplares de "+modificado.getTitulo()+", de los cuales "+modificado.getPrestados()+" están prestados.");
                    System.out.println();
                    int numElim=Utilidades.leerNumPositivo("Introduce el número de ejemplares a eliminar: ");
                    modificado.eliminarEjemplares(numElim);
                    break;
            }
        } while (opcion!=6);
    }

    // 3. Consultar libros del inventario

    public void consultarInventario() {
        Libro consulta=inventario.buscarLibro();
        System.out.println(consulta.toString());
        System.out.println();
        if (consulta.getPrestados()>0) {
            System.out.println("\tAlumnos que tienen en préstamo un ejemplar:");
            for (Alumno alumno : alumnosConPrestamos) {
                for (Libro libro : alumno.getPrestamos()) {
                    if (consulta.equals(libro)) {
                        System.out.println(alumno.getNombre() + " desde " + libro.getFechaPrestamo());
                        break;
                    }
                }
            }
        }
        String menu="\tMenú:\n";
        menu+="1. Añadir ejemplares\n";
        menu+="2. Eliminar ejemplares\n";
        menu+="3. Cambiar nombre\n";
        menu+="4. Mostrar datos completos\n";
        menu+="5. Volver\n\n";
        int opcion;
        do {
            System.out.println(menu);
            opcion = Utilidades.leerNumero("Introduzca la opción deseada: ", 1, 5);
            switch (opcion) {
                case 1: // Añadir ejemplares
                    System.out.println("Actualmente hay "+consulta.getEjemplares()+" ejemplares de "+consulta.getTitulo()+", de los cuales "+consulta.getPrestados()+" están prestados.");
                    System.out.println();
                    int numAnadir=Utilidades.leerNumPositivo("Introduce el número de ejemplares a añadir: ");
                    consulta.anadirEjemplares(numAnadir);
                    break;
                case 2: // Eliminar ejemplares
                    System.out.println("Actualmente hay "+consulta.getEjemplares()+" ejemplares de "+consulta.getTitulo()+", de los cuales "+consulta.getPrestados()+" están prestados.");
                    System.out.println();
                    int numElim=Utilidades.leerNumPositivo("Introduce el número de ejemplares a eliminar: ");
                    consulta.eliminarEjemplares(numElim);
                    break;
                case 3: // Cambiar nombre
                    String nuevoNom = Utilidades.leerCadena("Introduzca el título correcto: ");
                    consulta.setTitulo(nuevoNom);
                    break;
                case 4: // Mostrar datos de nuevo
                    System.out.println(consulta.toString());
                    System.out.println();
                    if (consulta.getPrestados()>0) {
                        System.out.println("\tAlumnos que tienen en préstamo un ejemplar:");
                        for (Alumno alumno : alumnosConPrestamos) {
                            for (Libro libro : alumno.getPrestamos()) {
                                if (consulta.equals(libro)) {
                                    System.out.println(alumno.getNombre() + " desde " + libro.getFechaPrestamo());
                                    break;
                                }
                            }
                        }
                    }
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
        Alumno alumno=buscarAlumno();
        if (alumno!=null) {
            System.out.println();
            System.out.println("\t"+alumno.getNombre());
            Libro libroADev=null;
            for (int i=0; i<alumno.getPrestamos().size(); i++) {
                System.out.println(i+". "+ alumno.getPrestamos().get(i).toStringPrestado());
            }
            System.out.println(alumno.getPrestamos().size()+". Volver");
            System.out.println();
            int opc=Utilidades.leerNumero("Introduzca el número del libro a devolver: ", 0, alumno.getPrestamos().size());
            if (opc!=alumno.getPrestamos().size()) libroADev=alumno.getPrestamos().get(opc);
            if (libroADev!=null) {
                for (Alumno alumnoLista:alumnosConPrestamos) {
                    if (alumnoLista.equals(alumno)) {
                        alumnoLista.getPrestamos().remove(libroADev);
                        if (alumnoLista.getPrestamos().isEmpty()) {
                            alumnosConPrestamos.remove(alumnoLista);
                            System.out.println("El alumno no tiene más préstamos, por lo que se le ha eliminado del sistema.");
                            System.out.println();
                        }
                        boolean encontrado=false;
                        for (Asignatura asignatura:inventario.getAsignaturas()) {
                            for (Libro libro:asignatura.getLibros()) {
                                if (libro.equals(libroADev)) {
                                    if(libro.devolverLibro()) System.out.println("Libro devuelto con éxito.");
                                    System.out.println();
                                    encontrado=true;
                                    break;
                                }
                            }
                            if (encontrado) break;
                        }
                        break;
                    }
                }
            }
        }
    }

    // 6. Alumnos con préstamos

    public void mostrarAlumnosConPrestamos() {
        System.out.println("Alumnos con libros prestados:\n\n");
        for (Alumno alumno:alumnosConPrestamos) {
            System.out.println("\t"+alumno.getNombre());
            for (Libro libro:alumno.getPrestamos()) {
                System.out.println(libro.toStringPrestado());
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();

    }

    // 7. Modificar datos de un alumno

    public void modificarAlumnos() {
        Alumno alumnoMod=buscarAlumno();
        System.out.println();
        String menu="\tMenú:\n";
        menu+="1. Cambiar nombre\n";
        menu+="2. Cambiar matrícula\n";
        menu+="3. Cambiar correo\n";
        menu+="4. Cambiar teléfono\n";
        menu+="5. Volver\n";
        int opcion;
        do {
            System.out.println(alumnoMod.toString());
            System.out.println(menu);
            opcion=Utilidades.leerNumero("Introduzca la opción deseada: ",1,5);
            switch (opcion) {
                case 1:
                    String nom=Utilidades.leerCadena("Introduzca el nombre correcto: ");
                    alumnoMod.setNombre(nom);
                    break;
                case 2:
                    String matr=Utilidades.leerMatricula("Introduzca la matrícula correcta:");
                    alumnoMod.setMatricula(matr);
                    break;
                case 3:
                    String corr=Utilidades.leerCorreo("Introduzca el correo correcto: ");
                    alumnoMod.setCorreo(corr);
                    break;
                case 4:
                    String tel=Utilidades.leerTel("Introduzca el teléfono correcto: ");
                    alumnoMod.setTelefono(tel);
                    break;
            }
        } while (opcion!=5);
    }

    // 8. Terminar ejecución

    public void terminar() {
        if (Utilidades.leerSiONo("¿Quiere guardar los cambios?")) {
            guardar();
        } else if (!Utilidades.leerSiONo("¿Está seguro de que no quiere guardar?")) {
            guardar();
        } else {
            System.out.println("Los cambios NO se han guardado.");
            System.out.println();
        }
    }

    public void guardar() {
        boolean archivoAbierto=false;
        String fich;
        while (!archivoAbierto) {
            if (Utilidades.leerSiONo("¿Quiere crear una base de datos nueva?")) {
                fich = Utilidades.leerFich("Introduce el nombre del fichero: ");
            } else {
                fich = Utilidades.leerFich("Introduce el path del fichero: ");
            }
            try (PrintWriter out=new PrintWriter(fich)){
                archivoAbierto=true;
                    // Guarda el inventario
                for (Asignatura asignatura : inventario.getAsignaturas()) {
                    out.println(asignatura.getNombre());
                    for (Libro libro : asignatura.getLibros()) {
                        out.println(libro.getTitulo() + ";" + libro.getEjemplares() + ";" + libro.getPrestados());
                    }
                    out.println("---");
                }
                    // Separador
                out.println("------");
                    // Guarda los alumnosConPrestamos
                if (!alumnosConPrestamos.isEmpty()) {
                    for (Alumno alumno : alumnosConPrestamos) {
                        out.println(alumno.getNombre() + ";" + alumno.getMatricula() + ";" + alumno.getCorreo() + ";" + alumno.getTelefono());
                        for (Libro libro : alumno.getPrestamos()) {
                            out.println(libro.getTitulo() + ";" + libro.getFechaPrestamo());
                        }
                        out.println("----");
                    }
                }
            } catch (FileNotFoundException ex) {
                System.out.println("ERROR AL BUSCAR EL ARCHIVO: " + ex.getMessage());
                System.out.println("INTÉNTELO DE NUEVO\n");
            }
        }
    }

    // Cargar de un archivo

    public static InterfazUsuario cargarDeArchivo(String fich) {
        InterfazUsuario interfaz=new InterfazUsuario();
        try (Scanner sc=new Scanner(new FileReader(fich))) {
            String linea;
                // Leer inventario
            while (!(linea=sc.nextLine()).equals("------")) {
                String nom =linea;
                Asignatura asign=new Asignatura(nom);
                while (!(linea=sc.nextLine()).equals("---")) {
                    String[] datos=linea.split(";");
                    String tit=datos[0];
                    int ejempl=Integer.parseInt(datos[1]);
                    int prest=Integer.parseInt(datos[2]);
                    Libro libr=new Libro(tit,ejempl,prest);
                    asign.anadirLibro(libr);
                }
                interfaz.inventario.anadirAsignatura(asign);
            }
                // Leer alumnosConPrestamos
            while (sc.hasNextLine()) {
                linea=sc.nextLine();
                if (linea.isEmpty() || linea.equals("----")) continue;
                String[] datos=linea.split(";");
                String nom=datos[0];
                String matr=datos[1];
                String corr=datos[2];
                String tel=datos[3];
                Alumno alum=new Alumno(nom,matr,corr,tel);
                while (!(linea=sc.nextLine()).equals("----")) {
                    String[] datLib=linea.split(";");
                    String tit=datLib[0];
                    String fech=datLib[1];
                    Libro libr=new Libro(tit,fech);
                    alum.prestar(libr);
                }
                interfaz.alumnosConPrestamos.add(alum);
            }
        } catch (IOException ex) {
            System.out.println("ERROR AL LEER EL FICHERO: "+ex.getMessage());
        }
        return interfaz;
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
