package ar.edu.unq.tpi.persistencia.exception;


public class UserException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserException(final String str) {
        super(str);
    }

    public UserException(final String string, final Exception e) {
        super(string, e);
    }

}
