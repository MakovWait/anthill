package by.mkwt.anthill.validation.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import by.mkwt.anthill.validation.annotation.Size;
import by.mkwt.anthill.validation.exception.IllegalAnnotationException;
import by.mkwt.anthill.validation.exception.IllegalAnnotatedTargetException;
import by.mkwt.anthill.validation.exception.ValidationException;

public class SizeValidator implements Validator {

	@Override
	public void handle(Annotation annotation, Object object, Field field)
			throws ValidationException, IllegalArgumentException, IllegalAccessException {
		Object value = field.get(object);

		if (value != null) {
			if (!(annotation instanceof Size)) {
				throw new IllegalAnnotationException(SizeValidator.class + " can handle only " + Size.class + "(but "
						+ annotation.annotationType() + " income)");
			}

			if (!(value instanceof String)) {
				throw new IllegalAnnotatedTargetException("Size annotation is require String type field ("
						+ object.getClass() + ":" + field.getName() + ")");
			}

			if (!(((String) value).length() <= ((Size) annotation).value())) {
				throw new ValidationException(
						field.getName() + " has illegal length. (>" + ((Size) annotation).value() + ")");
			}
		}
	}

}
