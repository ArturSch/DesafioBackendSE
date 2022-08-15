package br.com.softexpert.desafio.exception;

public class InvoiceNotFoundException extends RuntimeException {
	   
		private static final long serialVersionUID = -8115342856937866357L;

		public InvoiceNotFoundException() {
	        super();
	    }

	    public InvoiceNotFoundException(String message, Throwable cause) {
	        super(message, cause);
	    }

	    public InvoiceNotFoundException(String message) {
	        super(message);
	    }

	    public InvoiceNotFoundException(Throwable cause) {
	        super(cause);
	    }

	}

