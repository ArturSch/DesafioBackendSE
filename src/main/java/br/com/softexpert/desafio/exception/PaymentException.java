package br.com.softexpert.desafio.exception;

public class PaymentException extends RuntimeException {
	   
		private static final long serialVersionUID = -8115342856937866357L;

		public PaymentException() {
	        super();
	    }

	    public PaymentException(String message, Throwable cause) {
	        super(message, cause);
	    }

	    public PaymentException(String message) {
	        super(message);
	    }

	    public PaymentException(Throwable cause) {
	        super(cause);
	    }

	}
