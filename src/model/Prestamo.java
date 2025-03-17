package model;

public class Prestamo {

    private int idPrestamo;
    private Alumno alumnoPrestatario;
    private Libro libroPrestado;

    public Prestamo(int idPrestamo, Alumno alumnoPrestatario, Libro libroPrestado) {
        this.idPrestamo = idPrestamo;
        this.alumnoPrestatario = alumnoPrestatario;
        this.libroPrestado = libroPrestado;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    // No hay setter de idPréstamo porque es una mala práctica cambiar así el ID

    public Alumno getAlumnoPrestatario() {
        return alumnoPrestatario;
    }


    public Libro getLibroPrestado() {
        return libroPrestado;
    }

}
