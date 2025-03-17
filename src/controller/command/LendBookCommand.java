package controller.command;

import model.Alumno;

import java.awt.print.Book;

public class LendBookCommand implements Command {

    private final Alumno borrowerStudent; // Cacho spanglish
    private final Book book;

    public LendBookCommand(Alumno borrowerStudent, Book book) {
        this.borrowerStudent = borrowerStudent;
        this.book = book;
    }

    public Alumno getBorrowerStudent() {
        return borrowerStudent;
    }

    public Book getBook() {
        return book;
    }

    @Override
    public void execute() {

    }
}
