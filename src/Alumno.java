import java.util.ArrayList;
import java.util.Collections;

public class Alumno implements Comparable<Alumno> {

    // Atributos:

    private String nombre, matricula, correo, telefono;
    private final ArrayList<Libro> prestamos;

    // Constructor:

    public Alumno(String nombre, String matricula, String correo, String telefono) {
        this.nombre = nombre;
        this.matricula = matricula;
        this.correo = correo;
        this.telefono = telefono;
        prestamos = new ArrayList<>();
    }

    // Getters:

    public String getNombre() {
        return nombre;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public ArrayList<Libro> getPrestamos() {
        return prestamos;
    }

    // Setters:

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // compareTo

    @Override
    public int compareTo(Alumno otroAlumno) {
        return this.nombre.compareToIgnoreCase(otroAlumno.nombre);
    }

    // equals

    public boolean equals(Alumno otroAlumno) {
        return this.matricula.equals(otroAlumno.matricula);
    }

    // toString() {

    @Override
    public String toString() {
        String resp = "\t" + nombre + "\n";
        resp += "Matrícula: " + matricula + "\n";
        resp += "Correo: " + correo + "\n";
        resp += "Teléfono: " + telefono + "\n";
        return resp;
    }

    // Métodos:

    public static Alumno crearAlumno() {
        String nom = Utilidades.leerCadena("Introduzca el nombre: ");
        String matr = Utilidades.leerMatricula("Introduzca la matricula: ");
        String correo = Utilidades.leerCorreo("Introduzca el correo institucional: ");
        String telef = Utilidades.leerTel("Introduzca el teléfono: ");
        return new Alumno(nom, matr, correo, telef);
    }

    public void prestar(Libro libro) {
        prestamos.add(libro);
        Collections.sort(prestamos);
    }

    public boolean devolver(Libro libro) {
        return (prestamos.remove(libro));
    }
}
