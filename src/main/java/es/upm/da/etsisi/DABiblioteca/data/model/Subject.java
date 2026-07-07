package es.upm.da.etsisi.DABiblioteca.data.model;

public class Subject extends Entity {

    private Degree degree;
    private String name;

    public Subject(String name, Degree degree) {
        super(-1);
        this.name = name;
        this.degree = degree;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
