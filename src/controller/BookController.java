package controller;

import model.Libro;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BookController {

    private Libro book;

    public BookController() {

    }

    public Libro getBook() {
        return book;
    }

    public void setBook(Libro book) {
        this.book = book;
    }

}
