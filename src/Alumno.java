import java.util.ArrayList;

public class Alumno {

    // Atributos:

    private String nombre, matricula, correo, telefono;
    private ArrayList<Libro> prestamos;

    // Constructor:

    public Alumno (String nombre, String matricula, String correo, String telefono) {
        this.nombre=nombre;
        this.matricula=matricula;
        this.correo=correo;
        this.telefono=telefono;
        prestamos=new ArrayList<>();
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

    // Métodos:

    public static Alumno crearAlumno() {
        String nom=Utilidades.leerCadena("Introduzca el nombre: ");
        String matr=Utilidades.leerMatricula("Introduzca la matricula: ");
        String correo=Utilidades.leerCadena("Introduzca el correo institucional (sin @alumnos.upm.es): ");
        String telef=Utilidades.leerTel("Introduzca el teléfono: ");
        return new Alumno(nom,matr,correo,telef);
    }

    public void prestar(Libro libro) {
        prestamos.add(libro);
    }

    public boolean devolver(Libro libro) {
        return (prestamos.remove(libro));
    }
}
