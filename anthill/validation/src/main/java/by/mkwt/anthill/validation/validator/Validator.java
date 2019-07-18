package by.mkwt.anthill.validation.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import by.mkwt.anthill.validation.exception.ValidationException;

public interface Validator {

	public void handle(Annotation annotation, Object object, Field field) throws ValidationException, IllegalArgumentException, IllegalAccessException;
	
}
