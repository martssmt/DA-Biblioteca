package controller;

import model.Libro;

import java.util.HashMap;
import java.util.Map;

public class BookController {

    private Map<String, Libro> library;

    public BookController() {
        library = new HashMap<String, Libro>();
    }

    public Map<String, Libro> getLibrary() {
        return library;
    }

    public Libro getBook(String isbn){
        return library.get(isbn);
    }

}
