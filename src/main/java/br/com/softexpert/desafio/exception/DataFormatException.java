package br.com.softexpert.desafio.exception;

public final class DataFormatException extends RuntimeException {
  
	private static final long serialVersionUID = -8046482924127863134L;

	public DataFormatException() {
        super();
    }

    public DataFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataFormatException(String message) {
        super(message);
    }

    public DataFormatException(Throwable cause) {
        super(cause);
    }
}