package by.mkwt.anthill.filter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value= ElementType.FIELD)
@Retention(value= RetentionPolicy.RUNTIME)
public @interface FilterField {
	
	String mappedBy();
	String joinName() default "";
	String attrName();
	Class<?> type() default String.class;
	
}
