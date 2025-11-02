package app.services.exceptions;

public class DuplicateException extends RuntimeException {

    private static final String DESCRIPTION = "ID duplicado. Debiera ser único";

    public DuplicateException(String detail) {
        super(DESCRIPTION + ": " + detail);
    }

}
