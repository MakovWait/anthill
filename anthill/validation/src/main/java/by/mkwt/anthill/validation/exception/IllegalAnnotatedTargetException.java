package by.mkwt.anthill.validation.exception;

public class IllegalAnnotatedTargetException extends RuntimeException{

	public IllegalAnnotatedTargetException() {
		super();
	}

	public IllegalAnnotatedTargetException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IllegalAnnotatedTargetException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalAnnotatedTargetException(String message) {
		super(message);
	}

	public IllegalAnnotatedTargetException(Throwable cause) {
		super(cause);
	}
		
}
