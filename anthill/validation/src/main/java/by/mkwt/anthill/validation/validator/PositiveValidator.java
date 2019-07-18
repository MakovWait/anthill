package by.mkwt.anthill.validation.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;

import by.mkwt.anthill.validation.annotation.Size;
import by.mkwt.anthill.validation.exception.IllegalAnnotatedTargetException;
import by.mkwt.anthill.validation.exception.IllegalAnnotationException;
import by.mkwt.anthill.validation.exception.ValidationException;

public class PositiveValidator implements Validator {

	@Override
	public void handle(Annotation annotation, Object object, Field field)
			throws ValidationException, IllegalArgumentException, IllegalAccessException {

		Object value = field.get(object);
		
		if (!(value instanceof Number)) {
			throw new IllegalAnnotatedTargetException(
					"Size annotation is require Number type field (" + object.getClass() + ":" + field.getName() + ")");
		}
		
		if (Math.signum(((Number)value).doubleValue()) == -1) {
			throw new ValidationException(
					field.getName() + " must be natural. (>=0)");
		}
	}

}
