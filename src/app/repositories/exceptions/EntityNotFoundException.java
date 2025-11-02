package app.repositories.exceptions;

public class EntityNotFoundException extends RuntimeException {

    private static final String DESCRIPTION = "Entity Not Found Exception. No se ha encontrado entidad con id";

    public EntityNotFoundException(Integer id) {
        super(DESCRIPTION + ": " + id);
    }

}
