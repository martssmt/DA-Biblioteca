package controller.command;

import model.Biblioteca;
import model.Libro;

public class ShowInventoryCommand implements Command{

    private final Biblioteca<Libro> biblioteca; // Son "final" porque no van a modificarse en la misma petición

    public ShowInventoryCommand(Biblioteca<Libro> biblioteca) {
        this.biblioteca = biblioteca;
    }

    public Biblioteca<Libro> getBiblioteca() {
        return biblioteca;
    }

    @Override
    public void execute() {

    }
}
