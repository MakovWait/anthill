package by.mkwt.anthill.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import by.mkwt.anthill.validation.annotation.Positive;
import by.mkwt.anthill.validation.annotation.NotEmptyString;
import by.mkwt.anthill.validation.annotation.NotNull;
import by.mkwt.anthill.validation.annotation.Size;
import by.mkwt.anthill.validation.exception.ValidationException;
import by.mkwt.anthill.validation.validator.PositiveValidator;
import by.mkwt.anthill.validation.validator.NotEmptyStringValidator;
import by.mkwt.anthill.validation.validator.NotNullValidator;
import by.mkwt.anthill.validation.validator.SizeValidator;
import by.mkwt.anthill.validation.validator.Validator;

@Component
public class ValidationHandler {

	private Map<Class<? extends Annotation>, Validator> validators = new HashMap<>();

	public ValidationHandler() {
		validators.put(NotNull.class, new NotNullValidator());
		validators.put(NotEmptyString.class, new NotEmptyStringValidator());
		validators.put(Size.class, new SizeValidator());
		validators.put(Positive.class, new PositiveValidator());
	}
	
	public void validate(Object object) throws ValidationException {
		for (Field field : object.getClass().getDeclaredFields()) {
			for (Annotation annotation : field.getAnnotations()) {
				try {
					field.setAccessible(true);
					if (validators.containsKey(annotation.annotationType())) {
						validators.get(annotation.annotationType()).handle(annotation, object, field);
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} finally {
					field.setAccessible(false);
				}
			}
		}
	}
}
