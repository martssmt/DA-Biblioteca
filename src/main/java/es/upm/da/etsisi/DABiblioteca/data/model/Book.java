package es.upm.da.etsisi.DABiblioteca.data.model;

public class Book extends Entity {

    private String title;
    private Subject subject;
    private Integer copies;
    private Integer availableCopies;

    public Book(String title, Subject subject, Integer copies) {
        super(-1);
        this.title = title;
        this.subject = subject;
        setCopies(copies);
        this.availableCopies = copies;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        if (copies < 0) {
            throw new IllegalArgumentException("It is impossible to have negative copies.");
        }
        this.copies = copies;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        if (availableCopies >= copies) {
            throw new IllegalArgumentException("It is impossible to have more available copies than copies.");
        }
        this.availableCopies = availableCopies;
    }

    public void lendCopy() {
        if (availableCopies <= 0) {
            throw new IllegalStateException("There are no available copies.");
        }
        availableCopies--;
    }

    public void returnCopy() {
        if (availableCopies >= copies) {
            throw new IllegalStateException("There are no copies lent.");
        }
        availableCopies++;
    }
}
