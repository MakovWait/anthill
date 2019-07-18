package by.mkwt.anthill.validation.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import by.mkwt.anthill.validation.exception.ValidationException;

public class NotNullValidator implements Validator {

	@Override
	public void handle(Annotation annotation, Object object, Field field) throws ValidationException, IllegalArgumentException, IllegalAccessException {
		if (field.get(object) == null) {
			throw new ValidationException(field.getName() + " must be not null");
		}
	}

}
