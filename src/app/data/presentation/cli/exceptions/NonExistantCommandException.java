package app.data.presentation.cli.exceptions;

public class NonExistantCommandException extends RuntimeException {

    private static final String DESCRIPTION = "Comando no existe";

    public NonExistantCommandException(String detail) {
        super(DESCRIPTION + ": " + detail);
    }

}
