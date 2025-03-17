package model;

import view.CLI;
import java.time.LocalDate;

public class Libro {

    private final String isbn;
    private final String titulo;

    public Libro(String isbn, String titulo) {
        this.isbn = isbn;
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (o instanceof Libro libro) {
            result = (this == libro) || (libro.getIsbn().equals(this.getIsbn()));
        }
        return result;
    }

}
