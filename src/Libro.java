public class Libro implements Comparable<Libro> {

    // Atributos:

    private String titulo;
    private int ejemplares;
    private int prestados;
    private String fechaPrestamo;

    // Constructores:

        // Libro de Asignatura

    public Libro(String titulo, int ejemplares) {
        this.titulo = titulo;
        this.ejemplares = ejemplares;
        prestados = 0;
    }

        // Usado para cargar desde archivo

    public Libro(String titulo, int ejemplares, int prestados) {
        this.titulo = titulo;
        this.ejemplares = ejemplares;
        this.prestados = prestados;
    }

        // Usado para los préstamos

    public Libro(String titulo, String fechaPrestamo) {
        this.titulo = titulo;
        this.fechaPrestamo = fechaPrestamo;
    }

    // Getters:

    public String getTitulo() {
        return titulo;
    }

    public int getEjemplares() {
        return ejemplares;
    }

    public int getPrestados() {
        return prestados;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    // Setters:

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // toString

    @Override
    public String toString() {
        return "\t\t" + titulo +
                "\nEjemplares totales: " + ejemplares +
                "\t\tEjemplares prestados: " + prestados +
                "\t\tEjemplares disponibles: " + (ejemplares - prestados) + "\n";
    }

    public String toStringPrestado() {
        return "\t\t" + titulo + "\t\tFecha de préstamo: " + fechaPrestamo;
    }

    // compareTo

    @Override
    public int compareTo(Libro otroLibro) {
        return this.titulo.compareToIgnoreCase(otroLibro.titulo);
    }

    // equals

    public boolean equals(Libro libro) {
        return libro.getTitulo().equalsIgnoreCase(titulo);
    }

    // Métodos:

    public void anadirEjemplares(int n) {
        ejemplares += n;
        System.out.println("Ahora hay en total " + ejemplares + " ejemplares del libro " + titulo);
        System.out.println();
    }

    public void eliminarEjemplares(int n) {
        if (n > (ejemplares - prestados)) {
            System.out.println("El número introducido es mayor que el número de ejemplares disponibles en la biblioteca actualmente.");
            if (Utilidades.leerSiONo("Si continúa, se pondrá el número de ejemplares al número de ejemplares prestados(" + prestados + "), ¿está seguro?")) {
                ejemplares = prestados;
                System.out.println("Ahora hay en total " + ejemplares + " ejemplares del libro " + titulo);
            }
        } else {
            ejemplares -= n;
            System.out.println("Ahora hay en total " + ejemplares + " ejemplares del libro " + titulo);
            System.out.println();
        }
    }

    public Libro prestarLibro(String fecha) {
        prestados++;
        return new Libro(this.titulo, fecha);
    }

    public boolean devolverLibro() {
        boolean resp = false;
        if (prestados > 0) {
            prestados--;
            resp = true;
        } else {
            System.out.println("No hay ningún ejemplar prestado de este libro.");
            System.out.println();
        }
        return resp;
    }

}
