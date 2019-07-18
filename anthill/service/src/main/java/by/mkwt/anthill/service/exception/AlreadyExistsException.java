package by.mkwt.anthill.service.exception;

public class AlreadyExistsException extends Exception {

	public AlreadyExistsException() {
		super("Entity already exists");
	}

}
