package br.com.softexpert.desafio.exception;

public class FriendNotFoundException  extends RuntimeException {
   
	private static final long serialVersionUID = -8115342856937866357L;

	public FriendNotFoundException() {
        super();
    }

    public FriendNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FriendNotFoundException(String message) {
        super(message);
    }

    public FriendNotFoundException(Throwable cause) {
        super(cause);
    }

}
