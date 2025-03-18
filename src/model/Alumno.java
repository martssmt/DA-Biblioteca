package model;

import view.CLI;

import java.util.ArrayList;
import java.util.Collections;

public class Alumno {

    private final String matricula;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;

    public Alumno(String nombre, String apellido, String matricula, String correo, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
        this.correo = correo;
        this.telefono = telefono;
    }

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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean equals(Alumno otroAlumno) {
        return this.matricula.equals(otroAlumno.matricula);
    }

    @Override
    public String toString() {
        String resp = "\t" + nombre + "\n";
        resp += "Matrícula: " + matricula + "\n";
        resp += "Correo: " + correo + "\n";
        resp += "Teléfono: " + telefono + "\n";
        return resp;
    }

    public static Alumno crearAlumno() {
        String nom = CLI.leerCadena("Introduzca el nombre: ");
        String apellido = CLI.leerCadena("Introduzca el apellido: ");
        String matr = CLI.leerMatricula("Introduzca la matricula: ");
        String correo = CLI.leerCorreo("Introduzca el correo institucional: ");
        String telef = CLI.leerTel("Introduzca el teléfono: ");
        return new Alumno(nom, apellido, matr, correo, telef);
    }

}
