package model;

import view.CLI;

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
    private Biblioteca biblioteca;

    // Constructor:

    public InterfazUsuario() {
        alumnosConPrestamos = new LinkedList<>();
        biblioteca = new Biblioteca();
    }

    // Menú

    public void menu() {
        int opcion;
        String menu = "\n\n\tMenú:\n";
        menu += "1. Mostrar inventario\n";
        menu += "2. Modificar inventario\n";
        menu += "3. Consultar libros del inventario\n";
        menu += "4. Prestar libro\n";
        menu += "5. Devolver libro\n";
        menu += "6. Alumnos con préstamos\n";
        menu += "7. Ver datos de un alumno\n";
        menu += "8. Modificar datos de un alumno\n";
        menu += "9. Terminar ejecución\n\n";
        do {
            System.out.print(menu);
            opcion = CLI.leerNumero("Introduzca la opción deseada: ", 1, 9);
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
                case 7: // Ver datos de un alumno
                    verAlumno();
                    break;
                case 8: // Modificar datos de un alumno
                    modificarAlumnos();
                    break;
                case 9: // Terminar ejecución
                    terminar();
                    break;
            }
        } while (opcion != 9);
        System.out.println();
        System.out.println("Fin de la ejecución.");
        System.out.println("\u00A9 2025 Delegación de Alumnos de ETSI Sistemas Informáticos (UPM)");
    }

    // 1. Mostrar inventario

    public void mostrarInventario() {
        if (CLI.leerSiONo("¿Desea ver todo?")) {
            System.out.println(biblioteca.toString());
        } else {
            System.out.println();
            AsignaturaDeprecated asigAVer = biblioteca.buscarAsignatura();
            if (asigAVer != null) {
                System.out.println();
                System.out.println(asigAVer);
            }
        }
    }

    // 2. Modificar inventario

    public void modificarInventario() {
        int opcion;
        String menu = "\tMenú:\n";
        menu += "1. Añadir libro nuevo\n";
        menu += "2. Eliminar libro\n";
        menu += "3. Cambiar nombre a un libro\n";
        menu += "4. Añadir ejemplares\n";
        menu += "5. Eliminar ejemplares\n";
        menu += "6. Volver\n";
        do {
            System.out.println(menu);
            opcion = CLI.leerNumero("Introduzca la opción deseada: ", 1, 6);
            System.out.println();
            Libro modificado;
            switch (opcion) {
                case 1: // Añadir libro nuevo
                    AsignaturaDeprecated asignatura;
                    if (CLI.leerSiONo("¿Quieres añadir también una asignatura?")) {
                        System.out.println();
                        asignatura = biblioteca.crearAsignatura();
                    } else {
                        System.out.println();
                        asignatura = biblioteca.buscarAsignatura();
                    }
                    if (asignatura != null) {
                        System.out.println();
                         if (asignatura.crearLibro()) {
                            System.out.println();
                            System.out.println("Model.Libro añadido con éxito");
                        }
                        System.out.println();
                    }
                    break;
                case 2: // Eliminar libro
                    modificado = busqueda();
                    if (modificado != null && modificado.getPrestados()==0) {
                        for (AsignaturaDeprecated asignaturaEl : biblioteca.getAsignaturas()) {
                            if (asignaturaEl.getLibros().remove(modificado)) {
                                System.out.println();
                                System.out.println("Model.Libro eliminado con éxito");
                                System.out.println();
                                if (asignaturaEl.getLibros().isEmpty()) {
                                    biblioteca.getAsignaturas().remove(asignaturaEl);
                                    System.out.println("No hay más libros en la asignatura, por lo que se ha eliminado del inventario.");
                                }
                                break;
                            }
                        }
                        if (modificado.getPrestados()!=0) {
                            System.out.println("\nEl libro seleccionado tiene ejemplares prestados, por lo que no se puede eliminar.\n");
                        }
                    }
                    break;
                case 3: // Cambiar nombre a un libro
                    modificado = busqueda();
                    if (modificado!=null) {
                        String nuevoNom = CLI.leerCadena("Introduzca el título correcto: ");
                        modificado.setTitulo(nuevoNom);
                    }
                    break;
                case 4: // Añadir ejemplares
                    modificado = busqueda();
                    if (modificado!=null) {
                        System.out.println("\nActualmente hay " + modificado.getEjemplares() + " ejemplares de " + modificado.getTitulo() + ", de los cuales " + modificado.getPrestados() + " están prestados.");
                        System.out.println();
                        int numAnadir = CLI.leerNumPositivo("Introduce el número de ejemplares a añadir: ");
                        modificado.anadirEjemplares(numAnadir);
                    }
                    break;
                case 5: // Eliminar ejemplares
                    modificado = busqueda();
                    if (modificado!=null) {
                        System.out.println("\nActualmente hay " + modificado.getEjemplares() + " ejemplares de " + modificado.getTitulo() + ", de los cuales " + modificado.getPrestados() + " están prestados.");
                        System.out.println();
                        int numElim = CLI.leerNumPositivo("Introduce el número de ejemplares a eliminar: ");
                        System.out.println();
                        modificado.eliminarEjemplares(numElim);
                        if (modificado.getEjemplares() == 0) {
                            for (AsignaturaDeprecated asign : biblioteca.getAsignaturas()) {
                                if (asign.getLibros().remove(modificado)) {
                                    System.out.println();
                                    System.out.println("Como no quedan ejemplares, el libro se ha eliminado.");
                                    if (asign.getLibros().isEmpty()) {
                                        biblioteca.getAsignaturas().remove(asign);
                                        System.out.println("Como tampoco quedan libros en la asignatura, también se eliminará del inventario");
                                    }
                                    System.out.println();
                                    break;
                                }
                            }
                        }
                    }
                    break;
            }
        } while (opcion != 6);
    }

    // 3. Consultar libros del inventario

    public void consultarInventario() {
        Libro consulta = biblioteca.buscarLibro();
        if (consulta!=null) {
            System.out.println(consulta.toString());
            System.out.println();
            if (consulta.getPrestados() > 0) {
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
            String menu = "\n\tMenú:\n";
            menu += "1. Añadir ejemplares\n";
            menu += "2. Eliminar ejemplares\n";
            menu += "3. Cambiar nombre\n";
            menu += "4. Mostrar datos completos\n";
            menu += "5. Volver\n\n";
            int opcion;
            do {
                System.out.println(menu);
                opcion = CLI.leerNumero("Introduzca la opción deseada: ", 1, 5);
                System.out.println();
                switch (opcion) {
                    case 1: // Añadir ejemplares
                        System.out.println("Actualmente hay " + consulta.getEjemplares() + " ejemplares de " + consulta.getTitulo() + ", de los cuales " + consulta.getPrestados() + " están prestados.");
                        System.out.println();
                        int numAnadir = CLI.leerNumPositivo("Introduce el número de ejemplares a añadir: ");
                        consulta.anadirEjemplares(numAnadir);
                        break;
                    case 2: // Eliminar ejemplares
                        System.out.println("Actualmente hay " + consulta.getEjemplares() + " ejemplares de " + consulta.getTitulo() + ", de los cuales " + consulta.getPrestados() + " están prestados.");
                        System.out.println();
                        int numElim = CLI.leerNumPositivo("Introduce el número de ejemplares a eliminar: ");
                        consulta.eliminarEjemplares(numElim);
                        break;
                    case 3: // Cambiar nombre
                        boolean nomDispo = true;
                        do {
                            nomDispo = true;
                            String nuevoNom = CLI.leerCadena("Introduzca el título correcto: ");
                            boolean encontrado = false;
                            for (AsignaturaDeprecated asign : biblioteca.getAsignaturas()) {
                                for (Libro lib : asign.getLibros()) {
                                    if (lib != consulta && lib.getTitulo().equalsIgnoreCase(nuevoNom)) {
                                        System.out.println("\nYa existe un libro con ese nombre en esta asignatura. Introduzca otro nombre.\n");
                                        nomDispo = false;
                                        encontrado = true;
                                        break;
                                    }
                                }
                                if (encontrado) break;
                            }
                            if (nomDispo) {
                                consulta.setTitulo(nuevoNom);
                                System.out.println("El título se ha actualizado correctamente.\n");
                            }
                        } while (!nomDispo);
                        break;
                    case 4: // Mostrar datos de nuevo
                        System.out.println(consulta);
                        System.out.println();
                        if (consulta.getPrestados() > 0) {
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
                        System.out.println();
                        break;
                }
            } while (opcion != 5);
        }
    }

    // 4. Prestar libro

    public void prestar() {
        String fecha = CLI.leerFecha("Introduzca la fecha del día del préstamo (dd/mm/aaaa): ");
        Alumno alumno;
        boolean interrumpido = false;
        boolean alumNuevo = true;
        if (CLI.leerSiONo("¿El alumno tiene algún libro en su posesión?")) {
            alumno = buscarAlumno();
            if (alumno == null) {
                System.out.println("Se ha interrumpido el préstamo.");
                interrumpido = true;
            }
            alumNuevo = false;
        } else {
            alumno = Alumno.crearAlumno();
            System.out.println();
            for (Alumno alumnoConPrestamo : alumnosConPrestamos) {
                if (alumno.equals(alumnoConPrestamo)) {
                    System.out.println("El alumno ya está en el registro. Se le añadirá un libro más.");
                    System.out.println();
                    alumNuevo = false;
                    alumno = alumnoConPrestamo;
                }
            }
        }
        if (!interrumpido) {
            Libro libroAPrestar = busqueda();
            if (libroAPrestar != null) {
                Libro libroAIntroducirEnAlumno = libroAPrestar.prestarLibro(fecha);
                if (alumNuevo) {
                    alumnosConPrestamos.add(alumno);
                    Collections.sort(alumnosConPrestamos);
                }
                alumno.prestar(libroAIntroducirEnAlumno);
                System.out.println("\nModel.Libro prestado al alumno " + alumno.getNombre() + " con éxito.");
                System.out.println();
            }
        }
    }

    // 5. Devolver libro

    public void devolver() {
        Alumno alumno = buscarAlumno();
        if (alumno != null) {
            System.out.println();
            System.out.println("\t" + alumno.getNombre());
            Libro libroADev = null;
            for (int i = 0; i < alumno.getPrestamos().size(); i++) {
                System.out.println(i + ". " + alumno.getPrestamos().get(i).toStringPrestado());
            }
            System.out.println(alumno.getPrestamos().size() + ". Volver");
            System.out.println();
            int opc = CLI.leerNumero("Introduzca el número del libro a devolver: ", 0, alumno.getPrestamos().size());
            if (opc != alumno.getPrestamos().size()) libroADev = alumno.getPrestamos().get(opc);
            if (libroADev != null) {
                alumno.devolver(libroADev);
                System.out.println("\nEl libro ha sido devuelto con éxito.");
                System.out.println();
                if (alumno.getPrestamos().isEmpty()) {
                    alumnosConPrestamos.remove(alumno);
                    System.out.println("El alumno no tiene más préstamos, por lo que se le ha eliminado del sistema.");
                    System.out.println();
                }
                boolean encontrado = false;
                for (AsignaturaDeprecated asignatura : biblioteca.getAsignaturas()) {
                    for (Libro libro : asignatura.getLibros()) {
                        if (libro.equals(libroADev)) {
                            libro.devolverLibro();
                            encontrado = true;
                            break;
                        }
                    }
                    if (encontrado) break;
                }
            }
        }
    }

    // 6. Alumnos con préstamos

    public void mostrarAlumnosConPrestamos() {
        System.out.println("\t\tAlumnos con libros prestados:\n\n");
        for (Alumno alumno : alumnosConPrestamos) {
            System.out.println("\t" + alumno.getNombre());
            for (Libro libro : alumno.getPrestamos()) {
                System.out.println(libro.toStringPrestado());
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();

    }

    // 7. Ver datos de un alumno

    public void verAlumno() {
        Alumno alumno = buscarAlumno();
        System.out.println();
        if (alumno != null) System.out.println(alumno);
    }

    // 8. Modificar datos de un alumno

    public void modificarAlumnos() {
        Alumno alumnoMod = buscarAlumno();
        System.out.println();
        if (alumnoMod != null) {
            String menu = "\tMenú:\n";
            menu += "1. Cambiar nombre\n";
            menu += "2. Cambiar matrícula\n";
            menu += "3. Cambiar correo\n";
            menu += "4. Cambiar teléfono\n";
            menu += "5. Volver\n";
            int opcion;
            do {
                System.out.println(alumnoMod);
                System.out.println(menu);
                opcion = CLI.leerNumero("Introduzca la opción deseada: ", 1, 5);
                switch (opcion) {
                    case 1:
                        String nom = CLI.leerCadena("Introduzca el nombre correcto: ");
                        alumnoMod.setNombre(nom);
                        break;
                    case 2:
                        String matr = CLI.leerMatricula("Introduzca la matrícula correcta:");
                        alumnoMod.setMatricula(matr);
                        break;
                    case 3:
                        String corr = CLI.leerCorreo("Introduzca el correo correcto: ");
                        alumnoMod.setCorreo(corr);
                        break;
                    case 4:
                        String tel = CLI.leerTel("Introduzca el teléfono correcto: ");
                        alumnoMod.setTelefono(tel);
                        break;
                }
            } while (opcion != 5);
        }
    }

    // 9. Terminar ejecución

    public void terminar() {
        if (CLI.leerSiONo("¿Quiere guardar los cambios?")) {
            guardar();
        } else if (!CLI.leerSiONo("¿Está seguro de que no quiere guardar?")) {
            guardar();
        } else {
            System.out.println("Los cambios NO se han guardado.");
            System.out.println();
        }
    }

    public void guardar() {
        boolean archivoAbierto = false;
        String fich;
        while (!archivoAbierto) {
            if (CLI.leerSiONo("¿Quiere crear una base de datos nueva?")) {
                fich = CLI.leerFich("Introduce el nombre del fichero: ");
            } else {
                fich = CLI.leerFich("Introduce el path del fichero: ");
            }
            try (PrintWriter out = new PrintWriter(fich)) {
                archivoAbierto = true;
                // Guarda el inventario
                for (AsignaturaDeprecated asignatura : biblioteca.getAsignaturas()) {
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
        InterfazUsuario interfaz = new InterfazUsuario();
        try (Scanner sc = new Scanner(new FileReader(fich))) {
            String linea;
            // Leer inventario
            while (!(linea = sc.nextLine()).equals("------")) {
                String nom = linea;
                AsignaturaDeprecated asign = new AsignaturaDeprecated(nom);
                while (!(linea = sc.nextLine()).equals("---")) {
                    String[] datos = linea.split(";");
                    String tit = datos[0];
                    int ejempl = Integer.parseInt(datos[1]);
                    int prest = Integer.parseInt(datos[2]);
                    Libro libr = new Libro(tit, ejempl, prest);
                    asign.anadirLibro(libr);
                }
                interfaz.biblioteca.anadirAsignatura(asign);
            }
            // Leer alumnosConPrestamos
            while (sc.hasNextLine()) {
                linea = sc.nextLine();
                if (linea.isEmpty() || linea.equals("----")) continue;
                String[] datos = linea.split(";");
                String nom = datos[0];
                String matr = datos[1];
                String corr = datos[2];
                String tel = datos[3];
                Alumno alum = new Alumno(nom, matr, corr, tel);
                while (!(linea = sc.nextLine()).equals("----")) {
                    String[] datLib = linea.split(";");
                    String tit = datLib[0];
                    String fech = datLib[1];
                    Libro libr = new Libro(tit, fech);
                    alum.prestar(libr);
                }
                interfaz.alumnosConPrestamos.add(alum);
            }
        } catch (IOException ex) {
            System.out.println("ERROR AL LEER EL FICHERO: " + ex.getMessage());
        }
        return interfaz;
    }

    // Comunes y útiles

    public Alumno buscarAlumno() {
        Alumno resp = null;
        ArrayList<Alumno> coincidencias = new ArrayList<>();
        String nom;
        boolean volver = false;
        do {
            nom = CLI.leerCadena("Introduzca el nombre del alumno ('fin' para volver): ");
            if (!nom.equalsIgnoreCase("fin")) {
                for (Alumno alumno : alumnosConPrestamos) {
                    if (alumno.getNombre().toLowerCase().contains(nom.toLowerCase())) {
                        coincidencias.add(alumno);
                    }
                }
                if (coincidencias.isEmpty())
                    System.out.println("No se ha encontrado ninguna coincidencia. Vuelva a intentarlo.");
            } else volver = true;
        } while (coincidencias.isEmpty() && !volver);
        if (!volver) resp = seleccionarAlumno(coincidencias);
        return resp;
    }

    public Alumno seleccionarAlumno(ArrayList<Alumno> lista) {
        Alumno select = null;
        int resp;
        System.out.println("\n\tResultados:");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(i + ". " + lista.get(i).getNombre());
        }
        System.out.println(lista.size() + ". Volver");
        System.out.println();
        resp = CLI.leerNumero("Seleccione el alumno: ", 0, lista.size());
        if (resp != lista.size()) select = lista.get(resp);
        return select;
    }

    public Libro busqueda() {
        Libro resp=null;
        if (CLI.leerSiONo("¿Quiere buscar el libro por asignaturas?: ")) {
            System.out.println();
            AsignaturaDeprecated asignatura = biblioteca.buscarAsignatura();
            System.out.println();
            if (asignatura!=null) {
                ArrayList<Libro> todos = new ArrayList<>(asignatura.getLibros());
                resp = asignatura.seleccionarLibro(todos);
            }
        } else {
            System.out.println();
            resp = biblioteca.buscarLibro();
        }
        return resp; // Devuelve null si la búsqueda se ha interrumpido
    }

}
