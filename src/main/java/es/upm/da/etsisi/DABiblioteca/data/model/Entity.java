package es.upm.da.etsisi.DABiblioteca.data.model;

public abstract class Entity {

    private Integer id;

    public Entity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
