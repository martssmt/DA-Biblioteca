public class Libro extends Asignatura {

    // Atributos:

    private String titulo;
    private int ejemplares;
    private int prestados;
    private String fechaPrestamo;

    // Constructores:

    public Libro(String titulo, int ejemplares) {
        this.titulo=titulo;
        this.ejemplares=ejemplares;
        prestados=0;
    }

    public Libro(String titulo, String fechaPrestamo) {
        this.titulo=titulo;
        this.fechaPrestamo=fechaPrestamo;
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
        return "\t"+titulo+
                "\nEjemplares totales: "+ejemplares+
                "\t\tEjemplares prestados: "+prestados+
                "\t\tEjemplares disponibles: "+(ejemplares-prestados)+"\n";
    }

    public String toStringPrestado() {
        return titulo+"\t\tFecha de préstamo: "+fechaPrestamo+"\n";
    }

    // Métodos:

    public void anadirEjemplares(int n) {
        ejemplares+=n;
        System.out.println("Ahora hay en total "+ejemplares+" ejemplares.");
        System.out.println();
    }

    public void eliminarEjemplares(int n) {
        ejemplares-=n;
        System.out.println("Ahora hay en total "+ejemplares+" ejemplares.");
        System.out.println();
    }

    public Libro prestarLibro (String fecha) {
        prestados++;
        return new Libro(this.titulo, fecha);
    }

    public boolean devolverLibro() {
        boolean resp=false;
        if (prestados>0) {
            prestados--;
            resp=true;
        } else {
            System.out.println("No hay ningún ejemplar prestado de este libro.");
        }
        return resp;
    }

}
