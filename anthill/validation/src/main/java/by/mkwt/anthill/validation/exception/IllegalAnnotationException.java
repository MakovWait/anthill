package by.mkwt.anthill.validation.exception;

public class IllegalAnnotationException extends RuntimeException {

	public IllegalAnnotationException() {
		super();
	}

	public IllegalAnnotationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IllegalAnnotationException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalAnnotationException(String message) {
		super(message);
	}

	public IllegalAnnotationException(Throwable cause) {
		super(cause);
	}
	
}
