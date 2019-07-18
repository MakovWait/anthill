package by.mkwt.anthill.validation.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import by.mkwt.anthill.validation.exception.IllegalAnnotatedTargetException;
import by.mkwt.anthill.validation.exception.ValidationException;

public class NotEmptyStringValidator implements Validator {

	@Override
	public void handle(Annotation annotation, Object object, Field field)
			throws ValidationException, IllegalArgumentException, IllegalAccessException {
		Object value = field.get(object);

		if (!(value instanceof String)) {
			throw new IllegalAnnotatedTargetException("NotEmptyString annotation is require String type field ("
					+ object.getClass() + ":" + field.getName() + ")");
		}

		if (((String) value).trim().equals("")) {
			throw new ValidationException(field.getName() + " must be not empty");
		}
		
	}

}
