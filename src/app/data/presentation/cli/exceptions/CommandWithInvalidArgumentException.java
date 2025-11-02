package app.data.presentation.cli.exceptions;

public class CommandWithInvalidArgumentException extends RuntimeException {

    private static final String DESCRIPTION = "Comando con argumentos incorrectos";

    public CommandWithInvalidArgumentException(String detail) {
        super(DESCRIPTION + ". Formato correcto: " + detail);
    }

}
