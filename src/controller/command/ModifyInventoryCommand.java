package controller.command;

import model.Biblioteca;
import model.Libro;

public class ModifyInventoryCommand implements Command{

    private final Biblioteca<Libro> biblioteca;

    public ModifyInventoryCommand(Biblioteca<Libro> biblioteca) {
        this.biblioteca = biblioteca;
    }

    public Biblioteca<Libro> getBiblioteca() {
        return biblioteca;
    }

    @Override
    public void execute() {

    }
}
