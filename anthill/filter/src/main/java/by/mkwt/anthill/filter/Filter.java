package by.mkwt.anthill.filter;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import by.mkwt.anthill.filter.FilterUnit.OperationType;
import by.mkwt.anthill.filter.annotation.FilterField;
import by.mkwt.anthill.filter.annotation.FilterSchema;
import by.mkwt.anthill.filter.exception.FilterException;

public class Filter<T> {

	public CriteriaQuery<T> fillCretiriaQueryWithFilterParams(Map<String, FilterBatch> filterBatches,
			CriteriaBuilder cb, CriteriaQuery<T> q, Root<T> root, Class<T> rootClass) throws FilterException {

		List<Predicate> conditions = new LinkedList<Predicate>();
		fillConditions(cb, root, conditions, filterBatches, rootClass);

		q.select(root);

		q.where(conditions.toArray(new Predicate[] {}));
		q.distinct(true);

		return q;
	}

	public void fillConditions(CriteriaBuilder cb, Root<T> root, List<Predicate> conditions,
			Map<String, FilterBatch> filterBatches, Class<T> rootClass) throws FilterException {

		for (Field field : rootClass.getDeclaredFields()) {
			for (FilterSchema filterSchema : field.getAnnotationsByType(FilterSchema.class)) {
				for (FilterField filterField : filterSchema.filterFields()) {

					if (filterBatches.containsKey(filterField.mappedBy())) {

						FilterBatch batch = filterBatches.get(filterField.mappedBy());

						List<Predicate> batchConditions = new LinkedList<>();
						for (FilterUnit unit : batch.getFilterUnits()) {

							Path<T> path;
							if (filterField.joinName().equals("")) {
								path = root;
							} else {
								path = (Path<T>) join(root, filterField.joinName());
							}

							batchConditions.add(getSupportedPredicate(unit.getOperationType(), cb, path,
									filterField.attrName(), castValueTo(unit.getValue(), filterField.type())));
						}

						if (batch.isAndTypeUnits()) {
							conditions.add(cb.and(batchConditions.toArray(new Predicate[] {})));
						} else {
							conditions.add(cb.or(batchConditions.toArray(new Predicate[] {})));
						}

					}
				}
			}
		}

	}

	public <Y extends Comparable<? super Y>> Predicate getSupportedPredicate(OperationType type, CriteriaBuilder cb,
			Path<T> path, String pathAttrName, Y value) throws FilterException {
		switch (type) {
		case eq:
			return getEqualPredicate(cb, path, pathAttrName, value);
		case lt:
			return getLessThanOrEqualsPredicate(cb, path, pathAttrName, value);
		case gt:
			return getGreaterThanOrEqualsPredicate(cb, path, pathAttrName, value);
		default:
			throw new FilterException("Operation type[" + type + "] not supported");
		}
	}

	public <Y extends Comparable<? super Y>> Predicate getGreaterThanOrEqualsPredicate(CriteriaBuilder cb, Path<T> path,
			String pathAttrName, Y value) {
		return cb.greaterThanOrEqualTo(path.get(pathAttrName), value);
	}

	public <Y extends Comparable<? super Y>> Predicate getLessThanOrEqualsPredicate(CriteriaBuilder cb, Path<T> path,
			String pathAttrName, Y value) {
		return cb.lessThanOrEqualTo(path.get(pathAttrName), value);
	}

	public <Y extends Comparable<? super Y>> Predicate getEqualPredicate(CriteriaBuilder cb, Path<T> join,
			String pathAttrName, Y value) {
		return cb.equal(join.get(pathAttrName), value);
	}

	public Join<T, ?> join(Root<T> root, String joinFieldName) {
		return root.join(joinFieldName);
	}

	public Predicate getEqualPredicate(CriteriaBuilder cb, Path<T> path, String pathAttrName, Object value) {
		return cb.equal(path.get(pathAttrName), value);
	}

	public <Y extends Comparable<?>> Y castValueTo(String value, Class<?> classType) throws FilterException {
		if (classType.equals(String.class)) {
			return (Y) value;
		} else if (classType.equals(Boolean.class)) {
			return (Y) Boolean.valueOf(value);
		} else if (classType.equals(LocalDateTime.class)) {
			return (Y) LocalDateTime.parse(value);
		} else if (classType.equals(BigDecimal.class)) {
			return (Y) BigDecimal.valueOf(Double.parseDouble(value));
		} else if (classType.equals(Integer.class)) {
			return (Y) BigDecimal.valueOf(Integer.parseInt(value));
		}
		throw new FilterException("Not supported cast type");
	}

}
